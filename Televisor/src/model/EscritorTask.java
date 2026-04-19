package src.model;

import javax.swing.*;

public class EscritorTask implements Runnable {
    private String texto;
    private JTextArea destino;
    private int velocidad;

    // Al instanciarla, le pasamos "el papel" (destino) donde escribir
    public EscritorTask(String texto, JTextArea destino, int velocidad) {
        this.texto = texto;
        this.destino = destino;
        this.velocidad = velocidad;
    }

    @Override
    public void run() {
        destino.setText(""); // Limpiamos la pantalla antes de empezar

        char[] letras = texto.toCharArray();
        for (char letra : letras) {
            // Verificamos si alguien ha mandado parar el hilo
            if (Thread.currentThread().isInterrupted()) return;

            // Mandamos la letra a la interfaz de forma segura
            SwingUtilities.invokeLater(() -> destino.append(String.valueOf(letra)));

            try {
                Thread.sleep(velocidad);
            } catch (InterruptedException e) {
                // Si nos interrumpen mientras dormimos, salimos ordenadamente
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}