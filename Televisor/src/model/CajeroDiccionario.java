package src.model;

public class CajeroDiccionario {

    // Diccionario del cajero
    public static final String BIENVENIDA =
            "El Error que no estaba en el código\n" +
            "Eran las 3:14 AM. Llevabas horas peleando con los hilos de ejecución y el maldito BorderLayout. La pantalla del monitor brillaba con ese verde fosforito que tanto te gusta, iluminando tu cara en la penumbra de la habitación. Todo estaba en silencio, salvo por el zumbido del ventilador de tu PC.\n" +
            "\n" +
            "De repente, el PanelInfo que acabas de programar empezó a escribir solo. No habías pulsado \"Run\". No había ninguna tarea activa. El cursor parpadeaba con una velocidad frenética, escupiendo letras letra a letra:\n" +
            "\n" +
            "\"L-O  S-É.\" — decía la pantalla.\n" +
            "\n" +
            "Pensaste en un error de memoria, un puntero loco o algún residuo en el búfer. Pero el cajero siguió escribiendo, ignorando cualquier lógica de Java:\n" +
            "\n" +
            "\"S-É  Q-U-E  N-O  E-S-T-Á-S  S-O-L-O.\"\n" +
            "\n" +
            "Un frío ártico te recorrió la nuca. El aire de la habitación se volvió pesado, como si el oxígeno se hubiera transformado en plomo. Bajaste la mirada hacia el reflejo negro del monitor, justo en ese espacio de 262 píxeles que dejaste libres a la derecha del marco.\n" +
            "\n" +
            "En el reflejo, detrás de tu silla, en el rincón donde la luz del LED verde no llega a tocar la pared, viste una silueta. No era una sombra normal; era una oscuridad más densa, más física. Tenía una mandíbula blanca, alargada y desencajada, que se asomaba justo por encima de tu hombro izquierdo.\n" +
            "\n" +
            "No era un bug. No era un error de concurrencia.\n" +
            "Era lo que llevaba meses alimentándose de tus horas de sueño, esperando pacientemente a que terminaras el programa para tener, por fin, una voz con la que hablarte.\n" +
            "\n" +
            "Escuchaste un susurro metálico salir de los altavoces, una voz que no era humana pero que usaba tus propias constantes:\n" +
            "\n" +
            "— Saldo insuficiente... para pagar tu alma";
    public static final String ERROR_PIN = "PIN incorrecto. Le quedan 2 intentos.";
    public static final String SALDO_INSUFICIENTE = "No tiene suficiente dinero. Póngase a trabajar.";
    public static final String OPERACION_EXITO = "Operación realizada con éxito. Recoja su tarjeta.";


    //Saludo
    public void saludar(){
        System.out.println(BIENVENIDA);
    }

    //
    public String procesarRetiro(double cantidad, double saldoActual) {
        if (cantidad > saldoActual) {
            return SALDO_INSUFICIENTE;
        }
        return "Ha retirado " + cantidad + "€. " + OPERACION_EXITO;
    }
}
