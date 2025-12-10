package Tema17ProgramacionFuncional.Ejercicio01;

import java.util.List;

public class Ejercicio01ProgramacionFuncional {

    public static void main(String[] args) {
        //Declarativo
        List<Integer> numeros = List.of(13, 2, 45, 65, 756, 62, 0, 66, 6, 4);
        int contador = 0;
        for (int numero : numeros) {
            if (numero < 8) {
                contador++;
                System.out.println(numero);
            }
        }
        System.out.println(contador);

        // Imperativo
        Long resultado = numeros.stream().filter(num -> num > 8).count();
        System.out.println(resultado);
    }
}


