package br.com.sis.pedidos.backend.config;

import br.com.sis.pedidos.backend.security.JWTAuthenticationFilter;
import br.com.sis.pedidos.backend.security.JWTAuthorizationFilter;
import br.com.sis.pedidos.backend.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //anotação para determinar acessos a endPoints específicos para perfil informado.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    private static final String[] PUBLIC_MATCHERS = {
            "/h2-console/**"
    };

    // somente leitura
    private static final String[] PUBLIC_MATCHERS_GET = {
            "/produtos/**",
            "/categorias/**"
    };

    private static final String[] PUBLIC_MATCHERS_POST = {
            "/clientes/**",
            "/auth/refresh_token/"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //configuração específica para acesso ao profile teste
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }

        //desabilitando o cors e CSRF, pois não será necessário manter objeto durante as requisições.
        http.cors().and().csrf().disable();
        //chamada do cors para liberação de requisições básicas através de multiplas fontes.
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_POST).permitAll() //libera requisições POST informadas sem autenticação
                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll() //libera requisições GET informadas sem autenticação
                .antMatchers(PUBLIC_MATCHERS).permitAll() // liberar todas as requisições públicas informadas sem autenticação
                .anyRequest().authenticated();
        //registrando o filtro de AUTENTICAÇÃO do spring security
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
        //registrando o filtro de AUTORIZAÇÃO do spring security
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
        //estamos definindo que neste projeto não será necessário o armazenamento de sessão durante as requisições (stateless)
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    //bean permitindo o acesso a multiplas fontes com as configurações básicas
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
