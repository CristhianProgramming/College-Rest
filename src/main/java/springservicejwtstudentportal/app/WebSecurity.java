package springservicejwtstudentportal.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import springservicejwtstudentportal.app.services.authentication.jpaAuthenticationConfiguration;

@Configuration
public class WebSecurity{

	@Autowired
	jpaAuthenticationConfiguration jpaValidate;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests()
		.requestMatchers("/user/register**").permitAll()
		.and().csrf().disable()
		;
		
		return http.build();
	}
	
	void Configuration(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jpaValidate).passwordEncoder(encoder);
	}
	
}
