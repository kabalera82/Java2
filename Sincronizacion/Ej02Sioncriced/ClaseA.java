package Ej02Sioncriced;

/*
 * Tarea que será ejecutada por un hilo y que opera sobre una {@link Cuenta}.
 * <p>
 * Según el valor del booleano {@code usarMetodo1}, la tarea utilizará
 * el método sincronizado {@link Cuenta#incrementar()} o el método
 * {@link Cuenta#incrementar2()} con bloque synchronized.
 * </p>
 */
public class ClaseA implements Runnable {

    /**
     * Cuenta compartida sobre la que se realizarán los incrementos.
     */
    private final Cuenta cuenta;

    /*
     * Indica qué forma de incremento usar:
     * <ul>
     *   <li>{@code true}: usar {@link Cuenta#incrementar()}</li>
     *   <li>{@code false}: usar {@link Cuenta#incrementar2()}</li>
     * </ul>
     */
    private final boolean usarMetodo1;

    /*
     * Crea una nueva tarea asociada a una cuenta y a un tipo de incremento.
     *
     * @param cuenta      cuenta compartida que se va a modificar
     * @param usarMetodo1 {@code true} para usar el método sincronizado
     *                    {@link Cuenta#incrementar()}, {@code false} para usar
     *                    {@link Cuenta#incrementar2()}
     */
    public ClaseA(Cuenta cuenta, boolean usarMetodo1) {
        this.cuenta = cuenta;
        this.usarMetodo1 = usarMetodo1;
    }

    /*
     * Código que ejecutará el hilo al llamar a {@link Thread#start()}.
     * <p>
     * Realiza 5 incrementos sobre la cuenta, usando uno u otro método
     * según el valor de {@code usarMetodo1}. Tras cada incremento,
     * se llama al método estático sincronizado {@link Main#log(String)}
     * para registrar un mensaje global.
     * </p>
     */
    @Override
    public void run() {
        // Cada hilo hace 5 incrementos
        for (int i = 0; i < 5; i++) {
            if (usarMetodo1) {
                // Uso del método sincronizado de instancia
                cuenta.incrementar();
            } else {
                // Uso del bloque synchronized(this)
                cuenta.incrementar2();
            }

            // Ejemplo de uso de método estático sincronizado para log global
            Main.log("Log global desde " + Thread.currentThread().getName());

            try {
                // Pequeña pausa para ver mejor el entrelazado de los hilos
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
