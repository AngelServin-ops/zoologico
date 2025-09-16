/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import interfaces.IZoologicoDAO;
import conexion.ConexionBD;
import entidades.Zoologico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class ZoologicoDAO implements IZoologicoDAO {

    private final ConexionBD conexion;

    public ZoologicoDAO() {
        this.conexion = new ConexionBD();
    }

    @Override
    public Zoologico agregarZoologico(Zoologico zoologico) throws SQLException {
        String sql = "INSERT INTO zoologicos (nombre, ciudad, pais, fecha_inauguracion) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, zoologico.getNombre());
                ps.setString(2, zoologico.getCiudad());
                ps.setString(3, zoologico.getPais());
                ps.setDate(4, new java.sql.Date(zoologico.getFecha_inauguracion().getTime()));
                ps.executeUpdate();
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        zoologico.setId_zoologico(rs.getInt(1));
                    }
                }
                return zoologico;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Zoologico actualizarZoologico(Zoologico zoologico) throws SQLException {
        String sql = "UPDATE zoologicos SET nombre = ?, ciudad = ?, pais = ?, fecha_inauguracion = ? WHERE id_zoologico = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, zoologico.getNombre());
                ps.setString(2, zoologico.getCiudad());
                ps.setString(3, zoologico.getPais());
                ps.setDate(4, new java.sql.Date(zoologico.getFecha_inauguracion().getTime()));
                ps.setInt(5, zoologico.getId_zoologico());
                ps.executeUpdate();
                return zoologico;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Zoologico eliminarZoologico(Zoologico zoologico) throws SQLException {
        String sql = "DELETE FROM zoologicos WHERE id_zoologico = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, zoologico.getId_zoologico());
                ps.executeUpdate();
                return zoologico;
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
    }

    @Override
    public Zoologico consultarZoologicoPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM zoologicos WHERE id_zoologico = ?";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, id);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        Zoologico zoologico = new Zoologico();
                        zoologico.setId_zoologico(rs.getInt("id_zoologico"));
                        zoologico.setNombre(rs.getString("nombre"));
                        zoologico.setCiudad(rs.getString("ciudad"));
                        zoologico.setPais(rs.getString("pais"));
                        java.sql.Date sqlDate = rs.getDate("fecha_inauguracion");
                        if (sqlDate != null) {
                            zoologico.setFecha_inauguracion(new java.util.Date(sqlDate.getTime()));
                        }
                        return zoologico;
                    }
                }
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
        return null;
    }

    @Override
    public List<Zoologico> consultarTodosLosZoologicos() throws SQLException {
        List<Zoologico> zoologicos = new ArrayList<>();
        String sql = "SELECT * FROM zoologicos";
        Connection conn = null;
        try {
            conn = conexion.crearConexion();
            try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Zoologico zoologico = new Zoologico();
                    zoologico.setId_zoologico(rs.getInt("id_zoologico"));
                    zoologico.setNombre(rs.getString("nombre"));
                    zoologico.setCiudad(rs.getString("ciudad"));
                    zoologico.setPais(rs.getString("pais"));
                    java.sql.Date sqlDate = rs.getDate("fecha_inauguracion");
                    if (sqlDate != null) {
                        zoologico.setFecha_inauguracion(new java.util.Date(sqlDate.getTime()));
                    }
                    zoologicos.add(zoologico);
                }
            }
        } finally {
            conexion.cerrarConexion(conn);
        }
        return zoologicos;
    }
}
