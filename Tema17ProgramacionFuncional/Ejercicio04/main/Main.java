package Tema17ProgramacionFuncional.Ejercicio04.main;

import Tema17ProgramacionFuncional.Ejercicio04.mensajero.Mensajero;

public class Main {

    public static void main(String[] args) {

        /*
        Este codigo es igual a lo de abajo
        * public void emitirMensaje (String nombre, int edad) {
        * System.out.println("hola desde lambda " + nombre + edad);
        * }
         */
        Mensajero lambdaMsje = (String name, int edad) -> {
            System.out.println("Hola desde lambda a: " + name + " ");
            System.out.println("Su edad es de :" + edad);
        };
        lambdaMsje.sendMessage("marcos", 42);


    }
}
