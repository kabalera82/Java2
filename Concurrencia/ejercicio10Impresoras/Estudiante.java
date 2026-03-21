package ejercicio10Impresoras;

public class Estudiante implements Runnable {

    private Copisteria copisteria;
    private int copias = 0;
    private int tiempoRestante;

    public Estudiante(Copisteria c, int tiempo) {
        copisteria = c;
        tiempoRestante = tiempo;
    }

    public int getCopias() {
        return copias;
    }

    @Override
    public void run() {

        while (tiempoRestante > 0) {

            System.out.println(Thread.currentThread().getName() + " está estudiando");

            int estudio = (int)(Math.random() * 4 * 1000);

            try {
                Thread.sleep(estudio);
            } catch (InterruptedException e) {
                break;
            }

            tiempoRestante -= estudio;

            int maquina = copisteria.pedirImpresora();

            int imprimir = (int)(Math.random() * 4 * 1000);

            try {
                Thread.sleep(imprimir);
            } catch (InterruptedException e) {
                break;
            }

            tiempoRestante -= imprimir;

            copias++;

            copisteria.liberarImpresora(maquina);
        }
    }
}
