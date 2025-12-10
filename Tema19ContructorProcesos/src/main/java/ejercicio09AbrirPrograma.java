package Tema19ContructorProcesos.src.main.java;

public class ejercicio09AbrirPrograma {

    public static void main(String[] args) {

        try {
            ProcessBuilder pb = new ProcessBuilder("notepad.exe");
            Process p = pb.start();
            System.out.println("Block de notas abierto");
            p.waitFor();
            System.out.println("Bloc de notas cerrado");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
