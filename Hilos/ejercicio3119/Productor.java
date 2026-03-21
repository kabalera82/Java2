package ejercicio3119;

import static ejercicio3119.Main.producidos;

public class Productor implements Runnable {

    @Override
    public void run() {

        try {

            for (int i = 0; i < 10; i++) {

                Thread.sleep(700);

                int numero = (int) (Math.random() * 10 + 1);

                System.out.println("Produciendo -> " + numero + " <-");

                producidos.add(numero);
            }

            System.out.println("Productor ha terminado");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
