package ejercicio07Vater;


/**
 * EJERCICIO 3 — Cola para el baño (1 recurso)
 * objetivo: aprender a esperar correctamente (sin busy-wait)
 * Contexto
 * Hay 1 baño y 5 personas.
 * Reglas
 * Solo una persona puede usarlo a la vez.
 * Cada persona:
 * Espera un tiempo aleatorio.
 * Intenta usar el baño.
 * Lo usa un tiempo aleatorio.
 * Si está ocupado → espera.
 * Debes lograr
 * Cuando está ocupado, el hilo espera (no bucles infinitos).
 * Cuando se libera → se despierta el siguiente.
 * Comprobación
 * Nunca hay 2 personas dentro.
 * (Piensa: ya asoma wait / notify.)
 */

public class Main {

    public static void main(String[] args) {

        Vater vater = new Vater();

        // Creamos 5 personas
        for (int i = 1; i <= 5; i++) {

            Thread persona =
                    new Thread(new Persona(vater), "Persona-" + i);

            persona.start();
        }
    }
}
