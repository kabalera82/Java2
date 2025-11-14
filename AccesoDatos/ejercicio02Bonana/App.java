package ejercicio02Bonana;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ejercicio02Bonana.control.ControlProducto;
import ejercicio02Bonana.dao.Producto;


public class App {
    /*
    private static final Path RUTA = Paths.get ("data","productos.txt");
    ponemos rutas absolutas por los problemas con los modulos este metodo sirve para proyectos.
    */
    private static final Path RUTA = Path.of(
            "F:/Marcos/Programacion/Java/Java2/AcesoDatos/data/productos.txt"
    );

    private static final List <Producto> productos = new ArrayList<>();


    public static void main(String[] args) {
        start();
    }

    private static void start() {

        ControlProducto.importTxt(RUTA, productos);

        boolean salir = false;
        do {
            try {
                System.out.println("""
                        
                        ========== MENÚ FRUTAS ==========
                        1. Listar todos los Articulos.
                        2. Añadir Articulos.
                        3. Exportar Articulos.
                        4. Restaurar a Inicio.

                        0. Salir.
                        =================================
                        """);
                System.out.print("Selecciona una opción: ");
                Scanner sc = new Scanner(System.in);
                int opcion = sc.nextInt();
                switch (opcion) {
                    case 1 -> ControlProducto.listantoFruta(productos);
                    case 2 -> ControlProducto.anhadeFruta(productos);
                    case 3 -> ControlProducto.exportTxt(RUTA, productos);
                    case 4 -> ControlProducto.reset(RUTA,productos);

                    case 0 -> {
                        salir = true;
                        sc.close();
                        System.out.println("¡Saliendo -----!");
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!salir);
    }

}
