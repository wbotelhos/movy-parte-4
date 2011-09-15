package br.com.wbotelhos.movy.controller;

import java.io.File;
import java.util.Collection;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.download.Download;
import br.com.caelum.vraptor.interceptor.download.FileDownload;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.wbotelhos.movy.annotation.Permission;
import br.com.wbotelhos.movy.model.Usuario;
import br.com.wbotelhos.movy.model.common.TipoPerfil;
import br.com.wbotelhos.movy.repository.UsuarioRepository;

@Resource
public class UsuarioController {

	private final UsuarioRepository repository;
	private final Result result;

	public UsuarioController(Result result, UsuarioRepository repository) {
		this.result = result;
		this.repository = repository;
	}

	@Get("/usuario/{usuario.id}/imagem")
	public Download downloadImage(Usuario usuario) {
		usuario = repository.loadById(usuario.getId());

		File file = new File(Usuario.IMAGE_PATH, usuario.getImagem());

	    if (!file.exists()) {
	        return new FileDownload(new File(Usuario.IMAGE_PATH, "default.jpg"), "image/jpg", "default.jpg");
	    }

	    String fileName = usuario.getNome().replaceAll(" ", "-") + ".jpg";

	    return new FileDownload(file, "image/jpg", fileName);
	}

	@Get("/usuario/{usuario.id}/editar")
	public void editar(Usuario usuario) {
	  usuario = repository.loadById(usuario.getId());

	  result.include("usuario", usuario).forwardTo(this).novo();
	}

	@Get("/usuario/{usuario.id}")
	public void exibir(Usuario usuario) {
		usuario = repository.loadById(usuario.getId());

		result.include("usuario", usuario);
	}

	@Post("/usuario/{usuario.id}/imagem")
	public void uploadImage(UploadedFile file, Usuario usuario) {
		try {
			repository.uploadImage(file, usuario);
		} catch (Exception e) {
			result.include("error", e.getMessage());
		}

		result.redirectTo(this).exibir(usuario);
	}

	@Get("/usuario")
	public void listagem() {
		Collection<Usuario> usuarioList = repository.loadAll();

		result.include("usuarioList", usuarioList);
	}

	@Get("/negado")
	public void negado() {
		
	}

	@Get("/usuario/novo")
	public void novo() {

	}

	@Permission({ TipoPerfil.MODERADOR, TipoPerfil.ADMINISTRADOR })
	@Delete("/usuario/{usuario.id}")
	public void remover(Usuario usuario) {
	  repository.remove(usuario);

	  result
	  .include("message", "Usuário removido com sucesso!")
	  .redirectTo(this).listagem();
	}

	@Delete("/usuario/{usuario.id}/imagem")
	public void removeImage(Usuario usuario) {
		try {
			usuario = repository.loadById(usuario.getId());

			repository.removeImage(usuario);
		} catch (Exception e) {
			result.include("error", e.getMessage());
		}

		result.redirectTo(this).exibir(usuario);
	}

	@Post("/usuario")
	public void salvar(Usuario usuario) {
		usuario = repository.save(usuario);

		result
		.include("message", "Usuário salvo com sucesso!")
		.redirectTo(this).exibir(usuario);
	}

}