public class JButtonEjemplo {

    /*

    ====================================================
    JBUTTON - PROPIEDADES BÁSICAS Y EJEMPLOS
    ====================================================

    JButton boton = new JButton("Texto inicial");

    1) setText(String)
       - Cambia el texto que se muestra en el botón.
       Ejemplo:
           boton.setText("Aceptar");

    2) setEnabled(boolean)
       - Activa o desactiva el botón (si está desactivado queda gris y no se puede pulsar).
       Ejemplos:
           boton.setEnabled(false);   // desactivado
           boton.setEnabled(true);    // activado

    3) setBackground(Color)
       - Cambia el color de fondo del botón.
       Ejemplo:
           boton.setBackground(new Color(100, 149, 237));  // azul

    4) setForeground(Color)
       - Cambia el color del texto del botón.
       Ejemplo:
           boton.setForeground(Color.WHITE);  // texto blanco

    5) setFont(Font)
       - Cambia la fuente (tipo, estilo y tamaño) del texto del botón.
       Ejemplo:
           boton.setFont(new Font("SansSerif", Font.BOLD, 16));

    6) setToolTipText(String)
       - Muestra un texto emergente al pasar el ratón por encima del botón.
       Ejemplo:
           boton.setToolTipText("Haz clic para guardar");

    7) setBorder(Border)
       - Cambia el borde visual del botón.
       Ejemplo:
           boton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
       // Otros posibles:
       // boton.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
       // boton.setBorder(BorderFactory.createEtchedBorder());

    8) setFocusPainted(boolean)
       - Muestra u oculta el borde de foco (el contorno cuando el botón tiene el foco).
       Ejemplo:
           boton.setFocusPainted(false);  // oculta el borde de foco

    9) setIcon(Icon)
       - Asigna un icono al botón (normalmente a la izquierda del texto).
       Ejemplo:
           ImageIcon icono = new ImageIcon("guardar.png");
           boton.setIcon(icono);

    10) setHorizontalTextPosition(int)
        - Controla la posición horizontal del texto respecto al icono.
        Ejemplo:
           boton.setHorizontalTextPosition(SwingConstants.RIGHT);
           // texto a la derecha del icono

    11) setVerticalTextPosition(int)
        - Controla la posición vertical del texto respecto al icono.
        Ejemplo:
           boton.setVerticalTextPosition(SwingConstants.BOTTOM);
           // texto debajo del icono

    12) addActionListener(ActionListener)
        - Define qué pasa cuando se hace clic en el botón (o ENTER/ESPACIO con foco).
        Ejemplo:
           boton.addActionListener(e -> {
               System.out.println("Botón pulsado");
           });

    ====================================================
    EJEMPLO RESUMEN
    ====================================================

    JButton guardar = new JButton("Guardar");
    guardar.setBackground(new Color(0, 120, 215));
    guardar.setForeground(Color.WHITE);
    guardar.setFont(new Font("SansSerif", Font.BOLD, 14));
    guardar.setFocusPainted(false);
    guardar.setToolTipText("Guarda el archivo actual");
    guardar.addActionListener(e -> {
        System.out.println("Guardado correcto");
    });

    JButton guardar = new JButton("Guardar");
    // Crea un botón con el texto visible "Guardar"

    guardar.setBackground(new Color(0, 120, 215));
    // Cambia el color de fondo del botón (azul Windows 10)

    guardar.setForeground(Color.WHITE);
    // Cambia el color del texto del botón (blanco)

    guardar.setFont(new Font("SansSerif", Font.BOLD, 14));
    // Cambia la fuente del texto: SansSerif, negrita, tamaño 14

    guardar.setFocusPainted(false);
    // Elimina el borde azul que aparece al seleccionar el botón (más estético)

    guardar.setToolTipText("Guarda el archivo actual");
    // Muestra un texto emergente cuando se pasa el ratón por encima

    guardar.addActionListener(e -> {
        // Añade un evento que se ejecuta al pulsar el botón (clic, Enter o Espacio)
        System.out.println("Guardado correcto");
        // Acción que se ejecuta cuando se pulsa: muestra un mensaje por consola
    });


    ===========================================================
    FORMAS DE CAMBIAR EL ESTILO DE BOTONES EN SWING
    (GLOBALMENTE o DE FORMA INDIVIDUAL)
    ===========================================================

    -----------------------------------------------------------
    1. CAMBIAR ESTILO DE TODOS LOS BOTONES CON UIManager
    -----------------------------------------------------------
    Esto aplica un estilo global a TODOS los botones de la app.

    Debe colocarse ANTES de crear los botones.

    Ejemplo:
        UIManager.put("Button.font", new Font("SansSerif", Font.BOLD, 16));
        UIManager.put("Button.background", Color.BLACK);
        UIManager.put("Button.foreground", Color.WHITE);

    ✔ Se aplica automáticamente a todos los botones.
    ✔ Muy útil para un estilo uniforme en toda la interfaz.

    -----------------------------------------------------------
    2. CAMBIAR ESTILO DE BOTONES ESPECÍFICOS (usando setName)
    -----------------------------------------------------------
    Puedes identificar botones individuales con setName() y
    luego aplicarles estilo manualmente si el nombre coincide.

    Ejemplo:

        JButton boton = new JButton("Guardar");
        boton.setName("botonImportante");

        if ("botonImportante".equals(boton.getName())) {
            boton.setFont(new Font("Arial", Font.BOLD, 14));
            boton.setBackground(Color.RED);
        }

    ✔ Ideal si tienes pocos botones a personalizar.
    ✔ No afecta al resto.
    ✔ El nombre es solo una etiqueta interna (no se ve en pantalla).

    -----------------------------------------------------------
    CONSEJO PRÁCTICO
    -----------------------------------------------------------
    Usa UIManager para un estilo general.

    Usa setName() o clases personalizadas para excepciones
    (botones destacados, críticos, con color distinto, etc).



*/



}
