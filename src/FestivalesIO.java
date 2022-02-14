

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.LocalDate;
import java.util.HashSet;

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
        String [] parsear = lineaFestival.trim().split(":");
        String nombre = parsear[0].trim();
        String lugar = parsear[1].trim();
        LocalDate fechaInicio = LocalDate.parse(parsear[2].trim(), DateTimeFormatter.ofPattern("dd MMM yyyy"));
        int duracion = Integer.parseInt(parsear[3].trim());
        HashSet<Estilo> estilos = new HashSet<>();
        for (int i = 4; i < parsear.length; i++){
            Estilo estilo = Estilo.valueOf(parsear[i].trim().toUpperCase());
            estilos.add(estilo);
        }
        Festival festival = new Festival(nombre,lugar,fechaInicio,duracion,estilos);
        return festival;
    }
    
   
    
    
}
