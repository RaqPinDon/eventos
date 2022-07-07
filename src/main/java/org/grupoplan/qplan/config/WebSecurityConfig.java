package org.grupoplan.qplan.config;

import org.grupoplan.qplan.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//Para crear una clase de seguridad personalizada, necesitamos usar @EnableWebSecurity y extender la clase con @WebSecurityConfigurerAdapter
// para que podamos redefinir algunos de los métodos proporcionados. Spring Security te fuerza a hashear las contraseñas para que no se guarden en texto plano.

//

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Necesario para evitar que la seguridad se aplique a los resources, como los css, imágenes y javascripts

    String[] resources = new String[]{
            "/plugins/**", "/css/**", "/js/**", "/img/**", "/data/**"
    };

    //Para la autorización definimos qué recursos deben estar securizados y cuales no.Usamos el método configure que recibe HttpSecurity como parámetro.


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //Debemos tener en cuenta que las reglas más restrictivas deben estar en la parte superior.

        http
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/", "/index*","/contacto*", "/registro*", "/search*").permitAll()
                .antMatchers("/administracion*").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/perfil*").access("hasRole('ROLE_CLIENTE') or hasRole('ROLE_ANUNCIANTE')")
                .antMatchers("/eventos/addevent*").access("hasRole('ROLE_ANUNCIANTE') or hasRole('ROLE_ADMIN')")
                .antMatchers("/eventos/visualizar/**","/eventos/*","/eventos*").permitAll()
                .antMatchers("/eventos/editar_evento*").access("hasRole('ROLE_ANUNCIANTE') or hasRole('ROLE_ADMIN')")
                .anyRequest().authenticated();
        http.formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password");
        http.logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }


    //Encriptador de contraseñas: para encriptar la contraseña se utiliza la librería Bcript. Tiene una función
    //que es codificar mediante una función de hash. En este caso queremos codificar la contraseña, por tanto, le pasas otro parámetro
    // con el número de veces que quieres encriptarla. En este caso será 4. Lo ideal sería un mínimo de 10.


    BCryptPasswordEncoder bCryptPasswordEncoder;
    //Crea el encriptador de contraseñas

    public BCryptPasswordEncoder passwordEncoder() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
    //El numero 4 representa que tan fuerte quieres la encriptación. Se puede en un rango entre 4 y 31.

        return bCryptPasswordEncoder;
    }


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    //Registra el service para usuarios y el encriptador de contraseña
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}