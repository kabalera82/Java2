package src.ui;

import src.model.CajeroDiccionario;
import javax.swing.*;
import java.awt.*;

public class MenuTelevision {

    public static void menuCajero() {
        JFrame frame = new JFrame("Cajero Automático");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1024, 720);
        frame.getContentPane().setBackground(Color.DARK_GRAY);

        // --- Panel de Capas ---
        JLayeredPane panelCapas = new JLayeredPane();

        // CAPA INFERIOR (NIVEL 0): LA INTERFAZ REAL
        JPanel capaInterfaz = new JPanel(new BorderLayout());
        capaInterfaz.setBackground(Color.BLACK);
        capaInterfaz.setBounds(0, 0, 1024, 720);

        // --- Título Superior ---
        JLabel titulo = new JLabel("ATM SYSTEM - v1.0", SwingConstants.CENTER);
        titulo.setForeground(Color.GREEN);
        titulo.setFont(new Font("consolas", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(110, 0, 20, 180));
        capaInterfaz.add(titulo, BorderLayout.NORTH);

        // --- La Pantalla de Info (Tu Panel que habla) ---
        PanelInfo pantallaInfo = new PanelInfo();
        capaInterfaz.add(pantallaInfo, BorderLayout.CENTER);

        // Añadimos la interfaz al fondo (Nivel 0)
        panelCapas.add(capaInterfaz, Integer.valueOf(0));

        // CAPA SUPERIOR (NIVEL 1): EL MARCO FÍSICO (PNG)
        ImageIcon imagenTV = new ImageIcon("C:\\Marcos\\Java\\Java2\\Cajero\\resources\\pantalla.png");
        JLabel marcoTV = new JLabel(imagenTV) {
            @Override
            public boolean contains(int x, int y) {
                return false; // Los clics pasan a través hacia los botones
            }
        };
        marcoTV.setBounds(0, 0, 1024, 720);
        panelCapas.add(marcoTV, Integer.valueOf(1));

        // Montamos todo
        frame.add(panelCapas);
        frame.setVisible(true);

        // --- ¡EL MOMENTO DE LA VERDAD! ---
        // Llamamos al diccionario para que el cajero empiece a hablar
        pantallaInfo.ejecutarMensaje(CajeroDiccionario.BIENVENIDA);
    }
}