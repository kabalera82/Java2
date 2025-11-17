package ejercicio04NotePad;

public class Main {
    public static void main(String[] args) {

        // Este programa abre el Bloc de notas usando ProcessBuilder.
        // Al llamar a p.waitFor(), el programa Java queda bloqueado hasta que
        // el usuario cierre Notepad. Después continúa y muestra un mensaje.

        try {
            // Crea un proceso para abrir el Bloc de notas
            ProcessBuilder pb = new ProcessBuilder("notepad.exe");

            // Inicia el proceso (abre Notepad)
            Process p = pb.start();
            System.out.println("Bloc de notas abierto");

            // waitFor() bloquea el programa hasta que el usuario cierre Notepad
            p.waitFor();
            System.out.println("Bloc de notas cerrado");

        } catch (Exception e) {
            e.printStackTrace(); // Muestra el error si algo falla
        }

    }

}
