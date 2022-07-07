package org.grupoplan.qplan.controllers;

import org.grupoplan.qplan.dto.AnuncianteClienteDto;
import org.grupoplan.qplan.entity.CodigoPostal;
import org.grupoplan.qplan.entity.User;
import org.grupoplan.qplan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/perfil")
public class UserPerfilController{
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

    @GetMapping()
    public String perfil(Model model,  @AuthenticationPrincipal UserDetails currentUser) {
        Long userid = null;
        String username = null;
        Object loggedUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser instanceof UserDetails) {
            username = ((UserDetails)loggedUser).getUsername();
            userid = userService.findByEmail(username).get().getId();
        }
        return "redirect:/perfil/"+userid;  
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable("id") Long idUser, Model model) {
        Optional<User> oneUser = userService.findById(idUser);
        User OneUser = oneUser.get();
        model.addAttribute("datosUsuario", OneUser);
        return "profile-user";
    }

    @GetMapping("/modificar/{id}")
    public String modificarUser(@PathVariable("id") Long idUser, Model model) {
        Optional<User> oneUser = userService.findById(idUser);
        User OneUser = oneUser.get();
        model.addAttribute("datosUsuario", OneUser);
        return "modificar-profile-user";
    }

    @PostMapping("/modificar/1/save")
    // anotacion para recibir datos desde la vista
    public String modificar(@ModelAttribute User user) {
        userService.save(user);
        System.out.println("Cliente guardado con exito");
        return "redirect:/1";
    }
}