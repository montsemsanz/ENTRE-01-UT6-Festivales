package festivales.modelo;

import festivales.modelo.Estilo;
import festivales.modelo.Festival;
import festivales.modelo.Mes;

import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 * <p>
 * La agenda guardalos festivales en una colecci�n map
 * La clave del map es el mes (un enumerado festivales.modelo.festivales.modelo.Mes)
 * Cada mes tiene asociados en una colecci�n ArrayList
 * los festivales  de ese mes
 * <p>
 * Solo aparecen los meses que incluyen alg�n festival
 * <p>
 * Las claves se recuperan en orden alfab�ico
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;

    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * a�ade un nuevo festival a la agenda
     * <p>
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se crear� una nueva entrada
     * con dicha clave y la colecci�n formada por ese �nico festival
     * <p>
     * Si la clave (el mes) ya existe se a�ade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insert�ndolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el m�todo de ayuda
     * obtenerPosicionDeInsercion()
     */
    public void addFestival(Festival festival) {
        Mes mes = festival.getMes();
        int pos = obtenerPosicionDeInsercion(agenda.get(mes),festival);
        ArrayList<Festival> listaFestivales = new ArrayList<>();

        if(agenda.containsKey(festival)){
           agenda.get(mes).add(pos,festival);
        }
        else{
           agenda.put(mes,listaFestivales);
        }


    }

    /**
     * @param festivales una lista de festivales
     * @param festival
     * @return la posici�n en la que deber�a ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales, Festival festival) {
        int posicion = 0;

        for(Festival festi:festivales) {
            if (festi.getNombre().compareTo(festival.getNombre()) < 0){
                posicion ++;
             }
        }

        return posicion;

    }

    /**
     * Representaci�n textual del festival
     * De forma eficiente
     * Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        Set<Mes> rep = agenda.keySet();
        StringBuilder sb = new StringBuilder();
        for(Mes mes: rep){
            sb.append(mes + "\n ");
            sb.append( agenda.get(mes).toString() + "\n" );
            }

        return sb.toString();
    }

    /**
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
        int cuantos = 0;
        if(agenda.containsKey(mes)){
            cuantos = agenda.get(mes).size();
        }
        return cuantos;
    }

    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colecci�n
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     * <p>
     * Identifica el tipo exacto del valor de retorno
     */

    public TreeMap<Estilo, TreeSet<String>>  festivalesPorEstilo() {
        TreeMap<Estilo, TreeSet<String>> estilos = new TreeMap<>();
        Set<Mes> meses = agenda.keySet();

        return estilos;
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
        int cancelados = 0;
        for(String lugar:lugares){
            if(agenda.containsKey(lugar)){
                ArrayList<Festival> festivalesDeMes = agenda.get(mes);
                int aux = festivalesDeMes.size();
                for(int j = 0; j<festivalesDeMes.size(); j++ ){
                    if (festivalesDeMes.get(j).getMes() == mes){
                        agenda.get(mes).remove(j);
                    }
                }
                cancelados += (aux - festivalesDeMes.size());
                if(agenda.get(mes).size() == 0){
                    agenda.remove(mes);
                }
                else{
                    agenda.put(mes, festivalesDeMes);
                }
            }
        }
        return cancelados;
    }
}
