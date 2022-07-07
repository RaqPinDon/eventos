package org.grupoplan.qplan.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.grupoplan.qplan.entity.Anunciante;
import org.grupoplan.qplan.entity.Cliente;
import org.grupoplan.qplan.entity.Evento;
import org.grupoplan.qplan.service.AnuncianteService;
import org.grupoplan.qplan.service.ClienteService;
import org.grupoplan.qplan.service.EventoService;
import org.grupoplan.qplan.service.UserService;
import org.grupoplan.qplan.dto.AnuncianteDto;
import org.grupoplan.qplan.dto.ClienteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/administracion")
public class administracionController {

    @Autowired
    private UserService userService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AnuncianteService anuncianteService;

    @Autowired
    private EventoService eventoService;

    @GetMapping()
    public String administracion(Model model) {

        // Listar:
        // los siete clientes más recientes
        // los seis eventos más cercanos
        // siete anunciantes más recientes

        // ----------------------------- PARTE DE CLIENTES -----------------------------

        // Listar los siete clientes más recientes para ello ordenarlos por ID descendente
        List<Cliente> clientes = clienteService.findAll();
        Collections.sort(clientes, (Cliente c1, Cliente c2) -> c2.getId().compareTo(c1.getId()));

        // si clientes tiene menos de 7 clientes listar los que tenga, si no, listar los 7 primeros
        if (clientes.size() < 7) {
            model.addAttribute("clientes", clientes);
        } else {
            model.addAttribute("clientes", clientes.subList(0, 7));
        }

        // ----------------------------- PARTE DE ANUNCIANTES-----------------------------

        List<Anunciante> anunciantes = anuncianteService.findAll();
        Collections.sort(anunciantes, (Anunciante a1, Anunciante a2) -> a2.getId().compareTo(a1.getId()));
        if (anunciantes.size() < 7) {
            model.addAttribute("anunciantes", anunciantes);
        } else {
            model.addAttribute("anunciantes", anunciantes.subList(0, 7));
        }

        // ----------------------------- PARTE DE EVENTOS -----------------------------

        // Añadir a la lista eventos los eventos ordenados por fechaInicio

        List<Evento> eventos = eventoService.findAll();
        Collections.sort(eventos, (Evento e1, Evento e2) -> e1.getFechaInicio().compareTo(e2.getFechaInicio()));

        // filtrar "eventos" por fechaInicio eliminando los eventos pasados, y ordenarlos por fecha descendente.

        for (int i = 0; i < eventos.size(); i++) {
            LocalDateTime fechainicioevento = eventos.get(i).getFechaInicio().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            if (fechainicioevento.isBefore(LocalDateTime.now())) {
                eventos.remove(i);
            }
        }
        
        // si eventos tiene menos de 6 eventos listar los que tenga, si no, listar los 6 primeros


        if (eventos.size() < 6) {
            model.addAttribute("eventos", eventos);
        } else {
            model.addAttribute("eventos", eventos.subList(0, 6));
        }

        return "dashAdmin";
    }

    // ----------------------------- PARTE DE EVENTOS (TODOS)-----------------------------

    // listar todos los eventos (paginados)

    @GetMapping("eventos/page/{page}")
    public String listarEventos(Model model, Pageable pageable, @PathVariable("page") int currentpage,
            String contexto) {
        contexto = "administracion";
        model.addAttribute("contexto", contexto);
        Page<Evento> eventos = eventoService.getAllEventos(pageable);
        model.addAttribute("eventos", eventos);
        model.addAttribute("totalEventos", eventos.getTotalElements());
        model.addAttribute("totalPages", eventos.getTotalPages());
        model.addAttribute("currentpage", eventos.getNumber() + 1);
        return "listingeventos";
    }

    @GetMapping("eventos")
    public String getPagesEventos(Model model, Pageable pageable, String contexto) {
        contexto = "administracion";
        return listarEventos(model, pageable, 1, contexto);
    }

