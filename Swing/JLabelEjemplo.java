public class JLabelEjemplo {

    /*
     ===========================================================
     JLABEL - RESUMEN, PROPIEDADES Y EJEMPLOS DE USO
     ===========================================================

     -----------------------------------------------------------
     1. ¿QUÉ ES UN JLABEL?
     -----------------------------------------------------------
     JLabel es un componente de Swing que sirve para mostrar
     texto, iconos o ambos a la vez.

     ✔ No es editable por el usuario.
     ✔ Ideal para títulos, descripciones, avisos o imágenes.
     ✔ Se puede cambiar fuente, color, alineación, icono, etc.

     -----------------------------------------------------------
     2. CREAR UN JLABEL
     -----------------------------------------------------------
     Ejemplos:
         JLabel label = new JLabel("Hola Mundo");
         JLabel label = new JLabel("Cargando...", SwingConstants.CENTER);
         JLabel label = new JLabel(new ImageIcon("logo.png"));

     -----------------------------------------------------------
     3. CAMBIAR EL TEXTO
     -----------------------------------------------------------
         label.setText("Nuevo mensaje");

     -----------------------------------------------------------
     4. CAMBIAR COLOR Y FUENTE
     -----------------------------------------------------------
         label.setForeground(Color.BLUE); // color del texto
         label.setFont(new Font("Arial", Font.BOLD, 16)); // estilo y tamaño

     Otros ejemplos:
         label.setFont(new Font("SansSerif", Font.ITALIC, 14));
         label.setForeground(new Color(120, 80, 200)); // color personalizado

     -----------------------------------------------------------
     5. CAMBIAR ICONO
     -----------------------------------------------------------
         label.setIcon(new ImageIcon("imagen.png"));

     Texto + icono:
         label.setText("Descargar");
         label.setIcon(new ImageIcon("icono.png"));
         label.setHorizontalTextPosition(SwingConstants.RIGHT); // texto a la derecha

     -----------------------------------------------------------
     6. ALINEACIÓN DEL TEXTO
     -----------------------------------------------------------
         label.setHorizontalAlignment(SwingConstants.CENTER);
         label.setVerticalAlignment(SwingConstants.TOP);

     Otros valores:
         SwingConstants.LEFT
         SwingConstants.RIGHT
         SwingConstants.BOTTOM

     -----------------------------------------------------------
     7. AJUSTAR TAMAÑO DEL JLABEL
     -----------------------------------------------------------
         label.setPreferredSize(new Dimension(200, 40));

     -----------------------------------------------------------
     8. BORDES Y ESTILOS
     -----------------------------------------------------------
         label.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

     Otros bordes:
         BorderFactory.createEtchedBorder()
         BorderFactory.createEmptyBorder(10, 10, 10, 10)
         BorderFactory.createTitledBorder("Título")

     -----------------------------------------------------------
     9. JLABEL TRANSPARENTE
     -----------------------------------------------------------
         label.setOpaque(false);  // útil para fondos custom

     Para colorearlo:
         label.setOpaque(true);
         label.setBackground(Color.YELLOW);

     -----------------------------------------------------------
     EJEMPLO COMPLETO
     -----------------------------------------------------------

     JLabel titulo = new JLabel("Gestor de Tareas", SwingConstants.CENTER);
     titulo.setFont(new Font("Arial", Font.BOLD, 20));
     titulo.setForeground(new Color(60, 80, 140));
     titulo.setIcon(new ImageIcon("icono.png"));
     titulo.setHorizontalTextPosition(SwingConstants.RIGHT);
     titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
     titulo.setOpaque(true);
     titulo.setBackground(new Color(230, 230, 255));

     */



}
