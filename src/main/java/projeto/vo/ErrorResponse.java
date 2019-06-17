package projeto.vo;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResponse {

	private Instant timestamp;

    private int status;

    private String error;

    public ErrorResponse() {
	
    }

	public ErrorResponse(Instant timestamp, int status, String error, List<String> messages) {
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
		this.messages = messages;
	}


	@JsonProperty("error_description")
    private List<String> messages;

	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

}
