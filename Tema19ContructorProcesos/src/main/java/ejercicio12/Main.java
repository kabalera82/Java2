package Tema19ContructorProcesos.src.main.java.ejercicio12;

import java.util.Map;

public class Main {
    public static void main(String[] args) {

        try {
            ProcessBuilder pb = new ProcessBuilder();
            Map<String, String> entorno = pb.environment();

            System.out.println("Variables de entorno del sistema: ");

            for(String clave : entorno.keySet()){
                System.out.println(clave+ " = " + entorno.get(clave));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
