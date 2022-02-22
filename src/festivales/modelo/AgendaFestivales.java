package festivales.modelo;// Lo hace IntelliJ solo, yo importo todo lo que necesito y el programa hace esto

import java.util.*;

/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 * <p>
 * La agenda guardalos festivales en una colección map
 * La clave del map es el mes (un enumerado festivales.modelo.festivales.modelo.Mes)
 * Cada mes tiene asociados en una colección ArrayList
 * los festivales  de ese mes
 * <p>
 * Solo aparecen los meses que incluyen algún festival
 * <p>
 * Las claves se recuperan en orden alfabéico
 *
 * @author AIMAR
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
        // objeto de tipo mes
        Mes hilabetea = festival.getMes();
        // Si no encuntra el mes como clave
        if (!agenda.containsKey(hilabetea)) {
            // crea un nuevo arraylist de tipo festivales.modelo.Festival
            ArrayList<Festival> festivales = new ArrayList<>();
            // añade a la agenda el mes como clave y el array como objetos de es clave
            agenda.put(hilabetea, festivales);
        }
        // llamada al metodo obtenerPosicionDeInsercion
        int aux = obtenerPosicionDeInsercion(agenda.get(hilabetea), festival);
        // Se añade en el mes correspondiente el festival que se quiere añadir en su posicion correspondiente
        agenda.get(hilabetea).add(aux, festival);
    }

    /**
     * @param festivales una lista de festivales
     * @param festival
     * @return la posición en la que debería ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales, Festival festival) {
        // Variable para sacar el nombre del festival
        String izena = festival.getNombre();
        // Variable que se inicializa ha 0
        int i = 0;
        // Bucle que mientras i < la longuitud de festivales entre
        while (i < festivales.size()) {
            // Se compara el nombre con el nombre de la posicion i
            if (izena.compareTo(festivales.get(i).getNombre()) < 0) {
                // Devuelve la i
                return i;
            }
            // suma uno mas a la i
            i++;
        }
        // Devuelve la i
        return i;
    }

    /**
     * Representación textual del festival
     * De forma eficiente
     * Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        // Creamos el StringBuilder
        StringBuilder sb = new StringBuilder();
        Set<Map.Entry<Mes, ArrayList<Festival>>> jaialdiak = agenda.entrySet();
        // Creamos el Iterator
        Iterator<Map.Entry<Mes, ArrayList<Festival>>> it = jaialdiak.iterator();
        // Se añade texto al StringBuilder
        sb.append("Meses y nº festivales en ese mes\n\n\n");
        // While para recorrer el Iterator
        while (it.hasNext()) {
            Map.Entry<Mes, ArrayList<Festival>> jaialdia = it.next();
            // Se añade el festivales.modelo.Mes y cuantos festivales hay en ese mes
            sb.append(jaialdia.getKey() + ": " + jaialdia.getValue().size() + "\n");
        }
        return sb.toString();
    }

    /**
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
        // Adaptado de  un metodo de Actividad de Moodle Actividad V (Paises e idiomas)
        if (agenda.containsKey(mes)) {
            return agenda.get(mes).size();
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
        // Se crea el TreeMap
        TreeMap<Estilo, TreeSet<String>> era = new TreeMap<>();
        // Para sacar el mes
        Set<Mes> hilabeteak = agenda.keySet();
        // Se sacan los festivales que hay cada mes
        for (Mes hilabetea : hilabeteak) {
            ArrayList<Festival> jaialdia = agenda.get(hilabetea);
            // Se obtiene mediante un for todos los estilos de un festival
            for (int i = 0; i < jaialdia.size(); i++) {
                HashSet<Estilo> erak = jaialdia.get(i).getEstilos();
                // Variable de nombre que se inicializa con el nombre del festival que se obtiene de la posicion i del ArrayList creado antes del for
                String izena = jaialdia.get(i).getNombre();
                // Se añaden los estilos y festivales
                for (Estilo estilo : erak) {
                    // Si no esta el estilo como clave entra en el IF
                    if (!era.containsKey(estilo)) {
                        // Se crea un TreeSet para meter los nombres
                        TreeSet<String> izenak = new TreeSet<>();
                        // Se añade el estilo como clave principal y el TreeSet creado anteriormente como conjunto para la clave
                        era.put(estilo, izenak);
                    }
                    // Añade al estilo el festival nuevo
                    era.get(estilo).add(izena);
                }
            }

        }

        return era;
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
    public int cancelarFestival(HashSet<String> lugares, Mes mes) {
        //TODO

        return 0;
    }
}
