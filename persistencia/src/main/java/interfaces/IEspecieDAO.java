/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import entidades.Especie;
import java.util.List;

/**
 *
 * @author Usuario
 */
public interface IEspecieDAO {

    Especie agregarEspecie(Especie especie);

    Especie actualizarEspecie(Especie especie);

    Especie eliminarEspecie(Especie especie);

    Especie consultarEspeciePorId(Integer id);

    List<Especie> consultarTodasLasEspecies();
}
