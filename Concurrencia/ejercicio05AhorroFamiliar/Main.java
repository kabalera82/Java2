package ejercicio05AhorroFamiliar;

/**
 * objetivo: ENTENDER exclusión mutua simple
 * Contexto
 * Una familia mete dinero en una hucha.
 * Reglas
 * 3 miembros aportan dinero.
 * Cada ingreso es aleatorio (1–200).
 * La hucha tiene objetivo fijo (6000).
 * Solo una persona puede ingresar cada vez.
 * Debes lograr
 * Identificar el recurso compartido: saldo.
 * Marcar la sección crítica: leer → sumar → guardar.
 * Asegurar que nunca se “pierde dinero”.

 */


public class Main {
    public static void main(String[] args) throws InterruptedException {

        Hucha hucha = new Hucha();

        Thread t1 = new Thread(new MiembroFamilia(hucha), "Madre");
        Thread t2 = new Thread(new MiembroFamilia(hucha), "Padre");
        Thread t3 = new Thread(new MiembroFamilia(hucha), "Hijo");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("\nObjetivo: " + hucha.getObjetivo());
        System.out.println("Saldo final: " + hucha.getSaldo());
    }
}

