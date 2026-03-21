package ejercicio06Cajero;

/**
 * Cajero que no puede quedar en negativo
 * objetivo: operación indivisible (comprobar + restar)
 * Contexto
 * Varias personas retiran dinero del mismo cajero.
 * Reglas
 * Saldo inicial fijo (por ejemplo 2000).
 * Retiradas aleatorias (20–200).
 * Varios hilos retiran simultáneamente.
 * Debes lograr
 * Que “comprobar saldo” y “restar” sea una sola acción.
 * Nunca saldo negativo.
 * Ningún retiro fantasma.
 * Comprobación
 * El saldo nunca baja de 0.
 */

public class Main {

    public static void main(String[] args) {

        Cajero cajero = new Cajero();

        Thread t1 = new Thread(new Persona(cajero),"uno");
        Thread t2 = new Thread(new Persona(cajero),"dos");
        Thread t3 = new Thread(new Persona(cajero),"tres");

        t1.start();
        t2.start();
        t3.start();
    }
}

