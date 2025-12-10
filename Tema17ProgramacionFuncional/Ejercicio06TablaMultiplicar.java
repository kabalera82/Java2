package Tema17ProgramacionFuncional;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
 * Escribe un programa que pida un número entero
 * y muestre en pantalla la representación de la tabla de multiplicar de ese número.
 * La tabla mostrará el resultado total de multiplicar
 * dicho número por los valores entre 1 y 10, de esta forma:
 * 5 x 1 = 5, 5 x 2 = 10, ..., 5 x 10 = 50
 */
public class Ejercicio06TablaMultiplicar {

    public static void main(String[] args) {
        System.out.println("Inserte un numero: "); //Insertamos numero
        Scanner sc = new Scanner(System.in); // Abrimos escanner y creamos instancia
        int n = sc.nextInt(); // almacenamos esa intancia en una variable de tipo int

        IntStream.rangeClosed(0, 10) //Abrimos un Stream de tipo Int con un rango cerrado inclusive los nums
                // hacemos un mapa %d indica posicion de un int, long con el formato indicado
                .mapToObj(i-> "%d x %d = %d".formatted(n, i, n*i))
                .forEach(System.out::println); // hacemos un for each con cada resultado


    }

}
