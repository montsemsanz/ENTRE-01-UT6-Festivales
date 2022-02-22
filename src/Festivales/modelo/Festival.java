package Festivales.modelo;

import Festivales.io.FestivalesIO;

import java.time.LocalDate;
import java.util.HashSet;

/**
 * Un objeto de esta clase almacena los datos de un
 * festival.
 * Todo festival tiene un nombre, se celebra en un lugar
 * en una determinada fecha, dura una serie de d�as y
 * se engloba en un conjunto determinado de estilos
 *@ author Pablo Mosquera
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
    
    public  LocalDate getFechaInicio() {
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
     * devuelve el mes de celebraci�n del festival, como
     * valor enumerado
     *
     */
    public Mes getMes() {

        Mes[] meses = Mes.values();
        int mes = fechaInicio.getMonthValue();
        Mes mesInicio = meses[mes - 1];

        for(int i = 0; i < 12; i++) {
            if (mesInicio.equals(meses[i])){
                return mesInicio;
            }
        }

       return mesInicio;
    }

    /**
     *
     * @param otro
     * @return true si el festival actual empieza
     * en un fecha anterior a otro
     */
    public boolean empiezadespuesQue(Festival otro) {

        return fechaInicio.isAfter(otro.getFechaInicio());
        
    }

    /**
     *
     * @param otro
     * @return true si el festival actual empieza
     * en un fecha posteior a otro
     */
    public boolean empiezaAntespuesQue(Festival otro) {
        
        return fechaInicio.isBefore(otro.getFechaInicio());
        
    }

    /**
     *
     * @return true si el festival ya ha concluido
     */
    public boolean haConcluido() {

        LocalDate ahora = LocalDate.now();
        LocalDate dias = getFechaInicio().plusDays(getDuracion());
        return ahora.isAfter(dias) ;

    }

    /**
     * Representaci�n textual del festival, exactamente
     * como se indica en el enunciado
     *
     */
    @Override
    public String toString() {

        String cadena = "";

        cadena += String.format("%1s","%10S\n",getNombre(), getEstilos());
        cadena += String.format("%1s", getLugar());
        cadena += String.format("%1s", obtenerFecha());
        cadena += "******************************";

        return cadena;
    }

    private  String obtenerFecha(){

        String cadena = "";
        LocalDate inicio = fechaInicio;

        LocalDate hoy = LocalDate.now();

        if(inicio.isAfter(hoy)) {
            cadena += getFechaInicio();
            cadena += "(Quedan " + inicio.compareTo(hoy) + " d�as)";
        }

        if(inicio.equals(hoy)) {
            cadena += String.format(String.valueOf(inicio.getDayOfMonth()), inicio.getMonth(),
                                      hoy);
            cadena += "(Concluido)";
        }

        else{
            cadena += String.format(String.valueOf(inicio.getDayOfMonth()), inicio.getMonth(),
                    hoy);
            cadena += "(ON)";
        }
        return cadena;
    }

    /**
     * C�digo para probar la clase Festivales.modelo.Festival
     *
     */
    public static void main(String[] args) {
        System.out.println("Probando clase Festivales.modelo.Festival");
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
        if (f1.empiezaAntespuesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza antes que " + f2.getNombre());
        } else if (f1.empiezadespuesQue(f2)) {
            System.out.println(f1.getNombre() + " empieza despu�s que " + f2.getNombre());
        } else {
            System.out.println(f1.getNombre() + " empieza el mismo d�a que " + f2.getNombre());
        }

        System.out.println("\nProbando haConcluido()\n");
        System.out.println(f4);
        System.out.println(f4.getNombre() + " ha concluido? " + f4.haConcluido());
        System.out.println(f1);
        System.out.println(f1.getNombre() + " ha concluido? " + f1.haConcluido());
 
        
        
    }
}
