package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    public List<Cliente> findAll();

    public Optional<Cliente> findById(Long id);

    public Cliente save(Cliente cliente);

    public void deleteById(Long id);

    Page<Cliente> getAllClientes(Pageable pageable);

}
