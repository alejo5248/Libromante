package com.libromante.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//se encarga de dar acceso a los clientes a los recursos de la aplicacion siempre y cuando el token sea valido
import org.springframework.web.filter.CorsFilter;
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/admin/evento").permitAll()//que direcciones se puede ingrear sin autenticar
		.antMatchers(HttpMethod.GET, "/eventos/mostrareventos","/libros/queleer","/libros/listarpornombre/**").permitAll()
		/*.antMatchers(HttpMethod.GET,"/factura/mostrarfacturas/{id}").hasRole("ADMIN")*/
		.anyRequest().authenticated()
		.and().cors().configurationSource(corsConfigurationSource());
		
	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("https://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST","PUT", "DELETE","OPTIONS"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("Content-Type","Authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean; 
	}

	
}
