package org.grupoplan.qplan.service;

import org.grupoplan.qplan.entity.Authority;

import java.util.List;


public interface AuthorityService {

    public List<Authority> findAll();

    public Authority findByAuthority(String authority);

}
