package com.css.springboot.backend.apirest.models.dao;

import com.css.springboot.backend.apirest.models.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IProductoDAO extends CrudRepository<Producto, Long>
{
    @Query("SELECT p FROM producto p WHERE p.nombre LIKE %?1%")
    List<Producto> findByNombre(String filter);

    List<Producto> findByNombreContainingIgnoreCase(String filter);

    List<Producto> findByNombreStartingWithIgnoreCase(String filter);
}
