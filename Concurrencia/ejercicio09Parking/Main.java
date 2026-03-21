package ejercicio09Parking;

/**
 * Parking pequeño (entrada / salida)
 * objetivo: espera + notificación repetida
 * Contexto
 * Parking con 4 plazas, coches infinitos.
 * Reglas
 * Cada coche:
 * Llega (tiempo aleatorio).
 * Intenta entrar.
 * Aparca un tiempo.
 * Sale.
 * Si está lleno → espera.
 * Debes lograr
 * Controlar cupo como semáforo.
 * Notificar correctamente para que otros entren.
 * Sin bloqueos.
 * Comprobación
 * Nunca más de 4 coches dentro.
 */
public class Main {

    public static void main(String[] args) {

        Parking parking = new Parking(4);  // 4 plazas

        for (int i = 0; i < 10; i++) {     // 10 coches de prueba
            Thread coche = new Thread(new Coche(parking), "Coche-" + i);
            coche.start();
        }
    }
}
