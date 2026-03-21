package ejercicio10Impresoras;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Copisteria copisteria = new Copisteria(2);

        int fin = 20000;

        Estudiante e1 = new Estudiante(copisteria, fin);
        Estudiante e2 = new Estudiante(copisteria, fin);
        Estudiante e3 = new Estudiante(copisteria, fin);
        Estudiante e4 = new Estudiante(copisteria, fin);
        Estudiante e5 = new Estudiante(copisteria, fin);

        Thread t1 = new Thread(e1, "Ana");
        Thread t2 = new Thread(e2, "Luis");
        Thread t3 = new Thread(e3, "Marta");
        Thread t4 = new Thread(e4, "Carlos");
        Thread t5 = new Thread(e5, "Sara");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        t1.join();
        t2.join();
        t3.join();
        t4.join();
        t5.join();

        System.out.println("Ana hizo copias " + e1.getCopias());
        System.out.println("Luis hizo copias " + e2.getCopias());
        System.out.println("Marta hizo copias " + e3.getCopias());
        System.out.println("Carlos hizo copias " + e4.getCopias());
        System.out.println("Sara hizo copias " + e5.getCopias());
    }
}
