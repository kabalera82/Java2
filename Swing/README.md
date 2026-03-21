# Swing — Interfaces graficas en Java

> Tema 07 de 2ºDAM | Prerequisito: Java basico (clases, herencia, interfaces)

Swing es el framework de Java para crear interfaces graficas de escritorio (GUI). Forma parte de la JDK — no requiere dependencias externas. Aunque JavaFX es mas moderno, Swing sigue siendo parte del curriculum DAM y es muy comun en proyectos legacy.

---

## Teoria rapida

### Jerarquia de componentes clave

```
JFrame          <- Ventana principal
└── ContentPane <- Contenedor del contenido
    ├── JPanel  <- Contenedor intermedio (agrupa componentes)
    │   ├── JLabel      <- Texto / imagen estatica
    │   ├── JButton     <- Boton clickable
    │   ├── JTextField  <- Campo de texto de una linea
    │   └── JTextArea   <- Campo de texto multilínea
    └── JMenuBar        <- Barra de menus
        └── JMenu       <- Menu desplegable
            └── JMenuItem <- Opcion del menu
```

### Layouts — como se posicionan los componentes

| Layout | Descripcion | Uso tipico |
|--------|------------|-----------|
| `BorderLayout` (default JFrame) | Norte/Sur/Este/Oeste/Centro | Layout principal con zonas |
| `FlowLayout` (default JPanel) | Horizontal, ajusta segun espacio | Botones en fila |
| `GridLayout` | Cuadricula de filas x columnas | Calculadoras, tablas |
| `BoxLayout` | Vertical u horizontal, mas flexible | Formularios |
| `null` (posicion absoluta) | `setBounds(x, y, ancho, alto)` | Prototipos rapidos |

> **Nota:** `setLayout(null)` permite posicionamiento absoluto con `setBounds()`. Es util para prototipos rapidos pero no es responsive — los componentes no se reajustan al cambiar el tamaño.

### Patron basico de evento (ActionListener)

```java
// Lambda (Java 8+, forma recomendada)
boton.addActionListener(e -> {
    // codigo que se ejecuta al hacer click
    JOptionPane.showMessageDialog(null, "Click!");
});

// Clase anonima (forma clasica)
boton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // codigo al hacer click
    }
});
```

### Hilo de eventos (EDT — Event Dispatch Thread)

**IMPORTANTE**: Toda la creacion y modificacion de componentes Swing debe hacerse en el EDT para evitar problemas de concurrencia.

```java
// Correcto: crear la ventana en el EDT
SwingUtilities.invokeLater(() -> {
    JFrame frame = new JFrame("Mi App");
    frame.setVisible(true);
});
```

---

## Archivos

| Archivo | Descripcion |
|---------|------------|
| `Ej01Saludo.java` | Ventana completa: `JFrame` + `JLabel` + `JButton` + `JTextField` con evento |
| `Ej02EstilosGlobales.java` | `UIManager` para cambiar el look and feel global |
| `Ej03Calculadora.java` | Calculadora con `GridLayout` y manejo de operaciones |
| `BorderLayoutEjemplo.java` | Demostracion de `BorderLayout` (Norte/Sur/Este/Oeste/Centro) |
| `FontsEjemplo.java` | Uso de fuentes: `Font`, tamanios, estilos |
| `JButtonEjemplo.java` | `JButton` con colores, fuente y evento |
| `JLabelEjemplo.java` | `JLabel` con texto, icono y alineacion |
| `JPanelEjemplo.java` | `JPanel` como contenedor con `FlowLayout` |
| `JTextfieldEjemplo.java` | `JTextField` con lectura de texto al presionar Enter |
| `Menu.java` | `JMenuBar` + `JMenu` + `JMenuItem` con eventos |
| `UIManagerEjemplos.java` | Cambiar look and feel: Metal, Nimbus, System |

### `Ej01Saludo.java` — ejemplo completo

Ventana unica que combina todos los componentes basicos:
- `JFrame` con fondo de color
- `JLabel` centrado con texto en negrita
- `JButton` con evento que abre un dialogo
- `JTextField` + `JButton` para capturar texto del usuario y mostrarlo en un dialogo

---

## Como ejecutar

Swing requiere un entorno grafico. No se puede ejecutar en servidores sin display.

```bash
javac -d out Swing/Ej01Saludo.java
java -cp out Ej01Saludo

# Para Ej03 Calculadora
javac -d out Swing/Ej03Calculadora.java
java -cp out Ej03Calculadora
```

---

## Referencia — Estructura minima de una app Swing

```java
import javax.swing.*;
import java.awt.*;

public class MiApp {
    public static void main(String[] args) {
        // Crear en el EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Mi Aplicacion");
            ventana.setSize(400, 300);
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel label = new JLabel("Hola Swing", SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 24));
            ventana.add(label, BorderLayout.CENTER);

            JButton boton = new JButton("Click");
            boton.addActionListener(e -> JOptionPane.showMessageDialog(ventana, "!"));
            ventana.add(boton, BorderLayout.SOUTH);

            ventana.setVisible(true);
        });
    }
}
```
