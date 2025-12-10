package Tema17ProgramacionFuncional.Ejercicio10OptionalsLambdas;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    /*
     * Objetivo del ejercicio:
     * Simular una base de datos de clientes y evitar errores cuando
     * un cliente no tiene ciertos datos (por ejemplo, la dirección).
     * Usaremos Optional para manejar esos posibles valores nulos sin
     * provocar excepciones como NullPointerException.
     */

    public static void main(String[] args) {

        // Generamos una lista de clientes con datos de ejemplo
        List<Cliente> listadoClientes = obtenerClientes();

        System.out.println("Introduce el ID del cliente que deseas consultar:");

        // Leemos un número por teclado (ID del cliente que queremos buscar)
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();

        // Buscamos el cliente por ID. Como puede no existir, usamos Optional.
        Optional<Cliente> cliente = buscarCliente(listadoClientes, id);

        // Si existe el cliente, obtenemos su dirección.
        // Si no existe o no tiene dirección, evitamos el error gracias a Optional.
        if (cliente.isPresent()) {
            //Verificamos si tiene direccion
            Optional<String> direccion = obtenerDireccion(cliente);
            if (direccion.isPresent()) {
                System.out.println("La direccion es: " + direccion.get());
            } else {
                System.out.println("El cliente no tiene dirección");
            }
        } else {
            System.out.println("El cliente no existe");
        }

    }

    // Este método intenta extraer la dirección de un cliente, si existe
    private static Optional<String> obtenerDireccion(Optional<Cliente> cliente) {
        // Si el cliente está presente, accedemos a su dirección con map.
        // Si no, devolvemos Optional vacío. Así evitamos el uso de null.
        return cliente.map(Cliente::getDireccion);
    }

    // Este método busca un cliente en la lista por su ID
    private static Optional<Cliente> buscarCliente(List<Cliente> listadoClientes, int id) {
        // Recorremos la lista con stream, filtramos por ID, y devolvemos el primero que coincida.
        // Si no hay ninguno, devuelve un Optional vacío.
        return listadoClientes.stream()
                .filter(cli -> cli.getIdCliente() == id)
                .findFirst();
    }

    // Creamos una lista de clientes con todos los campos rellenos
    private static List<Cliente> obtenerClientes() {
        // En un caso real, estos datos vendrían de una base de datos.
        // Aquí simplemente los inventamos para hacer pruebas.
        return Arrays.asList(
                new Cliente("María", "Gómez", "Calle Luna 1", 612345678),
                new Cliente("Luis", "Pérez", "Avenida Sol 2", 622345678),
                new Cliente("Ana", "Martínez", "Calle Flor 3", 632345678),
                new Cliente("Carlos", "Ruiz", "Paseo Norte 4", 642345678),
                new Cliente("Lucía", "Fernández", "Calle Este 5", 652345678),
                new Cliente("Miguel", "Torres", "Plaza Sur 6", 662345678),
                new Cliente("Elena", "Díaz", "Ronda Oeste 7", 672345678),
                new Cliente("Jorge", "Moreno", "Calle Central 8", 682345678),
                new Cliente("Raquel", "Vega", null, 692345678),
                new Cliente("Sofía", "Navarro", "Avenida Mar 10", 602345678),
                new Cliente("Diego", "Castro", "Calle Solana 11", 601234567),
                new Cliente("Laura", "Sánchez", "Calle Nube 12", 611234567),
                new Cliente("Pablo", "García", "Calle Río 13", 621234567),
                new Cliente("Carmen", "León", "Paseo Azul 14", 631234567),
                new Cliente("Javier", "Molina", "Camino Verde 15", 641234567),
                new Cliente("Sara", "Hernández", "Calle Prado 16", 651234567),
                new Cliente("Iván", "Iglesias", "Av. Tranquila 17", 661234567),
                new Cliente("Alba", "Ortiz", "Calle Brisa 18", 671234567),
                new Cliente("Daniel", "Reyes", "Calle Roble 19", 681234567),
                new Cliente("Nuria", "Campos", "Paseo Claro 20", 691234567)
        );
    }
}
