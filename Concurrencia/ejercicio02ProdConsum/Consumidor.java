package ejercicio02ProdConsum;

public class Consumidor implements Runnable {

    private Buffer buffer;

    public Consumidor(Buffer buffer) {
        // Comparte el MISMO buffer que el productor.
        this.buffer = buffer;
    }

    @Override
    public void run() {
        int total = 0;

        // Consume exactamente 20 veces.
        // Esto cumple el enunciado: el consumidor se detiene tras leer 20 números.
        while (total < 20) {
            buffer.consumir();
            total++;
        }

        // Nota: al terminar el consumidor, el programa terminará cuando también termine el productor
        // (y el productor aquí produce 1..20 y luego sale del run()).
    }
}
