package Ejercicio05IsAlive;

public class Main {

    public static void main(String[] args) {

        MiHilo mh = new MiHilo();

        Thread h = new Thread(mh);

        System.out.println("Antes de start(), isAlive() = " + h.isAlive()); //-> false

        h.start();
        System.out.println("Despues de start, isAlive() = " + h.isAlive()); // -> true

        try {
            h.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Despues de join(), isAlive() = " + h.isAlive()); // -> false

    }

}
