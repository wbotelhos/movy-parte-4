package br.com.wbotelhos.movy.component;

import java.io.Serializable;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import br.com.wbotelhos.movy.model.Usuario;

@Component
@SessionScoped
public class UserSession implements Serializable {

	private static final long serialVersionUID = 9120380330411537761L;

	private Usuario user;

	public boolean isLogged() {
		return user != null;
	}

	public void logout() {
		user = null;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}