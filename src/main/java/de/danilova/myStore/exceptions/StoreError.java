package de.danilova.myStore.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor

public class StoreError {

    private int httpStatus;
    private List<String> messages;
    private Date timestamp;

    public StoreError(int httpStatus, String...messages){
        this.httpStatus = httpStatus;
        this.messages = new ArrayList<>(List.of(messages));
        this.timestamp = new Date();
    }


}
