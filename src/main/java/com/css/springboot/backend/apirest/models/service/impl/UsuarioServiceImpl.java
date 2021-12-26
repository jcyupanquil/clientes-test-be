package com.css.springboot.backend.apirest.models.service.impl;

import com.css.springboot.backend.apirest.models.dao.IUsuarioDAO;
import com.css.springboot.backend.apirest.models.entity.Usuario;
import com.css.springboot.backend.apirest.models.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    @Resource
    private IUsuarioDAO usuarioDAO;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioDAO.findByUsername(username);

        if (usuario == null) {
            logger.error("Error en el login, el usuario '" + username + "' no está registrado");
            throw new UsernameNotFoundException("Error en el login, el usuario '" + username + "' no está registrado");
        }

        List<GrantedAuthority> authorities = usuario.getRoles()
                .stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .peek(simpleGrantedAuthority -> logger.info("Rol: " + simpleGrantedAuthority.getAuthority()))
                .collect(Collectors.toList());

        return new User(username,
                usuario.getPassword(),
                usuario.getEnabled(),
                true,
                true,
                true,
                authorities);
    }


    @Override
    @Transactional(readOnly = true)
    public Usuario findByUsername(String username) {
        return usuarioDAO.findByUsername(username);
    }
}
