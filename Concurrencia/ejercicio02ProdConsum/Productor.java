package ejercicio02ProdConsum;

public class Productor implements Runnable {

    private Buffer buffer;

    public Productor(Buffer buffer) {
        // Comparte el MISMO buffer que el consumidor.
        this.buffer = buffer;
    }

    @Override
    public void run() {

        // Produce exactamente los números 1..20.
        for (int i = 1; i <= 20; i++) {

            // Si el buffer está lleno, producir() se bloqueará con wait().
            buffer.producir(i);

            // Pausa artificial para que se vea el “ping-pong” entre productor y consumidor.
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Al salir del for, el productor termina.
    }
}
