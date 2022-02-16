import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

/**
 * La clase contiene métodos estáticos que permiten
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
        }
        finally {
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
      HashSet estilos = new HashSet<Estilo>();
       String[] datos = new String[lineaFestival.split(":").length];
       String linea = lineaFestival.trim();
       datos = linea.split(":");

       String nombre = datos[0].trim();
       String lugar = datos[1].trim().toUpperCase();
       String fecha = datos[2].trim();
       String[] fechas = new String[fecha.split("-").length];
       fechas = fecha.split("-");
       LocalDate fecha2 = getFecha(fecha);


       int duracion = Integer.parseInt(datos[3].trim());
        for (int i = 4; i < datos.length; i++) {
            estilos.add(datos[i].toUpperCase());
        }

        System.out.println(Arrays.toString(datos));
        System.out.println(linea);
        System.out.println(nombre);
        System.out.println(lugar);
        System.out.println(fecha2);
        System.out.println(duracion);
        System.out.println(estilos);

        Festival festival = new Festival(nombre, lugar, fecha2, duracion, estilos);
        return festival;
    }

    public static LocalDate getFecha(String fecha) {
        String[] fechas = new String[fecha.split("-").length];
        fechas = fecha.split("-");
        LocalDate fecha2 = LocalDate.of(Integer.parseInt(fechas[2]),Integer.parseInt(fechas[1]),Integer.parseInt(fechas[0]));
        return fecha2;
    }

    public static void main(String[] args) {
        parsearLinea("     benidorm fest: benidorm:26-01-2022:3 :indie : pop  :rock");
    }
}
