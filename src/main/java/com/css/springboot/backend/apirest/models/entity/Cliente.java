package com.css.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    public Cliente() {
        this.facturas = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @NotEmpty(message = "El campo nombre no puede estar vacío")
    @Size(min = 4, max = 12, message = "La longitud del nombre es incorrecta")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "El campo apellido no puede estar vacío")
    private String apellido;

    @NotEmpty(message = "El campo correo no puede estar vacío")
    @Email(message = "El formato del correo no es válido")
    @Column(nullable = false, unique = false)
    private String email;

    @NotNull(message = "Se debe ingresar la fecha")
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Column(name = "foto")
    private String foto;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Region region;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"cliente", "hibernateLazyInitializer", "handler"}, allowSetters = true)
    private List<Factura> facturas;
}
