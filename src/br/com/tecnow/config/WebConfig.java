package br.com.tecnow.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import br.com.tecnow.interceptors.AutenticacaoPorSessaoInterceptor;
/**
 * Classe de configurações
 * @author Camila
 *
 */
@Configuration
@ComponentScan("br.com.tecnow")
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
	
	@Autowired
	AutenticacaoPorSessaoInterceptor autentica;
	
	/**
	 * Registra os interceptors no Spring
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(autentica).addPathPatterns("/**");
	}

	/**
	 * Configura o caminho das views
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		registry.viewResolver(resolver);
	}
	
	/**
	 * Armazena recursos como imagens, arquivos css e outros através do Spring que podem ser atendidos fora dos locais na raiz do sistema.
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry
			.addResourceHandler("/assets/**").addResourceLocations("/assets/");
		registry.addResourceHandler("/imagens/**").addResourceLocations("/imagens/");
	}
	
}
