package ejercicio02Bonana.control;


import ejercicio02Bonana.dao.Producto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class ControlProducto {

    public static  List<Producto> importTxt (Path RUTA, List<Producto> productos) {

        try (BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";", -1);

                if (partes.length != 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double stockKg = Double.parseDouble(partes[2]);
                    double precio = Double.parseDouble(partes[3]);

                    productos.add(new Producto(id, nombre, stockKg, precio));


                } catch (NumberFormatException ex) {
                    System.err.println("❌ Error numérico en línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error al cargar listado en" + e);
            e.printStackTrace();
        }
        System.out.println("\n 👌 Listado de productos cargado 👌");

        productos.forEach(System.out::println);
        return productos;
    }

    public static void listantoFruta (List<Producto>productos) {
        productos.forEach(System.out::println);

    }

    public static List<Producto> anhadeFruta(List<Producto> productos) {

        Scanner sc = new Scanner(System.in);
        boolean ok= true;

        do {
            ok = true;

            try {

                int id = productos.size()+1;
                System.out.println("id: " + id);
                System.out.println("Introduce el nombre del artículo:");
                String nombre= "";
                while (nombre.length() < 2) {
                    System.out.println("Nombre demasiado corto.");
                    nombre = sc.nextLine().trim();
                }

                System.out.println("Introduce el stock actual:");
                double stockKg = Double.parseDouble(sc.nextLine());

                System.out.println("Introduce el precio:");
                double precio = Double.parseDouble(sc.nextLine());

                Producto p = new Producto(id, nombre, stockKg, precio);
                productos.add(p);
                System.out.println("👌 producto añadido 👌 ");

            } catch (Exception e) {
                System.out.println("❌ Error en los datos. Vuelve a introducir todo.");
                ok = false;
            }

        } while (!ok);

        return productos;
    }


    public static List<Producto> exportTxt (Path RUTA, List<Producto> productos) {

        try (BufferedWriter bw = Files.newBufferedWriter(RUTA, StandardCharsets.UTF_8)) {
            for (Producto p : productos) {
                bw.write(p.getId() + ";" + p.getNombre() + ";" + p.getStockKg() + ";" + p.getPrecio());
                bw.newLine();
            }
            System.out.println(" 👌👌 Escrito en " + RUTA.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productos;
    }

    public static  List<Producto> reset (Path RUTA, List<Producto> productos) {

        productos.clear();
        try (BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";", -1);

                if (partes.length != 4) {
                    continue;
                }

                try {
                    int id = Integer.parseInt(partes[0]);
                    String nombre = partes[1];
                    double stockKg = Double.parseDouble(partes[2]);
                    double precio = Double.parseDouble(partes[3]);

                    productos.add(new Producto(id, nombre, stockKg, precio));

                } catch (NumberFormatException ex) {
                    System.err.println("❌ Error numérico en línea: " + linea);
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Error al cargar listado en" + e);
            e.printStackTrace();
        }
        System.out.println("\n 👌 Listado de productos cargado 👌");

        productos.forEach(System.out::println);
        return productos;
    }
}
