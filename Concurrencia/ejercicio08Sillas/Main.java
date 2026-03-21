package ejercicio08Sillas;
/**
 * Objetivo: recursos limitados (capacidad > 1)
 * Contexto
 * Comedor con 3 sillas y 6 estudiantes.
 * Reglas
 * Si hay silla libre → se sientan.
 * Si no → esperan.
 * Al terminar, liberan silla.
 * Debes lograr
 * Controlar contador de sillas libres.
 * Esperar solo cuando no hay sillas.
 * Notificar cuando alguien se levanta.
 * Comprobación
 * Nunca más de 3 sentados a la vez.
 */
public class Main {

    public static void main(String[] args) {

        Comedor comedor = new Comedor(3);

        for (int i = 0; i < 6; i++) {
            Thread estudiante =
                    new Thread(new Estudiante(comedor), "Estudiante-" + i);

            estudiante.start();
        }
    }
}