package de.danilova.myStore.api;


import io.swagger.v3.oas.annotations.media.Schema;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Schema(description = "Error's model")
public class StoreError {

    @Schema(description = "Error's httpStatus",example = "404")
    private int httpStatus;
    @Schema(description = "Error's description", example = "Product doesn't found")
    private List<String> messages;
    @Schema(description = "Time", example = "2022-12-15T10:27:10.510Z")
    private Date timestamp;

    public StoreError(int httpStatus, String...messages){
        this.httpStatus = httpStatus;
        this.messages = new ArrayList<>(List.of(messages));
        this.timestamp = new Date();
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public List<String> getMessages() {
        return messages;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public StoreError() {

    }
}
