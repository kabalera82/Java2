package Ej02Sioncriced;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // Una única cuenta compartida por varios hilos
        Cuenta c1 = new Cuenta("Cuenta-Compartida");

        // Dos tareas que usan la MISMA cuenta
        // a1 usará el método sincronizado incrementar()
        // a2 usará el bloque sincronizado incrementar2()
        ClaseA a1 = new ClaseA(c1, true);
        ClaseA a2 = new ClaseA(c1, false);

        Thread t1 = new Thread(a1, "Hilo-A");
        Thread t2 = new Thread(a2, "Hilo-B");

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("==================================");
        System.out.println("Valor final en " + c1.getNombre()
                + " = " + c1.getContador());
        System.out.println("==================================");

        // Si quisieras, podrías crear otra cuenta distinta:
        // Cuenta c2 = new Cuenta("Cuenta-2");
        // y verías que su contador evoluciona independientemente
        // porque cada objeto tiene su propio "candado" (this).
    }

    // 3) MÉTODO ESTÁTICO SINCRONIZADO
    //    -> El monitor es la CLASE Main.class, no una instancia concreta.
    public static synchronized void log(String mensaje) {
        System.out.println("[LOG estático] " + mensaje);
    }
}
