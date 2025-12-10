package Tema17ProgramacionFuncional.Ejercicio07Streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Ejemplo02 {

    public static void main(String[] args) {

        List<String> paises = Arrays.asList("España", "Argentina", "México", "Francia", "Peru", "Alemania", "Italia");

        //Vamos a filtrar
        paises.stream()
                .filter(pais -> pais.toLowerCase().contains("a")) // Operacion Intermedia
                .forEach(System.out::println);
    }
}
