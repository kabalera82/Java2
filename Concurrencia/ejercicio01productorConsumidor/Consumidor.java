package ejercicio01productorConsumidor;

public class Consumidor implements Runnable{

    private Buffer buffer;

    public Consumidor(Buffer buffer){
        // El consumidor comparte el MISMO buffer que el productor.
        this.buffer = buffer;
    }

    @Override
    public void run() {
        // Bucle infinito: intenta consumir continuamente.
        while (true){

            // Consumir puede bloquearse si el buffer está vacío (wait()).
            char valor = buffer.consumir();
            System.out.println("Recogido el caracter "+valor+" del buffer");

            // Simula tiempo de procesamiento/consumo, fuera del lock.
            try {
                Thread.sleep((int) (Math.random() * 4000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
