package ejercicio01productorConsumidor;

public class Buffer {

    private int tamanio = 1;// (según enunciado, 1)
    private char buffer[];
    private int siguiente;

    private boolean estaLlena; // estaLlena = true  -> NO se puede producir (no hay hueco)
    private boolean estaVacia; // estaVacia = true  -> NO se puede consumir (no hay nada)

    public Buffer(){
        // Buffer circular/lineal simple: aquí usas un array como “almacén” compartido.
        // No lo cambio, solo lo señalo.)
        buffer = new char[tamanio];

        // 'siguiente' apunta a la próxima posición libre para producir.
        // También funciona como “contador” de cuántos elementos hay:
        // - si siguiente == 0 -> vacío
        // - si siguiente == buffer.length -> lleno
        siguiente = 0;

        // Estado inicial:
        // - No está lleno (se puede producir)
        // - Sí está vacío (no se puede consumir)
        estaLlena = false;
        estaVacia = true;
    }

    public synchronized char consumir(){

        // Método synchronized: asegura exclusión mutua sobre 'buffer', 'siguiente' y banderas.
        // Si está vacío, el consumidor no puede avanzar: espera.
        // while (no if) para revalidar condición tras despertar (spurious wakeups / carreras).
        while (estaVacia){
            try {
                // wait() suelta el lock del Buffer y duerme este hilo hasta que alguien haga notifyAll().
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Aquí sabemos que hay al menos 1 elemento.
        // Decrementas 'siguiente' para apuntar al último elemento válido (modo “pila” LIFO).
        // Nota: esto NO consume FIFO, consume LIFO (último en entrar, primero en salir).
        siguiente--;

        // Si tras decrementar llegas a 0, el buffer queda vacío.
        if(siguiente == 0){
            estaVacia = true;
        }

        // Tras consumir, seguro que ya no está lleno.
        estaLlena = false;

        // Cambió el estado: despiertas a productores/consumidores esperando.
        notifyAll();

        // Devuelves el elemento consumido.
        return buffer[siguiente];
    }

    public synchronized void producir(char c){

        // Si está lleno, el productor no puede escribir: espera.
        while (estaLlena){
            try {
                // wait() suelta el lock para que el consumidor pueda entrar y liberar hueco.
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        // Escribes el carácter en la posición 'siguiente' (la próxima libre).
        buffer[siguiente] = c;

        // Avanzas el puntero/contador: ahora hay un elemento más.
        siguiente++;

        // Tras producir, seguro que ya no está vacío.
        estaVacia = false;

        // Si alcanzas la longitud, el buffer se considera lleno.
        if(siguiente == this.buffer.length){
            estaLlena = true;
        }

        // Cambió el estado: despiertas a productores/consumidores esperando.
        notifyAll();
    }
}
