/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.List;

/**
 *
 * @author trian
 */
public class ListUtils {

    // metodo que se encarga de validar si una lista es nula
    public static Boolean validarLista(List<?> lista) {
        Boolean bool = false;
        if (lista.isEmpty()) {
            bool = true;
        }
        return bool;
    }
}
