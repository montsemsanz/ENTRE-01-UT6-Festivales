

import java.time.LocalDate;
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
     * se parsea la línea extrayendo sus datos y creando y
     * devolviendo un objeto Festival
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {

       String[] texto = lineaFestival.trim().split(":");
       String nombre = texto[0];
       String lugar = texto[1];

       String[] fecha = texto[2].trim().split("-");
       LocalDate dia = LocalDate.of(Integer.parseInt(fecha[2]),Integer.parseInt(fecha[1]),Integer.parseInt(fecha[0]));

       Integer duracion = Integer.parseInt(texto[3].trim());

       HashSet<Estilo> estilos = new HashSet<>();
       Estilo[] estilo = Estilo.values();
       for(int i = 4; i <= texto.length -1 ; i++){

           estilos.add(Estilo.valueOf(texto[i].trim().toUpperCase()));
       }
       Festival festival = new Festival(nombre,lugar.toUpperCase(),dia,duracion,estilos);
        return festival;
    }
    

    
    
}
