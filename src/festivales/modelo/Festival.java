package festivales.modelo;

import festivales.io.FestivalesIO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

/**
 * Un objeto de esta clase almacena los datos de un
 * festival.
 * Todo festival tiene un nombre, se celebra en un lugar
 * en una determinada fecha, dura una serie de días y
 * se engloba en un conjunto determinado de estilos
 *
 * @author - Jiacheng Lin
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
        Mes[] meses = Mes.values();
        Mes mes = meses[fechaInicio.getMonthValue() - 1];
        return mes;
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
        return fechaInicio.isAfter(otro.getFechaInicio());
    }

    /**
     *
     * @return true si el festival ya ha concluido
     */
    public boolean haConcluido() {
        LocalDate fechaFinal = fechaInicio.plusDays(duracion);
        return fechaFinal.isBefore(LocalDate.now());
    }

    /**
     * Representación textual del festival, exactamente
     * como se indica en el enunciado
     *
     */
    @Override
    public String toString() {
        DateTimeFormatter formt = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate fechaFinal = fechaInicio.plusDays(duracion);
        int diff = fechaInicio.getDayOfYear() - LocalDate.now().getDayOfYear();
        Mes[] meses = Mes.values();
        Mes mes = meses[fechaFinal.getMonthValue() - 1];
        String cadena = "\n" + String.format("%-20s %-20s", nombre, estilos.toString()) + "\n";
        cadena += lugar + "\n";
        if (duracion != 1){
            formt = DateTimeFormatter.ofPattern("dd MMM");
            cadena += formt.format(fechaInicio);
            formt = DateTimeFormatter.ofPattern("dd MMM yyyy");
            cadena += " - " + formt.format(fechaFinal);
        }
        else{
            cadena += formt.format(fechaInicio);
        }
        if(fechaInicio.isBefore(LocalDate.now()) && fechaFinal.isAfter(LocalDate.now())){
            cadena += " (ON)";
        }
        else if(haConcluido()){
            cadena += " (concluido)";
        }
        else{
            cadena += " (quedan " + diff + " días)";
        }
        cadena += "\n----------------------------------------";
        return cadena;
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