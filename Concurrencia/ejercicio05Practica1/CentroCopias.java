package ejercicio05Practica1;

public class CentroCopias {
    private int maquinas;

    public CentroCopias(int total) {
        this.maquinas = total;
    }

    public synchronized void solicita() throws InterruptedException {
        while (maquinas == 0) {
            wait();
        }
        maquinas--;
    }

    public synchronized void libera() {
        maquinas++;
        notifyAll();
    }

    public void usa() throws InterruptedException {
        Thread.sleep((int) (Math.random() * 1000));
    }

    public void termina() {
    }
}