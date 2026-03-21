package ejercicio10Impresoras;

public class Copisteria {

    private int impresorasLibres;
    private boolean[] ocupada;

    public Copisteria(int num) {
        impresorasLibres = num;
        ocupada = new boolean[num];
    }

    public synchronized int pedirImpresora() {

        System.out.println(Thread.currentThread().getName() + " solicita máquina");

        while (impresorasLibres == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                return -1;
            }
        }

        impresorasLibres--;

        int maquina = -1;

        for (int i = 0; i < ocupada.length; i++) {
            if (!ocupada[i]) {
                ocupada[i] = true;
                maquina = i;
                break;
            }
        }

        System.out.println(Thread.currentThread().getName() + " usa máquina " + maquina);

        return maquina;
    }

    public synchronized void liberarImpresora(int maquina) {
        ocupada[maquina] = false;
        impresorasLibres++;

        System.out.println(Thread.currentThread().getName() + " termina y libera máquina " + maquina);

        notifyAll();
    }
}
