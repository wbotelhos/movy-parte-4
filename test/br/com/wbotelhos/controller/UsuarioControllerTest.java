package br.com.wbotelhos.controller;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.Localization;
import br.com.caelum.vraptor.util.test.MockResult;
import br.com.wbotelhos.movy.component.UserSession;
import br.com.wbotelhos.movy.controller.UsuarioController;
import br.com.wbotelhos.movy.model.Usuario;
import br.com.wbotelhos.movy.repository.UsuarioRepository;

public class UsuarioControllerTest {

	private Usuario usuario;
	private UsuarioController controller;

	@Spy private Result result = new MockResult();

	@Mock private UsuarioRepository repository;
	@Mock private UserSession userSession;
	@Mock Localization localization;

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);
		controller = new UsuarioController(result, repository, userSession);
	}

	@Test
	public void deveriaEditar() {
		// given
		dadoQueTenhoUmUsuarioChamado("Jedi");

		// when
		controller.editar(usuario);

		//then
		usuario = verify(repository).loadById(usuario.getId());
		verify(result).include("usuario", usuario);
	}

	@Test
	public void deveriaExibir() {
		// given
		dadoQueTenhoUmUsuarioChamado("Jedi");

		// when
		controller.editar(usuario);

		//then
		usuario = verify(repository).loadById(usuario.getId());
		verify(result).include("usuario", usuario);
	}

	@Test
	public void deveriaListagem() {
		// given
		
		// when
		controller.listagem();
		
		// then
		verify(repository).loadAll();
		verify(result).include("usuarioList", new ArrayList<Usuario>());
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
		dadoQueTenhoUmUsuarioChamado("Jedi");

		// when
		controller.remover(usuario);

		// then
		verify(repository).remove(usuario);
		verify(result).include("message", "Usuário removido com sucesso!");
	}

	@Test
	public void deveriaSalvar() {
		// given
		dadoQueTenhoUmUsuarioChamado("Jedi");
		
		// when
		controller.salvar(usuario);
		
		// then
		usuario = verify(repository).save(usuario);
		verify(result).include("message", "Usuário salvo com sucesso!");
	}

	@Test
	public void shouldTranslateToEnglish() {
		// given
		String expectedLanguage = "en";
		String expectedCountry = "US";

		// when
		controller.translateTo(expectedLanguage, expectedCountry);

		// then
		Assert.assertEquals("Language is different", expectedLanguage, Locale.getDefault().getLanguage());
		Assert.assertEquals("Country is different", expectedCountry, Locale.getDefault().getCountry());
	}

	@Test
	public void shouldTranslateToPortuguese() {
		// given
		String expectedLanguage = "pt";
		String expectedCountry = "BR";

		// when
		controller.translateTo(expectedLanguage, expectedCountry);

		// then
		Assert.assertEquals("Language is different", expectedLanguage, Locale.getDefault().getLanguage());
		Assert.assertEquals("Country is different", expectedCountry, Locale.getDefault().getCountry());
	}

	private void dadoQueTenhoUmUsuarioChamado(String nome) {
		usuario = new Usuario();
		usuario.setId(42l);
		usuario.setNome(nome);
		usuario.setEmail("email@email.com");
		usuario.setSenha("senha");
	}
	
}