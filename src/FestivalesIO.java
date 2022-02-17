

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * La clase contiene méodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 * @author David Sena
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
    String[] datos = lineaFestival.trim().split(":");// divide la linea de datos


    String[] primDato = datos[0].trim().split("[.\\s]+");//divide el primer dato para conseguir el nombre
        String nombre = "";
    for(int i = 0; i < primDato.length-1; i++){
        nombre += (primDato[i].substring(0,1).toUpperCase() + primDato[i].substring(1).toLowerCase()) + " " ;
    }

    String lugar = datos[1].toUpperCase(); //datos del lugar

    String [] fechadato = datos[0].trim().split("-") ; // divide ls linea de datos de la fecha y la convierte en un tipo fecha
    LocalDate fecha= LocalDate.of(Integer.parseInt(fechadato[2]),Integer.parseInt(fechadato[1]),Integer.parseInt(fechadato[0]));

    HashSet<Estilo> estiloDato = new HashSet<>();
    Estilo[] estilos = Estilo.values();
    for (int i = 4; i< datos.length-1; i++) {
        for (Estilo estiloBu : estilos) {
            if (datos[i].equalsIgnoreCase(String.valueOf(estiloBu))) {
                estiloDato.add(estiloBu);
            }
        }
    }
        return new Festival(nombre, lugar,fecha,Integer.parseInt(datos[3]),estiloDato);
    }

}
