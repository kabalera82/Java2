package Tema17ProgramacionFuncional;

import javax.swing.*;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio02EsBisiesto {
    public static void main(String[] args) {
        List<Integer> bisiestos = rangoBisiestos(); // Llama al método que pide los años y devuelve la lista de bisiestos
        System.out.println("Los años bisiestos son: " + bisiestos); // Imprime la lista resultante
    }
    //--------------------------------------------------------------------------------------------
    public static List<Integer> rangoBisiestos () {
        int a, b;
        boolean check;
        do {
            a = Integer.parseInt(JOptionPane.showInputDialog("Introduce el año inicial:")); // Pide año inicial
            b = Integer.parseInt(JOptionPane.showInputDialog("Introduce el año final:"));   // Pide año final
            check = a < b; // Verifica que el año inicial sea menor que el final
        } while (!check); // Repite si la condición no se cumple

        // Aquí comienza el uso de Streams:
        return IntStream.rangeClosed(a, b) // Crea un Stream de enteros desde 'a' hasta 'b' (ambos incluidos)
                .filter(esBisiesto)        // Filtra el stream, dejando solo los años que cumplen la condición de bisiesto
                .boxed()                   // Convierte cada int del stream en un Integer (necesario para colecciones de objetos)
                .collect(Collectors.toList()); // Recoge todos los elementos del stream y los mete en una List<Integer>
    }
    //------------------------------------------------------------------------------------------------

    public static IntPredicate esBisiesto = // interfaz funcional que recibe un int y retorna boolean
            ano -> (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0); // Función que determina si un año es bisiesto
}