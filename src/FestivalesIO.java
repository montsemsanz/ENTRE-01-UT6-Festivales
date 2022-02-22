

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * La clase contiene méodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 * @autor - Christhoper Pinday Delgado
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
        String estilosStr = "";

        lineaFestival = lineaFestival.trim();
        String[] datos = lineaFestival.split(":");

        //nombre...
        String nombre = datos[0].trim();
        String[] arrayNombre = nombre.split("[ ]+");
        nombre = "";
        for (int i = 0; i < arrayNombre.length; i++) {
            String primeraLetra = String.valueOf(arrayNombre[i].charAt(0)).toUpperCase();
            String sigLetras = arrayNombre[0].substring(1, arrayNombre[0].length());

            nombre += primeraLetra + sigLetras + " ";
        }
        nombre = nombre.trim();

        //lugar
        String lugar = datos[1].trim();
        lugar = primeraLetraMayuscula(lugar);

        //fecha
        String fechaStr = datos[2].trim();
        LocalDate fecha = LocalDate.parse(fechaStr);

        //duracion
        String duracionStr = datos[3];
        int duracion = Integer.valueOf(duracionStr).intValue();



        //estilos
        String[] estilos = new String[datos.length - 4];
        System.arraycopy(datos, 4, estilos, 0, datos.length - 4);

        //quitar espacios a cada estilo
        for (int i = 0; i < estilos.length; i++) {
            estilos[i] = estilos[i].trim();
        }

        //añadirlo al HashSet
        HashSet<Estilo> est = new HashSet<>();
        for (String estilo: estilos) {
            Estilo e = Estilo.valueOf(estilo);
            est.add(e);
        }
        Festival fest = new Festival(nombre, lugar, fecha, duracion, est);
        return fest;
    }

    /**
     *
     */
    public static String primeraLetraMayuscula(String palabra) {
        char[] array = palabra.toCharArray();
        array[0] = Character.toUpperCase(array[0]);
        return new String(array);
    }
    
    
}
