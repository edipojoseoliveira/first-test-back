package com.edipo.first.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edipo.first.exception.ConfirmacaoSenhaException;
import com.edipo.first.model.Usuario;
import com.edipo.first.service.UsuarioService;

import jakarta.validation.Valid;

@RestController
public class UsuarioController {
	
	private final UsuarioService service;
	
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	@PostMapping("/usuario")
	public Usuario salvar(@Valid @RequestBody Usuario usuario) {
		return service.salvar(usuario);
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConfirmacaoSenhaException.class)
    public Map<String, String> handleValidationExceptions(ConfirmacaoSenhaException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("confirmacaoSenha", ex.getMessage());
        return errors;
    }

}
