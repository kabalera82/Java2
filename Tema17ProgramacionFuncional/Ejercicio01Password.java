package Tema17ProgramacionFuncional;

import javax.swing.*;
import java.util.stream.IntStream;

public class Ejercicio01Password {

    public static void main(String[] args) {
        String pass = JOptionPane.showInputDialog("Save your pass"); // "Pide y guarda la contraseña"
        boolean check = IntStream.range(0, 3) // "Permite hasta 3 intentos"
                .mapToObj(i -> JOptionPane.showInputDialog("Insert your password")) // "Solicita la contraseña en cada intento"
                .anyMatch(input -> pass.equals(input)); // "Retorna true si alguna entrada es correcta"

        // Declaración de mensajes y parámetros según el resultado
        String mensaje = !check
                ? "Too many failed attempts.\nSystem Blocked" // "FALSE: Mensaje de error"
                : "System unlocked";                          // "TRUE: Mensaje de éxito"
        String titulo = !check ? "Error" : "Éxito";           // "FALSE: Título error, TRUE: Título éxito"
        int tipoMensaje = !check
                ? JOptionPane.ERROR_MESSAGE                   // "FALSE: Icono error"
                : JOptionPane.INFORMATION_MESSAGE;            // "TRUE: Icono información"

        // Muestra el mensaje correspondiente
        JOptionPane.showMessageDialog(
                null,      // No hay ventana padre
                mensaje,   // Mensaje a mostrar
                titulo,    // Título de la ventana
                tipoMensaje // Tipo de mensaje (error o información)
        );
    }
}
