package com.iyzico.event;

import com.iyzico.event.configuration.IyzipayApiProperties;
import org.apache.catalina.Context;
import org.apache.tomcat.util.descriptor.web.ErrorPage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;


@SpringBootApplication
@EnableConfigurationProperties(IyzipayApiProperties.class)
public class IyzicoEventApplication  implements WebApplicationInitializer
{
	public static void main(String[] args) {
		SpringApplication.run(IyzicoEventApplication.class, args);
	}

	@Bean
	public ServletRegistrationBean facesServletRegistraiton()
	{
		ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet(), new String[]{"*.xhtml"});
		registration.setName("FacesServlet");
		registration.setLoadOnStartup(1);
		return registration;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer()
	{
		return servletContext ->
		{
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
            /*servletContext.setInitParameter("primefaces.THEME", "bootstrap");*/
			servletContext.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
			servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
			servletContext.setInitParameter("primefaces.UPLOADER", "commons");
		};
	}

	@Override
	public void onStartup(ServletContext container)
	{
		ServletRegistration.Dynamic registration = container.addServlet("MVC Servlet", new DispatcherServlet());
		container.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
		container.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
		container.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
		registration.setLoadOnStartup(1);
		registration.addMapping("/*");
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer()
	{

		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();

		TomcatContextCustomizer contextCustomizer = new TomcatContextCustomizer()
		{
			@Override
			public void customize(Context context)
			{
				context.addWelcomeFile("index.xhtml");
				ErrorPage errorPage = new ErrorPage();
				errorPage.setErrorCode(500);
				errorPage.setExceptionType("java.lang.Throwable");
				errorPage.setLocation("/error/errorOccured.xhtml");
				context.addErrorPage(errorPage);
			}
		};
		factory.addContextCustomizers(contextCustomizer);

		return factory;
	}

}
