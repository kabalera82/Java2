public class JPanelEjemplo {
    /*
     ===========================================================
    JPANEL - TIPOS, ESTILOS Y EJEMPLOS DE USO
    ===========================================================

    -----------------------------------------------------------
    1. ¿QUÉ ES UN JPANEL?
    -----------------------------------------------------------
    JPanel es un contenedor genérico que se usa para organizar
    y agrupar componentes (botones, campos, etc.).

    ✔ No tiene borde ni fondo visible por defecto.
    ✔ Puede tener su propio layout, fondo, borde y tamaño.
    ✔ Se puede anidar (paneles dentro de paneles).

    -----------------------------------------------------------
    2. CAMBIAR COLOR DE FONDO
    -----------------------------------------------------------
    Ejemplo:
        JPanel panel = new JPanel();
        panel.setBackground(new Color(220, 220, 250));  // color suave

    -----------------------------------------------------------
    3. CAMBIAR BORDE
    -----------------------------------------------------------
    Ejemplo:
        panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

    Otros bordes:
        BorderFactory.createTitledBorder("Sección")
        BorderFactory.createEmptyBorder(10,10,10,10)
        BorderFactory.createEtchedBorder()

    -----------------------------------------------------------
    4. CAMBIAR LAYOUT DEL PANEL
    -----------------------------------------------------------
    Ejemplo:
        panel.setLayout(new FlowLayout());        // por defecto
        panel.setLayout(new BorderLayout());      // zonas
        panel.setLayout(new GridLayout(2, 2));    // rejilla
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // vertical

    -----------------------------------------------------------
    5. AÑADIR COMPONENTES AL PANEL
    -----------------------------------------------------------
    Ejemplo:
        panel.add(new JButton("Aceptar"));
        panel.add(new JTextField(10));

    -----------------------------------------------------------
    6. CAMBIAR TAMAÑO DEL PANEL
    -----------------------------------------------------------
    Ejemplo:
        panel.setPreferredSize(new Dimension(300, 200));

    También puedes usar:
        panel.setMinimumSize(...)
        panel.setMaximumSize(...)

    -----------------------------------------------------------
    7. PANEL CON NOMBRE PARA PERSONALIZACIÓN SELECTIVA
    -----------------------------------------------------------
    panel.setName("panelIzquierdo");

    if ("panelIzquierdo".equals(panel.getName())) {
        panel.setBackground(Color.LIGHT_GRAY);
    }

    -----------------------------------------------------------
    8. PANEL TRANSPARENTE
    -----------------------------------------------------------
    panel.setOpaque(false);  // útil para superponer cosas o fondo personalizado

    -----------------------------------------------------------
    EJEMPLO COMPLETO
    -----------------------------------------------------------

    JPanel panel = new JPanel(new FlowLayout());
    panel.setBackground(new Color(240,240,240));
    panel.setBorder(BorderFactory.createTitledBorder("Opciones"));
    panel.add(new JButton("OK"));
    panel.setName("panelPrincipal");

    if ("panelPrincipal".equals(panel.getName())) {
        panel.setPreferredSize(new Dimension(400,100));
    }


     */
}
