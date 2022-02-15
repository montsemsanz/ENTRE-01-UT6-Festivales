

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
     *
     */
    private String nombreFest(String nombre) {
        String[] nombres = nombre.trim().split("[.//s]+");
        String nombreParse = "";
        for (int i = 0; i < nombres.length; i++) {
            char[] letra = nombres[i].toCharArray();
            letra[0] = Character.toUpperCase(letra[0]);
            nombres[i] = letra.toString();
            nombreParse += nombres[i] + "\s";
        }
        nombreParse.trim();
        return nombreParse;
    }


    /**
     * se parsea la línea extrayendo sus datos y creando y
     * devolviendo un objeto Festival
     *
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {
        String[] parse = lineaFestival.trim().split("[:]+");
        for (int i = 0; i < parse.length; i++) {
            parse[i] = parse[i].trim();
        }
        String[] saveFecha = parse[2].split("-");
        LocalDate fecha = LocalDate.of(Integer.parseInt(saveFecha[2]), Integer.parseInt(saveFecha[1]), Integer.parseInt(saveFecha[0]));
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd MM yyyy");


        return null;
    }


}
