
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
        ArrayList<Festival> festivales = new ArrayList<>();
        if (!agenda.containsKey(festival.getMes())) {
            festivales.add(festival);
            agenda.put(festival.getMes(), festivales);
        }

        if (!agenda.get(festival.getMes()).contains(festival)) {
            agenda.get(festival.getMes()).add(obtenerPosicionDeInsercion(agenda.get(festival.getMes()), festival), festival);
        }
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
        for (int i = 0; i < festivales.size(); i++) {
            if (festivales.get(i).getNombre().compareTo(festival.getNombre()) < 0) {
                pos++;
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
        for (Map.Entry<Mes, ArrayList<Festival>> entrada: agenda.entrySet()) {
            sb.append(entrada.getKey()).append("  (" + festivalesEnMes(entrada.getKey())).append(" festival/es)\n");
            Iterator<Festival> it = entrada.getValue().iterator();
            while (it.hasNext()) {
                sb.append(it.next()).append("\n");
            }
            sb.append("\n");
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
        int suma = 0;
        if (agenda.containsKey(mes)) {
            for (int i = 0; i < agenda.get(mes).size(); i++) {
                suma++;
            }
        }
        return suma;
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
    public TreeMap<Estilo,TreeSet<String>> festivalesPorEstilo() {
        TreeMap<Estilo,TreeSet<String>> porEstilos = new TreeMap<>();

        ArrayList<Festival> festivales = new ArrayList<>();                     // festivales = un ArrayList con todos los festivales
        for (Map.Entry<Mes,ArrayList<Festival>> entrada:agenda.entrySet()) {
            Iterator<Festival> it = entrada.getValue().iterator();
            while (it.hasNext()) {
                festivales.add(it.next());
            }
        }

        // Ahora quiero sacar el nombre y estilos de cada festival.
        String nombre = "";
        HashSet<Estilo> estilosDeCadaFestival = new HashSet<>();
        for (Festival festival:festivales) {

            estilosDeCadaFestival = festival.getEstilos();               // Sacamos los estilos de cada festival

            for (Estilo cadaEstilo: estilosDeCadaFestival) {
                nombre = festival.getNombre();                          //Sacamos el nombre de cada festival
                TreeSet<String> nombres = new TreeSet<>();
                nombres.add(nombre);
                if (!porEstilos.containsKey(cadaEstilo)) {
                    porEstilos.put(cadaEstilo, nombres);
                }
                    porEstilos.get(cadaEstilo).add(nombre);
            }
        }
        return porEstilos;
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
    public int cancelarFestival(HashSet<String> lugares, Mes mes) {
       //TODO
        
        return 0;
    }
}
