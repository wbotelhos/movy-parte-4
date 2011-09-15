package br.com.wbotelhos.movy.business;

import java.io.File;
import java.io.FileOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.wbotelhos.movy.business.common.GenericBusiness;
import br.com.wbotelhos.movy.model.Filme;
import br.com.wbotelhos.movy.repository.FilmeRepository;
import br.com.wbotelhos.movy.util.Upload;

@Component
public class FilmeBusiness extends GenericBusiness<Filme> implements FilmeRepository {

	public FilmeBusiness(EntityManager manager) {
	  super(manager);
	}

	public void removeImage(Filme filme) throws Exception {
		if (!filme.getImagem().equals("default.jpg")) {

			File file = new File(Filme.IMAGE_PATH, filme.getImagem());

			if (file.exists() && !file.delete()) {
				throw new Exception("Não foi possível apagar a imagem.");
			}

			filme.setImagem("default.jpg");

			this.updateImage(filme);
		}
	}

	public void updateImage(Filme filme) {
		Query query = manager.createQuery("update Filme set imagem = :imagem where id = :id");
		query.setParameter("imagem", filme.getImagem());
		query.setParameter("id", filme.getId());
		query.executeUpdate();
	}

	public void uploadImage(UploadedFile file, Filme filme) throws Exception {
		String extension = Upload.getExtension(file.getFileName());

		if (!Upload.isValidExtension(extension)) {
			throw new Exception("Tipo de arquivo não permitido!\nUse: JPG, JPEG, GIF, BMP ou PNG.");
		 }

		filme.setImagem(filme.getId() + extension);

		File diretorio = new File(Filme.IMAGE_PATH);

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		IOUtils.copy(file.getFile(), new FileOutputStream(new File(diretorio, filme.getImagem())));

		this.updateImage(filme);
	}

}