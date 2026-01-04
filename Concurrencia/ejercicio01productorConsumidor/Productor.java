package ejercicio01productorConsumidor;

public class Productor implements Runnable {

    private Buffer buffer;
    private final String letras = "abcdefghijklmnopqrstuvwxyz";

    public Productor(Buffer buffer) {
        // El productor comparte el MISMO buffer que el consumidor.
        this.buffer = buffer;
    }

    @Override
    public void run() {
        // Bucle infinito: produce continuamente.
        while (true){

            // Genera un carácter aleatorio.
            char c = letras.charAt((int) (Math.random() * letras.length()));

            // Producir puede bloquearse si el buffer está lleno (wait()).
            buffer.producir(c);
            System.out.println("Despositado el caracter "+c+" en el buffer");

            // Simula tiempo de producción, fuera del lock.
            try {
                Thread.sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
