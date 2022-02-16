

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
        String nombre = capitalizar(parsear[0]);
        String lugar = parsear[1].trim().toUpperCase();
        LocalDate fechaInicio = LocalDate.parse(parsear[2].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        int duracion = Integer.parseInt(parsear[3].trim());
        HashSet<Estilo> estilos = new HashSet<>();
        for (int i = 4; i < parsear.length; i++){
            Estilo estilo = Estilo.valueOf(parsear[i].trim().toUpperCase());
            estilos.add(estilo);
        }
        Festival festival = new Festival(nombre,lugar,fechaInicio,duracion,estilos);
        return festival;
    }

    /**
     * Se capitaliza una cadena para que esta tenga la primera letra de cada palabra en mayúscula
     * @param cadena la cadena que se quiere capitalizar
     * @return El nombre capitalizado
     */
    public static String capitalizar(String cadena) {
        cadena = cadena.trim();
        String str = "";
        String[] palabras = cadena.split(" ");
        for (String palabra:palabras) {
            str += palabra.substring(0,1).toUpperCase() + palabra.substring(1) + " ";
        }
        return str;
    }
    
    
}
