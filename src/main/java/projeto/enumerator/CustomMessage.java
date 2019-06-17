package projeto.enumerator;

public enum CustomMessage {
	
	SERVER_ERROR("Ocorreu um erro inesperado. Por favor, tente novamente"),
	NOT_ACCEPTABLE("Não aceito"),
	NOT_FOUND("Não encontrado");

	private final String message;

	private CustomMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
