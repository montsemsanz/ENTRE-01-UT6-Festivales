package festivales.modelo;

import java.util.*;
import festivales.io.FestivalesIO;
import festivales.modelo.Festival;
import festivales.modelo.Mes;
import festivales.modelo.Estilo;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colección map
 * La clave del map es el mes (un enumerado festivales.modelo.festivales.modelo.Mes)
 * Cada mes tiene asociados en una colección ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen algún festival
 *
 * Las claves se recuperan en orden alfabéico
 *
 *  @author aimar monreal
 *
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;
    
    public AgendaFestivales() {
        this.agenda = new TreeMap<Mes, ArrayList<Festival>>();
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

        Mes m = festival.getMes();

        if(!agenda.containsKey(m))
        {
            ArrayList<Festival> festivals = new ArrayList<>();
            agenda.put(m, festivals);
        }
        agenda.get(m).add(obtenerPosicionDeInsercion(agenda.get(m), festival), festival);
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
        int i = 0;
        if (festivales.isEmpty()){return i;}
        for (Festival festi :festivales){
            if (festival.getNombre().compareTo(festi.getNombre()) > 0){

            }
            i++;

        }
        return i-1;
    }

    /**
     * Representación textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas par recorrer el map
     */

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Mes,ArrayList<Festival>>> entradas = agenda.entrySet();
        for (Map.Entry<Mes,ArrayList<Festival>> entrada :entradas) {
            sb.append("\n\n" + entrada.getKey() + " ");
            sb.append(entrada.getValue().toString());}
        return sb.toString();
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
       if (!agenda.containsKey(mes)){
           return 0; //voy a devolver 0 para que en el print salga que hay 0 festivales en ved de -1
       }
        return agenda.get(mes).size();
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

    public HashMap<Estilo, TreeSet<String>> festivalesPorEstilo() {
        HashMap<Estilo, TreeSet<String>> estilos = new HashMap<>();

        Set<Mes> claves = agenda.keySet();

        for (Mes m: claves) {
            ArrayList<Festival> festivales = agenda.get(m);
            for (Festival festival: festivales) {

                HashSet<Estilo> est = festival.getEstilos();

                for (Estilo estilo: est) {

                    if(!estilos.containsKey(estilo)){
                        TreeSet<String> festivaln = new TreeSet<>();
                        estilos.put(estilo, festivaln);
                    }

                    estilos.get(estilo).add(festival.getNombre());
                }
            }
        }
        return estilos;
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
        if (!agenda.containsKey(mes)){
            return -1;
        }
        HashSet<Festival> FestivalesABorrar = new HashSet<>();
        int count = 0;
        if (agenda.containsKey(mes)) {
            ArrayList<Festival> festivales = agenda.get(mes);
            for (int i = 0; i < festivales.size(); i++) {
                if (lugares.contains(festivales.get(i).getLugar()) && !festivales.get(i).haConcluido()){
                    FestivalesABorrar.add(festivales.get(i));
                    count++;
                }
            }
            agenda.get(mes).removeAll(FestivalesABorrar);
            if (agenda.get(mes).isEmpty()){
                agenda.remove(mes);
            }
        }

        return count;
    }
}
