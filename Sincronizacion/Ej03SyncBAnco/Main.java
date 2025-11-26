package Ej03SyncBAnco;

import Ej03SyncBAnco.CuentaBancaria;

/**
 * Enunciado
 * =========
 *
 * Una cuenta bancaria tiene un saldo inicial de 1000 euros.
 * 3 hilos representan cajeros automáticos donde diferentes usuarios intentan sacar dinero.
 *
 * Cada hilo realiza 5 intentos de retirada con cantidades aleatorias entre 100€ y 500€.
 *
 * Requisitos:
 * 1. Crear una clase CuentaBancaria con:
 *    - Atributo: private int saldo
 *    - Constructor con saldo inicial
 *    - Método retirar(int cantidad):
 *        - Si hay saldo suficiente, realiza la retirada.
 *        - Si no, mostrar mensaje de saldo insuficiente.
 *
 * 2. Crear una clase Cajero que:
 *    - Implemente Runnable
 *    - Reciba una referencia a la misma CuentaBancaria
 *    - En su método run():
 *        - Realice 5 retiradas
 *        - Genere una cantidad aleatoria entre 100 y 500
 *        - Llame al método retirar() de la cuenta
 *        - Duerma el hilo entre 300 y 800 ms
 *
 * 3. En el main:
 *    - Crear una sola cuenta (saldo 1000€)
 *    - Crear 3 hilos cajeros (Threads) que usen la misma cuenta
 *    - Lanzarlos simultáneamente
 */



public class Main {
    public static void main(String[] args) {


        CuentaBancaria cuenta = new CuentaBancaria(1000);

        Thread t1 = new Thread(new Cajero(cuenta), "cajer - 1");
        Thread t2 = new Thread(new Cajero(cuenta), "cajer - 2");
        Thread t3 = new Thread(new Cajero(cuenta), "cajer - 3");

        t1.start();
        t2.start();
        t3.start();


    }
}
