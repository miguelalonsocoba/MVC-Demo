package com.cjava.demo.domain.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "audit")
public class Audit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String tabla;
    @Column(name = "id_registro`")
    private Integer idRegistro;
    private Date fecha;
    private String usuario;
    private String tipo;

    public Audit() {
    }

    public Audit(String tabla, Integer idRegistro, Date fecha,
                 String usuario, String tipo) {
        super();
        this.tabla = tabla;
        this.idRegistro = idRegistro;
        this.fecha = fecha;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Auditoria [id=" + id + ", tabla=" + tabla + ", idRegistro="
                + idRegistro + ", fecha=" + fecha + ", usuario=" + usuario
                + ", tipo=" + tipo + "]";
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTabla() {
        return tabla;
    }
    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
    public Integer getIdRegistro() {
        return idRegistro;
    }
    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}