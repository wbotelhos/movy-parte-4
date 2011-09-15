package br.com.wbotelhos.movy.business;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;

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

	public Integer countByFilter(String search, String find) {
		Query query = manager.createQuery("select count(e.id) from Filme e where e." + find + " like :search");
		query.setParameter("search", "%" + search + "%");

		return ((Long) query.getSingleResult()).intValue();
	}

	public Collection<Filme> listByFilter(String search, int page, String sortName, String sortOrder, String find, int rows) {
		String sql = "from Filme e where e." + find + " like :search";
		String order = " order by e." + find;

		if (sortName != null && !sortName.isEmpty()) {
			order = " order by e." + sortName;
		}

		if (sortOrder != null && !sortOrder.isEmpty()) {
			order += " desc";
		}

		int inicio = (page - 1) * rows;

		Query query = manager.createQuery(sql + order);
		query.setFirstResult(inicio);
		query.setMaxResults(rows);

		query.setParameter("search", "%" + search + "%");

		@SuppressWarnings("unchecked")
		Collection<Filme> resultList = query.getResultList();

		return resultList;
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