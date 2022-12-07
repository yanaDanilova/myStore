package de.danilova.myStore.auth.controllers;


import de.danilova.myStore.api.JwtRequest;
import de.danilova.myStore.api.JwtResponse;
import de.danilova.myStore.api.RegistrationUserDto;
import de.danilova.myStore.api.StoreError;
import de.danilova.myStore.auth.entities.User;
import de.danilova.myStore.auth.services.UserService;
import de.danilova.myStore.auth.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")

public class AuthController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping()
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new StoreError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }


    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody RegistrationUserDto registrationUserDto) {
        if(!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())){
            return new ResponseEntity<>(new StoreError(HttpStatus.BAD_REQUEST.value(), "passwords don't match"),HttpStatus.BAD_REQUEST);
        }
        if(userService.findUserByUsername(registrationUserDto.getUsername()).isPresent()){
            return new ResponseEntity<>(new StoreError(HttpStatus.BAD_REQUEST.value(), "user with this name already exists"),HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setUsername(registrationUserDto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(registrationUserDto.getPassword()));
        user.setEmail(registrationUserDto.getEmail());

        userService.createUser(user);

        UserDetails userDetails = userService.loadUserByUsername(registrationUserDto.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}