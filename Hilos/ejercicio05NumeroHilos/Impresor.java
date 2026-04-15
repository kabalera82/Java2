class Impresor implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            // Imprimimos el nombre del hilo y su prioridad para verlo claro
            System.out.println(Thread.currentThread().getName() +
                    " (Prioridad " + Thread.currentThread().getPriority() +
                    ") - Vuelta " + i);

            // No lo pide el ejercicio pero si lo ralentizamos vemos como al arrancar de nuevo si se tiene en cuenta el orden de prioridades.
            try{
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}