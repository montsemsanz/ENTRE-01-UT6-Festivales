

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
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
        String[] linea = lineaFestival.split(":");
        String nombre = "";
        String[] palabras = linea[0].trim().split(" ");
        for (String palabra : palabras) {
            nombre += palabra.substring(0,1).toUpperCase() + palabra.substring(1) + " ";
        }
        nombre = nombre.trim();
        String lugar = linea[1].trim().toUpperCase();
        Locale spanishLocale = new Locale("es", "ES");
        DateTimeFormatter formatter  = DateTimeFormatter.ofPattern("dd-MM-yyyy", spanishLocale);
        LocalDate fecha = LocalDate.parse(linea[2].trim(), formatter);
        int duracion = Integer.parseInt(linea[3].trim());
        HashSet<Estilo> estilos = new HashSet<>();
        for (int i = 4; i < linea.length; i++) {
            estilos.add(Estilo.valueOf(linea[i].trim().toUpperCase()));
        }
        return new Festival(nombre,lugar, fecha, duracion, estilos);
    }
    
   
    
    
}
