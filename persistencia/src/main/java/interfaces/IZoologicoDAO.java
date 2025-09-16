/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Zoologico;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IZoologicoDAO {

    // Método para agregar un nuevo zoológico a la base de datos
    Zoologico agregarZoologico(Zoologico zoologico) throws SQLException;

    // Método para actualizar un zoológico existente en la base de datos
    Zoologico actualizarZoologico(Zoologico zoologico) throws SQLException;

    // Método para eliminar un zoológico de la base de datos
    Zoologico eliminarZoologico(Zoologico zoologico) throws SQLException;

    // Método para consultar un zoológico por su ID
    Zoologico consultarZoologicoPorId(Integer id) throws SQLException;

    // Método para consultar todos los zoológicos en la base de datos
    List<Zoologico> consultarTodosLosZoologicos() throws SQLException;
}