    // eliminar un evento
    @GetMapping("eventos/eliminar/{id}")
    public String deleteEvento(@PathVariable("id") Long id,  RedirectAttributes flash) {
        
        Optional<Evento> evento = eventoService.findById(id);

        if (evento != null) {
            eventoService.deleteById(id);
            flash.addFlashAttribute("success", "Evento eliminado con éxito!");
            return "redirect:/administracion/eventos";
        } else {
            flash.addFlashAttribute("error", "El evento no existe en la base de datos");
        }

        return "redirect:/administracion/eventos";
    }

    


    // ----------------------------- PARTE DE CLIENTES (TODOS)-----------------------------------------------------------------------------------

    // listar todos los clientes

    @GetMapping("clientes")
    public String getClientes(Model model, String contexto) {
        contexto = "administracion";
        model.addAttribute("contexto", contexto);
        List<Cliente> clientes = clienteService.findAll();
        List<ClienteDto> clientesDTO = new ArrayList<>();

        // traspasar los clientes a clientesDTO para poder pasar los datos a la vista, hay que pasar las fechas a string
        for (Cliente cliente : clientes) {
            ClienteDto clienteDTO = new ClienteDto();
            clienteDTO.setId(cliente.getId());
            clienteDTO.setNombre(cliente.getUser().getNombre());
            clienteDTO.setApellidos(cliente.getUser().getApellidos());
            clienteDTO.setEmail(cliente.getUser().getEmail());
            clienteDTO.setTelefono(cliente.getUser().getTelefono());
            clienteDTO.setFechaNacimiento(cliente.getFechaNacimiento().toString());
            clienteDTO.setFechaAlta(cliente.getUser().getFechaAlta().toString());
            clienteDTO.setNumEventos(cliente.getListaEventos().size());
            clientesDTO.add(clienteDTO);
        }

        model.addAttribute("clientes", clientesDTO);
        return "listingclientes";
    }

    // borrar clientes

    @GetMapping("clientes/eliminar/{id}")
    public String eliminarcliente(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        Optional<Cliente> cliente = clienteService.findById(id);

        if (cliente != null) {
            clienteService.deleteById(id);
            flash.addFlashAttribute("success", "Cliente eliminado con éxito!");
            return "redirect:/administracion/clientes";
        } else {
            flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
        }

        return "redirect:/administracion/clientes";
    }


    

    // ----------------------------- PARTE DE ANUNCIANTES (TODOS) -----------------------------

    // listar todos los anunciantes

    @GetMapping("anunciantes")
    public String listarAnunciantes(Model model, String contexto) {
        contexto = "administracion";
        model.addAttribute("contexto", contexto);
        List<Anunciante> anunciantes = anuncianteService.findAll();
        List<AnuncianteDto> anunciantesDTO = new ArrayList<>();

        for (Anunciante anunciante : anunciantes) {
            AnuncianteDto anuncianteDto = new AnuncianteDto();
            anuncianteDto.setId(anunciante.getId());
            anuncianteDto.setNombre(anunciante.getUser().getNombre());
            anuncianteDto.setApellidos(anunciante.getUser().getApellidos());
            anuncianteDto.setEmail(anunciante.getUser().getEmail());
            anuncianteDto.setTelefono(anunciante.getUser().getTelefono());
            anuncianteDto.setNombreLocal(anunciante.getNombreLocal());
            anuncianteDto.setDescripcion(anunciante.getDescripcion());
            anuncianteDto.setFechaAlta(anunciante.getUser().getFechaAlta().toString());
            anuncianteDto.setNumEventos(anunciante.getListaEventos().size());
            anunciantesDTO.add(anuncianteDto);
        }

        model.addAttribute("anunciantes", anunciantesDTO);
        return "listinganunciantes";
    }

    @GetMapping("anunciantes/eliminar/{id}")
    public String eliminarAnunciante(@PathVariable(value = "id") Long id, RedirectAttributes flash) {

        Optional<Anunciante> anunciante = anuncianteService.findById(id);

        if (anunciante != null) {
            anuncianteService.deleteById(id);
            flash.addFlashAttribute("success", "Anunciante eliminado con éxito!");
            return "redirect:/administracion/anunciantes";
        } else {
            flash.addFlashAttribute("error", "El anunciante no existe en la base de datos");
        }

        return "redirect:/administracion/anunciantes";
    }



}