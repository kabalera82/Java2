package ejercicio05AhorroFamiliar;

public class Hucha {

    // Objetivo fijo
    private static final int OBJETIVO = 6000;

    // Recurso compartido (peligroso)
    private int saldo = 0;


    // Zona protegida: solo un hilo entra a la vez
    public synchronized boolean ingresar(int cantidad) {

        // Si ya llegamos, no seguimos
        if (saldo >= OBJETIVO) return false;

        // SECCIÓN CRÍTICA
        // leer -> sumar -> escribir
        // Sin synchronized: dos hilos podrían pisarse y perder dinero
        saldo += cantidad;

        System.out.println(Thread.currentThread().getName() +
                " ingresa " + cantidad + "€ -> saldo: " + saldo);

        // Indica si aún falta para el objetivo
        return saldo < OBJETIVO;
    }

    public int getSaldo() { return saldo; }
    public int getObjetivo() { return OBJETIVO; }
}
