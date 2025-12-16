package ejercicio186;

public class Corredor implements Runnable{
    private int contador = 0;
    @Override
    public void run(){
        try {
            while (contador < 100) {
                int avance = (int) (Math.random() * 5) + 1;
                contador += avance;
                System.out.println(Thread.currentThread().getName() + " avanza a: " + contador);

// --> Dormir el hilo hace que el procesador cambie de hilo funcionando las prioridades.
                Thread.sleep(100);
            }
            System.out.println("¡LLEGADA A META! -> " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            System.out.println("El corredor " + Thread.currentThread().getName() + " se ha tropezado.");
        }
    }
}
