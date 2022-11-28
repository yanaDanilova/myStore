package de.danilova.myStore.auth.exceptions;

import de.danilova.myStore.api.ResourceNotFoundException;
import de.danilova.myStore.api.StoreError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
     public ResponseEntity<?> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        StoreError storeError = new StoreError(HttpStatus.NOT_FOUND.value(),e.getMessage());
        return new ResponseEntity<>(storeError, HttpStatus.NOT_FOUND);

     }

}
