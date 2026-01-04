package ejercicio2214;

public class Pasos implements Runnable{
    @Override
    public void run(){
        try{
            for(int i = 1; i<=10; i++){
                Thread.sleep(400);
                System.out.println("Paso: "+ i);
            }

        } catch (InterruptedException e){
            System.out.println("Interrumpido");
        }
        System.out.println(" Fin de los pasos.");

    }
}
