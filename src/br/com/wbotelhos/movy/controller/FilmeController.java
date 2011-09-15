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
import br.com.wbotelhos.movy.model.Filme;
import br.com.wbotelhos.movy.repository.FilmeRepository;

@Resource
public class FilmeController {

	private final FilmeRepository repository;
	private final Result result;

	public FilmeController(Result result, FilmeRepository repository) {
		this.result = result;
		this.repository = repository;
	}

	@Get("/filme/{filme.id}/imagem")
	public Download downloadImage(Filme filme) {
		filme = repository.loadById(filme.getId());

		File file = new File(Filme.IMAGE_PATH, filme.getImagem());

	    if (!file.exists()) {
	        return new FileDownload(new File(Filme.IMAGE_PATH, "default.jpg"), "image/jpg", "default.jpg");
	    }

	    String fileName = filme.getTitulo().replaceAll(" ", "-") + ".jpg";

	    return new FileDownload(file, "image/jpg", fileName);
	}

	@Get("/filme/{filme.id}/editar")
	public void editar(Filme filme) {
	  filme = repository.loadById(filme.getId());

	  result.include("filme", filme).forwardTo(this).novo();
	}

	@Get("/filme/{filme.id}")
	public void exibir(Filme filme) {
		filme = repository.loadById(filme.getId());

		result.include("filme", filme);
	}

	@Post("/filme/{filme.id}/imagem")
	public void uploadImage(UploadedFile file, Filme filme) {
		try {
			repository.uploadImage(file, filme);
		} catch (Exception e) {
			result.include("error", e.getMessage());
		}

		result.redirectTo(this).exibir(filme);
	}

	@Get("/filme")
	public void listagem() {
		Collection<Filme> filmeList = repository.loadAll();

		result.include("filmeList", filmeList);
	}

	@Get("/filme/novo")
	public void novo() {

	}

	@Delete("/filme/{filme.id}")
	public void remover(Filme filme) {
	  repository.remove(filme);

	  result
	  .include("message", "Filme removido com sucesso!")
	  .redirectTo(this).listagem();
	}

	@Delete("/filme/{filme.id}/imagem")
	public void removeImage(Filme filme) {
		try {
			filme = repository.loadById(filme.getId());

			repository.removeImage(filme);
		} catch (Exception e) {
			result.include("error", e.getMessage());
		}

		result.redirectTo(this).exibir(filme);
	}

	@Post("/filme")
	public void salvar(Filme filme) {
		filme = repository.save(filme);

		result
		.include("message", "Filme salvo com sucesso!")
		.redirectTo(this).exibir(filme);
	}

}