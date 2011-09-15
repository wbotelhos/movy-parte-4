package br.com.wbotelhos.movy.component;

import br.com.caelum.vraptor.interceptor.multipart.DefaultMultipartConfig;
import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;

@Component
@ApplicationScoped
public class CustomMultipartConfig extends DefaultMultipartConfig {

	@Override
	public long getSizeLimit() {
		return 3 * 1024 * 1024;
	}

}