public class JTextfieldEjemplo {

    /*
    ====================================================
    JTEXTFIELD - RESUMEN, PROPIEDADES Y EJEMPLOS DE USO
    ====================================================

    ----------------------------------------
    1. ¿QUÉ ES UN JTEXTFIELD?
    ----------------------------------------
    JTextField es un componente de Swing que permite
    ingresar una línea de texto. Se usa para formularios,
    buscadores, entradas rápidas, etc.

    Se puede:
    - escribir texto
    - leer texto
    - limitar caracteres
    - cambiar fuente, color y tamaño
    - añadir listeners (Enter, cambios, etc.)

    ----------------------------------------
    2. PRINCIPALES PROPIEDADES
    ----------------------------------------

    .setText(String)
        Establece el texto del campo.

    .getText()
        Devuelve el texto escrito por el usuario.

    .setColumns(int)
        Define ancho sugerido (número de caracteres visibles).

    .setEditable(boolean)
        Habilita/deshabilita edición. Ej: setEditable(false)

    .setBackground(Color)
        Cambia el color de fondo.

    .setForeground(Color)
        Cambia el color del texto.

    .setFont(Font)
        Cambia la fuente.

    .setHorizontalAlignment(int)
        Alineación del texto:
        - JTextField.LEFT
        - JTextField.CENTER
        - JTextField.RIGHT

    .addActionListener(...)
        Detecta cuando el usuario pulsa ENTER.

    .setBorder(Border)
        Cambia el borde visual.

    .setToolTipText(String)
        Texto emergente al pasar el ratón.

    ----------------------------------------
    3. EJEMPLOS DE USO BÁSICO
    ----------------------------------------

    // Crear un campo vacío
    JTextField campo = new JTextField();

    // Campo con texto inicial
    JTextField campo = new JTextField("Escribe aquí...");

    // Definir anchura (visible)
    campo.setColumns(20);

    // Obtener lo que escribió el usuario
    String texto = campo.getText();

    // Cambiar fondo, texto y fuente
    campo.setBackground(new Color(230,230,250));
    campo.setForeground(Color.BLACK);
    campo.setFont(new Font("SansSerif", Font.PLAIN, 14));

    // Alinear texto
    campo.setHorizontalAlignment(JTextField.CENTER);

    ----------------------------------------
    4. EJEMPLO CON EVENTO ENTER
    ----------------------------------------

    JTextField campo = new JTextField(15);

    campo.addActionListener(e -> {
        System.out.println("Has pulsado ENTER. Texto: " + campo.getText());
    });

    ----------------------------------------
    5. EJEMPLO INSERTADO EN UN PANEL
    ----------------------------------------

    JPanel panel = new JPanel();
    JTextField campo = new JTextField(20);
    panel.add(campo);

    ----------------------------------------
    6. NOTAS IMPORTANTES
    ----------------------------------------
    - JTextField solo permite UNA línea de texto.
    - Para varias líneas -> usar JTextArea.
    - Los columns no garantizan píxeles exactos: es una "sugerencia".
    - Se integra muy bien con GridLayout, FlowLayout y BorderLayout.

    ----------------------------------------
    FIN DEL DOCUMENTO
    ----------------------------------------

     */


}
