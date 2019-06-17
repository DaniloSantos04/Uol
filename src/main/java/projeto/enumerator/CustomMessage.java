package projeto.enumerator;

public enum CustomMessage {
	
	SERVER_ERROR("An unexpected error has occurred. Please try again"),
	NOT_ACCEPTABLE("Not Acceptable"),
	NOT_FOUND("Not Found");

	private final String message;

	private CustomMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
