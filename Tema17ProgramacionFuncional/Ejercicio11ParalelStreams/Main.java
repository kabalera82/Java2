package Tema17ProgramacionFuncional.Ejercicio11ParalelStreams;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        int[] numeros = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int[] numeros2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

        //Stream secuencial -> El orden de los elementos se va a mantener.
        long tiempoInicio = System.nanoTime(); //tiempo en nanosegungos
        Arrays.stream(numeros).forEach(n -> System.out.println("Secuencial: " + n + " "));
        Arrays.stream(numeros2).forEach(m -> System.out.println("Secuencial 2: " + m + " "));
        long tiempoFinal = System.nanoTime();
        System.out.println("\n" +
                "------------------------------------------------------------------------------------" + "\n" +
                "Tiempo secuencial: " + (tiempoFinal - tiempoInicio) + "nanosegundos" + "\n");

        //Parallel Stream -> Aumenta la velocidad pero el orden desaparece.
        tiempoInicio = System.nanoTime();
        Arrays.stream(numeros2).parallel().forEach(m -> System.out.println("Paralelo 2: " + m + " "));
        Arrays.stream(numeros).parallel().forEach(n -> System.out.print("Paralelo " + n + "\n"));
        Arrays.stream(numeros).parallel().forEach(n -> System.out.print("Paralelo " + n + "\n"));
        tiempoFinal = System.nanoTime();
        System.out.println("\n" +
                "-----------------------------------------------------------------------------" + "\n" +
                "Tiempo paralelo: " + (tiempoFinal - tiempoInicio) + "nanosegundos");
    }
}
