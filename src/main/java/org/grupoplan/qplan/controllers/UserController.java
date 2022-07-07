package org.grupoplan.qplan.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;  

import org.grupoplan.qplan.entity.Anunciante;
import org.grupoplan.qplan.entity.Authority;
import org.grupoplan.qplan.entity.Cliente;
import org.grupoplan.qplan.entity.CodigoPostal;
import org.grupoplan.qplan.entity.User;
import org.grupoplan.qplan.service.AnuncianteService;
import org.grupoplan.qplan.service.AuthorityService;
import org.grupoplan.qplan.service.ClienteService;
import org.grupoplan.qplan.service.CodigoPostalService;
import org.grupoplan.qplan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.grupoplan.qplan.dto.AnuncianteClienteDto;

//

@Controller
public class UserController {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private CodigoPostalService codigoPostalService;

    @Autowired
    private UserService userService;

    @Autowired
    private AnuncianteService anuncianteService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }



    @GetMapping("/registro")
    public String signup(Model model) {

        // usar DTO AnuncianteClienteDTO para enviar los datos del anunciante y cliente al formulario de registro. Por tanto
        //todos los datos del registro se encuentran en anunciantesclientes DTO

        AnuncianteClienteDto anunciantecliente =  new AnuncianteClienteDto();
        anunciantecliente.setRole("ROLE_CLIENTE");

         // para inicializar el radio button del tipo de usuario

        model.addAttribute("formregistro", anunciantecliente);

        //creo una lista de códigos postales para que muestre la ciudad y la provincia

        List<CodigoPostal> cpostales = codigoPostalService.findAll();

		//añado los atributos
		model.addAttribute("signup",true);
		model.addAttribute("cpostales",cpostales);

        //para que devuelva el siggn-up.html
    return "sign-up";
    }


    @PostMapping("/registro")
    public String registroAct(@ModelAttribute AnuncianteClienteDto formregistro, Model model) throws ParseException {

        //al realizar el registro se debe de encriptar las contraseñas y le pongo el nivel de seguridad identico al del login
        // Creamos una instancia de BCrypt para codificar la contraseña

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);

        // Creamos un usuario vacío que va a albergar la contraseña encriptada para guardar en la BBDD

        User user = new User();

        user.setPassword(passwordEncoder.encode(formregistro.getPassword()));
        user.setEstadoActivo(true);
        user.setEmail(formregistro.getEmail());
        user.setTelefono(formregistro.getTelefono());
        user.setNombre(formregistro.getNombre());
        user.setApellidos(formregistro.getApellidos());
        user.setFechaAlta(new Date());

        //para añadirle un rol. Rol es un tipo de usuario donde puedes elegir entre anunciante o cliente.
        //compruebo que el contenido del rol está en la autoridad.
        //al user le añado el authority y lo añado de esta forma porque es una colección Por tanto solo puedo añadirlo
        //como lista o como colección.
        final Authority authorityfinal = authorityService.findByAuthority(formregistro.getRole());
        List<Authority> roles = new ArrayList<>();
        roles.add(authorityfinal);
        user.setAuthority(roles);

        //si el rol que he obtenido en el formulario es igual a cliente, tengo que añadir las propiedades
        //de cliente. Si no tengo que añadir las propiedades de anunciante. Esto es así porque los usuarios que se vayan creando con el formulario
        //solo pueden ser cliente o anunciante. Sin embargo el administrador lo creamos directamente.
        //

        User savedUser = userService.save(user);
        if (formregistro.getRole().equals("ROLE_CLIENTE")) {
            Cliente cliente = new Cliente();
            String fnString = formregistro.getFechaNacimiento();
            Date fNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(fnString);
            //vinculo el usuario que acabo de crear con el cliente
            cliente.setUser(savedUser);
            cliente.setfechaNacimiento(fNacimiento);
            cliente.setSexo(formregistro.getSexo());
            clienteService.save(cliente);
        } else {
            Anunciante anunciante = new Anunciante();
            //vinculo el usuario que acabo de crear con el anunciante
            anunciante.setUser(savedUser);
            anunciante.setNombreLocal(formregistro.getNombreLocal());
            anunciante.setDescripcion(formregistro.getDescripcion());
            anuncianteService.save(anunciante);
        }
        //tras terminar de hacer el post redirijo hacia el login.html y  que mande un parámetro success.
        // Spring gestiona los login dentro de la carpeta config en websecurityconfig
        return "redirect:/login?success=true";

    }

     //Guardo las sesiones con los roles.
    //auth es un objeto de authentication, es igual que securityContextHolder que viene de context.
    //coge el contexto y la autenticación. Si la autenticación (auth) no es nula creo un nuevo SecurityContextLogoutHandler
    //y le mando el request y el response y el contexto.

    @RequestMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        //redirecciona a login y manda un parámetro logout
        return "redirect:/login?logout";
    }
    
}
