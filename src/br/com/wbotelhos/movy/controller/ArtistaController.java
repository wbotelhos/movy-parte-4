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
import br.com.wbotelhos.movy.model.Artista;
import br.com.wbotelhos.movy.model.common.TipoSexo;
import br.com.wbotelhos.movy.repository.ArtistaRepository;

@Resource
public class ArtistaController {

	private final ArtistaRepository repository;
	private final Result result;

	public ArtistaController(Result result, ArtistaRepository repository) {
		this.result = result;
		this.repository = repository;
	}

	@Get("/artista/{artista.id}/imagem")
	public Download downloadImage(Artista artista) {
		artista = repository.loadById(artista.getId());

		File file = new File(Artista.IMAGE_PATH, artista.getImagem());

	    if (!file.exists()) {
	        return new FileDownload(new File(Artista.IMAGE_PATH, "default.jpg"), "image/jpg", "default.jpg");
	    }

	    String fileName = artista.getNome().replaceAll(" ", "-") + ".jpg";

	    return new FileDownload(file, "image/jpg", fileName);
	}

	@Get("/artista/{artista.id}/editar")
	public void editar(Artista artista) {
	  artista = repository.loadById(artista.getId());

	  result.include("artista", artista).forwardTo(this).novo();
	}

	@Get("/artista/{artista.id}")
	public void exibir(Artista artista) {
		artista = repository.loadById(artista.getId());

		result.include("artista", artista);
	}

	@Post("/artista/{artista.id}/imagem")
	public void uploadImage(UploadedFile file, Artista artista) {
		try {
			repository.uploadImage(file, artista);
		} catch (Exception e) {
			result.include("error", e.getMessage());
		}

		result.redirectTo(this).exibir(artista);
	}

	@Get("/artista")
	public void listagem() {
		Collection<Artista> artistaList = repository.loadAll();

		result.include("artistaList", artistaList);
	}

	@Get("/artista/novo")
	public TipoSexo[] novo() {
		return TipoSexo.values();
	}

	@Delete("/artista/{artista.id}")
	public void remover(Artista artista) {
	  repository.remove(artista);

	  result
	  .include("message", "Artista removido com sucesso!")
	  .redirectTo(this).listagem();
	}

	@Delete("/artista/{artista.id}/imagem")
	public void removeImage(Artista artista) {
		try {
			artista = repository.loadById(artista.getId());

			repository.removeImage(artista);
		} catch (Exception e) {
			result.include("error", e.getMessage());
		}

		result.redirectTo(this).exibir(artista);
	}

	@Post("/artista")
	public void salvar(Artista artista) {
		artista = repository.save(artista);

		result
		.include("message", "Artista salvo com sucesso!")
		.redirectTo(this).exibir(artista);
	}

}