package com.css.springboot.backend.apirest.models.dao;

import com.css.springboot.backend.apirest.models.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {

    /**
     * Method 1
     */
    Usuario findByUsername(String username);

    /**
     * Method 2
     */
    @Query("SELECT u FROM Usuario u WHERE u.username = ?1")
    Usuario findByUsernameJQL(String username);

}
