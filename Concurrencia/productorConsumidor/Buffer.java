package productorConsumidor;

public class Buffer {

    private char buffer[];
    private int siguiente;

    private boolean estaLlena;
    private boolean estaVacia;

    public Buffer(int tamanio){
        buffer = new char[tamanio];
        siguiente = 0;
        estaLlena = false;
        estaVacia = true;
    }

    public synchronized char consumir(){
        while(estaVacia){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        siguiente--;
        if(siguiente == 0){
            estaVacia = true;
        }
        estaLlena = false;
        notifyAll(); // <-- Despertamos a TODOS los hilos que esten esperando

        return buffer [siguiente];

    }
}
