package br.com.wbotelhos.controller;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import br.com.wbotelhos.movy.controller.IndexController;

public class IndexControllerTest {

	private IndexController controller;

	@Before
	public void setUp() throws IOException {
		MockitoAnnotations.initMocks(this);
		controller = new IndexController();
	}

	@Test
	public void deveriaIndex() {
		// given

		// when
		controller.index();

		//then
	}

}