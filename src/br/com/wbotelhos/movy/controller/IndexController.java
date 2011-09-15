package br.com.wbotelhos.movy.controller;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Resource;

@Resource
public class IndexController {

	public IndexController() {

	}

	@Get("/")
	public void index() {

	}

}