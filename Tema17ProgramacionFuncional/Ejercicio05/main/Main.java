package Tema17ProgramacionFuncional.Ejercicio05.main;

import Tema17ProgramacionFuncional.Ejercicio05.operador.IOperador;

public class Main {

    public static void main(String[] args) {

        //Resuelto con clases anonimas
        IOperador suma = new IOperador() {
            @Override
            public int operar(int num1, int num2) {
                return num1 + num2;
            }
        };
        System.out.println("Suma anonima " + suma.operar(3, 5));

        //resuelto con lambdas
        //Lado izquierdo los parametros, lado derecho lo que hace el metodo
        IOperador suma2 = (num1, num2) -> num1 + num2;
        System.out.println("Suma lambda " + suma2.operar(3, 5));


    }
}
