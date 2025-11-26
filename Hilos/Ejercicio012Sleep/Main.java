package Ejercicio012Sleep;

    /*
        Haz un hilo que:
        Imprima "tic" cada 500 ms.
        Cuando haya impreso 5 veces, termine.
        Mientras tanto, el hilo main imprimirá “MAIN sigue…” cada 300 ms.
     */

public class Main {
    Tic tic = new Tic();
    Thread t1 = new Thread(tic);




}
