
import java.time.LocalDate;
import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colección map
 * La clave del map es el mes (un enumerado festivales.modelo.Mes)
 * Cada mes tiene asociados en una colección ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen algún festival
 *
 * Las claves se recuperan en orden alfabéico
 *
 *@ author Pablo Mosquera
 *
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;

    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * añade un nuevo festival a la agenda
     * <p>
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se creará una nueva entrada
     * con dicha clave y la colección formada por ese único festival
     * <p>
     * Si la clave (el mes) ya existe se añade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insertándolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el método de ayuda
     * obtenerPosicionDeInsercion()
     */
    public void addFestival(Festival festival) {

        Mes mes = festival.getMes();


        if (agenda.containsKey(mes)) {
            agenda.get(mes).add(festival);
        } else {
            ArrayList<Festival> festivales = new ArrayList<>();
            festivales.add(festival);
            agenda.put(mes, festivales);
        }

    }

    /**
     * @param festivales una lista de festivales
     * @param festival
     * @return la posición en la que debería ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales,
                                           Festival festival) {

        int pos = 0;
        for (int i = 0; i < festivales.size(); i++) {
            if (festivales.get(i).getNombre().compareToIgnoreCase(festival.getNombre()) < 0) {
                festivales.add(i, festival);
                pos = i;
            }
        }

        return 0;

    }

    /**
     * Representación textual del festival
     * De forma eficiente
     * Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {

        Mes[] mes = Mes.values();
        Estilo[] estilos = Estilo.values();

        String cadena = "";
        for (Festival festival: agenda.get(mes)) {
            cadena += "Meses y nºfestivales en ese mes\n";
            cadena +=  String.format("%1s","%1s\n",mes, festivalesEnMes(festival.getMes()));
            cadena += "Nombres de festivales agrupados por estilos\n";
            for (Estilo estilo: estilos){
                cadena += String.format("%1s","%1s\n",estilo, festivalesPorEstilo());
            }
            cadena += String.format("%1s","%1s","%10S\n",festival.getNombre(), festivalesPorEstilo(), festival.getLugar() );
            cadena += String.format("%1s", festival.getLugar());
            cadena += String.format("%1s", obtenerFecha());
            cadena += "******************************";
        }

        return cadena;
    }

    private  String obtenerFecha(){
        Mes[] mes = Mes.values();

        String cadena = "";

        for (Festival festival: agenda.get(mes)) {

            LocalDate inicio = festival.getFechaInicio();

            LocalDate hoy = LocalDate.now();

            if (inicio.isAfter(hoy)) {
                cadena += festival.getFechaInicio();
                cadena += "(Quedan " + inicio.compareTo(hoy) + " días)";
            }

            if (inicio.equals(hoy)) {
                cadena += String.format(String.valueOf(inicio.getDayOfMonth()), inicio.getMonth(),
                        hoy);
                cadena += "(Concluido)";
            } else {
                cadena += String.format(String.valueOf(inicio.getDayOfMonth()), inicio.getMonth(),
                        hoy);
                cadena += "(ON)";
            }
        }
        return cadena;
    }

    /**
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {


        if (!agenda.containsKey(mes)) {
            return -1;
        }

        return agenda.get(mes).size();
    }


    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colección
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     * <p>
     * Identifica el tipo exacto del valor de retorno
     */
    public TreeMap<Estilo, TreeSet<String>> festivalesPorEstilo() {

        TreeMap<Estilo, TreeSet<String>> festivalEstilos = new TreeMap<>();
        Set<Mes> claves = agenda.keySet();
        Estilo[] estilos = Estilo.values();

        Mes[] mes = Mes.values();

        TreeMap<Estilo, ArrayList<String>> nombres = new TreeMap<>();

        for (Festival festival : agenda.get(mes)) {
            for (Estilo estilo : estilos) {
                if (festival.getEstilos().equals(estilo)) {
                    if (nombres.containsKey(estilo)) {
                        nombres.get(estilo).add(festival.getNombre());
                    } else {
                        ArrayList<String> nombre = new ArrayList<>();
                        nombre.add(festival.getNombre());
                        nombres.put(estilo, nombre);
                    }
                }
            }

        }

        for (Mes mes2 : claves) {
            if (festivalEstilos.containsKey(mes)) {
                for (Estilo estilo : estilos) {
                    if (nombres.get(estilo).equals(estilo)) {
                        festivalEstilos.get(mes).add(nombres.get(estilo).toString());
                    }
                }
            } else {
                for (Estilo estilo : estilos) {
                    TreeSet<String> nombres2 = new TreeSet<>();
                    nombres2.add(nombres.get(estilo).toString());
                    festivalEstilos.put(estilo, nombres2);
                }
            }
        }
        return festivalEstilos;
    }

    /**
     * Se cancelan todos los festivales organizados en alguno de los
     * lugares que indica el conjunto en el mes indicado. Los festivales
     * concluidos o que no empezados no se tienen en cuenta
     * Hay que borrarlos de la agenda
     * Si el mes no existe se devuelve -1
     * <p>
     * Si al borrar de un mes los festivales el mes queda con 0 festivales
     * se borra la entrada completa del map
     */
    public int festivalesPorEstilo(HashSet<String> lugares, Mes mes) {

        int cancelados = 0;

        if(!agenda.containsKey(mes)) {
            cancelados = -1;
        }
        else {
            for(String lugar: lugares){
                for(Festival festival: agenda.get(mes)) {
                    if(!festival.haConcluido() || festival.getMes().compareTo(mes) < 0) {
                        if(festival.getLugar().equals(lugar)) {
                            agenda.get(mes).remove(lugar);
                        }
                        if(agenda.get(mes).size() == 0) {
                            agenda.remove(mes);
                        }
                    }
                }

            }
        }

        return cancelados;
    }
}
