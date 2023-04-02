/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import config.Conexion;
import dto.CargoDTO;
import dto.ClienteDTO;
import dto.SupermercadoDTO;
import utils.Constantes;
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
public class ClienteDAOImpl implements ClienteDAO {

    PreparedStatement st = null;
    ResultSet rs = null;
    Statement Stamen = null;

    @Override
    public List<ClienteDTO> obtenerClientes(Long idCliente) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexion();
        List<ClienteDTO> listaClientes = new ArrayList<>();
        String sql = "";
        try {
            //  si queremos filtrar un cliente en especifico por su id, en caso contrario listara todos sin ningun tipo de filtro
            if (idCliente != null) {
                sql = "SELECT CLIENTE.IDENTIFICACION AS IDENTIFICACION_CLIENTE,"
                        + "   CLIENTE.PRIMER_NOMBRE, "
                        + "   CLIENTE.SEGUNDO_NOMBRE, "
                        + "   CLIENTE.PRIMER_APELLIDO, "
                        + "   CLIENTE.SEGUNDO_APELLIDO, "
                        + "   CLIENTE.TELEFONO, "
                        + "   CLIENTE.CORREO_ELECTRONICO, "
                        + "   CARGO.ID AS ID_CARGO, "
                        + "   CARGO.NOMBRE AS NOMBRE_CARGO, "
                        + "   SUPERMERCADO.ID AS ID_SUPERMERCADO, "
                        + "   SUPERMERCADO.NOMBRE AS NOMBRE_SUPERMERCADO, "
                        + "   SUPERMERCADO.DIRECCION AS DIRECCION_SUPERMERCADO "
                        + "   FROM CLIENTE  "
                        + "   INNER JOIN CARGO "
                        + "   ON CLIENTE.CARGO_ID = CARGO.ID "
                        + "   INNER JOIN SUPERMERCADO "
                        + "   ON CLIENTE.SUPERMERCADO_ID = SUPERMERCADO.ID "
                        + "   WHERE CLIENTE.IDENTIFICACION = " + idCliente + " "
                        + "   ORDER BY CLIENTE.IDENTIFICACION ASC ";
            } else {
                sql = "SELECT CLIENTE.IDENTIFICACION AS IDENTIFICACION_CLIENTE,"
                        + "   CLIENTE.PRIMER_NOMBRE, "
                        + "   CLIENTE.SEGUNDO_NOMBRE, "
                        + "   CLIENTE.PRIMER_APELLIDO, "
                        + "   CLIENTE.SEGUNDO_APELLIDO, "
                        + "   CLIENTE.TELEFONO, "
                        + "   CLIENTE.CORREO_ELECTRONICO, "
                        + "   CARGO.ID AS ID_CARGO, "
                        + "   CARGO.NOMBRE AS NOMBRE_CARGO, "
                        + "   SUPERMERCADO.ID AS ID_SUPERMERCADO, "
                        + "   SUPERMERCADO.NOMBRE AS NOMBRE_SUPERMERCADO, "
                        + "   SUPERMERCADO.DIRECCION AS DIRECCION_SUPERMERCADO "
                        + "   FROM CLIENTE  "
                        + "   INNER JOIN CARGO "
                        + "   ON CLIENTE.CARGO_ID = CARGO.ID "
                        + "   INNER JOIN SUPERMERCADO "
                        + "   ON CLIENTE.SUPERMERCADO_ID = SUPERMERCADO.ID "
                        + "   ORDER BY CLIENTE.IDENTIFICACION ASC ";
            }

            st = connection.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Long identificacion = rs.getLong("IDENTIFICACION_CLIENTE");
                String primerNombre = rs.getString("PRIMER_NOMBRE");
                String segundoNombre = rs.getString("SEGUNDO_NOMBRE");
                String primerApellido = rs.getString("PRIMER_APELLIDO");
                String segundoApellido = rs.getString("SEGUNDO_APELLIDO");
                String telefono = rs.getString("TELEFONO");
                String correoElectronico = rs.getString("CORREO_ELECTRONICO");
                Long idCargo = rs.getLong("ID_CARGO");
                String nombreCargo = rs.getString("NOMBRE_CARGO");
                Long idSupermercado = rs.getLong("ID_SUPERMERCADO");
                String nombreSupermercado = rs.getString("NOMBRE_SUPERMERCADO");
                String direccionSupermercado = rs.getString("DIRECCION_SUPERMERCADO");
                // Se crea objeto Cargo
                CargoDTO cargoTemp = new CargoDTO();
                cargoTemp.setId(idCargo);
                cargoTemp.setNombre(nombreCargo);

                // Se crea objeto supermercado
                SupermercadoDTO supermercadoTemp = new SupermercadoDTO();
                supermercadoTemp.setId(idSupermercado);
                supermercadoTemp.setNombre(nombreSupermercado);
                supermercadoTemp.setDireccion(direccionSupermercado);

                // Se crea objeto cliente
                ClienteDTO clienteTemp = new ClienteDTO();
                clienteTemp.setIdentificacion(identificacion);
                clienteTemp.setPrimerNombre(primerNombre);
                clienteTemp.setSegundoNombre(segundoNombre);
                clienteTemp.setPrimerApellido(primerApellido);
                clienteTemp.setSegundoApellido(segundoApellido);
                clienteTemp.setTelefono(telefono);
                clienteTemp.setCorreoElectronico(correoElectronico);
                clienteTemp.setCargo(cargoTemp);
                clienteTemp.setSupermercado(supermercadoTemp);

                // Se añade a la lista el objeto 
                listaClientes.add(clienteTemp);
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
        return listaClientes;
    }

