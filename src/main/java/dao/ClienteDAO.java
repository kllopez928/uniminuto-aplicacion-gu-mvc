/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.ClienteDTO;
import java.util.List;

/**
 *
 * @author trian
 */
public interface ClienteDAO {
    
   List<ClienteDTO> obtenerClientes(Long idCliente);
   
   String guardarCliente(ClienteDTO cliente);
           
   String actualizarCliente(ClienteDTO cliente);
   
   String eliminarCliente(Long identificacionCliente);
}
