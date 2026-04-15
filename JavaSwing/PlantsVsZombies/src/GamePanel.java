package src;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        // fondo verde
                g.setColor(Color.GREEN);
                g.fillRect(0, 0, getWidth(), getHeight());

        // Cuadricula del tablero
        g.setColor(Color.BLACK);
        for (int row = 0; row < 6; row++)
            for (int col = 0; col < 10; col++)
                g.drawRect(80 + col*80, 100 + row*90, 80, 90);


    }

}
