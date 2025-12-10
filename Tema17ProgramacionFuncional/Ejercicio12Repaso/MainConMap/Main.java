package Tema17ProgramacionFuncional.Ejercicio12Repaso.MainConMap;

import Tema17ProgramacionFuncional.Ejercicio12Repaso.producto.Producto;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Producto> listaProductos = Arrays.asList(
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

        // 1. Filtrar por categoría
        List<Producto> frutas = listaProductos.stream()
                .filter(p -> p.getTipo().equalsIgnoreCase("frutas"))
                .toList();

        List<Producto> lacteos = listaProductos.stream()
                .filter(p -> p.getTipo().equalsIgnoreCase("lacteos"))
                .toList();

        List<Producto> panaderia = listaProductos.stream()
                .filter(p -> p.getTipo().equalsIgnoreCase("panadería"))
                .toList();

        // 2. Mostrar productos por categoría
        System.out.println("\n--- FRUTAS ---");
        frutas.forEach(System.out::println);

        System.out.println("\n--- LÁCTEOS ---");
        lacteos.forEach(System.out::println);

        System.out.println("\n--- PANADERÍA ---");
        panaderia.forEach(System.out::println);

        // 3. Producto más barato
        listaProductos.stream()
                .min(Comparator.comparingDouble(Producto::getPrecio))
                .ifPresent(p -> System.out.println("\n🟢 Producto más barato: " + p));

        // Producto más caro
        listaProductos.stream()
                .max(Comparator.comparingDouble(Producto::getPrecio))
                .ifPresent(p -> System.out.println("\n🔴 Producto más caro: " + p));

        // 4. Precio total por categoría
        double totalFrutas = frutas.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        double totalLacteos = lacteos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        double totalPanaderia = panaderia.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();

        System.out.printf("\n💰 Total Frutas: %.2f €\n", totalFrutas);
        System.out.printf("💰 Total Lácteos: %.2f €\n", totalLacteos);
        System.out.printf("💰 Total Panadería: %.2f €\n", totalPanaderia);

        // 5. Precio promedio por categoría
        frutas.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .ifPresent(avg -> System.out.printf("\n📊 Promedio Frutas: %.2f €\n", avg));

        lacteos.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .ifPresent(avg -> System.out.printf("📊 Promedio Lácteos: %.2f €\n", avg));

        panaderia.stream()
                .mapToDouble(Producto::getPrecio)
                .average()
                .ifPresent(avg -> System.out.printf("📊 Promedio Panadería: %.2f €\n", avg));

        // 6. Agrupación por categoría
        Map<String, List<Producto>> agrupado = listaProductos.parallelStream()
                .collect(Collectors.groupingBy(Producto::getTipo));

        System.out.println("\n📦 Agrupación por categoría:");
        agrupado.forEach((categoria, productos) -> {
            System.out.println("👉 " + categoria + ": " + productos.size() + " productos");
        });

        // 7. Promedio más alto de todas las categorías
        agrupado.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().stream()
                                .mapToDouble(Producto::getPrecio)
                                .average().orElse(0.0)
                ))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .ifPresent(entry -> System.out.printf("\n🏆 Categoría con promedio más alto: %s (%.2f €)\n",
                        entry.getKey(), entry.getValue()));
    }
}
