
package Formularios;

/**
 * Esta clase sera para manejar el flujo y lo logico del proyecto. Sera el intermediario entre los forms de la capa presentacion
 * y los daos de la capa persistencia
 * @author Jesus
 */
import DAO.AnimalDAO;
import DAO.EspecieDAO;
import DAO.ZoologicoDAO;
import conexion.ConexionBD;
public class ClaseControl {
    private final AnimalDAO animalDAO;
    private final EspecieDAO especieDAO;
    private final ZoologicoDAO zoologicoDAO;

    public ClaseControl(AnimalDAO animalDAO, EspecieDAO especieDAO, ZoologicoDAO zoologicoDAO) {
        ConexionBD conexion = new ConexionBD();
        this.animalDAO = animalDAO;
        this.especieDAO = especieDAO;
        this.zoologicoDAO = zoologicoDAO;
    }
    
    
}
