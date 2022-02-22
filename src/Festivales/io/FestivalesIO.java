package Festivales.io;

import Festivales.modelo.AgendaFestivales;
import Festivales.modelo.Estilo;
import Festivales.modelo.Festival;

import java.time.LocalDate;
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
     * devolviendo un objeto Festivales.modelo.Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {

        String nombre = nombreFestival(lineaFestival);

        String lugar = nombreLugar(lineaFestival);

        int duración = getDuracion(lineaFestival);

        LocalDate fecha = getFecha(lineaFestival);

        HashSet<Estilo> estilos = getEstilos(lineaFestival);


        Festival festival = new Festival(nombre, lugar, fecha, duración, estilos);
        
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
    private static LocalDate getFecha(String lineaFestival) {

        String[] fecha = lineaFestival.split(":");

        String date = fecha[2];

        String[] separada = fecha[2].split("-");

        LocalDate newFecha = LocalDate.of(Integer.parseInt(separada[0]), Integer.parseInt(separada[1]),
                Integer.parseInt(separada[2]));

        return newFecha;
    }

    /**
     * Obtener estilos
     */
    private static HashSet<Estilo> getEstilos(String lineaFestival) {

        HashSet<Estilo> estilo = new HashSet<>();

        Estilo[] est = Estilo.values();

         String[] estilos = lineaFestival.split(":");

        int j= 0;

        for (int i = 4; i < estilos.length; i++) {
            if(est[j].equals(estilos[i])) {
                estilo.add(est[i]);
                j++;
            }
        }



        return estilo;
    }
}
