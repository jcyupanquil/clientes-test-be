package com.css.springboot.backend.apirest.models.service.impl;

import java.util.List;

import javax.annotation.Resource;

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

}
