/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author trian
 */
public class StringUtils {
    // metodo que se encarga de validar si un string esta vacio

    public static Boolean validarString(String cadenaTexto) {
        Boolean bool = false;
        if (cadenaTexto.isEmpty()) {
            bool = true;
        }
        return bool;
    }

    public static boolean validarNumeroNoContengaLetras(String cadena) {
	try {
		Integer.parseInt(cadena);
		return true;
	} catch (NumberFormatException nfe){
		return false;
	}
    }

    public static boolean validarEmail(String email) {
        Pattern pat = null;
        Matcher mat = null;
        pat = Pattern.compile("^([0-9a-zA-Z]([_.w]*[0-9a-zA-Z])*@([0-9a-zA-Z][-w]*[0-9a-zA-Z].)+([a-zA-Z]{2,9}.)+[a-zA-Z]{2,3})$");
        mat = pat.matcher(email);
        if (mat.find()) {//si es falso
            return true;
        } else {
            return false;
        }
    }
}
