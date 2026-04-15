package Ejercicio011RunStart;

    /*
    - Ejercicio: Diferencia entre run() y start()
    Crea un programa que:
    Tenga un hilo que imprime números del 1 al 5.
    Llama a run() primero.
    Luego llama a start().
    Observa el orden de ejecución.
    Explica por qué ocurre.
    */


import Ejercicio08TresHilosOrden.MiHilo;

public class ClaseRun implements Runnable {

    public int num = 0;

    @Override
    public ClaseRun (){
        for (int i = 0; i<5; i++){
            System.out.println("Hilos de Run nº: " + i);
        }
    }



}
