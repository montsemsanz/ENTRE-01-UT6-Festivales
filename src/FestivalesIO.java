

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * La clase contiene méodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 * @author - Jiacheng Lin
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

        
        return null;
    }
    private String obtenerNombre(String lineaFestival){
        lineaFestival = lineaFestival.trim();
        String[] pala = lineaFestival.split(":");
        String[] pala2 = pala[0].split(" ");
        String nombre = "";
        for (int i = 0; i < pala2.length; i++){
            char letra = pala2[i].toUpperCase().charAt(0);
            nombre += letra + pala2[i].substring(1).toLowerCase() + " ";
        }
        return nombre;
    }

    public String obtenerLugar(String lineaFestival){
        lineaFestival = lineaFestival.trim();
        String[] pala = lineaFestival.split(":");
        pala[1] = pala[1].trim();
        String[] pala2 = pala[1].split(" ");
        String lugar = "";
        for (int i = 0; i < pala2.length; i++){
            char letra = pala2[i].toUpperCase().charAt(0);
            lugar += letra + pala2[i].substring(1).toLowerCase() + " ";
        }
        return lugar;
    }
}
