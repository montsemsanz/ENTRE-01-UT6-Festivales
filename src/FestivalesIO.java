

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
//@Author Aritz Ciriza
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
        String nombre = consigueNombre(lineaFestival);
        String lugar = consigueLugar(lineaFestival);
        LocalDate fecha = consigueFecha(lineaFestival);
        int duracion = consigueDuracion(lineaFestival);
        HashSet<Estilo> estilos = consigueEstilo(lineaFestival);
       Festival festival1 =new Festival(nombre,lugar,fecha,duracion,estilos);
        return festival1;
    }

    /**
     * Metodo auxiliar para conseguir el nombre
     */
    private static String  consigueNombre(String cadena) {
        String[] festival = cadena.trim().split(":");
        String[] aux = festival[0].split(" ");
        String nombre = "";
        nombre += aux[0].toUpperCase().charAt(0);
        nombre += aux[0].substring(1);
        nombre += aux[1].toUpperCase().charAt(0);
        nombre += aux[1].substring(1);
        return nombre;

    }

    /**
     * Metodo auxiliar para conseguir el lugar
     */
    private static String  consigueLugar(String cadena) {
        String[] festival = cadena.trim().split(":");
        String lugar = festival[1].toUpperCase();
        return lugar;

    }

    /**
     * Metodo auxiliar para conseguir la fecha
     */
    private static LocalDate  consigueFecha(String cadena) {
        String[] festival = cadena.trim().split(":");
        LocalDate fecha = LocalDate.parse(festival[2]);
        return fecha;

    }

    /**
     * Metodo auxiliar para conseguir la duracion
     */
    private static int  consigueDuracion(String cadena) {
        String[] festival = cadena.trim().split(":");
        int duracion = Integer.parseInt(festival[3]);
        return duracion;

    }

    /**
     * Metodo auxiliar para conseguir los estilos
     */
    private static HashSet<Estilo>  consigueEstilo(String cadena) {
        String[] festival = cadena.trim().split(":");
       HashSet<Estilo>estilos = new HashSet<>();

       Estilo estilo1 = Estilo.valueOf(festival[4]);
       Estilo estilo2 = Estilo.valueOf(festival[5]);
       Estilo estilo3 = Estilo.valueOf(festival[6]);
       estilos.add(estilo1);
        estilos.add(estilo2);
        estilos.add(estilo3);
        return estilos;

    }



    
    
}
