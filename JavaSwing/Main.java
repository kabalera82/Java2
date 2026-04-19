import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Main {

    // 1. ¿Qué es Java Swing?

    /* Swing es una biblioteca incluida en Java para crear interfaces gráficas (GUI):
       ventanas, botones, textos, tablas, listas, etc.
       Cada elemento visual se llama "componente".
       Todos estos componentes están en el paquete javax.swing.
     */

    // 2. Crear la primera ventana (JFrame)
    /* Un JFrame es la ventana principal de cualquier aplicación Swing.
       Siempre necesitamos uno para mostrar contenido.
    */
    public static void usandoJFrame() {
        JFrame ventana = new JFrame("Mi primera ventana"); // Crear ventana con título
        ventana.setSize(400, 300); // Establecer tamaño (ancho, alto)
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar la app al pulsar la X
        ventana.setVisible(true); // Hacer visible la ventana
    }

    // 3. Agregar un JLabel (texto)
    /*
       JLabel sirve para mostrar texto en pantalla.
       Si no se cambia el layout del JFrame (BorderLayout por defecto),
       el único componente ocupa todo el espacio.
     */
    public static void usandoJLabel() {
        JFrame ventana = new JFrame("Mi primera ventana");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel texto = new JLabel("Hola DAM!"); // Crear texto
        ventana.add(texto); // Añadirlo a la ventana

        ventana.setVisible(true);
    }

    // 4. Agregar JButton + capturar eventos
    /*
       JButton es un botón.
       Para saber cuándo el usuario lo pulsa, usamos ActionListener.
     */
    public static void usandoJButtonEvento() {
        JFrame ventana = new JFrame("Mi primera ventana");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton boton = new JButton("Haz click aquí"); // Crear botón
        boton.setBounds(120, 70, 150, 30); // Posición y tamaño (requiere layout null)
        ventana.add(boton);

        // Evento del botón: cuando se pulsa, se muestra un mensaje
        boton.addActionListener(e -> {
            JOptionPane.showMessageDialog(ventana, "Botón presionado");
        });

        JLabel texto = new JLabel("Hola DAM!");
        ventana.add(texto);

        ventana.setVisible(true);
    }

    // 5. JTextField: campo de texto
    /*
      Permite que el usuario escriba texto. En el ejemplo,
      se combina con un botón que muestra un saludo personalizado.
     */
    public static void usandoJTextField() {
        JFrame ventana = new JFrame("Mi primera ventana");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(null); // Usar posiciones absolutas

        JLabel etiqueta = new JLabel("Ingresa tu nombre:");
        etiqueta.setBounds(50, 30, 150, 25);
        ventana.add(etiqueta);

        JButton boton = new JButton("Saludar");
        boton.setBounds(140, 80, 80, 20);
        ventana.add(boton);

        JTextField campoTexto = new JTextField(); // Campo para escribir
        campoTexto.setBounds(180, 30, 100, 25);
        ventana.add(campoTexto);

        // Evento: toma el texto escrito y muestra un saludo
        boton.addActionListener(e -> {
            String nombre = campoTexto.getText();
            JOptionPane.showMessageDialog(ventana, "Hola " + nombre + "!");
        });

        ventana.setVisible(true);
    }

    // 6. Tipos de Layouts (organizadores de componentes)
    /*
       setLayout(null) -> posiciones manuales. No recomendable.
       FlowLayout -> coloca componentes en fila.
       BorderLayout -> 5 zonas: NORTH, SOUTH, EAST, WEST, CENTER.
       GridLayout -> crea una tabla uniforme de filas x columnas.
       BoxLayout -> coloca componentes en columna o fila.
     */

    public static void usandoFlowLayout() {
        JFrame ventana = new JFrame("FlowLayout");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new FlowLayout()); // Layout en fila

        ventana.add(new JButton("Botón 1"));
        ventana.add(new JButton("Botón 2"));
        ventana.add(new JButton("Botón 3"));

        ventana.setVisible(true);
    }

    public static void usandoGridLayout() {
        JFrame ventana = new JFrame("GridLayout");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new GridLayout(4, 2, 5, 5)); // Tabla 4x2

        ventana.add(new JButton("Botón 1"));
        ventana.add(new JButton("Botón 2"));
        ventana.add(new JButton("Botón 3"));
        ventana.add(new JButton("Botón 4"));

        ventana.setVisible(true);
    }

    public static void usandoBorderLayout() {
        JFrame ventana = new JFrame("BorderLayout");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        ventana.add(new JButton("Norte"), BorderLayout.NORTH);
        ventana.add(new JButton("sur"), BorderLayout.SOUTH);
        ventana.add(new JButton("este"), BorderLayout.EAST);
        ventana.add(new JButton("oeste"), BorderLayout.WEST);
        ventana.add(new JButton("centro"), BorderLayout.CENTER);

        ventana.setVisible(true);
    }

    public static void usandoBoxLayout() {
        JFrame ventana = new JFrame("BoxLayout");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout en columna vertical
        ventana.setLayout(new BoxLayout(ventana.getContentPane(), BoxLayout.Y_AXIS));

        ventana.add(new JButton("One"));
        ventana.add(new JButton("Two"));
        ventana.add(new JButton("Three"));
        ventana.add(new JButton("Four"));

        ventana.setVisible(true);
    }

    // Combinación de varios layouts en una misma ventana
    public static void usandoVariosLayout() {
        JFrame ventana = new JFrame("Combination Layout");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        // Panel superior con FlowLayout por defecto
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Resultado:"));
        topPanel.add(new JTextField(10));
        ventana.add(topPanel, BorderLayout.NORTH);

        // Panel central tipo GridLayout (ideal para una calculadora)
        JPanel centerPanel = new JPanel(new GridLayout(4, 4));
        centerPanel.add(new JButton("1"));
        centerPanel.add(new JButton("2"));
        centerPanel.add(new JButton("3"));
        centerPanel.add(new JButton("4"));
        centerPanel.add(new JButton("5"));
        centerPanel.add(new JButton("6"));
        centerPanel.add(new JButton("7"));
        centerPanel.add(new JButton("8"));
        centerPanel.add(new JButton("9"));
        centerPanel.add(new JButton("C"));
        ventana.add(centerPanel, BorderLayout.CENTER);

        ventana.setVisible(true);
    }

    // JList simple
    public static void usandoJList() {
        JFrame ventana = new JFrame("JList");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] frutas = {"Apple", "Orange", "pear", "Lemon"};

        JList<String> lista = new JList<>(frutas); // Lista estática
        JScrollPane scroll = new JScrollPane(lista); // Añadir scroll
        ventana.add(scroll);

        ventana.setVisible(true);
    }

    // JTable simple
    public static void usandoJTable() {
        JFrame ventana = new JFrame("JTable");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[][] datos = {
                {"1", "Juan", "25"},
                {"2", "Ana", "30"},
                {"3", "Luis", "29"}
        };
        String[] columnas = {"ID", "Nombre", "Edad"};

        JTable tabla = new JTable(datos, columnas); // Tabla estática
        JScrollPane scroll = new JScrollPane(tabla);
        ventana.add(scroll);

        ventana.setVisible(true);
    }

    // Lista dinámica usando DefaultListModel
    public static void usandoListaDinamica() {
        JFrame ventana = new JFrame("JList + JButton");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout(5, 5));

        DefaultListModel<String> modelo = new DefaultListModel<>(); // Lista editable
        JList<String> lista = new JList<>(modelo);
        JScrollPane scrollPane = new JScrollPane(lista);
        ventana.add(scrollPane, BorderLayout.CENTER);

        JTextField campo = new JTextField();
        JButton botonAdd = new JButton("Agregar");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(campo, BorderLayout.CENTER);
        panel.add(botonAdd, BorderLayout.EAST);
        ventana.add(panel, BorderLayout.NORTH);

        JButton botonMostrar = new JButton("Mostrar selección");
        ventana.add(botonMostrar, BorderLayout.SOUTH);

        // Añadir texto a la lista
        botonAdd.addActionListener(e -> {
            String texto = campo.getText();
            if (!texto.isEmpty()) {
                modelo.addElement(texto);
                campo.setText("");
            }
        });

        // Mostrar elemento seleccionado
        botonMostrar.addActionListener(e -> {
            String seleccionado = lista.getSelectedValue();
            if (seleccionado != null) {
                JOptionPane.showMessageDialog(ventana, "✏️ Seleccionaste: " + seleccionado);
            } else {
                JOptionPane.showMessageDialog(ventana, "❌ No hay selección");
            }
        });

        ventana.setVisible(true);
    }


    // ------------------------------
    // APP FINAL: JTable interactiva
    // ------------------------------
    public static void main(String[] args) {

        JFrame ventana = new JFrame("JTable interactiva");
        ventana.setSize(400, 300);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout(5, 5));

        // Panel superior: campo + botón
        JTextField campo = new JTextField();
        JButton botonAdd = new JButton("Agregar Tarea");
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(campo, BorderLayout.CENTER);
        panel.add(botonAdd, BorderLayout.EAST);
        ventana.add(panel, BorderLayout.NORTH);

        // Tabla con modelo dinámico (permite añadir/editar/borrar filas)
        String[] columnas = {"ID", "Tarea"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0); // 0 filas
        JTable table = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(table);
        ventana.add(scroll, BorderLayout.CENTER);

        // Panel inferior con botones
        JPanel panelBotones = new JPanel(new FlowLayout());
        JButton editar = new JButton("Editar");
        JButton eliminar = new JButton("Eliminar");
        panelBotones.add(editar);
        panelBotones.add(eliminar);
        ventana.add(panelBotones, BorderLayout.SOUTH);

        // Evento: añadir tarea
        botonAdd.addActionListener(e -> {
            String tarea = campo.getText().trim();
            if (!tarea.isEmpty()) {
                int id = modelo.getRowCount() + 1; // Generar ID automático
                modelo.addRow(new Object[]{id, tarea});
                campo.setText("");
            }
        });

        // Evento: editar tarea seleccionada
        editar.addActionListener(e -> {
            int fila = table.getSelectedRow(); // Obtener fila seleccionada
            if (fila != -1) {
                String nuevaTarea = JOptionPane.showInputDialog(
                        ventana, "Editar tarea:", modelo.getValueAt(fila, 1)
                );
                if (nuevaTarea != null && !nuevaTarea.trim().isEmpty()) {
                    modelo.setValueAt(nuevaTarea.trim(), fila, 1); // Reemplazar texto
                }
            } else {
                JOptionPane.showMessageDialog(ventana, "Selecciona una tarea para editar");
            }
        });

        // Evento: eliminar tarea seleccionada
        eliminar.addActionListener(e -> {
            int fila = table.getSelectedRow();
            if (fila != -1) {
                modelo.removeRow(fila); // Eliminar fila
            } else {
                JOptionPane.showMessageDialog(ventana, "Selecciona una tarea para eliminar");
            }
        });

        ventana.setVisible(true);
    }
}
