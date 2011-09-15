package br.com.wbotelhos.movy.repository;

import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.wbotelhos.movy.model.Usuario;
import br.com.wbotelhos.movy.repository.common.GenericRepository;

public interface UsuarioRepository extends GenericRepository<Usuario> {

	void removeImage(Usuario usuario) throws Exception;

	void updateImage(Usuario usuario);

	void uploadImage(UploadedFile file, Usuario usuario) throws Exception;

}