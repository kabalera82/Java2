package ejercicio3018;

/**
 * 18. Ejercicio: Sistema de registro
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
        Validacion v = new Validacion();
        Registro r = new Registro();
        Carpeta c = new Carpeta();
        Email e = new Email();

        Thread t1 = new Thread(r);
        Thread t2 = new Thread(v);
        Thread t3 = new Thread(c);
        Thread t4 = new Thread(e);
        try {
            t1.start();
            t1.join();
            t2.start();
            t2.join();
            t3.start();
            t3.join();
            t4.start();
            t4.join();

        }catch (Exception exception){
            System.out.println("Interummpido");
        }

    }
}
