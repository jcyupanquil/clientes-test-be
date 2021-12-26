package com.css.springboot.backend.apirest.models.service;

import com.css.springboot.backend.apirest.models.entity.Usuario;

public interface IUsuarioService {

    Usuario findByUsername(String username);

}
