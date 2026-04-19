package src;

import src.gameConstants.GameConstants;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    @Override
    protected void paintComponent(Graphics g){

        super.paintComponent(g);
        // Background
                g.setColor(GameConstants.TABLE_BACKGROUND);
                g.fillRect(0, 0, getWidth(), getHeight());

        // Table Tiles
        g.setColor(GameConstants.BORDER_TILE);
        for (int row = 0; row < GameConstants.ROWS; row++)
            for (int col = 0; col < GameConstants.COLUMNS; col++)
                g.drawRect( GameConstants.BOARD_X+ col*GameConstants.TILE_WIDTH,
                            GameConstants.BOARD_Y + row * GameConstants.TILE_HEIGHT,
                            GameConstants.TILE_WIDTH, GameConstants.TILE_HEIGHT);
    }

}
