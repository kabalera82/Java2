import java.util.Map;

public class Ej05VarEntorno {

    /*
    Crea un programa que utilice .environment() para listar todas las variables de entorno
    del sistema
    .environment() -> Es un método de ProcessBuilder que te devuelve un mapa de las variables de entorno que va a tener el proceso hijo.
     */


    public static void main(String[] args) {

        listarVariables();

    }

    private static void listarVariables(){
        //Creamos el process Builder
        ProcessBuilder proceso = new ProcessBuilder();
        // Obtenemos el mapa de variables dee entorno en un Map
        Map<String, String> entorno = proceso.environment();

        // Recoremos todas las claves del mapa

        for(String clave : entorno.keySet()) {
            System.out.println(clave + " = " + entorno.get(clave));
        }
    }

}
