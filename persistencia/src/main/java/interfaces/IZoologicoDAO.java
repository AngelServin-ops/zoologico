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

    Zoologico agregarZoologico(Zoologico zoologico) throws SQLException;

    Zoologico actualizarZoologico(Zoologico zoologico) throws SQLException;

    Zoologico eliminarZoologico(Zoologico zoologico) throws SQLException;

    Zoologico consultarZoologicoPorId(Integer id) throws SQLException;

    List<Zoologico> consultarTodosLosZoologicos() throws SQLException;
}
