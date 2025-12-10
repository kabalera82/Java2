package Teoría;

import repasometodos.HiloEsperador;
import repasometodos.HiloNotificador;
import repasometodos.HiloNotificadorTodos;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        /**
         * Problema Productor–Consumidor (modelo clásico de concurrencia)
         *
         * OBJETIVOS:
         * 1. El productor NO debe producir cuando el buffer está lleno.
         * 2. El consumidor NO debe consumir cuando el buffer está vacío.
         *
         * MECANISMOS QUE SE USAN:
         * - Monitores (Java: métodos sincronizados)
         * - Secciones críticas (uso de synchronized)
         * - Métodos wait() → hilo espera hasta que una condición se cumpla
         * - Métodos notify()/notifyAll() → despertar hilos que esperan
         *
         * ELEMENTOS DEL PROBLEMA:
         * 1. Productores → generan datos y los depositan en el buffer.
         * 2. Consumidores → extraen los datos del buffer y los procesan.
         * 3. Buffer común → recurso compartido que debe estar protegido.
         * 4. Monitor / mecanismo de sincronización → coordina acceso al buffer.
         *
         * IDEA PRINCIPAL:
         * - Los hilos deben comunicarse entre sí para no bloquearse ni corromper datos.
         * - El monitor controla cuándo puede producirse y cuándo puede consumirse.
         *
         * MONITOR:
         * Es un objeto que controla el acceso a un recurso compartido donde solo un hilo se puede estar ejecutando (baño).
         * El resto debe estar esperando.
         *
         * Cuiando usar notify y notifyAll
         * Usa notify() si se cumple:
         * Solo hay un consumidor o un productor esperando.
         * Solo uno de los hilos en espera puede hacer progreso.
         * No quieres despertar a todos para evitar peleas innecesarias por el monitor.
         *
         * Usa notifyAll() si se cumple:
         * Hay varios productores y/o varios consumidores esperando.
         * No sabes cuál de todos los hilos debe seguir.
         * Las condiciones son complejas y quieres asegurarte de que todos se reevalúen.
         */

        Object monitor = new Object();

        // Creamos 3 hilos que esperan
        Thread e1 = new Thread(new HiloEsperador(monitor), "Hilo-1");
        Thread e2 = new Thread(new HiloEsperador(monitor), "Hilo-2");
        Thread e3 = new Thread(new HiloEsperador(monitor), "Hilo-3");

        e1.start();
        e2.start();
        e3.start();

        // Damos tiempo para que todos hagan wait()
        Thread.sleep(1000);

        // PROBAR UN SOLO NOTIFY()
        Thread notificador1 = new Thread(new HiloNotificador(monitor), "Notificador-UNO");
        notificador1.start();

        // Esperamos para ver el efecto de notify()
        Thread.sleep(1000);

        // PROBAR NOTIFYALL()
        Thread notificadorTodos = new Thread(new HiloNotificadorTodos(monitor), "Notificador-TODOS");
        notificadorTodos.start();

    }
}
