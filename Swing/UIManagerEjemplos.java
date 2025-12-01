public class UIManagerEjemplos {



    /*
    ====================================================
    UIMANAGER - RESUMEN, PROPIEDADES Y EJEMPLOS DE USO
    ====================================================

    ----------------------------------------
    1. ¿QUÉ ES UIMANAGER?
    ----------------------------------------
    UIManager es una clase de Swing que permite definir
    propiedades globales de estilo para todos los componentes
    de la aplicación (fuentes, colores, bordes...).

    Se usa para:
    - Aplicar estilos a toda la interfaz
    - Evitar repetir estilos botón por botón
    - Unificar apariencia de toda la app Swing

    ----------------------------------------
    2. APLICAR ESTILOS GLOBALES
    ----------------------------------------

    // Se hace ANTES de crear la ventana o los componentes
    UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 16));
    UIManager.put("Button.background", new Color(0, 120, 215));
    UIManager.put("Button.foreground", Color.WHITE);

    UIManager.put("Label.font", new Font("Dialog", Font.PLAIN, 14));
    UIManager.put("TextField.font", new Font("Monospaced", Font.PLAIN, 13));
    UIManager.put("ToolTip.background", new Color(255,255,200));

    ----------------------------------------
    3. CAMBIAR LOOK & FEEL COMPLETO (opcional)
    ----------------------------------------

    // Cambiar el tema visual general (antes de crear la ventana)
    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");

    // Otros disponibles:
    // "Metal", "Nimbus", "Motif", "Windows", "WindowsClassic"

    ----------------------------------------
    4. LISTAR TODAS LAS CLAVES DISPONIBLES
    ----------------------------------------

    // Útil para ver qué propiedades puedes modificar
    for (Object key : UIManager.getLookAndFeelDefaults().keySet()) {
        System.out.println(key);
    }

    ----------------------------------------
    5. EJEMPLO COMPLETO
    ----------------------------------------

    public static void main(String[] args) throws Exception {
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 16));
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);

        JFrame ventana = new JFrame("Demo UIManager");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(300, 150);

        JPanel panel = new JPanel();
        JButton boton = new JButton("Aceptar");
        panel.add(boton);

        ventana.add(panel);
        ventana.setVisible(true);
    }

    ----------------------------------------
    6. NOTAS IMPORTANTES
    ----------------------------------------
    - Las claves deben aplicarse antes de crear componentes.
    - Cambia TODAS las instancias del mismo tipo (JButton, JLabel...).
    - Ideal para aplicar un tema global uniforme.
    - Se puede combinar con clases personalizadas o estilos locales.
    - Puedes crear tu propio "tema Swing" visual si combinas UIManager con LookAndFeel.

    ----------------------------------------
    FIN DEL DOCUMENTO
    ----------------------------------------

     */
}

