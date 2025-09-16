/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Animal;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IAnimalDAO {

    Animal agregarAnimal(Animal animal) throws SQLException;

    // Método para actualizar un animal existente
    Animal actualizarAnimal(Animal animal) throws SQLException;

    // Método para eliminar un animal
    Animal eliminarAnimal(Animal animal) throws SQLException;

    // Método para consultar un animal por su ID
    Animal consultarAnimalPorId(Integer id) throws SQLException;

    // Método para consultar todos los animales
    List<Animal> consultarTodosLosAnimales() throws SQLException;
}
