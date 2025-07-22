package lk.ijse.back_end.exception;

import lk.ijse.back_end.util.APIResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> handleGenericException(Exception e) {
        return new ResponseEntity(new APIResponse<>(
                500,
                e.getMessage(),
                null
        ), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<APIResponse<String>> handleNotFoundException(ChangeSetPersister.NotFoundException e) {
        return new ResponseEntity(new APIResponse<>(
                404,
                e.getMessage(),
                null
        ), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(
                (filedError) -> errors.put(
                        filedError.getField(), filedError.getDefaultMessage()
                ));
        return new ResponseEntity(new APIResponse<>(
                400,
                "Validation Failed",
                errors
        ), HttpStatus.BAD_REQUEST);
    }
}