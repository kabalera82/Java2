package ejercicio08Sillas;

public class Comedor {
    private int sillasLibres;
    public Comedor(int capacidad) {
        this.sillasLibres = capacidad;
    }

    public synchronized void sentarse() {
        while (sillasLibres == 0) {
            System.out.println(
                    Thread.currentThread().getName() +
                            " espera, no hay sillas..."
            );

            try {
                wait();
            } catch (InterruptedException e) {
                return;
            }
        }
        sillasLibres--;
        System.out.println(
                Thread.currentThread().getName() +
                        " se sienta. Sillas libres: " + sillasLibres
        );
    }

    public synchronized void levantarse() {
        sillasLibres++;
        System.out.println(
                Thread.currentThread().getName() +
                        " se levanta. Sillas libres: " + sillasLibres
        );
        notify();
    }
}
