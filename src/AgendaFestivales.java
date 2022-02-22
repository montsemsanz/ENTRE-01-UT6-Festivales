
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
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;

    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * añade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se creará una nueva entrada
     * con dicha clave y la colección formada por ese único festival
     *
     * Si la clave (el mes) ya existe se añade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insertándolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el método de ayuda
     * obtenerPosicionDeInsercion()
     *
     */
    public void addFestival(Festival festival) {
        Mes mes = festival.getMes();
        if(!agenda.containsKey(mes)){
            ArrayList<Festival> festivales = new ArrayList<>();
            agenda.put(mes, festivales);
        }
        int posicion = obtenerPosicionDeInsercion(agenda.get(mes), festival);
        agenda.get(mes).add(posicion, festival);
    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posición en la que debería ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales,
                                           Festival festival) {
        int pos = 0;
        if(!festivales.isEmpty()){
            for (Festival fes: festivales) {
                if(fes.getNombre().compareTo(festival.getNombre()) < 0){
                    pos++;
                }
            }
        }
        return pos;
    }

    /**
     * Representación textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Mes> conjuntoMeses = agenda.keySet();
        for (Mes mes: conjuntoMeses) {
            sb.append("\n" + mes + "(" + agenda.get(mes).size() + " festival/es)");
            for (Festival festival: agenda.get(mes)) {
                sb.append(festival.toString());
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
        int cantidad = -1;
        if(agenda.containsKey(mes)){
            cantidad = agenda.get(mes).size();
        }
        return cantidad;
    }

    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colección
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     *
     * Identifica el tipo exacto del valor de retorno
     */
    public LinkedHashMap<Estilo, TreeSet<String>> festivalesPorEstilo() {
        LinkedHashMap<Estilo, TreeSet<String>> tipos = new LinkedHashMap<>();
        Set<Mes> conjuntoMeses = agenda.keySet();
        for (Mes mes: conjuntoMeses) {
            ArrayList<Festival> festivales = agenda.get(mes);
            for (Festival festival: festivales) {
                HashSet<Estilo> estilos = festival.getEstilos();
                for (Estilo estilo: estilos) {
                    if(!tipos.containsKey(estilo)){
                        TreeSet<String> nombres = new TreeSet<>();
                        tipos.put(estilo, nombres);
                    }
                    tipos.get(estilo).add(festival.getNombre());
                }
            }
        }
        return tipos;
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
    public int cancelarFestivales(HashSet<String> lugares, Mes mes) {
        int borrados = 0;
        if(agenda.containsKey(mes)){
            for(String lugar: lugares){
                ArrayList<Festival> festivales =  agenda.get(mes);
                for (Festival festival: festivales){
                    if(lugar.equalsIgnoreCase(festival.getLugar())){
                        agenda.get(mes).remove(festival);
                        borrados++;
                        if(agenda.get(mes).isEmpty()){
                            agenda.remove(mes);
                        }
                    }
                }
            }
        }
        else {
            borrados = -1;
        }
        return borrados;
    }
}
