

import java.time.LocalDate;
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
        String del = "[.\\s\\-]+";
        String[] pala2 = pala[0].split(del);
        String nombre = "";
        for (int i = 0; i < pala2.length; i++){
            char letra = pala2[i].toUpperCase().charAt(0);
            nombre += letra + pala2[i].substring(1).toLowerCase();
            if(i + 1 < pala2.length){
                nombre += " ";
            }
        }
        return nombre;
    }

    private String obtenerLugar(String lineaFestival){
        lineaFestival = lineaFestival.trim();
        String[] pala = lineaFestival.split(":");
        pala[1] = pala[1].trim();
        String del = "[.\\s\\-]+";
        String[] pala2 = pala[1].split(del);
        String lugar = "";
        for (int i = 0; i < pala2.length; i++){
            lugar += pala2[i].toUpperCase();
            if(i + 1 < pala2.length){
                lugar += " ";
            }
        }
        return lugar;
    }

    private LocalDate obtenerFecha(String lineaFestival){
        lineaFestival = lineaFestival.trim();
        String[] pala = lineaFestival.split(":");
        pala[2] = pala[2].trim();
        String del = "[.\\s\\-]+";
        String[] pala2 = pala[2].split(del);
        int dia = Integer.parseInt(pala2[0]);
        int mes = Integer.parseInt(pala2[1]);
        int year = Integer.parseInt(pala2[2]);
        LocalDate fecha = LocalDate.of(dia, mes, year);
        return fecha;
    }

    private int obtenerDuracion(String lineaFestival){
        lineaFestival = lineaFestival.trim();
        String[] pala = lineaFestival.split(":");
        pala[3] = pala[3].trim();
        int duracion = Integer.parseInt(pala[3]);
        return duracion;
    }

    private HashSet<Estilo> obtenerEstilos(String lineaFestival){
        HashSet<Estilo> estilos = new HashSet<>();
        lineaFestival = lineaFestival.trim();
        String[] pala = lineaFestival.split(":");
        for (int i = 4; i < pala.length; i++){
            Estilo[] styles = Estilo.values();
            for (Estilo es:styles) {
                if(es.equals(pala[i])){
                    estilos.add(es);
                }
            }
        }
        return estilos;
    }
}
