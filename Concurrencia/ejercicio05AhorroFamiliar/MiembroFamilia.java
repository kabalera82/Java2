package ejercicio05AhorroFamiliar;
import java.util.Random;

public class MiembroFamilia implements Runnable {

    private final Hucha hucha;
    private final Random r = new Random();

    public MiembroFamilia(Hucha hucha) {
        this.hucha = hucha;
    }

    @Override
    public void run() {
        try {
            while (true) {

                int cantidad = 1 + r.nextInt(200);  // 1–200
                boolean seguir = hucha.ingresar(cantidad);

                if (!seguir) break;

                Thread.sleep(200 + r.nextInt(400)); // pausa 200–600 ms
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
