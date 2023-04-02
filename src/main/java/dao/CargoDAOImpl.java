/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Conexion;
import dto.CargoDTO;
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
public class CargoDAOImpl implements CargoDAO {

    PreparedStatement st = null;
    ResultSet rs = null;
    Statement Stamen = null;

    @Override
    public List<CargoDTO> obtenerCargos() {
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexion();
        List<CargoDTO> listaCargos = new ArrayList<>();
        try {
            String sql = "SELECT ID, NOMBRE"
                    + "   FROM CARGO "
                    + "   ORDER BY ID ASC ";
            st = connection.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("ID");
                String nombre = rs.getString("NOMBRE");
                CargoDTO cargoTemp = new CargoDTO();
                cargoTemp.setId(id);
                cargoTemp.setNombre(nombre);
                listaCargos.add(cargoTemp);
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
        return listaCargos;
    }
}
