

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        Festival festival = new Festival(getNombre(lineaFestival),getLugar(lineaFestival),getFecha(lineaFestival)
        ,getDuracion(lineaFestival),getEstilo(lineaFestival));


        return null;
    }
    public static String getNombre(String lineaFestival){
        String [] nombre =  lineaFestival.split(":");
        return nombre[0].trim();
    }

    public  static String getLugar(String lineaFestival){
        String [] lugar = lineaFestival.split(":");
        return lugar[1].trim();
    }

    public static LocalDate getFecha(String lineaFestival){
        String [] fecha = lineaFestival.split(":");
         String s = fecha[2].trim();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd-MM-yyyy");
         LocalDate fechaInicio = (LocalDate) f.parse(s);
         return fechaInicio;
    }

    public static int getDuracion(String lineaFestival){
        String [] duracion = lineaFestival.split(":");
        int d = Integer.parseInt(duracion[3].trim()) ;
        return d;
    }

    public static HashSet <Estilo> getEstilo (String lineaFestival){
        HashSet <Estilo> estilo = new HashSet<>();
        String [] e = lineaFestival.split(":");
        for (int i = 4; i < e.length ; i++) {
            estilo.add(Estilo.valueOf(e[i].trim().toUpperCase()));
        }
    }
    
}
