

import java.util.HashSet;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * @author Iñigo Camarero
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
        String [] nombres = lineaFestival.trim().split(":");
        Festival festival = new Festival();
        return festival;
    }


    /**
     * devuelve nombre
     */
    private static String nombre(String lineaFestival) {
        String [] nombres = lineaFestival.trim().split(":");
        String nombrefesti = nombres[0].trim();
        char primer = nombrefesti.toUpperCase().charAt(0);
        int pos = nombrefesti.lastIndexOf(" ");
        char segun = nombrefesti.toUpperCase().charAt(pos + 1);
        String nombre = primer + nombrefesti.substring(1,pos) + " " +segun + nombrefesti.substring(pos + 2,nombrefesti.length());
        return nombre;
    }

    /**
     * devuelve lugar
     */
    private static String lugar(String lineaFestival) {
        String [] nombres = lineaFestival.trim().split(":");
        String nombrefesti = nombres[1].trim();
        String lugar = nombrefesti.toUpperCase();
        return lugar;
    }

    /**
     * devuelve duracion
     */
    private static int duracion(String lineaFestival) {
        String [] nombres = lineaFestival.trim().split(":");
        String nombrefesti = nombres[3].trim();
        int duracion = Integer.valueOf(nombrefesti);
        return duracion;
    }

    /**
     * devuelve fecha
     */
    private static LocalDate fecha(String lineaFestival) {
        String [] nombres = lineaFestival.trim().split(":");
        String [] fechas =  nombres[2].trim().split("-");
        LocalDate fecha = LocalDate.of(Integer.parseInt(fechas[0]),Integer.parseInt(fechas[1]),Integer.parseInt(fechas[2]));
        return fecha;
    }


    /**
     * devuelve de estilos
     */
    private static HashSet estilo(String lineaFestival) {
        HashSet <Estilo> valores = new HashSet<>();

        String [] nombres = lineaFestival.trim().split(":");
        for (int i = 4; i < nombres.length;i++){
            if (nombres[i].equalsIgnoreCase() ){

            }
             valores.add(nombres[i]);
        }
        String duracion = nombrefesti  + "días";
        return duracion;
    }
}