    @Override
    public String guardarCliente(ClienteDTO cliente) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexion();
        String respuesta = null;
        try {

            // Se valida si la identificacion ya existe en la BD
            String query = "SELECT COUNT(*) AS CANTIDAD from CLIENTE "
                    + "WHERE IDENTIFICACION = ?";

            st = connection.prepareStatement(query);
            st.setLong(1, cliente.getIdentificacion());
            rs = st.executeQuery();
            Long cantidad = null;
            while (rs.next()) {
                cantidad = rs.getLong("CANTIDAD");
            }

            // Si la cantidad es mayor a cero es por que existe un usuario con ese ID,
            // por tanto se retornara un mensaje notificando el error...
            if (cantidad > 0) {
                return Constantes.CLIENTE_ID_REGISTRADO;
            }

            String sql = "INSERT INTO CLIENTE("
                    + "IDENTIFICACION, " // 1
                    + "PRIMER_NOMBRE, " // 2
                    + "SEGUNDO_NOMBRE, " // 3
                    + "PRIMER_APELLIDO, " // 4
                    + "SEGUNDO_APELLIDO, " // 5
                    + "TELEFONO, " // 6
                    + "CORREO_ELECTRONICO, " // 7
                    + "CARGO_ID, " // 8
                    + "SUPERMERCADO_ID " // 9
                    + ")"
                    + " values("
                    + "?, " // 1
                    + "?, " // 2
                    + "?, " // 3
                    + "? ," // 4
                    + "?, " // 5
                    + "?, " // 6
                    + "?, " // 7
                    + "?, " // 8
                    + "? " // 9
                    + ")";
            st = connection.prepareStatement(sql);
            st.setLong(1, cliente.getIdentificacion());
            st.setString(2, cliente.getPrimerNombre());
            st.setString(3, cliente.getSegundoNombre());
            st.setString(4, cliente.getPrimerApellido());
            st.setString(5, cliente.getSegundoApellido());
            st.setString(6, cliente.getTelefono());
            st.setString(7, cliente.getCorreoElectronico());
            st.setLong(8, cliente.getCargo().getId());
            st.setLong(9, cliente.getSupermercado().getId());
            Integer res = st.executeUpdate();

            // Si el resultado es mayor a 0 es por que se registro con exito el cliente
            if (res > 0) {
                respuesta = Constantes.CLIENTE_GUARDADO_CON_EXITO;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            respuesta = Constantes.CLIENTE_NO_REGISTRADO;
        } finally {
            try {
                st.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta = Constantes.CLIENTE_NO_REGISTRADO;
            }
        }
        return respuesta;
    }

    @Override
    public String eliminarCliente(Long identificacionCliente
    ) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexion();
        String respuesta = null;
        try {
            // Se valida si la identificacion ya existe en la BD
            String query = "SELECT COUNT(*) AS CANTIDAD from CLIENTE "
                    + "WHERE IDENTIFICACION = ?";

            st = connection.prepareStatement(query);
            st.setLong(1, identificacionCliente);
            rs = st.executeQuery();
            Long cantidad = null;
            while (rs.next()) {
                cantidad = rs.getLong("CANTIDAD");
            }

            // Si no se encuentra un ID con ese cliente se devolvera el mensaje
            if (cantidad == 0) {
                respuesta = Constantes.CLIENTE_NO_ENCONTRADO;
            } else {
                String sqlDelete = "DELETE FROM CLIENTE WHERE IDENTIFICACION=?";
                st = connection.prepareStatement(sqlDelete);
                st.setLong(1, identificacionCliente);
                st.executeUpdate();
                respuesta = Constantes.CLIENTE_ELIMINADO_CON_EXITO;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            respuesta = Constantes.CLIENTE_NO_ELIMINADO;
        }
        return respuesta;
    }

    @Override
    public String actualizarCliente(ClienteDTO cliente) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConexion();
        String respuesta = null;
        try {
            String sql = "UPDATE CLIENTE "
                    + "SET PRIMER_NOMBRE = ?,  " // 1
                    + "SEGUNDO_NOMBRE = ?, " // 2
                    + "PRIMER_APELLIDO = ?, " // 3
                    + "SEGUNDO_APELLIDO = ?, " // 4
                    + "TELEFONO = ?, " // 5
                    + "CORREO_ELECTRONICO = ?, " // 6
                    + "CARGO_ID = ?, " // 7
                    + "SUPERMERCADO_ID = ? " // 8
                    + " WHERE IDENTIFICACION = ? "; // 9

            st = connection.prepareStatement(sql);
            st.setString(1, cliente.getPrimerNombre());
            st.setString(2, cliente.getSegundoNombre());
            st.setString(3, cliente.getPrimerApellido());
            st.setString(4, cliente.getSegundoApellido());
            st.setString(5, cliente.getTelefono());
            st.setString(6, cliente.getCorreoElectronico());
            st.setLong(7, cliente.getCargo().getId());
            st.setLong(8, cliente.getSupermercado().getId());
            st.setLong(9, cliente.getIdentificacion());
            Integer res = st.executeUpdate();

            // Si el resultado es mayor a 0 es por que se actualizo con exito el cliente
            if (res > 0) {
                respuesta = Constantes.CLIENTE_ACTUALIZADO_CON_EXITO;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            respuesta = Constantes.CLIENTE_NO_ACTUALIZADO;
        } finally {
            try {
                st.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                respuesta = Constantes.CLIENTE_NO_ACTUALIZADO;
            }
        }
        return respuesta;
    }

}
