package Tema17ProgramacionFuncional.Ejercicio12Repaso.main;

import Tema17ProgramacionFuncional.Ejercicio12Repaso.producto.Producto;

import java.util.*;
import java.util.stream.Collectors;

/*
 * Aplicación para gestión de productos alimentarios.
 * Se trabaja con listas de productos divididos en categorías ("frutas", "lácteos", "panadería").
 * Objetivos:
 * 1. Filtrar productos por categoría
 * 2. Calcular el precio total de una categoría
 * 3. Obtener el producto más barato
 * 4. Agrupar productos por categoría
 * 5. Calcular el precio promedio por categoría y encontrar el más alto
 * 6. Usar paralelización cuando sea posible para rendimiento
 */

public class Main {

    public static void main(String[] args) {
        // 1. Obtener la lista completa de productos
        List<Producto> listaProductos = obtenerProductos(); // ✅ SOLO UNA VEZ

        // 2. Filtrar por categoría
        List<Producto> frutas = filtrarCategorias(listaProductos, "frutas");
        List<Producto> lacteos = filtrarCategorias(listaProductos, "lacteos");
        List<Producto> panaderia = filtrarCategorias(listaProductos, "panadería");

        // 3. Mostrar los productos por categoría
        System.out.println("\n--- FRUTAS ---");
        frutas.forEach(System.out::println);

        System.out.println("\n--- LÁCTEOS ---");
        lacteos.forEach(System.out::println);

        System.out.println("\n--- PANADERÍA ---");
        panaderia.forEach(System.out::println);

        // 4. Calcular:
        // a) El mas barato.
        Optional<Producto> masBarato = obtenerMasBarato(listaProductos);
        masBarato.ifPresent(p -> System.out.println("\n🟢 Producto más barato: " + p));
        // b) El mas caro.
        Optional<Producto> masCaro = obtenerMasCaro(listaProductos);
        masCaro.ifPresent(p -> System.out.println("\n🔴 Producto más caro: " + p));

        // 5. Calcular el precio total de cada categoría
        totalCategoria("Frutas", frutas);
        totalCategoria("Lácteos", lacteos);
        totalCategoria("Panadería", panaderia);


        // 6. Calcular el precio promedio por categoría
        calcularPromedio("Frutas", frutas);
        calcularPromedio("Lácteos", lacteos);
        calcularPromedio("Panadería", panaderia);


        // 7. Agrupar por categoría (por si se necesitara más adelante)
        Map<String, List<Producto>> agrupado = listaProductos.parallelStream()
                .collect(Collectors.groupingBy(Producto::getTipo));
        System.out.println("\n📦 Agrupación por categoría:");
        agrupado.forEach((categoria, productos) -> {
            System.out.println("👉 " + categoria + ": " + productos.size() + " productos");
        });
    }

    private static List<Producto> filtrarCategorias(List<Producto> productos, String tipo) {
        return productos.parallelStream()
                .filter(p -> p.getTipo().equalsIgnoreCase(tipo))
                .toList();
    }

    private static Optional<Producto> obtenerMasBarato(List<Producto> productos) {
        return productos.parallelStream()
                .min(Comparator.comparingDouble(Producto::getPrecio));
    }

    private static Optional<Producto> obtenerMasCaro(List<Producto> productos) {
        return productos.parallelStream()
                .max(Comparator.comparingDouble(Producto::getPrecio));
    }

    private static void totalCategoria(String categoria, List<Producto> productos) {
        double total = productos.parallelStream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        System.out.printf("💰 Precio total de %s: %.2f\n", categoria, total);
    }

    private static void calcularPromedio(String categoria, List<Producto> productos) {
        productos.parallelStream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .ifPresent(avg -> System.out.printf("📊 Precio promedio de %s: %.2f\n", categoria, avg));
    }

    //Datos en un metodo
    private static List<Producto> obtenerProductos() {
        return Arrays.asList(
                new Producto("Manzana", "frutas", 10, 2.50),
                new Producto("Plátano", "frutas", 12, 3.60),
                new Producto("Naranja", "frutas", 15, 2.70),
                new Producto("Melón", "frutas", 5, 1.80),
                new Producto("Fresas", "frutas", 8, 2.50),
                new Producto("Kiwi", "frutas", 10, 1.10),
                new Producto("Leche", "lacteos", 20, 1.20),
                new Producto("Yogur", "lacteos", 30, 0.80),
                new Producto("Queso", "lacteos", 6, 2.50),
                new Producto("Mantequilla", "lacteos", 7, 1.90),
                new Producto("Kéfir", "lacteos", 9, 1.50),
                new Producto("Nata", "lacteos", 11, 1.60),
                new Producto("Pan blanco", "panadería", 10, 1.10),
                new Producto("Pan integral", "panadería", 8, 1.30),
                new Producto("Baguette", "panadería", 14, 1.00),
                new Producto("Croissant", "panadería", 12, 1.20),
                new Producto("Pan de molde", "panadería", 10, 1.50),
                new Producto("Pan sin gluten", "panadería", 5, 2.00),
                new Producto("Uvas", "frutas", 7, 2.00),
                new Producto("Cerezas", "frutas", 4, 2.80),
                new Producto("Requesón", "lacteos", 6, 1.40),
                new Producto("Brie", "lacteos", 3, 3.20),
                new Producto("Pan de centeno", "panadería", 9, 1.60),
                new Producto("Donut", "panadería", 13, 1.00),
                new Producto("Piña", "frutas", 6, 2.10),
                new Producto("Melocotón", "frutas", 8, 1.30),
                new Producto("Danonino", "lacteos", 15, 0.90),
                new Producto("Rollito", "panadería", 10, 0.60),
                new Producto("Tarta de queso", "panadería", 2, 3.50),
                new Producto("Pan rústico", "panadería", 7, 1.80)
        );
    }
}
