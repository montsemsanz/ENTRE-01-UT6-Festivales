package festivales.modelo;

import java.time.LocalDate;
import java.util.*;
//@Author Aritz Ciriza

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
        ArrayList<Festival> aux = new ArrayList<>();
        Mes mesFestival = festival.getMes();
        int posicion = obtenerPosicionDeInsercion(agenda.get(mesFestival),festival);

        if (agenda.containsKey(mesFestival)){
            agenda.get(mesFestival).add(posicion,festival);
        }
        else{
            ArrayList<Festival> festivalesNuevos =new ArrayList<>();
            festivalesNuevos.add(festival);
            agenda.put(mesFestival,festivalesNuevos);
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
        int posicion = 0;
        for (int i = festivales.size()-1; i >= 0; i--){
            if(festivales.get(i).getNombre().compareTo(festival.getNombre()) < 0){
                posicion = i;
            }
        }
        return posicion;
        
    }

    /**
     * Representación textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Mes,ArrayList<Festival>>> entradas = agenda.entrySet();
        for (Map.Entry<Mes,ArrayList<Festival>> entrada:entradas) {
            sb.append(entrada.getKey() + "\t\t"+entrada.getValue() + "\n");
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
    public  TreeMap<Estilo,ArrayList<Festival>> festivalesPorEstilo() {

       TreeMap<Estilo,ArrayList<Festival>> estilos = new TreeMap<>();
       Set<Map.Entry<Mes,ArrayList<Festival>>> entradasOriginal = agenda.entrySet();
       ArrayList<Festival> festivalesEstilo = new ArrayList<>();
       int contador = 0;
        for (Map.Entry<Mes,ArrayList<Festival>> entradas:entradasOriginal) {
            HashSet<Estilo> estilosFestival = entradas.getValue().get(contador).getEstilos();
            Iterator<Estilo> it = estilosFestival.iterator();
            while (it.hasNext()) {
                Estilo next =  it.next();

                if (estilos.containsKey(next)){

//                    estilos.get(next).add(festivalesEstilo);
                }
            }

            contador++;
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

        ArrayList<Festival> festivales = agenda.get(mes);
        for (Festival festival:festivales) {
            String lugar = festival.getLugar();
            if (lugares.contains(lugar)){
                festivales.remove(lugar);
            }
            agenda.get(mes);
        }
        
        return -1;
    }


    public static void main(String[] args) {
        AgendaFestivales agenda = new AgendaFestivales();
        HashSet<Estilo> estilos = new HashSet<>();
        estilos.add(Estilo.ROCK);
        estilos.add(Estilo.INDIE);

        LocalDate fecha = LocalDate.now();
        agenda.addFestival(new Festival("Medusa","Valencia",fecha,4,estilos));
        System.out.println(agenda.toString());
    }
}

