/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CargoDAO;
import dao.CargoDAOImpl;
import dao.ClienteDAO;
import dao.ClienteDAOImpl;
import dao.SupermercadoDAO;
import dao.SupermercadoDAOImpl;
import dto.CargoDTO;
import dto.ClienteDTO;
import dto.SupermercadoDTO;
import utils.Constantes;
import utils.ListUtils;
import java.util.List;

/**
 *
 * @author trian
 */
public class ClienteController {

    public List<ClienteDTO> obtenerClientes(Long idCliente) {
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        List<ClienteDTO> listaClientes = clienteDAO.obtenerClientes(idCliente);
        return listaClientes;
    }

    public String guardarCliente(ClienteDTO cliente) {
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        return clienteDAO.guardarCliente(cliente);
    }

    public String actualizarCliente(ClienteDTO cliente) {
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        return clienteDAO.actualizarCliente(cliente);
    }

    public String eliminarCliente(Long identificacionCliente) {
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        return clienteDAO.eliminarCliente(identificacionCliente);
    }

    public List<CargoDTO> obtenerCargos() {
        CargoDAO cargoDAO = new CargoDAOImpl();
        List<CargoDTO> listaCargos = cargoDAO.obtenerCargos();
        // se valida si la lista llega vacia
        if (ListUtils.validarLista(listaCargos)) {
            System.err.println(Constantes.CARGOS_NO_ENCONTRADOS);
        }
        return listaCargos;
    }

    public List<SupermercadoDTO> obtenerSupermercados() {
        SupermercadoDAO supermercadoDAO = new SupermercadoDAOImpl();
        List<SupermercadoDTO> listaSupermercados = supermercadoDAO.obtenerSupermercados();
        // se valida si la lista llega vacia
        if (ListUtils.validarLista(listaSupermercados)) {
            System.err.println(Constantes.SUPERMERCADOS_NO_ENCONTRADOS);
        }
        return listaSupermercados;
    }
}
