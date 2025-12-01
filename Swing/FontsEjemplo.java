public class FontsEjemplo {

    /*
    ============================================================
    FUENTES EN JAVA (GARANTIZADAS + CONSOLAS + EJEMPLOS DE USO)
    ============================================================
    - titulo.setFont(new Font("SansSerif", Font.BOLD, 24)); -
    ----------------------------------------
    1. FUENTES GARANTIZADAS POR JAVA
    (Siempre disponibles en cualquier sistema)
    ----------------------------------------
    Serif
    SansSerif
    Monospaced
    Dialog
    DialogInput

    ----------------------------------------
    2. FUENTES TIPO CONSOLA (muy comunes)
    (No garantizadas al 100%, pero casi siempre presentes)
    ----------------------------------------
    Courier New
    Consolas
    Lucida Console
    DejaVu Sans Mono
    Ubuntu Mono
    JetBrains Mono
    Fira Code
    Source Code Pro

    ----------------------------------------
    3. MODIFICADORES DE FUENTE
    ----------------------------------------
    Font.PLAIN      → normal
    Font.BOLD       → negrita
    Font.ITALIC     → cursiva

    Los modificadores se pueden COMBINAR:
    Font.BOLD | Font.ITALIC

    ----------------------------------------
    4. EJEMPLOS DE USO (GARANTIZADOS)
    ----------------------------------------

    new Font("Serif", Font.PLAIN, 16);
    new Font("SansSerif", Font.BOLD, 18);
    new Font("Monospaced", Font.ITALIC, 14);
    new Font("Dialog", Font.PLAIN, 15);
    new Font("DialogInput", Font.BOLD, 16);

    ----------------------------------------
    5. EJEMPLOS DE USO (CONSOLAS)
    ----------------------------------------

    // Consolas comunes (si existen en tu sistema)
    new Font("Courier New", Font.PLAIN, 16);
    new Font("Consolas", Font.BOLD, 15);
    new Font("Lucida Console", Font.PLAIN, 14);
    new Font("DejaVu Sans Mono", Font.PLAIN, 16);
    new Font("Ubuntu Mono", Font.BOLD, 17);
    new Font("JetBrains Mono", Font.PLAIN, 16);
    new Font("Fira Code", Font.ITALIC, 18);
    new Font("Source Code Pro", Font.PLAIN, 16);

    ----------------------------------------
    6. EJEMPLOS COMBINANDO ESTILO + TAMAÑO
    ----------------------------------------
    new Font("SansSerif", Font.BOLD | Font.ITALIC, 20);
    new Font("Monospaced", Font.PLAIN, 12);
    new Font("Courier New", Font.BOLD, 18);
    new Font("Consolas", Font.PLAIN, 22);

    ----------------------------------------
    7. EJEMPLO APLICADO A COMPONENTES SWING
    ----------------------------------------
    JLabel titulo = new JLabel("Título");
    titulo.setFont(new Font("SansSerif", Font.BOLD, 24));

    JButton boton = new JButton("Aceptar");
    boton.setFont(new Font("Consolas", Font.PLAIN, 16));

    JTextArea texto = new JTextArea();
    texto.setFont(new Font("Monospaced", Font.PLAIN, 14));

    JTable tabla = new JTable();
    tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
    tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

    ----------------------------------------
    8. NOTA IMPORTANTE
    ----------------------------------------
    - La única fuente consola 100% garantizada es "Monospaced".
    - Las demás funcionan si están instaladas en tu sistema operativo.
    - Si una fuente no existe, Java selecciona otra automáticamente.

    FIN DEL DOCUMENTO


     */
}
