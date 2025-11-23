

public final class Ej00Ejemplos {

    // Abrir archivo con app determinada ---------------------------------------------
    ProcessBuilder pShell = new ProcessBuilder(
            "powershell", "-command", "Start-Process", "asdf.txt"
    );
    ProcessBuilder pCmd = new ProcessBuilder(
            "cmd", "/c", "start", "asdf.txt"
    );

    // ----------------------------------------------------------------------------------------
    /*
    dir                     // Lista archivos y carpetas del directorio actual
    cd nombreCarpeta        // Entra dentro de la carpeta indicada
    cd ..                   // Sube un nivel (carpeta padre)
    cd \                    // Va a la raíz del disco (C:\)
    cd ruta\completa        // Navega directamente a la ruta especificada
    cls                     // Limpia la pantalla de la consola

    mkdir nombre            // Crea una carpeta nueva
    rmdir nombre            // Borra una carpeta vacía
    rmdir /s nombre         // Borra una carpeta y todo su contenido
    del archivo.txt         // Borra un archivo concreto
    copy origen destino     // Copia archivos a otra ubicación
    move origen destino     // Mueve archivos a otra ubicación
    ren viejo nuevo         // Renombra un archivo o carpeta

    ipconfig                // Muestra la configuración de red básica
    ipconfig /all           // Muestra configuración de red completa y detallada
    ping 8.8.8.8            // Comprueba si tienes conexión enviando paquetes a Google DNS
    systeminfo              // Muestra información del sistema operativo y hardware
    tasklist                // Lista todos los procesos en ejecución
    taskkill /IM nombre_proceso.exe /F   // Fuerza la finalización del proceso indicado por nombre

    cls                     // Limpia la pantalla nuevamente
    */




}
