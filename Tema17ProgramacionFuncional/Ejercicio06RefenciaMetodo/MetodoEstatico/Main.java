package Tema17ProgramacionFuncional.Ejercicio06RefenciaMetodo.MetodoEstatico;

import Tema17ProgramacionFuncional.Ejercicio06RefenciaMetodo.MetodoInstancia.Persona;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        /*
        String resultado = String.valueOf(5);
        Vamos a hacer referencia a estos metodos con:
        lambda
        int num -> String.valueOf(num);
        String::valueOf; //No necesita pasar parámetros
        */

        //Type , Return
        Function<Integer, String> convertidor = String::valueOf;

        String resultado = convertidor.apply(35);//apply ejecuta
        //Entra un Int y devuelve un String que es el que devuelve el valueOf
    }
}
