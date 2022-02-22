package festivales.io;

import festivales.modelo.AgendaFestivales;
import festivales.modelo.Festival;

import java.time.LocalDate;
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
     * devolviendo un objeto festivales.modelo.Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {
        String palabras[] = lineaFestival.split(":");
       Festival festival = new Festival(getNombreFestival(lineaFestival),getLugar(lineaFestival),getFecha(lineaFestival),getDuracion(lineaFestival),getEstilos(lineaFestival));
        
        return festival;
    }
    public static String getNombreFestival(String lineaFestival) {
        String palabras[] = lineaFestival.split(":");
        String nombre = palabras[0];
        return nombre;
    }
    public static String getLugar(String lineaFestival) {
        String palabras[] = lineaFestival.split(":");
        String lugar = palabras[1];
        return lugar;

    }
    public static LocalDate getFecha(String lineaFestival) {
        String palabras[] = lineaFestival.split(":");
        String fecha = palabras[2];
        LocalDate date = LocalDate.parse(fecha);
        return date;

    }
    public static int getDuracion(String lineaFestival) {
        String palabras[] = lineaFestival.split(":");
        int duracion = Integer.parseInt(palabras[3]);
        return duracion;

    }
    public static HashSet getEstilos(String lineaFestival) {
        String palabras[] = lineaFestival.split(":");
        HashSet<String> estilos = new HashSet<String>();
        for(int i = 4; i < palabras.length;i++ ){
             estilos.add(palabras[i]);
        }
        return estilos;

    }
    
   
    
    
}
