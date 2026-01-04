package ejercicio02ProdConsum;

public class Buffer {
    // Buffer de 1 solo hueco:
    // - dato == null  -> vacío (no se puede consumir)
    // - dato != null  -> lleno (no se puede producir)
    private Integer dato = null;

    public synchronized void producir(int valor){

        // Productor espera mientras el buffer esté lleno.
        // while (no if) para revalidar al despertar (spurious wakeups / otros hilos se adelantan).
        while (dato != null) {
            try {
                // wait() libera el lock del Buffer y duerme al productor hasta que cambie el estado.
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Aquí el buffer está vacío: se puede escribir.
        dato = valor;
        System.out.println("Productor produce -> "+valor);

        // Estado cambió (ahora está lleno): despierta al consumidor si estaba esperando.
        notifyAll();
    }

    public synchronized int consumir(){

        // Consumidor espera mientras el buffer esté vacío.
        while (dato == null){
            try {
                // wait() libera el lock y duerme al consumidor hasta que el productor meta algo.
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Aquí el buffer está lleno: se puede leer.
        int valor = dato;

        // Al consumir, se vacía el buffer.
        dato = null;

        System.out.println("Consumidor consume -> "+valor);

        // Estado cambió (ahora está vacío): despierta al productor si estaba esperando.
        notifyAll();

        return valor;
    }
}
