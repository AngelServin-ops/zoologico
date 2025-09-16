/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class ConexionBD implements IConexionBD {

    private final String cadenaConexion;
    private final String usuario;
    private final String password;

    // Constructor que usa los parámetros pasados
    public ConexionBD(String usuario, String contrasena) {
        this.cadenaConexion = "jdbc:mysql://localhost/zoologico_db"; // Asumiendo que esta es la DB del proyecto
        this.usuario = usuario;
        this.password = contrasena;
    }

    // Constructor sin parámetros para usar los valores fijos
    public ConexionBD() {
        this("root", "Angel.240831");
    }

    @Override
    // Establecer una conexión con la BD
    public Connection crearConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error: No se pudo cargar el driver JDBC de MySQL.", e);
        }
        return DriverManager.getConnection(cadenaConexion, usuario, password);
    }
}
