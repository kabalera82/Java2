package Ej02Sioncriced;

/*
 * Clase que representa una cuenta con un contador entero.
 * <p>
 * Incluye dos formas distintas de sincronizar el acceso al contador:
 * un método de instancia sincronizado y un bloque synchronized(this).
 * </p>
 */
public class Cuenta {

    /*
     * Contador interno de la cuenta.
     */
    private int contador = 0;

    /*
     * Nombre identificativo de la cuenta.
     */
    private final String nombre;

    /*
     * Crea una nueva cuenta con el nombre proporcionado.
     *
     * @param nombre nombre de la cuenta
     */
    public Cuenta(String nombre) {
        this.nombre = nombre;
    }

    /*
     * Incrementa el contador usando un método de instancia sincronizado.
     * <p>
     * Al ser {@code synchronized}, el monitor asociado es {@code this},
     * es decir, la propia instancia de {@link Cuenta}. Solo un hilo
     * puede ejecutar este método al mismo tiempo sobre el mismo objeto.
     * </p>
     */
    public synchronized void incrementar() {
        int valor = contador;
        System.out.println(Thread.currentThread().getName()
                + " (método sincronizado) leyendo " + valor + " en " + nombre);

        try {
            // Simulamos trabajo con una pequeña pausa
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Restablecemos el estado de interrupción y salimos del método
            Thread.currentThread().interrupt();
            return;
        }

        contador = valor + 1;
        System.out.println(Thread.currentThread().getName()
                + " (método sincronizado) escribiendo " + contador + " en " + nombre);
    }

    /*
     * Incrementa el contador usando un bloque synchronized(this).
     * <p>
     * El monitor también es {@code this}, pero aquí solo se sincroniza
     * la sección crítica dentro del bloque {@code synchronized}.
     * </p>
     */
    public void incrementar2() {
        System.out.println(Thread.currentThread().getName()
                + " entrando en incrementar2() NO sincronizado de " + nombre);

        synchronized (this) {
            int valor = contador;
            System.out.println(Thread.currentThread().getName()
                    + " (bloque sincronizado) leyendo " + valor + " en " + nombre);

            try {
                // Simulamos trabajo con una pequeña pausa
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            contador = valor + 1;
            System.out.println(Thread.currentThread().getName()
                    + " (bloque sincronizado) escribiendo " + contador + " en " + nombre);
        }

        System.out.println(Thread.currentThread().getName()
                + " saliendo de incrementar2() de " + nombre);
    }

    /*
     * Devuelve el valor actual del contador.
     *
     * @return valor entero del contador
     */
    public int getContador() {
        return contador;
    }

    /*
     * Devuelve el nombre de la cuenta.
     *
     * @return nombre de la cuenta
     */
    public String getNombre() {
        return nombre;
    }
}
