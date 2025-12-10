package Tema17ProgramacionFuncional;

import java.util.Arrays;
import java.util.Scanner;

public class Ejercicio03EstadisticaBasica {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] numeros = new int[10];
        System.out.println("Introduce 10 números:");
        for (int i = 0; i < 10; i++) {
            numeros[i] = sc.nextInt();
        }
        //----------------- Suma ----------------------------------------------------------------
        int suma = Arrays
                .stream(numeros)
                .sum();
        //----------------- Media ---------------------------------------------------------------
        double media = Arrays
                .stream(numeros)
                .average()
                .orElse(0);
        //----------------- Mínimo --------------------------------------------------------------
        int min = Arrays
                .stream(numeros)
                .min()
                .orElse(Integer.MAX_VALUE);
        //----------------- Maximo --------------------------------------------------------------
        int max = Arrays
                .stream(numeros)
                .max()
                .orElse(Integer.MIN_VALUE);
        //----------------- Mediana -------------------------------------------------------------
        Arrays.sort(numeros);
        double mediana = (numeros[4] + numeros[5]) / 2.0;
        //----------------- Desviación Tipica ---------------------------------------------------
        double desviacionTipica =
                Math.sqrt(Arrays.stream(numeros)
                        .mapToDouble(n -> Math.pow(n - media, 2))
                        .sum() / numeros.length);
        //----------------- Salida ---------------------------------------------------------------
        System.out.println("----- RESULTADOS -----");
        System.out.println("Suma: " + suma);
        System.out.println("Media: " + media);
        System.out.println("Mínimo: " + min);
        System.out.println("Máximo: " + max);
        System.out.println("Mediana: " + mediana);
        System.out.println("Desviación típica: " + desviacionTipica);
    }
}


