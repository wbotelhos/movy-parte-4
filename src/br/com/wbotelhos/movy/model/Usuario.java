package br.com.wbotelhos.movy.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import br.com.wbotelhos.movy.model.common.AbstractEntity;
import br.com.wbotelhos.movy.model.common.TipoPerfil;

@Entity
public class Usuario extends AbstractEntity {

	private static final long serialVersionUID = 3783393251821367126L;

	public static final String IMAGE_PATH = "/Users/botelho/movy/img/usuario";

	private String nome;
	private String email;
	private String senha;
	private String imagem;

	@Enumerated(EnumType.STRING)
	private TipoPerfil perfil;

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

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public TipoPerfil getPerfil() {
		return perfil;
	}

	public void setPerfil(TipoPerfil perfil) {
		this.perfil = perfil;
	}

}