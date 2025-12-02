import javax.swing.*;
import java.awt.*;

public class Ej02EstilosGlobales {

    private static final String TEXTO = "HOLA MUNDO";
    private static final Font FUENTE_GLOBAL = new Font("Monospaced", Font.BOLD, 16);
    private static final Color COLOR_BOTON_FONDO = new Color(0, 120, 215);
    private static final Color COLOR_BOTON_TEXTO = Color.WHITE;
    private static final Color COLOR_FIELD_FONDO = new Color(255, 100, 100);
    private static final Color COLOR_FIELD_TEXTO = Color.WHITE;
    private static final Color COLOR_LABEL_TEXTO = new Color(255, 100, 100);
    private static final Color COLOR_FONDO_VENTANA = new Color(50, 220, 220);



    public static void main(String[] args) {
        configuradorEstilos();
        usandoJFrame();
    }

    public static void configuradorEstilos(){

        // ------ LABEL ------
        UIManager.put("Label.font", FUENTE_GLOBAL);
        UIManager.put("Label.foreground", COLOR_LABEL_TEXTO);

        // ------ BOTONES ------
        UIManager.put("Button.font", FUENTE_GLOBAL);
        UIManager.put("Button.background", COLOR_BOTON_FONDO);
        UIManager.put("Button.foreground", COLOR_BOTON_TEXTO);
        UIManager.put("Button.focusPainted", false);

        // ------ CAMPOS DE TEXTO ------
        UIManager.put("TextField.font", FUENTE_GLOBAL);
        UIManager.put("TextField.background", COLOR_FIELD_FONDO);
        UIManager.put("TextField.foreground", COLOR_FIELD_TEXTO);
        UIManager.put("TextField.caretForeground", COLOR_FIELD_TEXTO);

    }

    public static void usandoJFrame() {
        JFrame ventana = new JFrame("Calculadora");
        ventana.setSize(600, 400);
        ventana.getContentPane().setBackground(COLOR_FONDO_VENTANA);
        ventana.setLayout(null); // posiciones absolutas
        ventana.add(usandoJLabel());
        ventana.add(usandoJButton());
        ventana.add(insertaTexto());
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }

    public static JLabel usandoJLabel() {
        JLabel texto = new JLabel(TEXTO);
        texto.setBounds(0, 30, 600, 40);
        texto.setHorizontalAlignment(SwingConstants.CENTER);
        return texto;
    }

    public static JButton usandoJButton() {
        JButton boton = new JButton("haz click aqui");
        boton.setBounds(100, 320, 150, 30);
        boton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "botón presionado");
        });
        return boton;
    }

    public static JPanel insertaTexto() {
        JPanel panel = new JPanel(null);
        panel.setBounds(150, 200, 300, 120);
        panel.setBackground(COLOR_FONDO_VENTANA);
        JTextField campoTexto = new JTextField();
        campoTexto.setBounds(75, 10, 150, 30);  // centrar dentro del panel
        JButton boton = new JButton("insertar");
        boton.setBounds(75, 55, 150, 30);       // centrar bajo el campo
        boton.addActionListener(e -> {
            String texto = campoTexto.getText();
            JOptionPane.showMessageDialog(null, TEXTO + " " + texto);
        });
        panel.add(campoTexto);
        panel.add(boton);
        return panel;
    }

}
