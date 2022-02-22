package Festivales.test;

import Festivales.modelo.AgendaFestivales;
import Festivales.modelo.Mes;

import java.util.HashSet;

public class TestAgendaFestivales {
    
    /**
     * Código para probar la clase Festivales.modelo.AgendaFestivales
     *
     */
    public static void main(String[] args) {
        AgendaFestivales agenda = new AgendaFestivales();
        
        testCargarAgenda(agenda);
        testFestivalesEnMes(agenda);
        testFestivalesPorEstilo(agenda);
        testCancelarFestivales(agenda);
        
        
    }
    
    
    private static void testCargarAgenda(AgendaFestivales agenda) {
        // Festivales.io.FestivalesIO.cargarFestivales(agenda);
        // System.out.println(agenda);
        // System.out.println();
    }
    
    
    private static void testFestivalesEnMes(AgendaFestivales agenda) {
        // System.out.println("Meses y nº festivales en ese mes\n");
        // Festivales.modelo.Mes[] meses = {Festivales.modelo.Mes.FEBRERO, Festivales.modelo.Mes.ABRIL, Festivales.modelo.Mes.MAYO, Festivales.modelo.Mes.JUNIO,
                // Festivales.modelo.Mes.SEPTIEMBRE, Festivales.modelo.Mes.OCTUBRE};
        // for (Festivales.modelo.Mes mes : meses) {
            // System.out.println(mes + ": " + agenda.festivalesEnMes(mes));
            
        // }
        // System.out.println();
    }
    
    
    private static void testFestivalesPorEstilo(AgendaFestivales agenda) {
        // System.out.println("Nombres de festivales agrupados por estilos\n");
        // for (Map.Entry<Festivales.modelo.Estilo, TreeSet<String>> entrada :
                // agenda.festivalesPorEstilo().entrySet()) {
            // System.out.println(entrada.getKey() + " - " + entrada.getValue());
            
        // }
        // System.out.println();
    }
    
    private static void testCancelarFestivales(AgendaFestivales agenda) {
   
        // HashSet<String> lugares = new HashSet<>(Arrays.asList("VITORIA",
                // "BILBAO", "ZARAGOZA"));
        // testCancelarFestivales(agenda, Festivales.modelo.Mes.JUNIO, lugares);
 
        // lugares = new HashSet<>(Arrays.asList("SEVILLA"));
        // testCancelarFestivales(agenda, Festivales.modelo.Mes.MARZO, lugares);
    
        // lugares = new HashSet<>(Arrays.asList("MALLORCA"));
        // testCancelarFestivales(agenda, Festivales.modelo.Mes.DICIEMBRE, lugares);
        
    }

    private static void testCancelarFestivales(AgendaFestivales agenda,
                                               Mes mes,
                                               HashSet<String> lugares) {
        // System.out.println(lugares + " cancela/n su/s festival/es de " + mes +
                // "\n\n");
        // int borrados = agenda.cancelarFestivales(lugares, mes);
        // if (borrados == -1) {
            // System.out.println("No hay programados festivales en " + mes);
        // } else {
            // System.out.println("Cancelados " + borrados + " festival/es");
        // }
        // System.out.println("Después de borrar ....");
        // System.out.println(agenda);
    }
}
