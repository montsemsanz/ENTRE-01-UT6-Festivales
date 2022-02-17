

import java.time.LocalDate;
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

        String nombre = nombreFestival(lineaFestival);

        String lugar = nombreLugar(lineaFestival);

        int duración =

        LocalDate fecha = ;

        HashSet<Estilo> estilos = ;


        Festival festival = new Festival(nombre, lugar, duración, fecha, estilos);
        
        return festival;
    }


    /**
     * Obtener el nombre
     */
    private static String nombreFestival(String lineaFestival) {

        String[] nom = lineaFestival.split(":");

        return nom[0];
    }

    /**
     * Obtener la fecha
     */
    private static String nombreLugar(String lineaFestival) {

        String[] lugar = lineaFestival.split(":");

        return lugar[1];
    }

    /**
     * Obtener la duración
     */
    private static int getDuracion(String lineaFestival) {

        String[] duracion = lineaFestival.split(":");

        return Integer.parseInt(duracion[2]);
    }


    /**
     * Obtener la fecha
     */
    private static String getFecha(String lineaFestival) {

        String[] fecha = lineaFestival.split(":");

        return fecha[2];
    }

    /**
     * Obtener estilos
     */
    private static String getEstilos(String lineaFestival) {

        String[] estilo = lineaFestival.split(":");

        String cadena = "";

        for (int i = 3; i < estilo.length; i++) {
            cadena += "estilo[i]";
        }
        return cadena;
    }
}
