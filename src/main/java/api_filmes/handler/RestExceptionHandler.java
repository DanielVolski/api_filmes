package api_filmes.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import api_filmes.domain.entities.ResponseError;
import api_filmes.domain.exception.BadRequestException;
import api_filmes.domain.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ResponseError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ResponseError error = new ResponseError(HttpStatus.NOT_FOUND.value(), "Recurso não encontrado", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseError> handleBadRequestException(BadRequestException ex) {
        ResponseError error = new ResponseError(HttpStatus.BAD_REQUEST.value(), "Requisição inválida", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> handleException(Exception ex) {
        ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
