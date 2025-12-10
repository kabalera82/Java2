package Tema17ProgramacionFuncional.Ejercicio06RefenciaMetodo.ReferenciaConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {

        //Referencia a un constructor
        // Interfaz funcional pasamos tipos de parametros y la salida de contructor
        BiFunction<String, Double, Persona> crearPersona = Persona::new;

        Persona persona = crearPersona.apply("Maria", 1.54);
        System.out.println("La persona es: " + persona);

        //Referencia a un metodo de un objeto arbitrario (Arraylist)
        List<Persona> personas = new ArrayList<>();
        personas.add(new Empleado());
        personas.add(new Gerente());
        personas.add(new Empleado());
        personas.add(new Gerente());

        personas.forEach(Persona::saludar);


    }
}
