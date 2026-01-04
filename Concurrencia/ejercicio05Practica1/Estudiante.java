package ejercicio05Practica1;

public class Estudiante implements Runnable {
    private CentroCopias centro;
    private int copias = 0;

    public Estudiante(int id, CentroCopias centro) {
        this.centro = centro;
    }

    public int getCopias() {
        return copias;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println(Thread.currentThread().getName() + " está estudiando");
                Thread.sleep((int) (Math.random() * 1000));

                System.out.println(Thread.currentThread().getName() + " solicita máquina");
                centro.solicita();

                System.out.println(Thread.currentThread().getName() + " usa máquina");
                centro.usa();
                copias++;

                System.out.println(Thread.currentThread().getName() + " termina y libera máquina");
                centro.termina();
                centro.libera();
            }
        } catch (InterruptedException e) {
            // El hilo termina aquí al recibir interrupt() desde el Main
        }
    }
}