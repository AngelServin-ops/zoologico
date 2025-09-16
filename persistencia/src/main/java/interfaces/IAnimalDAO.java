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

    Animal actualizarAnimal(Animal animal) throws SQLException;

    Animal eliminarAnimal(Animal animal) throws SQLException;

    Animal consultarAnimalPorId(Integer id) throws SQLException;

    List<Animal> consultarTodosLosAnimales() throws SQLException;
}
