package src;

import src.gameConstants.*;
import src.GamePanel;


import javax.swing.*;
import java.awt.*;

public class Main {


    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            JFrame jFrame = new JFrame("Plantas vs Zombies");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setSize(GameConstants.WINDOW_WIDTH, GameConstants.WINDOW_HEIGHT);
            jFrame.setResizable(false);

            GamePanel panel = new GamePanel(); // Creamos el panel
            jFrame.add(panel);

            jFrame.setVisible(true);
        });
    }
}
