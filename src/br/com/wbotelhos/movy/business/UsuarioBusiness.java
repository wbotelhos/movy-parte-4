package br.com.wbotelhos.movy.business;

import java.io.File;
import java.io.FileOutputStream;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
import br.com.wbotelhos.movy.business.common.GenericBusiness;
import br.com.wbotelhos.movy.model.Usuario;
import br.com.wbotelhos.movy.repository.UsuarioRepository;
import br.com.wbotelhos.movy.util.Upload;

@Component
public class UsuarioBusiness extends GenericBusiness<Usuario> implements UsuarioRepository {

	public UsuarioBusiness(EntityManager manager) {
	  super(manager);
	}

	public void removeImage(Usuario usuario) throws Exception {
		if (!usuario.getImagem().equals("default.jpg")) {

			File file = new File(Usuario.IMAGE_PATH, usuario.getImagem());

			if (file.exists() && !file.delete()) {
				throw new Exception("Não foi possível apagar a imagem.");
			}

			usuario.setImagem("default.jpg");

			this.updateImage(usuario);
		}
	}

	public void updateImage(Usuario usuario) {
		Query query = manager.createQuery("update Usuario set imagem = :imagem where id = :id");
		query.setParameter("imagem", usuario.getImagem());
		query.setParameter("id", usuario.getId());
		query.executeUpdate();
	}

	public void uploadImage(UploadedFile file, Usuario usuario) throws Exception {
		String extension = Upload.getExtension(file.getFileName());

		if (!Upload.isValidExtension(extension)) {
			throw new Exception("Tipo de arquivo não permitido!\nUse: JPG, JPEG, GIF, BMP ou PNG.");
		 }

		usuario.setImagem(usuario.getId() + extension);

		File diretorio = new File(Usuario.IMAGE_PATH);

		if (!diretorio.exists()) {
			diretorio.mkdirs();
		}

		IOUtils.copy(file.getFile(), new FileOutputStream(new File(diretorio, usuario.getImagem())));

		this.updateImage(usuario);
	}

}