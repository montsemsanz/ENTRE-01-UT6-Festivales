
import java.lang.reflect.Array;
import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 * <p>
 * La agenda guardalos festivales en una colección map
 * La clave del map es el mes (un enumerado festivales.modelo.Mes)
 * Cada mes tiene asociados en una colección ArrayList
 * los festivales  de ese mes
 * <p>
 * Solo aparecen los meses que incluyen algún festival
 * <p>
 * Las claves se recuperan en orden alfabéico
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

        if (!agenda.containsKey(mes)) {
            ArrayList<Festival> aux = new ArrayList<>();
            aux.add(festival);
            agenda.put(mes, aux);
        } else {
            int pos = obtenerPosicionDeInsercion(agenda.get(mes), festival);
            agenda.get(mes).add(pos, festival);
        }

    }

    /**
     * @param festivales una lista de festivales
     * @param festival
     * @return la posición en la que debería ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales, Festival festival) {
        int pos = 0;
        for (int i = 0; i < festivales.size(); i++) {
            if (festival.getNombre().compareTo(festivales.get(i).getNombre()) > 0) {
                pos++;
            }
        }
        return pos;
    }

    /**
     * Representación textual del festival
     * De forma eficiente
     * Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Mes, ArrayList<Festival>>> entradas = agenda.entrySet();
        Iterator<Map.Entry<Mes, ArrayList<Festival>>> it = entradas.iterator();
        sb.append("Festivales" + "\n");
        while (it.hasNext()) {
            String str = "";
            Map.Entry<Mes, ArrayList<Festival>> entrada = it.next();
            sb.append("\n" + entrada.getKey() + " (" + festivalesEnMes(entrada.getKey()) + " festival/es)");
            for (Festival festival : entrada.getValue()) {
                str += festival;
            }
            sb.append(str);
        }

        return sb.toString();
    }

    /**
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
        if (agenda.containsKey(mes)) {
            int cuantos = 0;
            while (cuantos < agenda.get(mes).size()) {
                cuantos++;
            }
            return cuantos;
        }
        return -1;
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
        TreeMap<Estilo, TreeSet<String>> festivalesEstilo = new TreeMap<>();
        Set<Mes> conjunto = agenda.keySet();
        for (Mes mes : conjunto) {
            ArrayList<Festival> festivales = agenda.get(mes);
            TreeSet<String> nombres = new TreeSet<>();
            for (Festival festival : festivales) {
                HashSet<Estilo> estilos = festival.getEstilos();
                for (Estilo estilo : estilos) {
                    if (festival.getEstilos().contains(estilo)) {
                        nombres.add(festival.getNombre());
                        if (!festivalesEstilo.containsKey(estilo)) {
                            festivalesEstilo.put(estilo, nombres);
                        } else {
                            festivalesEstilo.get(estilo).add(festival.getNombre());
                        }
                    }
                }

            }
        }
        return festivalesEstilo;
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
    public int cancelarFestivales(HashSet<String> lugares, Mes mes) {
        if (!(festivalesEnMes(mes) == -1)) {
            int cancelados = 0;
            ArrayList<Festival> festivales = agenda.get(mes);
            Iterator<Festival> it = festivales.iterator();
            while (it.hasNext()) {
                Festival festival = it.next();
                for (String lugar : lugares) {
                    if (festival.getLugar().equalsIgnoreCase(lugar) && !festival.haConcluido()) {
                        it.remove();
                        cancelados++;
                    }
                }
            }
            if (festivales.size() == 0) {
                agenda.remove(mes);
            }
            return cancelados;
        }
        return -1;
    }
}
