/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CargoDTO;
import java.util.List;

/**
 *
 * @author trian
 */
public interface CargoDAO {
    List<CargoDTO> obtenerCargos();
}
