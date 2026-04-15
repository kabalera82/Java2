package src;

import src.GamePanel;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame jFrame = new JFrame("Plantas vs Zombies");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setSize(1024,720);
            jFrame.setResizable(false);

            GamePanel panel = new GamePanel(); // Creamos el panel
            jFrame.add(panel);

            jFrame.setVisible(true);
        });
    }
}
