package br.com.wbotelhos.movy.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.wbotelhos.movy.component.UserSession;
import br.com.wbotelhos.movy.model.Usuario;
import br.com.wbotelhos.movy.repository.LoginRepository;

@Resource
public class LoginController {

	private final Result result;
	private final LoginRepository repository;
	private final UserSession userSession;

	public LoginController(Result result, LoginRepository repository, UserSession userSession) {
		this.result = result;
		this.repository = repository;
		this.userSession = userSession;
	}

	@Get("/login")
	public void login() {

	}

	@Post("/autenticar")
	public void autenticar(Usuario usuario) {
		Usuario user = repository.autenticar(usuario.getEmail(), usuario.getSenha());

		if (user != null) {
			userSession.setUser(user);
	
			result.redirectTo(IndexController.class).index();
		} else {
			result.include("error", "Usuário ou senha incorreta!").redirectTo(this).login();
		}
	}

	@Get("/logout")
	public void logout() {
		userSession.logout();
		result.redirectTo(this).login();
	}

}