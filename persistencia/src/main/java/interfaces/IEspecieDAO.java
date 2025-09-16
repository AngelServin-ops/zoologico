/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Especie;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IEspecieDAO {

    Especie agregarEspecie(Especie especie) throws SQLException;

    Especie actualizarEspecie(Especie especie) throws SQLException;

    Especie eliminarEspecie(Especie especie) throws SQLException;

    Especie consultarEspeciePorId(Integer id) throws SQLException;

    List<Especie> consultarTodasLasEspecies() throws SQLException;
}
