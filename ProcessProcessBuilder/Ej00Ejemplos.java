

public final class Ej00Ejemplos {

    // Abrir archivo con app determinada ---------------------------------------------
    ProcessBuilder pShell = new ProcessBuilder(
            "powershell", "-command", "Start-Process", "asdf.txt"
    );
    ProcessBuilder pCmd = new ProcessBuilder(
            "cmd", "/c", "start", "asdf.txt"
    );

    // ----------------------------------------------------------------------------------------

}
