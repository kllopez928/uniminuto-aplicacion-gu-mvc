/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Conexion;
import dto.SupermercadoDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author trian
 */
public class SupermercadoDAOImpl implements SupermercadoDAO {

    PreparedStatement st = null;
    ResultSet rs = null;
    Statement Stamen = null;
    
    @Override
    public List<SupermercadoDTO> obtenerSupermercados() {
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexion();
        List<SupermercadoDTO> listaSupermercados = new ArrayList<>();
        try {
            String sql = "SELECT ID, NOMBRE, DIRECCION "
                    + "   FROM SUPERMERCADO "
                    + "   ORDER BY ID ASC ";
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("ID");
                String nombre = rs.getString("NOMBRE");
                String direccion = rs.getString("DIRECCION");
                
                SupermercadoDTO supermercadoTemp = new SupermercadoDTO();
                supermercadoTemp.setId(id);
                supermercadoTemp.setNombre(nombre);
                supermercadoTemp.setDireccion(direccion);
                listaSupermercados.add(supermercadoTemp);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                st.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return listaSupermercados;
    }
}
