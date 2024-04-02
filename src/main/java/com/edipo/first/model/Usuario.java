package com.edipo.first.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "nome")
	@NotNull(message = "O nome é obrigatório.")
	@Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres.")
	private String nome;
	
	@Column(name = "email")
	@NotNull(message = "O e-mail é obrigatório.")
	@Pattern(regexp = "^(.+)@(\\S+)$", message = "O e-mail deve ser válido.")
	private String email;
	
	@Column(name = "senha")
	@NotNull(message = "A senha é obrigatória.")
	@Size(min = 6, max = 20, message = "A senha deve ter entre 6 e 20 caracteres.")
	private String senha;
	
	@Column(name = "confirmacao_senha")
	@NotNull(message = "A confirmação de senha é obrigatória.")
	private String confirmacaoSenha;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}
	
	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}
	
}
