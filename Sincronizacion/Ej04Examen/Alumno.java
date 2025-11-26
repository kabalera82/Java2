package Ej04Examen;

public class Alumno implements Runnable {
    private Caldero caldero;
    private String nombre;

    public Alumno(Caldero c, String nombre) {
        this.caldero = c;
        this.nombre = nombre;
    }

    @Override
    public void run() {
        // Cada alumno añade 5 ingredientes al caldero
        for (int i = 0; i < 5; i++) {
            caldero.anadirIngrediente(nombre);
        }
    }
}
