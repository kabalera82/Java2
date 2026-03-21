package ejercicio09Parking;

import java.util.LinkedList;
import java.util.Queue;

public class Parking {

    // Número de plazas libres en este momento
    private int plazasLibres;

    // Cola FIFO de coches que están esperando porque encontraron el parking lleno
    private final Queue<Thread> cola = new LinkedList<>();

    public Parking(int plazas) {
        this.plazasLibres = plazas;
    }

    public synchronized void aparcar() {

        Thread yo = Thread.currentThread();
        boolean enCola = false;  // para saber si ya me apunté a la cola

        while (true) {

            // 1) Caso ideal:
            //    - hay plazas libres
            //    - y NO hay nadie esperando en la cola
            //    -> entro directamente sin cola
            if (plazasLibres > 0 && cola.isEmpty()) {

                plazasLibres--;  // ocupo una plaza

                System.out.println(
                        yo.getName() +
                                " aparca directamente. Plazas libres: " + plazasLibres
                );
                return;
            }

            // 2) Parking lleno (o hay gente esperando de antes):
            //    me apunto a la cola solo una vez
            if (!enCola) {
                cola.add(yo);      // me pongo al final de la cola
                enCola = true;
                System.out.println(yo.getName() + " entra en la cola del parking.");
            }

            // 3) Espero hasta que:
            //    - se libere una plaza
            //    - y sea mi turno (yo sea el primero de la cola)
            try {
                wait();   // suelto el bloqueo y duermo
            } catch (InterruptedException e) {
                // Si me interrumpen, salgo y me quito de la cola
                if (enCola) {
                    cola.remove(yo);
                }
                return;
            }

            // 4) Me han despertado:
            //    si soy el primero en la cola y hay plaza libre -> entro
            if (cola.peek() == yo && plazasLibres > 0) {

                cola.remove();  // salgo de la cola
                plazasLibres--; // ocupo plaza

                System.out.println(
                        yo.getName() +
                                " aparca desde la cola. Plazas libres: " + plazasLibres
                );
                return;
            }

            // Si no soy yo el primero, o no hay plaza,
            // vuelvo al while y sigo esperando.
        }
    }

    // --- SALIR DEL PARKING ---
    public synchronized void salir() {

        plazasLibres++;  // libero una plaza

        System.out.println(
                Thread.currentThread().getName() +
                        " sale del parking. Plazas libres: " + plazasLibres
        );

        // Despierto a todos los que esperan:
        // solo los que cumplan la condición en el while podrán entrar.
        notifyAll();
    }
}
