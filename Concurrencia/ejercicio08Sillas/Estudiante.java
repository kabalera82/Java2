package ejercicio08Sillas;

public class Estudiante implements Runnable {

    private Comedor comedor;

    public Estudiante(Comedor comedor) {
        this.comedor = comedor;
    }

    @Override
    public void run() {
        try {
            System.out.println(
                    Thread.currentThread().getName() + " llega y quiere sentarse."
            );
            Thread.sleep((int)(Math.random() * 1000) + 500);
        } catch (InterruptedException e) {
            return;
        }
        comedor.sentarse();
        try {
            System.out.println(
                    Thread.currentThread().getName() + " está comiendo."
            );
            Thread.sleep((int)(Math.random() * 1500) + 500);
        } catch (InterruptedException e) {
            return;
        }
        comedor.levantarse();
    }
}
