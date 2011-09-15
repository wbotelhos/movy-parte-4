package br.com.wbotelhos.movy.repository;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.wbotelhos.movy.model.Artista;
import br.com.wbotelhos.movy.repository.common.GenericRepository;

public interface ArtistaRepository extends GenericRepository<Artista> {

	void removeImage(Artista artista) throws Exception;

	void updateImage(Artista artista);

	void uploadImage(UploadedFile file, Artista artista) throws Exception;

}