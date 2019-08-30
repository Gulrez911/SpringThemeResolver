package com.gul.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.CacheControl;
import org.springframework.ui.context.ThemeSource;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan("com")
public class JavaConfig implements WebMvcConfigurer {


	   @Override
	   public void configureViewResolvers(ViewResolverRegistry registry) {
	      registry.jsp("/WEB-INF/views/", ".jsp");
	   }

	   @Override
	   public void addResourceHandlers(ResourceHandlerRegistry registry) {
	      registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
	            .setCacheControl(CacheControl.maxAge(2, TimeUnit.HOURS).cachePublic());
	   }

	   @Bean
	   public ThemeSource themeSource() {
	      ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
	      themeSource.setBasenamePrefix("theme/");
	      return themeSource;
	   }

	   @Bean
	   public ThemeResolver themeResolver() {
	      CookieThemeResolver resolver = new CookieThemeResolver();
	      resolver.setDefaultThemeName("bright");
	      return resolver;
	   }

	   @Bean
	   public LocaleResolver localeResolver() {
	      CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	      return localeResolver;
	   }

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      ThemeChangeInterceptor themeChangeInterceptor = new ThemeChangeInterceptor();
	      themeChangeInterceptor.setParamName("theme");
	      registry.addInterceptor(themeChangeInterceptor);
	   }

}
