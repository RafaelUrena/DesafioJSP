/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 *
 * @author rafa
 */
public class Bitacora {
     private static String nomFichero = "Bitacora.txt";

    public static void escribeLinea(String linea) {

        FileWriter fs = null;
        Calendar c = Calendar.getInstance();
        
        
        linea = c.getTime() + "|" + c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR) + "|" + linea;
        
        try {
            fs = new FileWriter(Bitacora.nomFichero,true);
            fs.write(linea+"\r\n");
            fs.close();
        } catch (IOException ex) {
            System.err.println("Error al escribir el fichero bitácora");
        }
    }
}
