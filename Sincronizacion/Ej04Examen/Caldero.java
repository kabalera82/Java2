package Ej04Examen;

// Clase que representa el caldero compartido por todos los alumnos
public class Caldero {
    // Nivel actual de ingredientes del caldero (número de ingredientes añadidos)
    private int nivelIngredientes= 0;

    // Método sincronizado: solo un hilo/alumno puede entrar a la vez
    public synchronized void anadirIngrediente(String alumno){
        // Muestra qué alumno está añadiendo un ingrediente
        System.out.println(alumno+" alumno añade un ingrediente");
        try {
            // Simula el tiempo que tarda en añadir el ingrediente (300-700 ms)
            Thread.sleep(300 + (int) (Math.random() * 400));
        } catch (InterruptedException e) {
            // Si el hilo es interrumpido, se lanza una excepción en tiempo de ejecución
            throw new RuntimeException(e);
        }
        // Zona crítica: se incrementa el nivel de ingredientes del caldero
        nivelIngredientes++; //<--zona critica
        // Muestra el nivel actual tras añadir el ingrediente
        System.out.println("Nivel actual del caldero: "+ nivelIngredientes);
    }

    // Devuelve el nivel total de ingredientes del caldero
    public int getNivelIngredientes(){
        return nivelIngredientes;
    }
}
