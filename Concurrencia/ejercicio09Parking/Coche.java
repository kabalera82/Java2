package ejercicio09Parking;

public class Coche implements Runnable {

    private final Parking parking;

    public Coche(Parking parking){
        this.parking = parking;
    }

    @Override
    public void run() {

        try {
            System.out.println(
                    Thread.currentThread().getName() +
                            " llegando y quiere aparcar."
            );

            // Cada coche llega en un momento aleatorio
            Thread.sleep((int)(Math.random() * 1000) + 500);

        } catch (InterruptedException e) {
            return;
        }

        // Intenta aparcar (puede quedar esperando en la cola)
        parking.aparcar();

        try {
            System.out.println(
                    Thread.currentThread().getName() +
                            " aparcado."
            );

            // Tiempo aparcado
            Thread.sleep((int)(Math.random() * 1500) + 500);

        } catch (InterruptedException e) {
            return;
        }

        System.out.println(
                Thread.currentThread().getName() +
                        " saliendo de la plaza..."
        );

        parking.salir();
    }
}
