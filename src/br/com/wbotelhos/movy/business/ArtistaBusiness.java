package br.com.wbotelhos.movy.business;

import java.io.File;
import java.io.FileOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.wbotelhos.movy.business.common.GenericBusiness;
import br.com.wbotelhos.movy.model.Artista;
import br.com.wbotelhos.movy.repository.ArtistaRepository;
import br.com.wbotelhos.movy.util.Upload;

@Component
public class ArtistaBusiness extends GenericBusiness<Artista> implements ArtistaRepository {

	public ArtistaBusiness(EntityManager manager) {
	  super(manager);
	}

	public void removeImage(Artista artista) throws Exception {
		if (!artista.getImagem().equals("default.jpg")) {

			File file = new File(Artista.IMAGE_PATH, artista.getImagem());

			if (file.exists() && !file.delete()) {
				throw new Exception("Não foi possível apagar a imagem.");
			}

			artista.setImagem("default.jpg");

			this.updateImage(artista);
		}
	}

	public void updateImage(Artista artista) {
		Query query = manager.createQuery("update Artista set imagem = :imagem where id = :id");
		query.setParameter("imagem", artista.getImagem());
		query.setParameter("id", artista.getId());
		query.executeUpdate();
	}

	public void uploadImage(UploadedFile file, Artista artista) throws Exception {
		String extension = Upload.getExtension(file.getFileName());

		if (!Upload.isValidExtension(extension)) {
			throw new Exception("Tipo de arquivo não permitido!\nUse: JPG, JPEG, GIF, BMP ou PNG.");
		 }

		artista.setImagem(artista.getId() + extension);

		File diretorio = new File(Artista.IMAGE_PATH);

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		IOUtils.copy(file.getFile(), new FileOutputStream(new File(diretorio, artista.getImagem())));

		this.updateImage(artista);
	}

}