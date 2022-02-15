

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
            if(i < pala2.length){
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
            if(i < pala2.length){
                lugar += " ";
            }
        }
        return lugar;
    }
    private String obtenerFecha(String lineaFestival){
        lineaFestival = lineaFestival.trim();
        String[] pala = lineaFestival.split(":");
        pala[1] = pala[2].trim();
        String del = "[.\\s\\-]+";
        String[] pala2 = pala[1].split(del);
        String fecha = "";
        for (int i = 0; i < pala2.length; i++){
            if(i == 0){
                fecha += pala2[i] + " de ";
            }
            else if(i == 1){
                switch (pala2[1]){
                    case "01": fecha += Mes.ENERO;
                        break;
                    case "02": fecha += Mes.FEBRERO;
                        break;
                    case "03": fecha += Mes.MARZO;
                        break;
                    case "04": fecha += Mes.ABRIL;
                        break;
                    case "05": fecha += Mes.MAYO;
                        break;
                    case "06": fecha += Mes.JUNIO;
                        break;
                    case "07": fecha += Mes.JULIO;
                        break;
                    case "08": fecha += Mes.AGOSTO;
                        break;
                    case "09": fecha += Mes.SEPTIEMBRE;
                        break;
                    case "10": fecha += Mes.OCTUBRE;
                        break;
                    case "11": fecha += Mes.NOVIEMBRE;
                        break;
                    case "12": fecha += Mes.DICIEMBRE;
                        break;
                }
            }
            else{
                fecha += " " + pala2[2];
            }
        }
        return fecha;
    }
}
