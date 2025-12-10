package Tema17ProgramacionFuncional.Ejercicio08ArrayAsList;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    /*
     * Vamos a gestionar uin listado de vehiculos. tendremos Marca, Modelo, Precio
     * Ordenar la lista de vehiculos por precio de menor a mayor
     * Ordenar al mismo tiempo tanto por marca como por precio.
     * Extraer una lista de todos los vehiculos cuyo precio no supere los 23000
     * Filñtrar unicamente los vehiculos de marca Chevrolet y Volkswagen
     * Mostrar los coches cuyo modelo contengan por lo menos un letra "a"
     */

    public static void main(String[] args) {


        List<Coche> coches = Arrays.asList(
                new Coche("Toyota", "Corolla", 19500),
                new Coche("Ford", "Focus", 18500),
                new Coche("Volkswagen", "Golf", 22000),
                new Coche("Peugeot", "208", 17500),
                new Coche("Renault", "Clio", 17000),
                new Coche("Seat", "Ibiza", 16000),
                new Coche("BMW", "Serie 1", 28000),
                new Coche("Mercedes-Benz", "Clase A", 32000),
                new Coche("Audi", "A3", 30000),
                new Coche("Kia", "Ceed", 19000),
                new Coche("Hyundai", "i30", 19500),
                new Coche("Nissan", "Micra", 16500),
                new Coche("Fiat", "Punto", 15000),
                new Coche("Citroën", "C3", 17000),
                new Coche("Mazda", "3", 21000),
                new Coche("Skoda", "Octavia", 23000),
                new Coche("Opel", "Astra", 18500),
                new Coche("Honda", "Civic", 25000),
                new Coche("Tesla", "Model 3", 42000),
                new Coche("Dacia", "Sandero", 13500)
        );

        // 1. Ordenamos por precio de menos a mayor
        System.out.println("------------------------------------ PUNTO 1 ------------------------------------------------");
        // Vamos a hacer uso el metodo sort que es un metodo para ordenar
        //sort es un metodo propio de las listas es sobrescribir la lista original por eso creamos una lista original

        List<Coche> ordenPrecio = new ArrayList<Coche>(coches); //Le paso la lista de coche

        // dentro del sort hacemos la comparacion con COMPARATOR.comparing y le decimos el atributos por lo que hacemos referencia al metodo
        ordenPrecio.sort(Comparator.comparing(Coche::getPrecio));
        ordenPrecio.forEach(System.out::println);


        System.out.println("------------------------------------ PUNTO 2 ------------------------------------------------");


        // 2. Ordenar por marca y luego por precio usando sorted()
        // Sorted pertence a la clase stream por que es para un momento y no se reemplaza

        coches.stream()
                .sorted(Comparator
                        .comparing(Coche::getMarca)
                        .thenComparing(Coche::getPrecio))
                .forEach(System.out::println);


        System.out.println("------------------------------------ PUNTO 3 ------------------------------------------------");

        // 3. Coches cuyo precio no pase de los 23000


        coches.stream()
                .filter(coche -> coche.getPrecio() >= 23000)
                .forEach(System.out::println);

        System.out.println("------------------------------------ PUNTO 4 ------------------------------------------------");

        // 4. Coches de marca Chevrolet o Volkswagen

        coches.stream()
                .filter(coche -> coche.getMarca().equals("BMW") || coche.getMarca().equals("Toyota"))
                .forEach(System.out::println);

        System.out.println("------------------------------------ PUNTO 5 ------------------------------------------------");

        // 5. Coches cuyo modelo contien al menos una "a"

        coches.stream()
                .filter(coche -> coche.getMarca().toLowerCase().contains("w"))
                .forEach(System.out::println);
    }

}
