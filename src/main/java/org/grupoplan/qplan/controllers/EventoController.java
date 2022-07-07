package org.grupoplan.qplan.controllers;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.grupoplan.qplan.dto.EventoDto;
import org.grupoplan.qplan.entity.Anunciante;
import org.grupoplan.qplan.entity.Categoria;
import org.grupoplan.qplan.entity.CodigoPostal;
import org.grupoplan.qplan.entity.Evento;
import org.grupoplan.qplan.entity.User;
import org.grupoplan.qplan.service.CategoriaService;
import org.grupoplan.qplan.service.CodigoPostalService;
import org.grupoplan.qplan.service.EventoService;
import org.grupoplan.qplan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private CodigoPostalService codigoPostalService;

    @Autowired
    private UserService userService;


    @GetMapping("/page/{page}")
    public String listarEventos(@RequestParam(required = false) Long categoria, Model model, Pageable pageable , @RequestParam(value = "page", defaultValue = "0") long page, @RequestParam(value = "size", defaultValue = "8") long size, int currentpage) {

        Page<Evento> eventos = eventoService.getAllEventos(pageable);
        //filtrar los eventos y eliminar los que no están activos
        List<Evento> listaEventos = new ArrayList<Evento>();
        for (Evento evento : eventos) {
            if (!evento.getEstado().equals(0)) {
                listaEventos.add(evento);
            }
        }

        // eliminar los eventos que no pertenecen a la categoría seleccionada si no hay ninguna categoría seleccionada no se filtra
        if (categoria != null) {
            List<Evento> listaEventosFiltrada = new ArrayList<Evento>();
            for (Evento evento : listaEventos) {
                if (evento.getCategoria().getId() == categoria) {
                    listaEventosFiltrada.add(evento);
                }
            }
            listaEventos = listaEventosFiltrada;
        }


        model.addAttribute("eventos", listaEventos);
        model.addAttribute("totalEventos", listaEventos.size());
        //cuantas paginas tienen los eventos
        model.addAttribute("totalPages", eventos.getTotalPages());
        //getNumber te da la pagina actual
        model.addAttribute("currentpage", eventos.getNumber() + 1);

        //devuelvo los datos en el listingeventos
        return "listingeventos";
    }
    //cuando haga un getmapping, retorno listar eventos solamente que no tiene páginas, el size lo pongo en 8 y digo que la página actual empiece por 1
    //de forma que cuando listo los eventos me debe de salir el número de eventos encontrados
    @GetMapping()
    public String getPagesEventos(@RequestParam(required = false) Long categoria, Model model, Pageable pageable, Long page, Long size) {
        return listarEventos(categoria, model, pageable, 0, 8, 1);
}

    //Buscador de eventos.
    @PostMapping()
    public String formularioIndex(@RequestParam(value = "form-control") String form, Model model) {
        form = form.toLowerCase();
        List<Evento> eventos = new ArrayList<Evento>();
        eventos.addAll(eventoService.findByCategoriaNombreContaining(form));
        
        // no añadir eventos repetidos
        for (Evento evento : eventoService.findByNombreEventoContaining(form)) {
            if (!eventos.contains(evento)) {
                eventos.add(evento);
            }
        }

        for (Evento evento : eventoService.findByDescripcionContaining(form)) {
            if (!eventos.contains(evento)) {
                eventos.add(evento);
            }
        }

        model.addAttribute("eventos", eventos);
        model.addAttribute("totalEventos", eventos.size());
        return "listingeventos";
    }



    @GetMapping("/addevent")
    public String formevent(Model model) {

        model.addAttribute("formevento", new EventoDto());

        List<Categoria> listaCategorias = categoriaService.findAll();
        model.addAttribute("listaCategorias", listaCategorias);

        List<CodigoPostal> listaCodigosPostales = codigoPostalService.findAll();
        model.addAttribute("listaCodigosPostales", listaCodigosPostales);

        model.addAttribute("titulopest", "Añadir evento");
        model.addAttribute("formevent", true);

        return "add-listings";
    }

    @PostMapping("/addevent")
    public String addEvent(@ModelAttribute("formevento") EventoDto formevento, Model model, RedirectAttributes redirectedAttributes) throws ParseException {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        User loggedUser = userService.findByEmail(email).get();
        Anunciante anunciante = loggedUser.getAnunciante();


        Evento evento = new Evento();
        evento.setAnunciante(anunciante);
        evento.setNombreEvento(formevento.getNombreEvento());
        evento.setDescripcion(formevento.getDescripcion());
        evento.setDireccion(formevento.getDireccion());
        evento.setCodigoPostal(codigoPostalService.findById(formevento.getIdCodigoPostal()).get());
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        evento.setFechaInicio(sdf.parse(formevento.getFechaInicio()));
        evento.setFechaFin(sdf.parse(formevento.getFechaFin()));

        evento.setAforo(Integer.parseInt(formevento.getAforo()));
        evento.setCategoria(categoriaService.findById(formevento.getCategoria()).get());

        evento.setEstado(1);
        evento.setImagen(formevento.getImagen());

        Evento savedEvento = eventoService.save(evento);
        Long id = savedEvento.getId();

        redirectedAttributes.addFlashAttribute("message", "Evento añadido correctamente");
        redirectedAttributes.addFlashAttribute("messageType", "success");
        redirectedAttributes.addAttribute("id", id);

        return "redirect:/eventos/visualizar/{id}?success=true";
    }

    @GetMapping("/visualizar/{id}")
    public String visualizeEvent(@PathVariable("id") Long idEvento, Model model) {
        Evento evento = eventoService.findById(idEvento).get();
        Categoria categoria = evento.getCategoria();
        Anunciante anunciante = evento.getAnunciante();
        User usuario = anunciante.getUser();
        model.addAttribute("evento", evento);
        model.addAttribute("anunciante", anunciante);
        model.addAttribute("usuario", usuario);
        model.addAttribute("categoria", categoria);
        model.addAttribute("titulopest", "Visualizar evento: " + evento.getNombreEvento());
        return "single-event";
    }


}
