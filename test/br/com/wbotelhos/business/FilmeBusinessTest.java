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

import br.com.wbotelhos.movy.business.FilmeBusiness;
import br.com.wbotelhos.movy.model.Filme;

public class FilmeBusinessTest {

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private FilmeBusiness business;
	private Filme filme;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		business = new FilmeBusiness(manager);
	}

	@Test
	public void deveriaLoadAll() {
		// given
		dadoQueTenhoUmFilmeChamado("Matrix");

		// when
		manager.merge(filme);

		// when then
		when(manager.createQuery("from " + Filme.class.getName())).thenReturn(query);
		when(query.getResultList()).thenReturn(Collections.singletonList(filme));

		// then
	}

	@Test
	public void deveriaLoadById() {
		// given
		dadoQueTenhoUmFilmeChamado("Matrix");

		// when
		business.loadById(filme.getId());

		// when then
		when(manager.find(Filme.class, filme.getId())).thenReturn(filme);

		// then
		verify(manager).find(Filme.class, filme.getId());
	}

	@Test
	public void deveriaRemove() {
		// given
		dadoQueTenhoUmFilmeChamado("Matrix");

		// when
		business.remove(filme);

		// then
		filme = verify(manager).getReference(Filme.class, filme.getId());
		verify(manager).remove(filme);
	}
	
	@Test
	public void deveriaSave() {
		// given
		dadoQueTenhoUmFilmeChamado("Matrix");

		// when
		business.save(filme);

		// when then
		when(business.save(filme)).thenReturn(filme);

		// then
		filme = verify(manager).merge(filme);
	}

	private void dadoQueTenhoUmFilmeChamado(String titulo) {
		filme = new Filme();
		filme.setId(42l);
		filme.setTitulo(titulo);
	}

}