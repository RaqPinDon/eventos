package org.grupoplan.qplan.controllers;

import org.grupoplan.qplan.entity.Categoria;
import org.grupoplan.qplan.service.CategoriaService;
import org.grupoplan.qplan.service.EventoService;
import org.grupoplan.qplan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping()
    public String index(Model model, Pageable pageable,  @AuthenticationPrincipal UserDetails currentUser) {

        // si existe un usuario logueado obtener el ID para poder redirigir al perfil del usuario.
        
        
        // obtener la lista de categorias y añadirlas al modelo para después mostrarlas en la vista iteradas.

        List<Categoria> listaCategorias = categoriaService.getAllCategorias(pageable);
        model.addAttribute("listaCategorias", listaCategorias);

        // obneter el tamaño de la lista de eventos para pasarlo a la vista y así hacer los count (circulos que contienen
        // el numero de eventos, se hace lo mismo con usuarios y categorias)

        model.addAttribute("numeroEventos", String.valueOf(eventoService.findAll().size()));
        model.addAttribute("numeroUsuarios", String.valueOf(userService.findAll().size()));
        
        model.addAttribute("numeroCategorias", String.valueOf(categoriaService.findAll().size()));

        // obtener el usuario logueado y añadirlo al modelo para después mostrarlo en la vista. Igualmente obtener su id
        // para poder redirigir al perfil del usuario.

        String username;
        Long userid;
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser instanceof UserDetails) {
            username = ((UserDetails)loggedUser).getUsername();
            userid = userService.findByEmail(username).get().getId();
            model.addAttribute("idusuariologueado", userid);
        } else {
            username = loggedUser.toString();
        }
        model.addAttribute("nombreUsuario", username);
        return "index";
    }
    
    @GetMapping("home")
    public String home() {
        return "redirect:/";
    }
    
    @GetMapping("/contacto")
    public String contacto() {
        return "contact-us";
    }

}
