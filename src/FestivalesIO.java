

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * La clase contiene méodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 * @ author Pablo Mosquera
 */
public class FestivalesIO {

    
    public static void cargarFestivales(AgendaFestivales agenda) {
        Scanner sc = null;
        try {
            sc = new Scanner(FestivalesIO.class.
                    getResourceAsStream("/festivales.csv"));
            while (sc.hasNextLine()) {
                String lineaFestival = sc.nextLine();
                Festival festival = parsearLinea(lineaFestival);
                agenda.addFestival(festival);
                
            }
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        
        
    }

    /**
     * se parsea la línea extrayendo sus datos y creando y
     * devolviendo un objeto Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {


        
        return null;
    }


    /**
     * Obtener el nombre
     */
    private  String nombreFestival(String lineaFestival) {

        String[] nom = lineaFestival.split(":");

        return nom[0];
    }

    /**
     * Obtener la fecha
     */
    private  String nombreLugar(String lineaFestival) {

        String[] lugar = lineaFestival.split(":");

        return lugar[1];
    }

    /**
     * Obtener la fecha
     */
    private  String getFecha(String lineaFestival) {

        String[] fecha = lineaFestival.split(":");

        return fecha[2];
    }

    /**
     * Obtener estilos
     */
    private  String getEstilos(String lineaFestival) {

        String[] estilo = lineaFestival.split(":");

        String cadena = "";

        for (int i = 3; i < estilo.length; i++) {
            cadena += "estilo[i]";
        }
        return cadena;
    }
}
