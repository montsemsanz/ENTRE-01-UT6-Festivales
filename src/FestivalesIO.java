

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
        String [] nombres = lineaFestival.trim().split(":");
        Festival festival = new Festival();
        return festival;
    }


    /**
     * se parsea la línea extrayendo sus datos y creando y
     * devolviendo un objeto Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    private  String nombre(String lineaFestival) {
        String [] nombres = lineaFestival.trim().split(":");
        String nombrefesti = nombres[0].trim();
        char primer = nombrefesti.toUpperCase().charAt(0);
        int pos = nombrefesti.lastIndexOf(" ");
        char segun = nombrefesti.toUpperCase().charAt(pos + 1);
        String nombre = primer + nombrefesti.substring(1,pos) + " " +segun + nombrefesti.substring(pos + 2,nombrefesti.length());
        return nombre;
    }



}
