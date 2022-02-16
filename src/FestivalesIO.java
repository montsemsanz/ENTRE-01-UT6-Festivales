
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * La clase contiene méodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 * @author AIMAR
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
     *
     * @param lineaFestival los datos de un festival
     * @return el festival creado
     */
    public static Festival parsearLinea(String lineaFestival) {
        // Creamos un Array eliminando los caracteres blancos iniciales y finales de la cadena y añadiendo ":"
        String [] parseado = lineaFestival.trim().split(":");
        // Creamos variable para el nombre y se llama al metodo privado que hemos creado
        String izena = capitalizado(parseado[0]);
        // Creamos variable para el lugar del festival eliminando los caracteres blancos iniciales y finales de la cadena y pasandolo a Mayusculas.
        String tokia = parseado[1].trim().toUpperCase();
        // Fecha de inicio de tipo LocalDate , sin espacios y con el formato "DD-MM-YYYY"
        LocalDate hasieraData = LocalDate.parse(parseado[2].trim(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        // Variable para parsar el String de duracion a un int eliminando los caracteres blancos iniciales y finales de la cadena
        int iraupena = Integer.parseInt(parseado[3].trim());
        // Creamos una tabla Hash de la clase Estilo
        HashSet<Estilo> erak = new HashSet<>();
        // For para recorrer lo que queda del Array que hemos creado. Empenzamos desde la posicion 4 porque es donde empiezan los estilos
        for (int i = 4; i < parseado.length; i++){
            // Creamos una Variable de tipo Estilo que  convierte diferentes tipos de valores en cadena eliminando los caracteres blancos iniciales y finales de la cadena y en Mayusculas
            Estilo era = Estilo.valueOf(parseado[i].trim().toUpperCase());
            // Añadimos a la tabla HashSet la variable que hemos creado en este mismo for
            erak.add(era);
        }
        Festival f = new Festival(izena,tokia,hasieraData,iraupena,erak);
        return f;
    }

    /**
     *
     */
    private static String capitalizado(String str) {
        // elimina los caracteres blancos iniciales y finales de la cadena
        str = str.trim();
        String str1 = "";
        // Creamos un array para dividir la cadena cada vez que encuentre un espacio
        String[] palabras = str.split(" ");
        // For mejorado para recorrer el Array de arriba
        for (String cadena:palabras) {
            // Añadir a la cadena vacia str1 lo que ha cogido del for mejorado y devuelve en Mayusculas una cadena independiente desde la posicion 0 hasta la posicion 1 que esta ultima no entra
            str1 += cadena.substring(0,1).toUpperCase() +
                    // Desde la posicion 1 una cadena independiente que llega hasta el final de la cadena
                    cadena.substring(1) + " ";
        }
        // Devuelve la cadena str1
        return str1;
    }
   
    
    
}
