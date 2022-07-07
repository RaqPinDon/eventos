package org.grupoplan.qplan.service;
import java.util.List;
import java.util.Optional;
import org.grupoplan.qplan.entity.User;


public interface UserService {

    public List<User> findAll();

    public Optional<User> findById(Long id);

    public User save(User user);

    public void deleteById(Long id);

    public Optional<User> findByEmail(String email);
}



