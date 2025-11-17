/*
 Haz un programa que lance dos programas a la vez (por ejemplo, Notepad y Calc)
 y espere a que ambos terminen antes de mostrar “Todos los procesos finalizados”.
*/

public class Ej09MultipleProcesos {

    public static void main(String[] args) {
        progSimultaneo();
    }

    public static void progSimultaneo() {

        try {

            // Lanzar Notepad directamente
            Process p1 = new ProcessBuilder("notepad.exe").start();

            // Lanzar Calculadora directamente
            Process p2 = new ProcessBuilder("calc.exe").start();

            System.out.println("Esperando a que ambos procesos terminen...");

            // Ahora sí: Espera REAL hasta que se cierren
            p1.waitFor();
            p2.waitFor();

            System.out.println("Todos los procesos finalizados");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
