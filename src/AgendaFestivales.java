
import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colecci�n map
 * La clave del map es el mes (un enumerado festivales.modelo.Mes)
 * Cada mes tiene asociados en una colecci�n ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen alg�n festival
 *
 * Las claves se recuperan en orden alfab�ico
 *
 *  @AUTHOR Rub�n Saiz
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;
    
    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * a�ade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se crear� una nueva entrada
     * con dicha clave y la colecci�n formada por ese �nico festival
     *
     * Si la clave (el mes) ya existe se a�ade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insert�ndolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el m�todo de ayuda
     * obtenerPosicionDeInsercion()
     *
     */
    public void addFestival(Festival festival) {
        Mes mesFestival = festival.getMes();
        if (agenda.containsKey(mesFestival)) {
            agenda.get(mesFestival).add(obtenerPosicionDeInsercion(agenda.get(mesFestival), festival), festival);
        } else {
            ArrayList<Festival> festivales = new ArrayList<>();
            festivales.add(festival);
            agenda.put(mesFestival, festivales);
        }
        
    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posici�n en la que deber�a ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales,
                                           Festival festival) {
        for (int i = 0; i < festivales.size(); i++) {
            if (festivales.get(i).getNombre().compareTo(festival.getNombre()) > 0) {
                return i;
            }
        }
        
        return festivales.size();
        
    }

    /**
     * Representaci�n textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Mes, ArrayList<Festival>>> conjuntoEntradas = agenda.entrySet();
        for (Map.Entry<Mes, ArrayList<Festival>> entrada : conjuntoEntradas) {
            sb.append(entrada.getKey().toString()).append(" (").append(festivalesEnMes(entrada.getKey()))
                    .append(" festival/es)\n");
            for (Festival festivals : entrada.getValue()) {
                sb.append(festivals.toString()).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
       if (agenda.containsKey(mes)) {
           return agenda.get(mes).size();
       }
        return 0;
    }

    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colecci�n
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     *
     * Identifica el tipo exacto del valor de retorno
     */
    public LinkedHashMap<Estilo, TreeSet<String>>   festivalesPorEstilo() {
        LinkedHashMap<Estilo, TreeSet<String>> festivalesPorEstilo = new LinkedHashMap<>();
        for (Estilo estilo : Estilo.values()) {
            for (Mes mes : agenda.keySet()) {
                for (Festival festival : agenda.get(mes)) {
                    if (festival.getEstilos().contains(estilo)) {
                        if (festivalesPorEstilo.containsKey(estilo)) {
                            festivalesPorEstilo.get(estilo).add(festival.getNombre());
                        } else {
                            TreeSet<String> festivalEstilo = new TreeSet<>();
                            festivalEstilo.add(festival.getNombre());
                            festivalesPorEstilo.put(estilo, festivalEstilo);
                        }
                    }
                }
            }
        }
        return festivalesPorEstilo;
    }

    /**
     * Se cancelan todos los festivales organizados en alguno de los
     * lugares que indica el conjunto en el mes indicado. Los festivales
     * concluidos o que no empezados no se tienen en cuenta
     * Hay que borrarlos de la agenda
     * Si el mes no existe se devuelve -1
     *
     * Si al borrar de un mes los festivales el mes queda con 0 festivales
     * se borra la entrada completa del map
     */
    public int festivalesPorEstilo(HashSet<String> lugares, Mes mes) {
       //TODO
        
        return 0;
    }
}
