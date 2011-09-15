package br.com.wbotelhos.movy.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;
import br.com.wbotelhos.movy.annotation.Permission;
import br.com.wbotelhos.movy.model.common.TipoPerfil;

@Resource
@Permission(TipoPerfil.ADMINISTRADOR)
public class AdminController {

	@Get("/admin")
	public void admin() {

	}

}