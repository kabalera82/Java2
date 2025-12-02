import javax.swing.*;
import java.awt.*;

public class Ej01Saludo {

    public static final String TEXTO = "HOLA";

    public static void main(String[] args) {
        //ejemplo 1
        usandoJFrame();
        //ejemplo 2
        // usandoJLabel();  --> incluido en usandoJFrame
        /*
        ejemplo 3
        usando un JButton
        ejemplo 4
        capturando un evento
        - ejemplo 5
        Agregar JTextFiel --> campo de texto para ingresar datos
        con un boton que use ese nombre para decir Hola
         */
    }

    public static void usandoJFrame (){
        // Creamos la ventana principal
        JFrame ventana = new JFrame("Calculadora");
        // ventana.setUndecorated(true); --> esto quita el marco a la ventana
        // tamaño de la ventana
        ventana.setSize(600,400);
        ventana.getContentPane().setBackground(new Color(50,220,220, 255)); // Hay que pintar el contenido
        ventana.add(usandoJLabel(), BorderLayout.CENTER); // añadir el JLabel y ocupa la zona central del JFrame3
        ventana.setLayout(null); //sino el boton se pone done le sale de los huevos
        ventana.add(usandoJButton());
        ventana.add(insertaTexto());
        // cruz de cierre
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    public static JLabel usandoJLabel () {
         // Por defecto utiliza todo el espacio disponible el layout de JFrame ws BorderLayout, y si lo añadimos ocupa todo el espacio.
         JLabel texto = new JLabel(TEXTO);
         texto.setForeground(new Color(255, 100, 100, 255));
         texto.setHorizontalAlignment(SwingConstants.CENTER);  // centro horizontal
         texto.setVerticalAlignment(SwingConstants.CENTER);    // centro vertical
         texto.setFont(new Font("Arial", Font.BOLD, 20));
         return texto;
    }

    public static JButton usandoJButton () {
        JButton boton = new JButton("haz click aqui");
        boton.setBounds(100,320,150,30); // x y ancho y alto
        boton.setBackground(new Color(0, 120, 215));
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Monospaced", Font.BOLD, 14));
        boton.setFocusPainted(false);
        //crear el evento
        boton.addActionListener(e->{
            JOptionPane.showMessageDialog(null,"boton presionado");
        });
     return boton;
    }

    public static JPanel insertaTexto (){
        JPanel panel = new JPanel();
        panel.setLayout(null); // Para permitir setBounds dentro del panel
        panel.setBounds(0,0,600,400);

        JButton boton = new JButton("insertar");
        boton.setFont(new Font("Monospaced", Font.BOLD, 14));
        boton.setBounds(260,320,150,30);
        boton.setBackground(new Color(0, 120, 215));
        boton.setForeground(Color.WHITE);

        JTextField campoTexto = new JTextField();
        campoTexto.setFont(new Font("Monospaced", Font.BOLD, 14));
        campoTexto.setBounds(260,280,150,30);
        campoTexto.setBackground(new Color(255, 100, 100, 255));
        campoTexto.setForeground(Color.WHITE);

        boton.addActionListener(e -> {
            String texto = campoTexto.getText();
            JOptionPane.showMessageDialog(null, TEXTO + " " + texto);
        });

        panel.add(boton);
        panel.add(campoTexto);

        return panel;
    }

}
