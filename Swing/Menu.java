import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Menu {

    public static void main(String[] args) {

        // --------------   VENTANA PRINCIPAL -----------------------------------

        // Creamos la ventana principal
        JFrame ventana = new JFrame("Todo List");
        // ventana.setUndecorated(true); --> esto quita el marco a la ventana
        // tamaño de la ventana
        ventana.setSize(600, 400);
        // cruz de cierre
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Organizamos los paneles con Borderlayout
        //hgap = 10 px → separación izquierda/derecha
        //vgap = 10 px → separación arriba/abajo
        ventana.setLayout(new BorderLayout(10, 10));
        // color al fondo
        ventana.getContentPane().setBackground(new Color(15, 160, 90));

        // ------------  PANEL SUPERIOR  ---------------------------------------------

        // Campo para escribir las tareas
        JTextField campoTareas = new JTextField();
        // Boton para añadir tareas
        JButton botonAdd = new JButton("Agregar Tarea");
        //Cambiamos la fuente al boton
        botonAdd.setFont(new Font("Monospaced", Font.BOLD, 16));
        // Color del fondo del boton
        botonAdd.setBackground(new Color(100, 149, 237));
        // Color del texto del boton
        botonAdd.setForeground(new Color(170, 240, 205));
        // Activa u oculta el borde cuando el boton tiene el foco
        botonAdd.setFocusPainted(true);
        // Panel superior con separación interna
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        // Color del fondo del panel superior
        panel.setBackground(new Color(200, 250, 120));
        // Añadimos al panel el campo tareas y boton
        panel.add(campoTareas, BorderLayout.CENTER);
        panel.add(botonAdd, BorderLayout.EAST);
        // Panel superior colocado arriba
        ventana.add(panel, BorderLayout.NORTH);

        // ------------------------  Tabla CENTRAL ------------------------------------

        // Encabezados
        String[] columnas = {"ID", "Tarea", "Fecha", "Estado"};

        // Modelo de la tabla ( define tipos y permisos de edición)
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0) {
            // sobrescritura de las columnas para que JTable sepa que tipo de dato tiene
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return Boolean.class; // La columna 4 usan checkbox (boolean)
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3; // Solo editable la columna del checkbox
            }
        };

    // Añadimos las columnas modelo al Jtable
        JTable table = new JTable(modelo);
        table.setFont(new Font("Monospaced", Font.PLAIN, 16));









    // Para que sea visible

        ventana.setVisible(true);

    }


}
