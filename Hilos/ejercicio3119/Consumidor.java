package ejercicio3119;

import static ejercicio3119.Main.producidos;

public class Consumidor implements Runnable {

    @Override
    public void run() {

        try {

            for (int i = 0; i < 20; i++) {

                Thread.sleep(300);

                if (producidos.size() != 0) {

                    System.out.println(
                            "consumiendo -> " + producidos.get(0) + " <-"
                    );

                    producidos.remove(0);

                } else {
                    System.out.println("no hay datos todavía…");
                }
            }

            System.out.println("Consumidor ha terminado");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
