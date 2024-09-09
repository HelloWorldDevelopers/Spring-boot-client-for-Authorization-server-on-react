package ai.rnt.customerFeedback.security.config;

import java.beans.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 
@Configuration
@EnableWebMvc
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Component
 
public class SecurityConfig {
 
	@Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.cors();
		httpSecurity.csrf().disable();
        httpSecurity
            .authorizeHttpRequests(authz -> authz
                .antMatchers("/api/v1/redirect/**","/api/v1/message/**").permitAll() // Allow access to your redirect API
                .anyRequest().authenticated() // Secure other endpoints
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(org.springframework.security.config.Customizer.withDefaults())
            );
        return httpSecurity.build();
    }
	@Bean
	public JwtDecoder jwtDecoder() {
		NimbusJwtDecoder jwtDecoder=NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/oauth2/jwks")
				.build();
		return jwtDecoder;
	}
 
}
