

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
    public static Festival parsearLinea(String lineaFestival) {
        String[] arr = lineaFestival.trim().split(":");

        String nombre = arr[0];
        String lugar = arr[1];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicio = LocalDate.parse(arr[2], formatter);
        int duracion = Integer.parseInt(arr[3]);

        HashSet<Estilo> e = new HashSet<>();
        Estilo estilos[] = Estilo.values();
        for (int i = 4; i < arr.length; i++){
            for (Estilo estilo: estilos){
                if (estilo.toString().equalsIgnoreCase(arr[i])){
                    e.add(estilo);
                }
            }
        }

        Festival fest = new Festival(nombre, lugar, fechaInicio, duracion, e);

        return fest;
    }




}

