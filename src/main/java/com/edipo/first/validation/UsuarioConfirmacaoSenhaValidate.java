package com.edipo.first.validation;

import org.springframework.stereotype.Component;

import com.edipo.first.exception.ConfirmacaoSenhaException;
import com.edipo.first.model.Usuario;

@Component
public class UsuarioConfirmacaoSenhaValidate implements UsuarioValidate {

	@Override
	public void validate(Usuario usuario) {
		if (!usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
			throw new ConfirmacaoSenhaException("A confirmação da senha não corresponde à senha fornecida.");
		}
	}
	
}
