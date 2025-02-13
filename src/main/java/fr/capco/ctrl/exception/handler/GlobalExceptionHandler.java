package fr.capco.ctrl.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {

		Map<String, String> errors = new HashMap<>();

		ex.getAllErrors().forEach(e -> {

			FieldError f = (FieldError) e;

			String fieldName = f.getField();
			String errorMessage = f.getDefaultMessage();
			errors.put(fieldName, errorMessage);

		});

		return errors;
	}
}