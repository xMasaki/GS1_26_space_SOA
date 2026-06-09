package br.com.fiap.space.exception;

import br.com.fiap.space.exception.type.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorGlobalDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Void> erro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DadosBadRequest>> erro400(MethodArgumentNotValidException ex) {
        List<DadosBadRequest> erros = ex.getFieldErrors().stream()
                .map(DadosBadRequest::new)
                .toList();
        return ResponseEntity.badRequest().body(erros);
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<DadosErroNegocio> erroNegocio(ValidacaoException ex) {
        return ResponseEntity.badRequest().body(new DadosErroNegocio(ex.getMessage()));
    }

    private record DadosBadRequest(String campo, String mensagem) {
        DadosBadRequest(FieldError e) {
            this(e.getField(), e.getDefaultMessage());
        }
    }

    private record DadosErroNegocio(String mensagem) {}
}
