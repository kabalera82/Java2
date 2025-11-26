package Ej02Sioncriced;

/*
 * Clase principal del ejemplo de sincronización.
 * <p>
 * Crea una cuenta compartida y dos hilos que la incrementan,
 * cada uno utilizando una forma distinta de sincronización.
 * También define un método estático sincronizado de log.
 * </p>
 */
public class Main {

    /*
     * Método principal del programa.
     *
     * @param args argumentos de línea de comandos (no se usan)
     * @throws InterruptedException si algún {@link Thread} es interrumpido
     *                              mientras se espera con {@link Thread#join()}
     */
    public static void main(String[] args) throws InterruptedException {

        // Una única cuenta compartida por varios hilos
        Cuenta c1 = new Cuenta("Cuenta-Compartida");

        // Dos tareas que usan la MISMA cuenta:
        // a1 usará el método sincronizado incrementar()
        // a2 usará el bloque sincronizado incrementar2()
        ClaseA a1 = new ClaseA(c1, true);
        ClaseA a2 = new ClaseA(c1, false);

        Thread t1 = new Thread(a1, "Hilo-A");
        Thread t2 = new Thread(a2, "Hilo-B");

        // Arrancamos los hilos
        t1.start();
        t2.start();

        // Esperamos a que ambos terminen
        t1.join();
        t2.join();

        // Mostramos el valor final de la cuenta compartida
        System.out.println("==================================");
        System.out.println("Valor final en " + c1.getNombre()
                + " = " + c1.getContador());
        System.out.println("==================================");

        // Si quisieras, podrías crear otra cuenta distinta:
        // Cuenta c2 = new Cuenta("Cuenta-2");
        // y verías que su contador evoluciona independientemente
        // porque cada objeto tiene su propio "candado" (this).
    }

    /*
     * Método estático sincronizado para escribir un mensaje de log.
     * <p>
     * Al ser estático y {@code synchronized}, el monitor asociado es la
     * propia clase {@code Main.class}, de modo que el acceso está
     * sincronizado entre todos los hilos que lo usen.
     * </p>
     *
     * @param mensaje texto a mostrar como log
     */
    public static synchronized void log(String mensaje) {
        System.out.println("[LOG estático] " + mensaje);
    }
}
