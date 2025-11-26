package Ej04Examen;

public class Caldero {
    private int nivelIngredientes= 0;

    public synchronized void anadirIngrediente(String alumno){
        System.out.println(alumno+" alumno añade un ingrediente");
        try {
            Thread.sleep(300 + (int) (Math.random() * 400));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        nivelIngredientes++; //<--zona critica
        System.out.println("Nivel actual del caldero: "+ nivelIngredientes);
    }

    public int getNivelIngredientes(){
        return nivelIngredientes;
    }
}
