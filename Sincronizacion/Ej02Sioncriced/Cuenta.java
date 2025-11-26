package Ej02Sioncriced;

public class Cuenta {

    private int contador = 0;
    private final String nombre;

    public Cuenta(String nombre) {
        this.nombre = nombre;
    }

    // 1) synchronized en MÉTODO de instancia
    //    -> El monitor es "this" (la propia cuenta).
    public synchronized void incrementar() {
        int valor = contador;
        System.out.println(Thread.currentThread().getName()
                + " (método sincronizado) leyendo " + valor + " en " + nombre);

        try {
            Thread.sleep(100); // simulamos trabajo
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }

        contador = valor + 1;
        System.out.println(Thread.currentThread().getName()
                + " (método sincronizado) escribiendo " + contador + " en " + nombre);
    }

    // 2) synchronized(this) en BLOQUE
    //    -> El monitor también es "this", pero solo se sincroniza esta parte.
    public void incrementar2() {
        System.out.println(Thread.currentThread().getName()
                + " entrando en incrementar2() NO sincronizado de " + nombre);

        synchronized (this) {
            int valor = contador;
            System.out.println(Thread.currentThread().getName()
                    + " (bloque sincronizado) leyendo " + valor + " en " + nombre);

            try {
                Thread.sleep(100); // simulamos trabajo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            contador = valor + 1;
            System.out.println(Thread.currentThread().getName()
                    + " (bloque sincronizado) escribiendo " + contador + " en " + nombre);
        }

        System.out.println(Thread.currentThread().getName()
                + " saliendo de incrementar2() de " + nombre);
    }

    public int getContador() {
        return contador;
    }

    public String getNombre() {
        return nombre;
    }
}
