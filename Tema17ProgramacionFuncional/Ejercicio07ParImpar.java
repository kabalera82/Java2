package Tema17ProgramacionFuncional;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Tema 17: [Extraccion de pares e impares]
 * <p>
 * Ejercicio 17: [Dado un rango de números separar los pares de los impares.]
 *
 * @author kabadev
 */
public class Ejercicio07ParImpar {
    /*
    ===============================================================
     - Collectors.partitioningBy() --------------------------------
     - clasifica los elementos de un stream en un Map de Boolean y List
     - Boolean actua como clave donde true cumplen y false no
     ==============================================================
     */

    public static void main(String[] args) {

        System.out.println("Inserte un numero de inicio");
        Scanner sc = new Scanner(System.in);
        int num1 = sc.nextInt();
        System.out.println("Inserte un numero de final");
        int num2 = sc.nextInt();

        // Determinar el inicio y fin del rango de forma segura para garantizar el orden
        int inicio = Math.min(num1, num2);
        int fin = Math.max(num1, num2);

        // Crear un mapa para almacenar los números pares e impares
        // Se usa `boxed()` para convertir el `IntStream` en un `Stream<Integer>`
        // y poder usar `Collectors.partitioningBy`
        Map<Boolean, List<Integer>> numerosSeparados = IntStream.rangeClosed(inicio, fin)
                .boxed()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));

        // Acceder a las listas de pares e impares
        List<Integer> pares = numerosSeparados.get(true);
        List<Integer> impares = numerosSeparados.get(false);
        System.out.println("Numero en el rango [" + num1 + ", " + num2 + "]");
        System.out.println("Números pares: " + pares);
        System.out.println("Números impares: " + impares);


    }
}
