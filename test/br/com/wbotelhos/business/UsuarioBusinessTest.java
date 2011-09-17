package br.com.wbotelhos.business;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.wbotelhos.movy.business.UsuarioBusiness;
import br.com.wbotelhos.movy.model.Usuario;

public class UsuarioBusinessTest {

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private UsuarioBusiness business;
	private Usuario usuario;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		business = new UsuarioBusiness(manager);
	}

	@Test
	public void deveriaLoadAll() {
		// given
		dadoQueTenhoUmUsuarioChamado("Jedi");

		// when
		manager.merge(usuario);

		// when then
		when(manager.createQuery("from " + Usuario.class.getName())).thenReturn(query);
		when(query.getResultList()).thenReturn(Collections.singletonList(usuario));

		// then
	}

	@Test
	public void deveriaLoadById() {
		// given
		dadoQueTenhoUmUsuarioChamado("Jedi");

		// when
		business.loadById(usuario.getId());

		// when then
		when(manager.find(Usuario.class, usuario.getId())).thenReturn(usuario);

		// then
		verify(manager).find(Usuario.class, usuario.getId());
	}

	@Test
	public void deveriaRemove() {
		// given
		dadoQueTenhoUmUsuarioChamado("Jedi");

		// when
		business.remove(usuario);

		// then
		usuario = verify(manager).getReference(Usuario.class, usuario.getId());
		verify(manager).remove(usuario);
	}
	
	@Test
	public void deveriaSave() {
		// given
		dadoQueTenhoUmUsuarioChamado("Jedi");

		// when
		business.save(usuario);

		// when then
		when(business.save(usuario)).thenReturn(usuario);

		// then
		usuario = verify(manager).merge(usuario);
	}

	private void dadoQueTenhoUmUsuarioChamado(String nome) {
		usuario = new Usuario();
		usuario.setId(42l);
		usuario.setNome(nome);
	}

}