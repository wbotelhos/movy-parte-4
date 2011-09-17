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
import br.com.wbotelhos.movy.controller.FilmeController;
import br.com.wbotelhos.movy.model.Filme;
import br.com.wbotelhos.movy.repository.FilmeRepository;

public class FilmeControllerTest {

	private Filme filme;
	private FilmeController controller;

	@Spy
	private Result result = new MockResult();

	@Mock
	private FilmeRepository repository;

	@Mock
	Localization localization;

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);
		controller = new FilmeController(result, repository);
	}

	@Test
	public void deveriaEditar() {
		// given
		dadoQueTenhoUmFilmeChamado("Matrix");

		// when
		controller.editar(filme);

		//then
		filme = verify(repository).loadById(filme.getId());
		verify(result).include("filme", filme);
	}

	@Test
	public void deveriaExibir() {
		// given
		dadoQueTenhoUmFilmeChamado("Matrix");

		// when
		controller.editar(filme);

		//then
		filme = verify(repository).loadById(filme.getId());
		verify(result).include("filme", filme);
	}

	@Test
	public void deveriaListagem() {
		// given
		
		// when
		controller.listagem();
		
		// then
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
		dadoQueTenhoUmFilmeChamado("Matrix");

		// when
		controller.remover(filme);

		// then
		verify(repository).remove(filme);
		verify(result).include("message", "Filme removido com sucesso!");
	}

	@Test
	public void deveriaSalvar() {
		// given
		dadoQueTenhoUmFilmeChamado("Matrix");
		
		// when
		controller.salvar(filme);
		
		// then
		filme = verify(repository).save(filme);
		verify(result).include("message", "Filme salvo com sucesso!");
	}

	private void dadoQueTenhoUmFilmeChamado(String titulo) {
		filme = new Filme();
		filme.setId(42l);
		filme.setTitulo(titulo);
	}
	
}