package projeto.advice;

import static java.time.Instant.now;
import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

import javax.validation.ConstraintDefinitionException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import projeto.enumerator.CustomMessage;
import projeto.exception.NotFoundException;
import projeto.vo.ErrorResponse;

@RestControllerAdvice
public class UolExpectionHandler {
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException (final Exception e) {
        return status(INTERNAL_SERVER_ERROR)
                .body(this.constructErrorResponse(INTERNAL_SERVER_ERROR, CustomMessage.SERVER_ERROR.getMessage()));
    }
	
	
	@ExceptionHandler(ConstraintDefinitionException.class)
    public ResponseEntity<ErrorResponse> handleConstraintDefinitionException (final ConstraintDefinitionException e) {
        return status(NOT_ACCEPTABLE)
                .body(this.constructErrorResponse(NOT_ACCEPTABLE, CustomMessage.NOT_ACCEPTABLE.getMessage()));
    }
	
	@ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUpdateException (final NotFoundException e) {
        return status(NOT_FOUND)
                .body(this.constructErrorResponse(NOT_FOUND, CustomMessage.NOT_FOUND.getMessage()));
    }
	
	private ErrorResponse constructErrorResponse (final HttpStatus httpStatus, final String... messages) {
        return new ErrorResponse(now(), httpStatus.value(), httpStatus.getReasonPhrase(), asList(messages));
    }

}
