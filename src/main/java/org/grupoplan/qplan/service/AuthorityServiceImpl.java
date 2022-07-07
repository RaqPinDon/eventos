package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Authority;
import org.grupoplan.qplan.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class AuthorityServiceImpl implements AuthorityService{

    @Autowired
    private AuthorityRepository authorityRepository;

    //todo creo que en vez de listar es collect
    @Override
    @Transactional(readOnly = true)
    public List<Authority> findAll() {
        return authorityRepository.findAll();
    }

    //todo no estoy segura de que esté bn
    @Override
    @Transactional
    public Authority findByAuthority(String authority)  {
        return authorityRepository.findByAuthority(authority);
    }
}
