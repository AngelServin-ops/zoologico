/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "animales")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_animal;

    @Column(nullable = false, length = 20)
    private String identificacion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Sexo sexo;

    @Column
    private Integer anio_nacimiento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_especie", nullable = false)
    private Especie especie;

    @ManyToOne
    @JoinColumn(name = "id_zoologico")
    private Zoologico zoologico;

    public Animal(Integer id_animal, String identificacion, Sexo sexo, Integer anio_nacimiento, Especie especie, Zoologico zoologico) {
        this.id_animal = id_animal;
        this.identificacion = identificacion;
        this.sexo = sexo;
        this.anio_nacimiento = anio_nacimiento;
        this.especie = especie;
        this.zoologico = zoologico;
    }

    public Animal(String identificacion, Sexo sexo, Integer anio_nacimiento, Especie especie, Zoologico zoologico) {
        this.identificacion = identificacion;
        this.sexo = sexo;
        this.anio_nacimiento = anio_nacimiento;
        this.especie = especie;
        this.zoologico = zoologico;
    }

    public Animal() {
    }

    // Enumeración para el campo sexo
    public enum Sexo {
        MACHO, HEMBRA
    }

    public Integer getId_animal() {
        return id_animal;
    }

    public void setId_animal(Integer id_animal) {
        this.id_animal = id_animal;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getAnio_nacimiento() {
        return anio_nacimiento;
    }

    public void setAnio_nacimiento(Integer anio_nacimiento) {
        this.anio_nacimiento = anio_nacimiento;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public Zoologico getZoologico() {
        return zoologico;
    }

    public void setZoologico(Zoologico zoologico) {
        this.zoologico = zoologico;
    }

}
