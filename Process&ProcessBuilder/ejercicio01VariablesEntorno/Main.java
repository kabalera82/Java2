package ejercicio01VariablesEntorno;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        // Crea un ProcessBuilder vacío (sin comando)
        ProcessBuilder pb = new ProcessBuilder();

        // Obtiene el mapa de variables de entorno del sistema
        Map<String, String> entorno = pb.environment();

        System.out.println("Variables de entorno del sistema:");

        // Recorre todas las claves del mapa y muestra cada variable con su valor
        for (String clave : entorno.keySet()) {
            System.out.println(clave + " = " + entorno.get(clave));
        }
    }

}
