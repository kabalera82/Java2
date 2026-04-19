
public class Main {
    /**
     * 7. Ejercicio: Tres tareas que deben ejecutarse en orden
     * Tienes 3 hilos:
     * Hilo 1 debe ejecutar primero.
     * Hilo 2 debe esperar a que el 1 termine → join.
     * Hilo 3 debe esperar al 2 → join.
     * Cada hilo imprime un mensaje.
     */
    public static void main(String[] args) {

        // Creamos un Array de Hilos de la clase Thread
        Thread[] hilos = new Thread[3];

        // Primero bucle para configurar los hilos
        for(int i=0; i< hilos.length; i++){
            //Necesitamos una variable final para poder usarla dentro de una expresion lambda del hilo
            final int index = i;

            hilos[i] = new Thread(() -> {
                try {
                    // si el indice es mayor que cero
                    if(index> 0){
                        // El hilos de index -1 debe unirse y esperar
                        hilos[index -1].join();
                    }

                    System.out.println("Mensaje desde el hilo " + (index + 1));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
        // Segundo bucle para iniciar todos los hilos
        for(int i=0; i < hilos.length; i++){
            hilos[i].start();
        }



    }
}
