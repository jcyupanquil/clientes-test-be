package com.css.springboot.backend.apirest.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Juan Carlos Yupanqui
 */
@Getter
@Setter
@Entity(name = "factura")
public class Factura implements Serializable
{

    private static final long serialVersionUID = 1L;

    public Factura() {
        this.items = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    private String descripcion;

    private String observacion;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    @JsonIgnoreProperties(value= {"facturas","hibernateLazyInitializer", "handler"}, allowSetters = true)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<ItemFactura> items;

    @PrePersist
    public void prePersist(){
        this.createAt = new Date();
    }

    public Double getTotal() {
        return this.items.stream().mapToDouble(ItemFactura::getImporte)
                .reduce(0.00, Double::sum);
    }
}
