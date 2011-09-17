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

import br.com.wbotelhos.movy.business.ArtistaBusiness;
import br.com.wbotelhos.movy.model.Artista;

public class ArtistaBusinessTest {

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private ArtistaBusiness business;
	private Artista artista;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		business = new ArtistaBusiness(manager);
	}

	@Test
	public void deveriaLoadAll() {
		// given
		dadoQueTenhoUmArtistaChamado("Neo");

		// when
		manager.merge(artista);

		// when then
		when(manager.createQuery("from " + Artista.class.getName())).thenReturn(query);
		when(query.getResultList()).thenReturn(Collections.singletonList(artista));

		// then
	}

	@Test
	public void deveriaLoadById() {
		// given
		dadoQueTenhoUmArtistaChamado("Neo");

		// when
		business.loadById(artista.getId());

		// when then
		when(manager.find(Artista.class, artista.getId())).thenReturn(artista);

		// then
		verify(manager).find(Artista.class, artista.getId());
	}

	@Test
	public void deveriaRemove() {
		// given
		dadoQueTenhoUmArtistaChamado("Neo");

		// when
		business.remove(artista);

		// then
		artista = verify(manager).getReference(Artista.class, artista.getId());
		verify(manager).remove(artista);
	}
	
	@Test
	public void deveriaSave() {
		// given
		dadoQueTenhoUmArtistaChamado("Neo");

		// when
		business.save(artista);

		// when then
		when(business.save(artista)).thenReturn(artista);

		// then
		artista = verify(manager).merge(artista);
	}

	private void dadoQueTenhoUmArtistaChamado(String nome) {
		artista = new Artista();
		artista.setId(42l);
		artista.setNome(nome);
	}

}