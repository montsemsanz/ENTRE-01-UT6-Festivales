import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Scanner;

/**
 * La clase contiene métodos estáticos que permiten
 * cargar la agenda de festivales leyendo los datos desde
 * un fichero
 *
 * @author - Iratxe Remón
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

       String[] datos = new String[lineaFestival.split(":").length];
       String linea = lineaFestival.trim();
       datos = linea.split(":");

       String[] nombreCompleto = new String[datos[0].trim().split(" ").length];
       nombreCompleto =  datos[0].trim().split(" ");
       String nombre = getNombre(nombreCompleto);

       String lugar = datos[1].trim().toUpperCase();

       String fecha = datos[2].trim();
       LocalDate fecha2 = getFecha(fecha);

       int duracion = Integer.parseInt(datos[3].trim());

        HashSet<Estilo> estilos = new HashSet<>();
        Estilo[] estilo = Estilo.values();
        for (int i = 4; i < datos.length; i++) {
            for (Estilo todos:estilo) {
              if (datos[i].trim().equalsIgnoreCase(String.valueOf(todos))) {
                    estilos.add(todos);
                }
            }
        }
//        System.out.println(nombre + ", " + lugar + ", " + fecha2 + ", " + duracion + ", " + estilos);
        Festival festival = new Festival(nombre, lugar, fecha2, duracion, estilos);
        return festival;
    }

    public static String getNombre(String[] nombreCompleto) {
        String nombre = "";
        for (String nom:nombreCompleto) {
            nombre += nom.substring(0, 1).toUpperCase();
            nombre += nom.substring(1).toLowerCase();
            nombre += " ";
        }
        nombre = nombre.trim();
        return nombre;
    }

    public static LocalDate getFecha(String fecha) {
        String[] fechas = new String[fecha.split("-").length];
        fechas = fecha.split("-");
        LocalDate fecha2 = LocalDate.of(Integer.parseInt(fechas[2]),Integer.parseInt(fechas[1]),Integer.parseInt(fechas[0]));
        return fecha2;
    }
}
