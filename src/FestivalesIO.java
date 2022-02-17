

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

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

    public static String[] arry (String lineaFestival){
        String[] arr = lineaFestival.trim().split(":");
        return arr;
    }

    public static Festival parsearLinea(String lineaFestival) {
        String[] arr = lineaFestival.trim().split(":");

        // Capitalizacion de nombres
        String[] capn = arr[0].split(" ");
        String n = "";
            for (String pal: capn){
                String cap = pal.substring(0, 1).toUpperCase() + pal.substring(1);
                n += cap + " ";
            }

        String nombre = n;

        // Lugar
        String lugar = arr[1].trim().toUpperCase();

        // Fecha
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaInicio = LocalDate.parse(arr[2].trim(), formatter);
        int duracion = Integer.parseInt(arr[3].trim());

        //Conjunto de estilos
        HashSet<Estilo> e = new HashSet<>();
        Estilo estilos[] = Estilo.values();
        for (int i = 4; i < arr.length; i++){
            for (Estilo estilo: estilos){
                if (estilo.toString().equalsIgnoreCase(arr[i].trim())){
                    e.add(estilo);
                }
            }
        }

        // Creacion de objeto Festival
        Festival fest = new Festival(nombre, lugar, fechaInicio, duracion, e);

        return fest;
    }




}

