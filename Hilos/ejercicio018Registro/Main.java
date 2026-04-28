/**
 * Ejercicio: Sistema de registro
 * Crea 4 hilos:
 * Registrar usuario (2s)
 * Validar correo (1.5s)
 * Crear carpeta personal (1s)
 * Enviar email de bienvenida (1s)
 * El orden obligatorio:
 * Registrar → Validar → Carpeta → Email
 * Usa join() encadenado + sleep().
 */

public class Main {

    public static void main(String[] args) {


        Thread t1 = new Thread (new Carpeta(),"carpeta");
        Thread t2 = new Thread (new Carpeta(),"registro");
        Thread t3 = new Thread (new Carpeta(),"validar");
        Thread t4 = new Thread (new Carpeta(),"email");





    }
}
