/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.ClienteController;
import dto.CargoDTO;
import dto.ClienteDTO;
import dto.SupermercadoDTO;

/**
 *
 * @author trian
 */
public class Test {

    public static void main(String[] args) {
        ClienteController controller = new ClienteController();
        System.err.println("------- Lista de supermercados disponibles  ----------");
        controller.obtenerSupermercados().forEach(supermercado -> {
            System.out.println("----------------------------");
            System.out.println("Id supermercado : " + supermercado.getId());
            System.out.println("nombre supermercado : " + supermercado.getNombre());
            System.out.println("Direccion supermercado : " + supermercado.getDireccion());
            System.out.println("----------------------------");
        });

        System.err.println("\n \n ------- Lista de cargos  ----------");
        controller.obtenerCargos().forEach(cargo -> {
            System.out.println("----------------------------");
            System.out.println("Cargo ID : " + cargo.getId());
            System.out.println("Cargo nombre : " + cargo.getNombre());
            System.out.println("----------------------------");
        });

        System.err.println("\n \n ------- Listar todos los clientes ----------");
        Long idCliente = null;
        controller.obtenerClientes(idCliente).forEach(cliente -> {
            System.out.println("----------------------------");
            System.out.println("Identificacion : " + cliente.getIdentificacion());
            System.out.println("Primer Nombre : " + cliente.getPrimerNombre());
            System.out.println("Segundo nombre : " + cliente.getSegundoNombre());
            System.out.println("primer apellido : " + cliente.getPrimerApellido());
            System.out.println("Segundo apellido : " + cliente.getSegundoApellido());
            System.out.println("Telefono : " + cliente.getTelefono());
            System.out.println("Correo electronico  : " + cliente.getCorreoElectronico());
            System.out.println("Cargo nombre : " + cliente.getCargo().getNombre());
            System.out.println("Supermecado nombre : " + cliente.getSupermercado().getNombre());
            System.out.println("----------------------------");
        });

        idCliente = 11603L;
        System.err.println("\n \n ------- Filtrar el cliente con IDENTIFICACION " + idCliente +"  ----------");
        controller.obtenerClientes(idCliente).forEach(cliente -> {
            System.out.println("----------------------------");
            System.out.println("Identificacion : " + cliente.getIdentificacion());
            System.out.println("Primer Nombre : " + cliente.getPrimerNombre());
            System.out.println("Segundo nombre : " + cliente.getSegundoNombre());
            System.out.println("primer apellido : " + cliente.getPrimerApellido());
            System.out.println("Segundo apellido : " + cliente.getSegundoApellido());
            System.out.println("Telefono : " + cliente.getTelefono());
            System.out.println("Correo electronico  : " + cliente.getCorreoElectronico());
            System.out.println("Cargo nombre : " + cliente.getCargo().getNombre());
            System.out.println("Supermecado nombre : " + cliente.getSupermercado().getNombre());
            System.out.println("----------------------------");
        });

        System.out.println("\n \n ------- Registro de cliente  ----------");
        ClienteDTO clienteGuardado = new ClienteDTO();
        clienteGuardado.setIdentificacion(12345L);
        clienteGuardado.setPrimerNombre("PrimerNombreTest");
        clienteGuardado.setSegundoNombre("SegundoNombreTest");
        clienteGuardado.setPrimerApellido("PrimerApellidoTest");
        clienteGuardado.setSegundoApellido("SegundoApellidoTest");
        clienteGuardado.setTelefono("3102436512");
        clienteGuardado.setCorreoElectronico("example@gmail.com");
        clienteGuardado.setCargo(new CargoDTO(1L, "Cajero"));
        clienteGuardado.setSupermercado(new SupermercadoDTO(1L, "Mi Ac Company", "Ap #494-8934 Fusce Rd."));

        String respuesta = controller.guardarCliente(clienteGuardado);
        System.out.println("Respuesta guardado del cliente : " + respuesta);

        System.out.println("\n \n ------- Eliminacion de cliente  ----------");
        respuesta = controller.eliminarCliente(12345L);
        System.out.println("Respuesta eliminacion del cliente : " + respuesta);

        System.out.println("\n \n ------- Actualizacion del cliente  ----------");
        ClienteDTO clienteActualizado = new ClienteDTO();
        clienteActualizado.setIdentificacion(11603L);
        clienteActualizado.setPrimerNombre("Jennifer");
        clienteActualizado.setSegundoNombre("S.");
        clienteActualizado.setPrimerApellido("gomez");
        clienteActualizado.setSegundoApellido("Lopez");
        clienteActualizado.setTelefono("123456789");
        clienteActualizado.setCorreoElectronico("example123456@gmail.com");
        clienteActualizado.setCargo(new CargoDTO(1L, "Cajero"));
        clienteActualizado.setSupermercado(new SupermercadoDTO(1L, "Mi Ac Company", "Ap #494-8934 Fusce Rd."));
        respuesta = controller.actualizarCliente(clienteActualizado);
        System.out.println("Respuesta actualizacion del cliente : " + respuesta);
    }
}
