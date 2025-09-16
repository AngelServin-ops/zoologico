/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "zoologicos")
public class Zoologico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_zoologico;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String ciudad;

    @Column(nullable = false, length = 100)
    private String pais;

    @Temporal(TemporalType.DATE)
    private Date fecha_inauguracion;

    public Zoologico(Integer id_zoologico, String nombre, String ciudad, String pais, Date fecha_inauguracion) {
        this.id_zoologico = id_zoologico;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fecha_inauguracion = fecha_inauguracion;
    }

    public Zoologico(String nombre, String ciudad, String pais, Date fecha_inauguracion) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.pais = pais;
        this.fecha_inauguracion = fecha_inauguracion;
    }

    public Zoologico() {
    }

    public Integer getId_zoologico() {
        return id_zoologico;
    }

    public void setId_zoologico(Integer id_zoologico) {
        this.id_zoologico = id_zoologico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public Date getFecha_inauguracion() {
        return fecha_inauguracion;
    }

    public void setFecha_inauguracion(Date fecha_inauguracion) {
        this.fecha_inauguracion = fecha_inauguracion;
    }

}
