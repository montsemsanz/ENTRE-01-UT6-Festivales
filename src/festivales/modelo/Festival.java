package festivales.modelo;

import festivales.io.FestivalesIO;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.HashSet;
import java.util.Locale;
import static java.time.temporal.ChronoUnit.DAYS;
//@Author Aritz Ciriza

//Antes de empezar a ver el proyecto, mil perdones por los nombres de las variables, me ha costado unas cuantas horas de trabajo, lo que pides esta hecho y bien hecho
//pero los nombres de las variables quizas no sean los mejores.



/**
 * Un objeto de esta clase almacena los datos de un
 * festival.
 * Todo festival tiene un nombre, se celebra en un lugar
 * en una determinada fecha, dura una serie de días y
 * se engloba en un conjunto determinado de estilos
 *
 */
public class Festival {
    private final String nombre;
    private final String lugar;
    private final LocalDate fechaInicio;
    private final int duracion;
    private final HashSet<Estilo> estilos;
    
    
    public Festival(String nombre, String lugar, LocalDate fechaInicio,
                    int duracion, HashSet<Estilo> estilos) {
        this.nombre = nombre;
        this.lugar = lugar;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.estilos = estilos;
        
    }
    
    public String getNombre() {

        return nombre;
    }
    
    public String getLugar() {

        return lugar;
    }
    
    public LocalDate getFechaInicio() {

        return fechaInicio;
    }
    
    public int getDuracion() {

        return duracion;
    }
    
    public HashSet<Estilo> getEstilos() {

        return estilos;
    }
    
    public void addEstilo(Estilo estilo) {
        this.estilos.add(estilo);
        
    }

    /**
     * devuelve el mes de celebración del festival, como
     * valor enumerado
     *
     */
    public Mes getMes() {

            return Mes.values()[fechaInicio.getMonthValue() - 1];
    }

    /**
     *
     * @param otro
     * @return true si el festival actual empieza
     * en un fecha anterior a otro
     */
    public boolean empiezaAntesQue(Festival otro) {

        return fechaInicio.isBefore(otro.getFechaInicio());
        
    }

    /**
     *
     * @param otro
     * @return true si el festival actual empieza
     * en un fecha posteior a otro
     */
    public boolean empiezaDespuesQue(Festival otro) {


        return fechaInicio.isAfter(otro.getFechaInicio()) ;
    }

    /**
     *
     * @return true si el festival ya ha concluido
     */
    public boolean haConcluido() {
        LocalDate hoy = LocalDate.now();

        return hoy.isAfter(fechaInicio);

    }

    /**
     * Representación textual del festival, exactamente
     * como se indica en el enunciado
     *
     */
    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append(nombre + "\t" + "\t" + estilos + "\n");
       sb.append(lugar + "\n");
       LocalDate hoy = LocalDate.now();
        Period p = Period.between(fechaInicio, hoy);
        int tiemporRestante = p.getDays();

        LocalDate fechaFinalizacion = fechaInicio.plusDays(duracion);

        // Obtienes el mes actual
        Month mes ;
        mes = fechaInicio.getMonth();
        Month mes2;
        mes2 = fechaFinalizacion.getMonth();

        // Obtienes el nombre del mes
        String nombre = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        String nombre2 = mes2.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));

        long tiempoRestante1 = DAYS.between(hoy, fechaFinalizacion);

        String fechaInicioF = "";
        fechaInicioF += fechaInicio.getDayOfMonth();
        fechaInicioF += " ";
        fechaInicioF += nombre.substring(0,3);
        fechaInicioF += ".";
        fechaInicioF += fechaInicio.getYear();

        String fechaFinalizacionF = "";
        fechaFinalizacionF += fechaFinalizacion.getDayOfMonth();
        fechaFinalizacionF += " ";
        fechaFinalizacionF += nombre2.substring(0,3);
        fechaFinalizacionF += ".";
        fechaFinalizacionF += fechaFinalizacion.getYear();

        String fechaInicioF2 = "";
        fechaInicioF2 += fechaInicio.getDayOfMonth();
        fechaInicioF2 += " ";
        fechaInicioF2 += nombre.substring(0,3);
        fechaInicioF2 += ".";

        if(duracion == 1){
           if(fechaInicio.isAfter(hoy)){
               sb.append(fechaInicioF + "("+"quedan "+ tiempoRestante1 +" dias"+")");
           }

           else if ((hoy.isAfter(fechaInicio) && hoy.isBefore(fechaFinalizacion))){
               sb.append(fechaInicioF + "(ON)");
           }

           else{
               sb.append(fechaInicioF + "(CONCLUIDO)");
           }

       }
       else {
            if(fechaInicio.isAfter(hoy)){
                sb.append(fechaInicioF2 +" - "+ fechaFinalizacionF + "(" + "quedan " + tiempoRestante1 + " dias" + ")");
            }

            else if ((hoy.isAfter(fechaInicio) && hoy.isBefore(fechaFinalizacion))){
                sb.append(fechaInicioF2 +" - "+ fechaFinalizacionF + "(ON)");
            }

            else{
                sb.append(fechaInicioF2 +" - "+ fechaFinalizacionF + "(CONCLUIDO)");
            }
        }
       sb.append("\n" + "-------------------------------------");


        return sb.toString();
        
    }

    /**
     * Código para probar la clase festivales.modelo.Festival
     *
     */
    public static void main(String[] args) {
        System.out.println("Probando clase festivales.modelo.Festival");
        String datosFestival = "Gazpatxo Rock : " +
                "valencia: 28-02-2022  :1  :rock" +
                ":punk " +
                ": hiphop ";
        Festival f1 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f1);
        
        datosFestival = "black sound fest:badajoz:05-02-2022:  21" +
                ":rock" + ":  blues";
        Festival f2 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f2);
    
        datosFestival = "guitar bcn:barcelona: 28-01-2022 :  170" +
                ":indie" + ":pop:fusion";
        Festival f3 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f3);
    
        datosFestival = "  benidorm fest:benidorm:26-01-2022:3" +
                ":indie" + ": pop  :rock";
        Festival f4 = FestivalesIO.parsearLinea(datosFestival);
        System.out.println(f4);
      
        
        System.out.println("\nProbando empiezaAntesQue() empiezaDespuesQue()" +
                "\n");
        if (f1.empiezaAntesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza antes que " + f2.getNombre());
        } else if (f1.empiezaDespuesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza después que " + f2.getNombre());
        } else {
            System.out.println(f1.getNombre() + " empieza el mismo día que " + f2.getNombre());
        }

        System.out.println("\nProbando haConcluido()\n");
        System.out.println(f4);
        System.out.println(f4.getNombre() + " ha concluido? " + f4.haConcluido());
        System.out.println(f1);
        System.out.println(f1.getNombre() + " ha concluido? " + f1.haConcluido());
 
        
        
    }

}
