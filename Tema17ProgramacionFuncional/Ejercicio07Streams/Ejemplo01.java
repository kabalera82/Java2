package Tema17ProgramacionFuncional.Ejercicio07Streams;

import java.util.ArrayList;
import java.util.List;

public class Ejemplo01 {

    public static void main(String[] args) {
        List<String> nombre = new ArrayList<>();

        nombre.add("nuevo Nombre");
        nombre.add("nuevo Nombre2");
        nombre.add("nuevo Nombre3");

        //Normalmente se realizaria con un for para imprimir resultados
        //Vamos a realizarlo con Stream

        //1. nuestra collection
        //2. stram invocandolo
        //3. Operaciones intermedias u operaciones terminales (las intermedias son opcionales)
        // En este caso vamos a un forEach
        //Hacemos uso de referencia a metodos

        nombre.stream().forEach(System.out::println);
    }
}
