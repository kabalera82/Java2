package Tema17ProgramacionFuncional;

import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.stream.Stream;

public class Ejercicio04CarreraStrings {

        // Record para guardar resultados de cada tarea
        record Resultado(String nombre, int longitud, long duracionMs) {}

        public static void main(String[] args) throws Exception {
            Scanner sc = new Scanner(System.in);

            System.out.print("Introduce la primera frase: ");
            String frase1 = sc.nextLine();

            System.out.print("Introduce la segunda frase: ");
            String frase2 = sc.nextLine();

            ExecutorService executor = Executors.newFixedThreadPool(2);

            // Declarativo: tareas como funciones puras
            Callable<Resultado> tarea1 = medir("Frase 1", frase1);
            Callable<Resultado> tarea2 = medir("Frase 2", frase2);

            // Ejecutamos en paralelo
            var futuros = executor.invokeAll(Stream.of(tarea1, tarea2).toList());
            executor.shutdown();

            // Recogemos los resultados
            var resultados = futuros.stream()
                    .map(f -> {
                        try {
                            return f.get();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }).toList();

            System.out.println("\n----- RESULTADOS -----");
            resultados.forEach(r -> System.out.printf("%s → Longitud: %d, Tiempo: %d ms%n",
                    r.nombre(), r.longitud(), r.duracionMs()));

            // 📏 Frase más corta (menor longitud)
            Resultado masCorta = resultados.stream()
                    .min(Comparator.comparingInt(Resultado::longitud))
                    .orElseThrow();

            // ⏱️ Tarea más rápida (menor tiempo)
            Resultado masRapida = resultados.stream()
                    .min(Comparator.comparingLong(Resultado::duracionMs))
                    .orElseThrow();

            System.out.println("\n📏 La frase más corta es: " + masCorta.nombre());
            System.out.println("⚡ El hilo que terminó antes fue: " + masRapida.nombre());
        }

        // Método declarativo que devuelve una tarea con nombre, longitud y tiempo
        private static Callable<Resultado> medir(String nombre, String frase) {
            return () -> {
                long inicio = System.nanoTime();
                TimeUnit.MILLISECONDS.sleep((long) (Math.random() * 100));  // Simula tiempo aleatorio
                long fin = System.nanoTime();
                return new Resultado(nombre, frase.length(), (fin - inicio) / 1_000_000);
            };
        }
    }



