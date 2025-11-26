package Ej04Examen;

// Clase que representa a un alumno que trabajará con el caldero en un hilo
public class Alumno implements Runnable {
    // Referencia al caldero compartido entre todos los alumnos
    private Caldero caldero;
    // Nombre del alumno (para identificarlo en los mensajes)
    private String nombre;

    // Constructor: recibe el caldero y el nombre del alumno
    public Alumno(Caldero c, String nombre) {
        this.caldero = c;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // Cada alumno añade 5 ingredientes al caldero
        for (int i = 0; i < 5; i++) {
            // Llama al método del caldero para añadir un ingrediente
            // pasando su nombre para mostrarlo por pantalla
            caldero.anadirIngrediente(nombre);
        }
    }
}
