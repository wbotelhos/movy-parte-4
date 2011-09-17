package br.com.wbotelhos.controller;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.wbotelhos.movy.controller.ArtistaController;
import br.com.wbotelhos.movy.model.Artista;
import br.com.wbotelhos.movy.repository.ArtistaRepository;

public class ArtistaControllerTest {

	private Artista artista;
	private ArtistaController controller;

	@Spy
	private Result result = new MockResult();

	@Mock
	private ArtistaRepository repository;

	@Mock
	Localization localization;

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);
		controller = new ArtistaController(result, repository);
	}

	@Test
	public void deveriaEditar() {
		// given
		dadoQueTenhoUmArtistaChamado("Neo");

		// when
		controller.editar(artista);

		//then
		artista = verify(repository).loadById(artista.getId());
		verify(result).include("artista", artista);
	}

	@Test
	public void deveriaExibir() {
		// given
		dadoQueTenhoUmArtistaChamado("Neo");

		// when
		controller.editar(artista);

		//then
		artista = verify(repository).loadById(artista.getId());
		verify(result).include("artista", artista);
	}

	@Test
	public void deveriaListagem() {
		// given
		
		// when
		controller.listagem();
		
		// then
		verify(repository).loadAll();
		verify(result).include("artistaList", new ArrayList<Artista>());
	}

	@Test
	public void deveriaNovo() {
		// given

		// when
		controller.novo();

		// then
	}

	@Test
	public void deveriaRemover() {
		// given
		dadoQueTenhoUmArtistaChamado("Neo");

		// when
		controller.remover(artista);

		// then
		verify(repository).remove(artista);
		verify(result).include("message", "Artista removido com sucesso!");
	}

	@Test
	public void deveriaSalvar() {
		// given
		dadoQueTenhoUmArtistaChamado("Neo");
		
		// when
		controller.salvar(artista);
		
		// then
		artista = verify(repository).save(artista);
		verify(result).include("message", "Artista salvo com sucesso!");
	}

	private void dadoQueTenhoUmArtistaChamado(String nome) {
		artista = new Artista();
		artista.setId(42l);
		artista.setNome(nome);
	}
	
}