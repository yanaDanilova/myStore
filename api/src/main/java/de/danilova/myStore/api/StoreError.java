package de.danilova.myStore.api;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreError {

    private int httpStatus;
    private List<String> messages;
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
