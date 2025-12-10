package Tema19ContructorProcesos.src.main.java.ejercicio10;

public class Main {

    public static void main(String[] args) {

        GestorProcesos.ejecutarComando("dir");
        GestorProcesos.redirigirSalida("ipconfig", "salida.txt");
        GestorProcesos.mostrarErrores("comandoInexistente");
    }
}
