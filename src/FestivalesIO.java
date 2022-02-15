

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;

/**
 * La clase contiene méodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
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
        Festival festival = new Festival(obtenerNombre(lineaFestival),obtenerLugar(lineaFestival),
                obtenerfecha(lineaFestival),obtenerDuracion(lineaFestival),obtenerEstilos(lineaFestival));
        return festival;
    }

    public static String obtenerNombre (String lineaFestival) {
        String[] n = lineaFestival.split(":");
        return n[0];
    }

    public static String obtenerLugar (String lineaFestival) {
        String[] l = lineaFestival.split(":");
        return l[1];
    }

    public static String obtenerfecha (String lineaFestival) {
        String[] l = lineaFestival.split(":");
        return l[2];
    }

    public static int obtenerDuracion (String lineaFestival) {
        String[] l = lineaFestival.split(":");
        return Integer.parseInt(l[3]);
    }

    public static HashSet<Estilo> obtenerEstilos (String lineaFestival) {
        HashSet<Estilo> estilos = new HashSet<>();
        String[] l = lineaFestival.split(":");
        for (int i = 4; i < l.length; i++) {
            estilos.add(Estilo.valueOf(l[i].trim().toUpperCase()));
        }
        return estilos;
    }




    
    
}
