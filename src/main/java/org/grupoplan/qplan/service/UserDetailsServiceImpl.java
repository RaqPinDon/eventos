package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Authority;
import org.grupoplan.qplan.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

//UserDetailServiceImpl implementa un servicio que nos permitirá cargar datos específicos del usuario.
//como hacer login y asegurarme que en la aplicación solo entren los que queremos.
//no hace falta que nos creemos una interfaz de userdatailservice porque ya usamos la que viene por defecto

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //Buscar el usuario con el repositorio y si no existe lanza una excepción
        org.grupoplan.qplan.entity.User appUser =
                userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));

        //Mapear nuestra lista de Authority con la de spring security. Por cada usuario se solicita que rol tienen y los guarda en una lista.
        List grantList = new ArrayList();
        for (Authority authority: appUser.getAuthority()) {

            // ROLE_CLIENTE, ROLE_ANUNCIANTE, ROLE_ADMIN,..

            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getAuthority());
            grantList.add(grantedAuthority);
        }
        //comprueba que el usuario y la contraseña coincida con el rol.
        //Crea el objeto UserDetails que va a ir en sesión y retornarlo.
        UserDetails user = (UserDetails) new User(appUser.getEmail(), appUser.getPassword(), grantList);
        return user;
    }
}
