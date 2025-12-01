public class BorderLayoutEjemplo {

    /*
    BORDERLAYOUT - RESUMEN COMPLETO + NOTAS + EJEMPLO

    ----------------------------------------------------
    1. QUÉ ES BORDERLAYOUT
    ----------------------------------------------------
    BorderLayout es un gestor de diseño que divide un contenedor
    en 5 zonas principales:

    - NORTH  → Zona superior
    - SOUTH  → Zona inferior
    - EAST   → Lado derecho
    - WEST   → Lado izquierdo
    - CENTER → Centro (ocupa lo que queda)

    Se usa para organizar interfaces con estructura clara
    (arriba, abajo, centro, etc.).

    ----------------------------------------------------
    2. SINTAXIS
    ----------------------------------------------------
    setLayout(new BorderLayout(hgap, vgap));

    - hgap = separación horizontal entre componentes.
    - vgap = separación vertical entre componentes.

    Si no se indican → hgap=0, vgap=0.

    ----------------------------------------------------
    3. REGLAS IMPORTANTES
    ----------------------------------------------------
    • Solo puede haber UN componente por región.
      Si añades otro en la misma zona, se reemplaza.

    • NORTH y SOUTH se expanden a LO ANCHO, pero no hacia abajo.

    • EAST y WEST se expanden a LO ALTO, pero no hacia los lados.

    • CENTER es la zona MÁS importante:
      - se expande en ancho y alto
      - ocupa todo lo restante
      - si no se especifica región al añadir un componente,
        se coloca automáticamente en CENTER.

    • Los gaps (hgap, vgap) solo separan ENTRE regiones,
      NO crean margen externo contra la ventana.
      Si quieres margen externo:
      panel.setBorder(new EmptyBorder(10,10,10,10));

    • Normalmente se combina con paneles secundarios
      (con otros layouts) para crear interfaces completas.

    ----------------------------------------------------
    4. POR QUÉ SE USA MUCHO
    ----------------------------------------------------
    Es ideal para ventanas con:
    - Barra superior
    - Área central grande (tablas, paneles)
    - Botonera inferior
    - Barras laterales opcionales (menús, filtros)

    Muy común en GUIs profesionales.

    ----------------------------------------------------
    5. EJEMPLO DE USO COMPLETO
    ----------------------------------------------------

    import javax.swing.*;
    import java.awt.*;

    public class EjemploBorderLayout {

        public static void main(String[] args) {

            JFrame ventana = new JFrame("Ejemplo BorderLayout");
            ventana.setSize(500, 400);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Establecer BorderLayout con separación de 10px
            ventana.setLayout(new BorderLayout(10, 10));

            // Crear componentes
            JButton arriba = new JButton("NORTH / Arriba");
            JButton abajo = new JButton("SOUTH / Abajo");
            JButton derecha = new JButton("EAST / Derecha");
            JButton izquierda = new JButton("WEST / Izquierda");
            JButton centro = new JButton("CENTER / Centro");

            // Añadirlos en sus regiones
            ventana.add(arriba, BorderLayout.NORTH);
            ventana.add(abajo, BorderLayout.SOUTH);
            ventana.add(derecha, BorderLayout.EAST);
            ventana.add(izquierda, BorderLayout.WEST);
            ventana.add(centro, BorderLayout.CENTER);

            ventana.setVisible(true);
        }
    }

    ----------------------------------------------------
    6. DIAGRAMA VISUAL
    ----------------------------------------------------

    +------------------- NORTH ------------------------+
    |                                                   |
    +-------- WEST ----------+----------- EAST ---------+
    |                        |                          |
    |                        |                          |
    |        CENTER          |                          |
    |                        |                          |
    +------------------------+--------------------------+
    |                      SOUTH                        |
    +---------------------------------------------------+

    ----------------------------------------------------
    7. CUÁNDO NO USARLO
    ----------------------------------------------------
    BorderLayout NO es buena opción si:
    - necesitas una rejilla (⇒ usa GridLayout)
    - necesitas alinear vertical/horizontal (⇒ BoxLayout)
    - necesitas posición absoluta (⇒ null layout, poco recomendable)

    ----------------------------------------------------
    FIN DEL DOCUMENTO
    ----------------------------------------------------

    */

}
