package src.ui;

import src.model.EscritorTask;
import javax.swing.*;
import java.awt.*;

public class PanelInfo extends JPanel {
    private JTextArea areaTexto;
    private Thread hiloActual;

    public PanelInfo() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.BLACK);

        /* * AJUSTE DE POSICIÓN (A ojo para mover arriba e izquierda):
         * Bajamos los valores de Top (era 150 -> ahora 60)
         * y Left (era 262 -> ahora 100)
         */
        this.setBorder(BorderFactory.createEmptyBorder(60, 100, 200, 350));

        areaTexto = new JTextArea();
        areaTexto.setBackground(Color.BLACK);
        areaTexto.setForeground(Color.GREEN);
        areaTexto.setFont(new Font("Monospaced", Font.BOLD, 18));
        areaTexto.setEditable(false);
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);

        // --- EL TRUCO DEL SCROLL INVISIBLE ---
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setBorder(null); // Sin bordes feos
        scroll.setBackground(Color.BLACK);
        // Escondemos las barras para que parezca una pantalla plana
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scroll, BorderLayout.CENTER);
    }

    public void ejecutarMensaje(String mensaje) {
        if (hiloActual != null && hiloActual.isAlive()) {
            hiloActual.interrupt();
        }
        // Pasamos el área de texto a la tarea
        EscritorTask tarea = new EscritorTask(mensaje, areaTexto, 40);
        hiloActual = new Thread(tarea);
        hiloActual.start();
    }
}