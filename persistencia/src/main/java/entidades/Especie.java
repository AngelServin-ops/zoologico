/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "especies")
public class Especie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_especie;

    @Column(nullable = false, length = 100)
    private String nombre_vulgar;

    @Column(nullable = false, length = 100)
    private String nombre_cientifico;

    @Column(length = 200)
    private String familia;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean peligro_extincion;

    public Especie(Integer id_especie, String nombre_vulgar, String nombre_cientifico, String familia, Boolean peligro_extincion) {
        this.id_especie = id_especie;
        this.nombre_vulgar = nombre_vulgar;
        this.nombre_cientifico = nombre_cientifico;
        this.familia = familia;
        this.peligro_extincion = peligro_extincion;
    }

    public Especie(String nombre_vulgar, String nombre_cientifico, String familia, Boolean peligro_extincion) {
        this.nombre_vulgar = nombre_vulgar;
        this.nombre_cientifico = nombre_cientifico;
        this.familia = familia;
        this.peligro_extincion = peligro_extincion;
    }

    public Especie() {
    }

    public Integer getId_especie() {
        return id_especie;
    }

    public void setId_especie(Integer id_especie) {
        this.id_especie = id_especie;
    }

    public String getNombre_vulgar() {
        return nombre_vulgar;
    }

    public void setNombre_vulgar(String nombre_vulgar) {
        this.nombre_vulgar = nombre_vulgar;
    }

    public String getNombre_cientifico() {
        return nombre_cientifico;
    }

    public void setNombre_cientifico(String nombre_cientifico) {
        this.nombre_cientifico = nombre_cientifico;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public Boolean getPeligro_extincion() {
        return peligro_extincion;
    }

    public void setPeligro_extincion(Boolean peligro_extincion) {
        this.peligro_extincion = peligro_extincion;
    }

}
