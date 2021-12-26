package com.css.springboot.backend.apirest.models.dao;

import com.css.springboot.backend.apirest.models.entity.Cliente;
import com.css.springboot.backend.apirest.models.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IClienteDAO extends JpaRepository<Cliente, Long> {

    @Query("from Region")
    List<Region> findAllRegiones();
}
