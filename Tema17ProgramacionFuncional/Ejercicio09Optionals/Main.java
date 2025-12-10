package Tema17ProgramacionFuncional.Ejercicio09Optionals;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Optional<String> stringNull = Optional.ofNullable(null);

        if (stringNull.isEmpty()) {
            System.out.println("El valor esta vacío");
        }
        if (stringNull == null) {
            System.out.println("El valor es null");
        }

        //Puede devolver un null o puede devolver un vacío
        Optional<String> cadena = Optional.of("Vacio"); //Si devuelvo un null me saldra expception
        if (cadena.isEmpty()) {
            System.out.println("cadena esta vacía");
        } else {
            System.out.println("tiene un valor");
        }

        //Devolver un valor vaio
        Optional<String> vacia = Optional.empty();
        if (vacia.isEmpty()) {
            System.out.println("Es vacía");
        } else {
            System.out.println("Tiene un valor");
        }
    }

}
