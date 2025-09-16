package com.mycompany.persistencia;

import DAO.AnimalDAO;
import DAO.EspecieDAO;
import DAO.ZoologicoDAO;
import entidades.Animal;
import entidades.Animal.Sexo;
import entidades.Especie;
import entidades.Zoologico;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Persistencia {

    public static void main(String[] args) {
        // Creamos instancias de los DAOs
        EspecieDAO especieDAO = new EspecieDAO();
        ZoologicoDAO zoologicoDAO = new ZoologicoDAO();
        AnimalDAO animalDAO = new AnimalDAO();

        try {
            // --- Pruebas de EspecieDAO ---
            System.out.println("--- Probando EspecieDAO ---");

            // 1. Agregar una especie
            Especie nuevaEspecie = new Especie("León Africano", "Panthera leo", "Felidae", false);
            Especie especieAgregada = especieDAO.agregarEspecie(nuevaEspecie);
            System.out.println("Especie agregada: " + especieAgregada.getNombre_vulgar() + " con ID: " + especieAgregada.getId_especie());

            // 2. Consultar todas las especies
            List<Especie> todasLasEspecies = especieDAO.consultarTodasLasEspecies();
            System.out.println("Total de especies: " + todasLasEspecies.size());

            // 3. Consultar una especie por ID
            Especie especieConsultada = especieDAO.consultarEspeciePorId(especieAgregada.getId_especie());
            System.out.println("Especie consultada por ID: " + especieConsultada.getNombre_cientifico());

            // 4. Actualizar una especie
            especieConsultada.setPeligro_extincion(true);
            Especie especieActualizada = especieDAO.actualizarEspecie(especieConsultada);
            System.out.println("Especie actualizada. ¿Está en peligro de extinción?: " + especieActualizada.getPeligro_extincion());

            // --- Pruebas de ZoologicoDAO ---
            System.out.println("\n--- Probando ZoologicoDAO ---");

            // 1. Agregar un zoológico
            Zoologico nuevoZoologico = new Zoologico("Zoo de CDMX", "Ciudad de México", "México", new Date());
            Zoologico zoologicoAgregado = zoologicoDAO.agregarZoologico(nuevoZoologico);
            System.out.println("Zoológico agregado: " + zoologicoAgregado.getNombre() + " con ID: " + zoologicoAgregado.getId_zoologico());

            // 2. Consultar todos los zoológicos
            List<Zoologico> todosLosZoologicos = zoologicoDAO.consultarTodosLosZoologicos();
            System.out.println("Total de zoológicos: " + todosLosZoologicos.size());

            // 3. Consultar un zoológico por ID
            Zoologico zoologicoConsultado = zoologicoDAO.consultarZoologicoPorId(zoologicoAgregado.getId_zoologico());
            System.out.println("Zoológico consultado por ID: " + zoologicoConsultado.getCiudad());

            // 4. Actualizar un zoológico
            zoologicoConsultado.setNombre("Valito");
            Zoologico zoologicoActualizado = zoologicoDAO.actualizarZoologico(zoologicoConsultado);
            System.out.println("Zoológico actualizado: " + zoologicoActualizado.getNombre());

            // --- Pruebas de AnimalDAO ---
            System.out.println("\n--- Probando AnimalDAO ---");

            // 1. Agregar un animal
            Animal nuevoAnimal = new Animal();
            nuevoAnimal.setIdentificacion("L-001");
            nuevoAnimal.setSexo(Sexo.MACHO);
            nuevoAnimal.setAnio_nacimiento(2020);
            nuevoAnimal.setEspecie(especieAgregada);
            nuevoAnimal.setZoologico(zoologicoAgregado);

            Animal animalAgregado = animalDAO.agregarAnimal(nuevoAnimal);
            System.out.println("Animal agregado: " + animalAgregado.getIdentificacion() + " con ID: " + animalAgregado.getId_animal());

            // 2. Consultar todos los animales
            List<Animal> todosLosAnimales = animalDAO.consultarTodosLosAnimales();
            System.out.println("Total de animales: " + todosLosAnimales.size());

            // 3. Consultar un animal por ID
            Animal animalConsultado = animalDAO.consultarAnimalPorId(animalAgregado.getId_animal());
            System.out.println("Animal consultado por ID: " + animalConsultado.getIdentificacion() + ", de la especie: " + animalConsultado.getEspecie().getNombre_vulgar());

            // 4. Actualizar un animal
            animalConsultado.setAnio_nacimiento(2021);
            Animal animalActualizado = animalDAO.actualizarAnimal(animalConsultado);
            System.out.println("Animal actualizado. Nuevo año de nacimiento: " + animalActualizado.getAnio_nacimiento());

            System.out.println("\n¡Pruebas finalizadas con éxito! Revisa tu base de datos para ver los nuevos registros.");

        } catch (SQLException e) {
            System.err.println("Ocurrió un error en la base de datos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Ocurrió un error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
