package br.com.wbotelhos.movy.repository;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.wbotelhos.movy.model.Filme;
import br.com.wbotelhos.movy.repository.common.GenericRepository;

public interface FilmeRepository extends GenericRepository<Filme> {

	void removeImage(Filme filme) throws Exception;

	void updateImage(Filme filme);

	void uploadImage(UploadedFile file, Filme filme) throws Exception;

}