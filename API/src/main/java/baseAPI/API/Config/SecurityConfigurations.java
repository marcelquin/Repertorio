package baseAPI.API.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return  httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/musica/**", "/api/evento/**", "/api/integrante/**", "/api/banda/**" ).permitAll() //busca todos evento integrante banda
                        .requestMatchers(HttpMethod.POST, "/api/musica/**", "/api/evento/**", "/api/integrante/**", "/api/banda/**" ).permitAll() //salvar
                        .requestMatchers(HttpMethod.PUT, "/api/musica/**", "/api/evento/**", "/api/integrante/**", "/api/banda/**" ).permitAll() // atualizar
                        .requestMatchers(HttpMethod.DELETE, "/api/musica/**", "/api/evento/**", "/api/integrante/**", "/api/banda/**" ).permitAll() //delete
                       /* .requestMatchers(HttpMethod.POST, "/rota", "/rota", ).permitAll() //salvar
                        .requestMatchers(HttpMethod.GET, "/rota", "/rota").permitAll() //busca por id
                        .requestMatchers(HttpMethod.GET, "/rota", "/rota").permitAll() //busca por nome
                        .requestMatchers(HttpMethod.PUT, "/rota", "/rota").permitAll() //atualizar
                        .requestMatchers(HttpMethod.DELETE, "/rota", "/rota").permitAll() // deletar */
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/auth/login", "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/auth/login", "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/auth/login", "/api/v1/auth/**", "/api-api-docs/swagger-config","/api-api-docs", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                      //  .requestMatchers(HttpMethod.POST, "/rota").hasRole("ADMIN")
						.anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
/*
                            

 */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
