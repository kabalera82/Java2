/**
 * Una cuenta bancaria tiene un saldo inicial de 100€. Dos usuarios (representados por dos hilos independientes) tienen acceso a la misma cuenta y acuden a distintos cajeros automáticos para intentar retirar 80€ exactamente al mismo tiempo.
 * Requisitos de implementación:
 * Clase CuentaBancaria:
 * Crea una variable saldo inicializada en 100.
 * Crea el método public void retirar(int cantidad, String nombrePersona).
 * El método debe verificar si el saldo es suficiente (saldo >= cantidad).
 * Si hay saldo, el hilo debe pausarse 50 milisegundos (Thread.sleep(50)) para simular la conexión de red del cajero.
 * Tras la pausa, se debe restar la cantidad al saldo e imprimir el mensaje: "[Nombre] ha retirado [Cantidad]€. Saldo restante: [Saldo]€".
 * Si no hay saldo suficiente, se debe imprimir: "[Nombre] intentó retirar pero no hay saldo suficiente".
 * Main:
 * Crea una única instancia de la clase CuentaBancaria.
 * Crea dos hilos distintos y pásales a ambos la misma instancia de la cuenta.
 * Programa ambos hilos para que intenten retirar 80€ simultáneamente y arráncalos.
 * 🎯 Objetivo Final:
 * Al ejecutar el código sin protección,
 * observarás una "Condición de Carrera" (Race Condition) donde ambos cajeros autorizan la operación
 * y el saldo acaba en negativo (-60€).
 * Tu tarea final será aplicar los mecanismos de sincronización necesarios
 * en Java para que la cuenta sea segura y el saldo nunca baje de 0.
 */

public class Main {
    public static void main(String[] args) {

    }
}
