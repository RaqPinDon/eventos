package org.grupoplan.qplan;


import org.grupoplan.qplan.entity.Categoria;
import org.grupoplan.qplan.entity.Evento;
import org.grupoplan.qplan.repository.CategoriaRepository;
import org.grupoplan.qplan.repository.EventoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class EventoRepositoryTest {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void testCreateEvento() {
        Evento evento = new Evento();
        evento.setNombreEvento("Senderismo por la Sierra de Huelva");
        evento.setDescripcion("Senderismo por la Sierra de Huelva, donde podremos disfrutar de su paisaje natural y de sus senderos, con parada gastronómica.");
        evento.setAforo(10);
        evento.setImagen("https://i.ibb.co/jkf5rqZ/descarga-1.jpg");
        evento.setEstado(1);
        evento.setDireccion("Varios senderos");
        evento.setFechaInicio(new Date());
        evento.setFechaFin(new Date());
        evento.setAnunciante(null);

        final Categoria nombcategoria = categoriaRepository.findByNombre("Cultura");
        List <Categoria> categoria = new ArrayList<>();
        categoria.add(nombcategoria);
        evento.setCategoria(nombcategoria);

        Evento savedEvento = eventoRepository.save(evento);

        Evento foundEvento = entityManager.find(Evento.class, savedEvento.getId());

        assert foundEvento.getId() == savedEvento.getId();
    }
}
