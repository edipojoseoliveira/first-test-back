package com.edipo.first.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edipo.first.model.Usuario;
import com.edipo.first.repository.UsuarioRepository;
import com.edipo.first.validation.UsuarioValidate;

@Service
public class UsuarioService {
	
	private final List<UsuarioValidate> usuarioValidations;
	private final UsuarioRepository repository;
	
	@Autowired
	public UsuarioService(List<UsuarioValidate> usuarioValidations,
			UsuarioRepository repository) {
		this.usuarioValidations = usuarioValidations;
		this.repository = repository;
	}
	
	public Usuario salvar(Usuario usuario) {
		this.usuarioValidations.forEach(validation -> validation.validate(usuario));
		return this.repository.save(usuario);
	}

}
