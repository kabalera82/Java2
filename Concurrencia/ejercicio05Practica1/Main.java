package ejercicio05Practica1;

public class Main {
    public static void main(String[] args) {
        CentroCopias centro = new CentroCopias(2);

        Estudiante e1 = new Estudiante(1, centro);
        Estudiante e2 = new Estudiante(2, centro);
        Estudiante e3 = new Estudiante(3, centro) ;
        Estudiante e4 = new Estudiante(4, centro);
        Estudiante e5 = new Estudiante(5, centro);

        Thread t1 = new Thread(e1, "Rodrigo");
        Thread t2 = new Thread(e2, "Maria");
        Thread t3 = new Thread(e3, "Antonio");
        Thread t4 = new Thread(e4, "Juan");
        Thread t5 = new Thread(e5, "Anton");

        t1.start(); t2.start(); t3.start(); t4.start(); t5.start();

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {}

        t1.interrupt(); t2.interrupt(); t3.interrupt(); t4.interrupt(); t5.interrupt();

        try {
            t1.join(); t2.join(); t3.join(); t4.join(); t5.join();
        } catch (InterruptedException e) {}

        System.out.println("Estudiante Rodrigo hizo copias " + e1.getCopias() + " veces");
        System.out.println("Estudiante Maria hizo copias " + e2.getCopias() + " veces");
        System.out.println("Estudiante Antonio hizo copias " + e3.getCopias() + " veces");
        System.out.println("Estudiante Juan hizo copias " + e4.getCopias() + " veces");
        System.out.println("Estudiante Anton hizo copias " + e5.getCopias() + " veces");
    }
}