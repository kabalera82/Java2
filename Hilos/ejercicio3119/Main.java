package ejercicio3119;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<Integer> producidos = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Productor p1 = new Productor();
        Consumidor c1 = new Consumidor();

        Thread h1 = new Thread(p1, "PRODUCTOR");
        Thread h2 = new Thread(c1, "CONSUMIDOR");

        h1.start();
        h2.start();

        // vigilar estados
        for (int i = 0; i < 15; i++) {

            System.out.println(
                    "Main -> productor vivo?: " + h1.isAlive() +
                            "\nMain -> consumidor vivo?: " + h2.isAlive() +
                            "\n"
            );

            Thread.sleep(500);
        }

        System.out.println("Main terminado");
    }
}
