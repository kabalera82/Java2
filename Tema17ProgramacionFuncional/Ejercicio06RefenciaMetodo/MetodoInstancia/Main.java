package Tema17ProgramacionFuncional.Ejercicio06RefenciaMetodo.MetodoInstancia;

public class Main {
    public static void main(String[] args) {

        //Referencia a la instancia
        Persona persona = new Persona();
        persona.nombre = "Maria";

        //vamos a hacer referencia al metodo
        Runnable saludo = persona::saludar;
        saludo.run();//metodo run
    }
}
