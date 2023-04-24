package se.lexicon.teaterwebapp.Exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data

public class APIError {
    private HttpStatus status;
    private String statusText;
    private LocalDateTime timestamp;

    public APIError() {
        this.timestamp = LocalDateTime.now();
    }

    public APIError(HttpStatus status, String statusText) {
        this();
        this.status = status;
        this.statusText = statusText;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}