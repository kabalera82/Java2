package Tema17ProgramacionFuncional.Ejercicio02;

public class Main {
    public static void main(String[] args) {


        //Vamos a hacer referencia directa sin crear la clase
        IAnimal perro = new IAnimal() {
            //Se sobrescribe un metodo
            @Override
            public void hacerSonido() {
                System.out.println("ladrar");
            }
        };//Estoy definiendo con el ; para decir donde termina

        perro.hacerSonido();
    }
}
