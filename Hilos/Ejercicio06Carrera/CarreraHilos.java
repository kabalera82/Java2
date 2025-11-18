package Ejercicio06Carrera;

public class CarreraHilos implements Runnable {

    private int distancia = 0;
    private final String nombre;
    private static final int DISTANCIA_MAXIMA = 100;

    // Recurso compartido estático para registrar al primer ganador
    private static String ganador = null;

    public CarreraHilos(String nombre){
        this.nombre = nombre;
    }

    @Override
    public void run(){
        // Bucle de la carrera: avanza hasta la meta
        while (distancia < DISTANCIA_MAXIMA){

            // 1. Lógica de avance (mínimo 1, máximo 4 metros como indica el comentario)
            // Genera un número entre 1 y 4 (inclusive)
            int avance = (int) (Math.random() * 4 + 1);
            distancia += avance;

            // Aseguramos que la distancia no exceda la máxima (aunque no es estrictamente necesario aquí)
            if (distancia > DISTANCIA_MAXIMA) {
                distancia = DISTANCIA_MAXIMA;
            }

            System.out.println(nombre + " ha avanzado " + avance + "m. Distancia total: " + distancia + "m.");

            // 2. Comprobación de la victoria
            if (distancia >= DISTANCIA_MAXIMA) {
                // Bloque sincronizado para garantizar que solo un hilo actualice 'ganador'
                synchronized (CarreraHilos.class) {
                    if (ganador == null) {
                        ganador = nombre;
                        System.out.println("\n🎉 ¡¡¡EL GANADOR ES: " + nombre + "!!! 🎉");
                        // Aquí podría lanzar una interrupción para detener a los demás hilos si fuera necesario
                    }
                }
                // Si ya hay un ganador, este hilo simplemente termina su bucle y sale
                break;
            }

            // 3. Pausa simulada
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // Esto maneja si alguien interrumpe el hilo (p.ej., para detener la carrera)
                System.out.println(nombre + " ha sido interrumpido y ha terminado en " + distancia + "m.");
                return; // Termina el método run si es interrumpido
            }
        }
    }
}