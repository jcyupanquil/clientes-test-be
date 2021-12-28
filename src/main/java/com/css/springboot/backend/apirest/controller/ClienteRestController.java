package com.css.springboot.backend.apirest.controller;

import com.css.springboot.backend.apirest.models.entity.Cliente;
import com.css.springboot.backend.apirest.models.entity.Region;
import com.css.springboot.backend.apirest.models.service.IClienteService;
import com.css.springboot.backend.apirest.models.service.IUploadFileService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/api")
public class ClienteRestController {

    public static final String CLIENTE_ID = "cliente-id";

    @Resource
    private IClienteService clienteService;

    @Resource
    private IUploadFileService uploadFileService;

    @GetMapping("/clientes")
    public List<Cliente> index() {
        return clienteService.findAll();
    }

    @GetMapping("/clientes/page/{page-number}")
    public Page<Cliente> index(@PathVariable("page-number") final Integer pageNumber) {
        return clienteService.findAll(PageRequest.of(pageNumber, 4));
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @GetMapping("/clientes/{cliente-id}")
    public ResponseEntity<?> show(@PathVariable(CLIENTE_ID) Long clienteId) {
        Cliente cliente = null;
        Map<String, Object> response = new HashMap<>();

        try {
            cliente = clienteService.findById(clienteId);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar la consulta en la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (cliente == null) {
            response.put("message", "El cliente ID: " + clienteId + " no existe");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/clientes")
    public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {

        Cliente clienteCreado = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            clienteCreado = clienteService.save(cliente);
        } catch (DataAccessException e) {
            response.put("message", "Error al insertar en la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "El cliente ha sido creado con éxito");
        response.put("data", clienteCreado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/clientes/{cliente-id}")
    public ResponseEntity<?> update(@PathVariable(CLIENTE_ID) Long clienteId, @Valid @RequestBody Cliente cliente,
                                    BindingResult result) {
        Cliente clienteActualizado = null;
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(fieldError -> fieldError.getDefaultMessage())
                    .collect(Collectors.toList());

            response.put("errors", errors);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
        }

        Cliente clienteActual = clienteService.findById(clienteId);

        if (clienteActual == null) {
            response.put("error", "El cliente no existe en la base de datos");
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            clienteActual.setApellido(cliente.getApellido());
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setEmail(cliente.getEmail());
            clienteActual.setCreateAt(cliente.getCreateAt());
            clienteActual.setRegion(cliente.getRegion());

            clienteActualizado = clienteService.save(clienteActual);
        } catch (DataAccessException e) {
            response.put("message", "Error al actualizar al cliente en la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "El cliente ha sido actualizado con éxito");
        response.put("data", clienteActualizado);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/clientes/{cliente-id}")
    public ResponseEntity<?> delete(@PathVariable(CLIENTE_ID) Long clienteId) {
        Map<String, Object> response = new HashMap<>();

        try {
            Cliente cliente = clienteService.findById(clienteId);

            String oldFotoName = cliente.getFoto();
            uploadFileService.delete(oldFotoName);
            clienteService.delete(clienteId);
        } catch (DataAccessException e) {
            response.put("message", "Error al eliminar al cliente en la base de datos");
            response.put("error", e.getMessage().concat(" : ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put("message", "Se ha eliminado al cliente satisfactoriamente");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    @PostMapping("/clientes/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
                                    @RequestParam("id") final Long id) {
        Map<String, Object> response = new HashMap<>();

        Cliente cliente = clienteService.findById(id);

        if (file != null && !file.isEmpty()) {
            String fileName = null;
            try {
                fileName = uploadFileService.copy(file);
            } catch (IOException e) {
                e.printStackTrace();
                response.put("message", "Error al subir la imagen");
                response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            String oldFotoName = cliente.getFoto();

            uploadFileService.delete(oldFotoName);

            cliente.setFoto(fileName);
        }

        clienteService.save(cliente);
        response.put("data", cliente);
        response.put("message", "La imagen ha sido cargada correctamente");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping("/uploads/img/{fileName:.+}")
    public ResponseEntity<org.springframework.core.io.Resource> showFoto(@PathVariable final String fileName) {

        org.springframework.core.io.Resource resource = null;
        try {
            resource = uploadFileService.get(fileName);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"");

        return new ResponseEntity<>(resource, httpHeaders, HttpStatus.OK);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/clientes/regiones")
    public List<Region> listarRegiones() {
        return clienteService.findAllRegiones();
    }
}
