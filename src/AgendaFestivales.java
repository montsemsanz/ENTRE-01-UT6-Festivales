
import com.sun.tools.javac.Main;

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
 * @author Asier Galisteo
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
            ArrayList<Festival> festivales = new ArrayList<>();
            agenda.put(mes,festivales);
        }
        int i = obtenerPosicionDeInsercion(agenda.get(mes),festival);
        agenda.get(mes).add(i,festival);
        
        
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
        String nombre = festival.getNombre();
        int i = 0;
        while (i < festivales.size()){
            if (nombre.compareTo(festivales.get(i).getNombre()) < 0){
                return i;
            }
            i++;
        }
        return i;
        
    }

    /**
     * Representación textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Mes,ArrayList<Festival>>> festivales = agenda.entrySet();
        Iterator<Map.Entry<Mes,ArrayList<Festival>>> it = festivales.iterator();
        while (it.hasNext()) {
            Map.Entry<Mes, ArrayList<Festival>> festival =  it.next();
            sb.append(festival.getKey() + " (" + festival.getValue().size() + " festival/les)" + "\n");
            ArrayList<Festival> nuevo = festival.getValue();
            for (int i = 0; i < nuevo.size(); i++) {
                sb.append(nuevo.get(i) + "\n");
            }
        }

        return sb.toString();
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve 0
     */
    public int festivalesEnMes(Mes mes) {
        if (agenda.containsKey(mes)){
            return agenda.get(mes).size();
        }
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
    public  TreeMap<Estilo,TreeSet<String>>   festivalesPorEstilo() {
        TreeMap<Estilo,TreeSet<String>> porEstilos = new TreeMap<>();
        Set<Mes> meses = agenda.keySet();
        for (Mes mes:meses) { /* foreach para obtener los festivales que hay en cada mes*/
            ArrayList<Festival> festivales = agenda.get(mes);
            for (int i = 0; i < festivales.size(); i++) { /* for para obtener los estilos de cada festival*/
                HashSet<Estilo> estilos = festivales.get(i).getEstilos();
                String nombre = festivales.get(i).getNombre();
                for (Estilo estilo:estilos) { /* foreach para añadir estilos y festivales al TreeMap final*/
                    if (!porEstilos.containsKey(estilo)){
                        TreeSet<String> nombres = new TreeSet<>();
                        porEstilos.put(estilo,nombres);
                    }
                    porEstilos.get(estilo).add(nombre);
                }
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
    public int cancelarFestivales(HashSet<String> lugares, Mes mes) {
        int total = 0;
        ArrayList<Festival> festivales = agenda.get(mes);

        if(agenda.containsKey(mes)){
            Iterator<String> it = lugares.iterator();
            while (it.hasNext()) { /* se obtienen cada uno de los lugares del HashSet */
                String lugar =  it.next();
                for (int i = 0; i < festivales.size(); i++) { /* Se recorre el ArrayList festivales para ver que festivales se celebran en un determinado lugar*/
                    Festival festival = festivales.get(i);
                    if (festival.getLugar().equalsIgnoreCase(lugar) && !festival.haConcluido()){
                        festivales.remove(festival);
                        total++;
                    }
                }
            }
            if(festivales.size() == 0){
                agenda.remove(mes);
            }
            return total;
        }


        return -1;
    }

    /** Código para probar la clase */

    public static void main(String[] args) {
        AgendaFestivales agenda = new AgendaFestivales();
        agenda.addFestival(FestivalesIO.parsearLinea("Gazpatxo Rock : valencia: 28-02-2022  :1  :rock :punk : hiphop"));
        agenda.addFestival(FestivalesIO.parsearLinea("black sound fest:badajoz:05-02-2022:  21 :rock :  blues"));
        agenda.addFestival(FestivalesIO.parsearLinea("guitar bcn:barcelona: 28-01-2022 :  170 :indie :pop:fusion"));
        agenda.addFestival(FestivalesIO.parsearLinea("el bosque sonoro: zaragoza:17-06-2022:3 : indie: pop"));
        agenda.addFestival(FestivalesIO.parsearLinea("festival vintoro: mallorca: 17-06-2022: 2: rock"));
        totalEnMes(agenda.festivalesEnMes(Mes.FEBRERO));
        totalEnMes(agenda.festivalesEnMes(Mes.MARZO));
        System.out.println(agenda.toString());
        System.out.println(agenda.festivalesPorEstilo());
        System.out.println(agenda);
    }

    public static void totalEnMes(int i) {
        if (i != -1){
            System.out.println("En febrero hay " + i + " festivales");
        }
        else{
            System.out.println("Aún no hay festivales en ese mes registrados");
        }
    }


}
