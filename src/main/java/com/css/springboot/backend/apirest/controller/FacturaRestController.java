package com.css.springboot.backend.apirest.controller;

import com.css.springboot.backend.apirest.models.entity.Factura;
import com.css.springboot.backend.apirest.models.entity.Producto;
import com.css.springboot.backend.apirest.models.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Juan Carlos Yupanqui
 */
@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class FacturaRestController
{

    @Resource
    private IClienteService clienteService;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/facturas/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Factura show(@PathVariable Long id){
        return clienteService.findFacturaById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("/facturas/{factura-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteFactura(@PathVariable("factura-id") Long id){
        clienteService.deleteFacturaById(id);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping("/facturas/filtrar-productos/{filter}")
    public List<Producto> filterProductos(@PathVariable final String filter){
        return clienteService.findProductosByName(filter);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping("/facturas")
    @ResponseStatus(HttpStatus.CREATED)
    public Factura crearFactura(@RequestBody final Factura factura){
        return clienteService.saveFactura(factura);
    }

}
