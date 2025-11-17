


    /*
    Escribe un programa que abra una aplicación del sistema (por ejemplo, Bloc de notas, Paint
    o la Calculadora) usando ProcessBuilder.
    */


public final class Ej08ProgSimultaneos {


    public static void main(String[] args) {
    progSimultaneo();

    }

    public static void progSimultaneo(){

        try {

            ProcessBuilder pCmd = new ProcessBuilder(
                    "cmd", "/c", "start", "Notepad.exe"
            );

            ProcessBuilder pCmd2 = new ProcessBuilder(
                    "cmd", "/c", "start", "Calc.exe"
            );

            pCmd.redirectErrorStream(true);
            pCmd2.redirectErrorStream(true);

            Process p2 = pCmd2.start();
            Process p = pCmd.start();

            p.waitFor();
            p2.waitFor();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }



}
