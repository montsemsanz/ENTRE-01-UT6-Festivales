
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


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
 * @autor - Christhoper Pinday Delgado
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
        if (!agenda.containsKey(mes)) {
            ArrayList<Festival> nuevo = new ArrayList<Festival>();
            nuevo.add(festival);
            agenda.put(mes, nuevo);
        }
        else {// si el mes ya exista...
            //añadir festival
            int posicion = obtenerPosicionDeInsercion(agenda.get(mes), festival);
            agenda.get(mes).add(posicion, festival);
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
        for (int i = 0; i < festivales.size() - 1; i++) {
            int posmin = i;
            for (int j = i + 1; j < festivales.size(); j++) {
                if (festivales.get(j).getNombre().compareTo(festivales.get(posmin).getNombre()) < 0) {
                    posmin = j;
                }
            }
            //intercambiar valores del ArrayList
            Festival aux = festivales.get(i);
            //eliminamos ese
            // festivales.remove(i);
            //colocamos el correspondiente en esa posicion y desplaza a la derecha...
            festivales.add(i, festivales.get(posmin));
            festivales.remove(i + 1);
            // ahora borramos...
            // festivales.remove(posmin);
            //ksa
            festivales.add(posmin, aux);
            festivales.remove(posmin + 1);
        }
        //buscar posicion de inserccion...
        int i = festivales.size() - 1;
        while (i >= 0 && festivales.get(i).getNombre().compareTo(festival.getNombre()) > 0) {
            i--;
        }
        //posicion de inserccion hallada
        return i + 1;
    }

    /**
     * Representación textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        //TODO
        
        return null;
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
       //TODO
        
        return 0;
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
    public  Map   festivalesPorEstilo() {
       //TODO

         

        return null;
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
