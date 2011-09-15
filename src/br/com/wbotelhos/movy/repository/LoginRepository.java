package br.com.wbotelhos.movy.repository;

import br.com.wbotelhos.movy.model.Usuario;

public interface LoginRepository {

	Usuario autenticar(String email, String senha);

}