package Vendas.config;

import Vendas.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

@Autowired

private UsuarioServiceImpl usuarioService;

    // encoder de senha , gerando hash para cada senha gerada
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    //mudança do cadastro para ser via banco e cadastrar no sistema
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/clientes/**")
                .hasAnyRole("ADMIN","USER")
                .antMatchers("/api/produtos/**")
                .hasRole("ADMIN")
                .antMatchers("api/pedidos/**")
                .hasAnyRole("ADMIN","USER")
                .antMatchers(HttpMethod.POST, "/api/usuarios/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }

//    /**
//     * <form method = "post> <input type="test" name= "username"
//     * <form method = "post> <input type="test" name= "username"
//     *
//     *     </form>
//     */
}
