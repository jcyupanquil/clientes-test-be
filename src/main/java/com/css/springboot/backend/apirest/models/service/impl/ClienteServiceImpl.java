package com.css.springboot.backend.apirest.models.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.css.springboot.backend.apirest.models.dao.IFacturaDAO;
import com.css.springboot.backend.apirest.models.dao.IProductoDAO;
import com.css.springboot.backend.apirest.models.entity.Factura;
import com.css.springboot.backend.apirest.models.entity.Producto;
import com.css.springboot.backend.apirest.models.entity.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.css.springboot.backend.apirest.models.dao.IClienteDAO;
import com.css.springboot.backend.apirest.models.entity.Cliente;
import com.css.springboot.backend.apirest.models.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Resource
    private IClienteDAO clienteDAO;

    @Resource
    private IFacturaDAO facturaDAO;

    @Resource
    private IProductoDAO productoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return (List<Cliente>) clienteDAO.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Cliente> findAll(Pageable pageable) {
        return clienteDAO.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente findById(Long id) {
        return clienteDAO.findById(id).orElse(null);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteDAO.save(cliente);
    }

    @Override
    public void delete(Long id) {
        clienteDAO.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Region> findAllRegiones() {
        return clienteDAO.findAllRegiones();
    }

    @Transactional(readOnly = true)
    @Override
    public Factura findFacturaById(Long id)
    {
        return facturaDAO.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public Factura saveFactura(Factura factura)
    {
        return facturaDAO.save(factura);
    }

    @Transactional
    @Override
    public void deleteFacturaById(Long id)
    {
        facturaDAO.deleteById(id);
    }

    @Override
    public List<Producto> findProductosByName(String filter)
    {
        return productoDAO.findByNombreContainingIgnoreCase(filter);
    }

}
