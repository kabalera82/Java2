public class Registrar implements Runnable{

    @Override
    public void run (){

        try{
            System.out.println("Registrando alumnos" + Thread.currentThread().getName());
            Thread.sleep(4000);
        } catch(InterruptedException e){
            throw new RuntimeException(e);
        }
        System.out.println("Registrado");

    }
}
