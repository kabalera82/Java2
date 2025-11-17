package ejercicio07GestorProcesos;

public class Main {
    public static void main(String[] args) {

        // Este ejercicio define una clase GestorProcesos con tres métodos:
        // - ejecutarComando(): ejecuta un comando y muestra su salida normal.
        // - redirigirSalida(): envía la salida del comando directamente a un archivo.
        // - mostrarErrores(): muestra únicamente los errores del comando.

        // Es una versión estructurada y reutilizable del uso de ProcessBuilder.



        GestorProcesos g = new GestorProcesos();
        g.ejecutarComando("dir");
        g.redirigirSalida("ipconfig","salida.txt");
        g.mostrarErrores("comandoInexistente");
    }
}

