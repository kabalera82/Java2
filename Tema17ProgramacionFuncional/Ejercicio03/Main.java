package Tema17ProgramacionFuncional.Ejercicio03;

public class Main {
    public static void main(String[] args) {

        new Vehiculo() {
            private int numPasajeros;

            public void conducir() {
                System.out.println("Estoy conduciendo");
            }
        }.conducir();//ponemos a continuacion del punto para que detecto el total del objeto

    }
}
