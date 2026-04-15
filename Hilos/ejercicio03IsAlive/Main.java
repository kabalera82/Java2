/**
 * 3. Ejercicio: isAlive()
 * Crea un hilo que tarde 2 segundos en terminar.
 * Desde el main, antes de start(), comprueba isAlive().
 * Justo después de start(), vuelve a comprobar.
 * Después de join(), comprueba otra vez.
 */
public class Main {

    public static void main(String[] args) {

    Join tarea = new Join();
    Thread t1 = new Thread(tarea);

    //1. Comprobamos antes de start
        System.out.println("¿Estas vivo antes de start?: " + t1.isAlive());
    t1.start();

    // 2. Comprobamos despues de start
        System.out.println("¿Esta vivo despues de start?: " + t1.isAlive());
    t1.isAlive();

    try {
        // 3. Esperamos a que termine
        t1.join();

        // 4. Comprobamos otra vez
        System.out.println("¿Esta vivo después de join?:" + t1.isAlive());
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }

    }
}
