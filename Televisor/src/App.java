package src;

import javax.swing.*;

import static src.ui.MenuTelevision.menuCajero;

public class App {
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> menuCajero());

    }
}