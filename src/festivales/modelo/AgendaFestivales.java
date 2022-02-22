package festivales.modelo;

import festivales.modelo.Festival;
import festivales.modelo.Mes;

import java.time.LocalDate;
import java.util.*;


/**
 * @author Iñigo Camarero
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
        if (agenda.containsKey(festival.getMes())){
            agenda.get(festival.getMes()).add(festival);
        }
        else{
            ArrayList <Festival> festivalesara = new ArrayList<>();
            agenda.put(festival.getMes(),festivalesara);
            agenda.get(festival.getMes()).add(obtenerPosicionDeInsercion(festivalesara,festival),festival);
        }
    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posición en la que debería ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales, Festival festival) {
        int pos = 0;
        for (Festival item:festivales) {
            if (festival.getNombre().compareTo(item.getNombre()) < 0){
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
        Set<Mes> meses = agenda.keySet();
        for (Mes mes:meses) {
            sb.append(" "+ mes + agenda.get(mes).size() + "festival" + "\n");
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
        int festivales = -1;
        if (agenda.containsKey(mes)) {
            festivales = agenda.get(mes).size();
        }
        return festivales;
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
    public  TreeMap  <String, ArrayList <String>> festivalesPorEstilo() {
        TreeMap <String, ArrayList <String>> tipos = new TreeMap();

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
        int pos = 0;
        if(!agenda.containsKey(mes)){
           pos = -1;
       }
        ArrayList<Festival> festivalesLugares = agenda.get(mes);
        Iterator<Festival> it = festivalesLugares.iterator();
        int i = 0;
        for (String item : lugares) {
           if (item == festivalesLugares.get(i).getLugar()) {
               Festival festival = it.next();
               while (it.hasNext()) {
                   if(!festival.haConcluido() && festival.getFechaInicio().isBefore(LocalDate.now())){
                       it.remove();
                   }
                   if (agenda.get(mes).size() == 0) {
                       agenda.remove(mes);
                   }
               }
           }
           i++;
        }
        return pos;
}
}
