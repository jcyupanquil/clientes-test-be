package com.css.springboot.backend.apirest.models.dao;

import com.css.springboot.backend.apirest.models.entity.Factura;
import org.springframework.data.repository.CrudRepository;

public interface IFacturaDAO extends CrudRepository<Factura, Long>
{

}
