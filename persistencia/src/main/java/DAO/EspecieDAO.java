/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import conexion.ConexionBD;
import entidades.Especie;
import interfaces.IEspecieDAO;
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
public class EspecieDAO implements IEspecieDAO {

    private final ConexionBD conexion;

    public EspecieDAO() {
        this.conexion = new ConexionBD();
    }

    @Override
    public Especie agregarEspecie(Especie especie) throws SQLException {
        String sql = "INSERT INTO especies (nombre_vulgar, nombre_cientifico, familia, peligro_extincion) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, especie.getNombre_vulgar());
                ps.setString(2, especie.getNombre_cientifico());
                ps.setString(3, especie.getFamilia());
                ps.setBoolean(4, especie.getPeligro_extincion());
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        especie.setId_especie(rs.getInt(1));
                    }
                }
                return especie;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Especie actualizarEspecie(Especie especie) throws SQLException {
        String sql = "UPDATE especies SET nombre_vulgar = ?, nombre_cientifico = ?, familia = ?, peligro_extincion = ? WHERE id_especie = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, especie.getNombre_vulgar());
                ps.setString(2, especie.getNombre_cientifico());
                ps.setString(3, especie.getFamilia());
                ps.setBoolean(4, especie.getPeligro_extincion());
                ps.setInt(5, especie.getId_especie());
                ps.executeUpdate();
                return especie;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Especie eliminarEspecie(Especie especie) throws SQLException {
        String sql = "DELETE FROM especies WHERE id_especie = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, especie.getId_especie());
                ps.executeUpdate();
                return especie;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Especie consultarEspeciePorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM especies WHERE id_especie = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Especie especie = new Especie();
                        especie.setId_especie(rs.getInt("id_especie"));
                        especie.setNombre_vulgar(rs.getString("nombre_vulgar"));
                        especie.setNombre_cientifico(rs.getString("nombre_cientifico"));
                        especie.setFamilia(rs.getString("familia"));
                        especie.setPeligro_extincion(rs.getBoolean("peligro_extincion"));
                        return especie;
                    }
                }
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
        return null;
    }

    @Override
    public List<Especie> consultarTodasLasEspecies() throws SQLException {
        List<Especie> especies = new ArrayList<>();
        String sql = "SELECT * FROM especies";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Especie especie = new Especie();
                    especie.setId_especie(rs.getInt("id_especie"));
                    especie.setNombre_vulgar(rs.getString("nombre_vulgar"));
                    especie.setNombre_cientifico(rs.getString("nombre_cientifico"));
                    especie.setFamilia(rs.getString("familia"));
                    especie.setPeligro_extincion(rs.getBoolean("peligro_extincion"));
                    especies.add(especie);
                }
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
        return especies;
    }
}
