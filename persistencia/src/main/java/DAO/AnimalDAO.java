/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import interfaces.IAnimalDAO;
import conexion.ConexionBD;
import entidades.Animal;
import entidades.Animal.Sexo;
import entidades.Especie;
import entidades.Zoologico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class AnimalDAO implements IAnimalDAO {

    private final ConexionBD conexion;

    public AnimalDAO() {
        this.conexion = new ConexionBD();
    }

    @Override
    public Animal agregarAnimal(Animal animal) throws SQLException {
        String sql = "INSERT INTO animales (identificacion, sexo, anio_nacimiento, id_especie, id_zoologico) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, animal.getIdentificacion());
                ps.setString(2, animal.getSexo().toString());
                ps.setInt(3, animal.getAnio_nacimiento());
                ps.setInt(4, animal.getEspecie().getId_especie());
                if (animal.getZoologico() != null) {
                    ps.setInt(5, animal.getZoologico().getId_zoologico());
                } else {
                    ps.setNull(5, java.sql.Types.INTEGER);
                }
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        animal.setId_animal(rs.getInt(1));
                    }
                }
                return animal;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Animal actualizarAnimal(Animal animal) throws SQLException {
        String sql = "UPDATE animales SET identificacion = ?, sexo = ?, anio_nacimiento = ?, id_especie = ?, id_zoologico = ? WHERE id_animal = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, animal.getIdentificacion());
                ps.setString(2, animal.getSexo().toString());
                ps.setInt(3, animal.getAnio_nacimiento());
                ps.setInt(4, animal.getEspecie().getId_especie());
                if (animal.getZoologico() != null) {
                    ps.setInt(5, animal.getZoologico().getId_zoologico());
                } else {
                    ps.setNull(5, java.sql.Types.INTEGER);
                }
                ps.setInt(6, animal.getId_animal());
                ps.executeUpdate();
                return animal;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Animal eliminarAnimal(Animal animal) throws SQLException {
        String sql = "DELETE FROM animales WHERE id_animal = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, animal.getId_animal());
                ps.executeUpdate();
                return animal;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Animal consultarAnimalPorId(Integer id) throws SQLException {
        String sql = "SELECT a.id_animal, a.identificacion, a.sexo, a.anio_nacimiento, "
                + "e.id_especie, e.nombre_vulgar, e.nombre_cientifico, e.familia, e.peligro_extincion, "
                + "z.id_zoologico, z.nombre, z.ciudad, z.pais, z.fecha_inauguracion "
                + "FROM animales a JOIN especies e ON a.id_especie = e.id_especie "
                + "LEFT JOIN zoologicos z ON a.id_zoologico = z.id_zoologico "
                + "WHERE a.id_animal = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return construirAnimalDesdeResultSet(rs);
                    }
                }
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
        return null;
    }

    @Override
    public List<Animal> consultarTodosLosAnimales() throws SQLException {
        List<Animal> animales = new ArrayList<>();
        String sql = "SELECT a.id_animal, a.identificacion, a.sexo, a.anio_nacimiento, "
                + "e.id_especie, e.nombre_vulgar, e.nombre_cientifico, e.familia, e.peligro_extincion, "
                + "z.id_zoologico, z.nombre, z.ciudad, z.pais, z.fecha_inauguracion "
                + "FROM animales a JOIN especies e ON a.id_especie = e.id_especie "
                + "LEFT JOIN zoologicos z ON a.id_zoologico = z.id_zoologico";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    animales.add(construirAnimalDesdeResultSet(rs));
                }
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
        return animales;
    }

    private Animal construirAnimalDesdeResultSet(ResultSet rs) throws SQLException {
        Especie especie = new Especie();
        especie.setId_especie(rs.getInt("id_especie"));
        especie.setNombre_vulgar(rs.getString("nombre_vulgar"));
        especie.setNombre_cientifico(rs.getString("nombre_cientifico"));
        especie.setFamilia(rs.getString("familia"));
        especie.setPeligro_extincion(rs.getBoolean("peligro_extincion"));

        Zoologico zoologico = null;
        if (rs.getObject("id_zoologico") != null) {
            zoologico = new Zoologico();
            zoologico.setId_zoologico(rs.getInt("id_zoologico"));
            zoologico.setNombre(rs.getString("nombre"));
            zoologico.setCiudad(rs.getString("ciudad"));
            zoologico.setPais(rs.getString("pais"));
            zoologico.setFecha_inauguracion(rs.getDate("fecha_inauguracion"));
        }

        Animal animal = new Animal();
        animal.setId_animal(rs.getInt("id_animal"));
        animal.setIdentificacion(rs.getString("identificacion"));
        animal.setSexo(Sexo.valueOf(rs.getString("sexo")));
        animal.setAnio_nacimiento(rs.getInt("anio_nacimiento"));
        animal.setEspecie(especie);
        animal.setZoologico(zoologico);

        return animal;
    }
}
