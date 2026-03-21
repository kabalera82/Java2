# Tema 18 — Programacion de Red en Java

> Tema 09 de 2ºDAM | Prerequisito: Hilos (Tema 01), Java basico (I/O, excepciones)

La programacion de red en Java permite que aplicaciones se comuniquen a traves de la red usando el paquete `java.net`. Se trabaja principalmente con **sockets TCP**, que ofrecen comunicacion bidireccional y fiable entre un cliente y un servidor.

---

## Teoria rapida

### Conceptos de red en Java

| Clase | Funcion |
|-------|---------|
| `URL` | Representa una URL — parsea protocolo, host, puerto, recurso |
| `Socket` | Socket de cliente — se conecta a un servidor TCP |
| `ServerSocket` | Socket de servidor — escucha y acepta conexiones |
| `InetAddress` | Representacion de una direccion IP (IPv4/IPv6) |
| `HttpURLConnection` | Conexiones HTTP (peticiones GET/POST basicas) |

### Modelo cliente-servidor con sockets

```
SERVIDOR                           CLIENTE
ServerSocket(puerto)               Socket(host, puerto)
    |                                  |
accept() -- conexion establecida -- connect()
    |                                  |
InputStream / OutputStream         InputStream / OutputStream
    |                                  |
    <---- comunicacion bidireccional ---->
    |                                  |
close()                            close()
```

### Ejemplo minimo — servidor

```java
try (ServerSocket servidor = new ServerSocket(8080)) {
    System.out.println("Servidor escuchando en 8080...");

    while (true) {
        Socket cliente = servidor.accept(); // bloquea hasta que llega un cliente

        // Tratar cada cliente en un hilo separado (evita bloquear al siguiente)
        new Thread(() -> {
            try (BufferedReader in = new BufferedReader(
                        new InputStreamReader(cliente.getInputStream()));
                 PrintWriter out = new PrintWriter(cliente.getOutputStream(), true)) {

                String mensaje;
                while ((mensaje = in.readLine()) != null) {
                    System.out.println("Recibido: " + mensaje);
                    out.println("Echo: " + mensaje);
                }
            } catch (IOException e) { e.printStackTrace(); }
        }).start();
    }
}
```

### Ejemplo minimo — cliente

```java
try (Socket socket = new Socket("localhost", 8080);
     PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
     BufferedReader in = new BufferedReader(
             new InputStreamReader(socket.getInputStream()))) {

    out.println("Hola servidor");
    System.out.println("Respuesta: " + in.readLine());
}
```

---

## Estructura

```
Tema18ProgramacionRed/
├── Ejercicio01/
│   ├── Conexion.java                          <- Parsear una URL con la clase URL
│   └── Ejercicio01ConexionLocal/
│       ├── Servidor.java                      <- Servidor TCP basico en puerto local
│       ├── ImageServer.java                   <- Servidor especializado para imagenes
│       ├── MusicServer.java                   <- Servidor especializado para audio
│       └── VideoServer.java                   <- Servidor especializado para video
│
└── Ejercicio02API/
    └── Main.java                              <- Llamada a una API REST externa con HTTP
```

### `Ejercicio01/Conexion.java`

Demuestra la clase `URL` de Java:
- Crear un objeto URL desde una cadena: `new URL("http://www.google.es:80/index.html")`
- Crear un objeto URL con parametros: `new URL(protocolo, host, puerto, recurso)`
- Acceder a sus partes: `getProtocol()`, `getHost()`, `getPort()`, `getFile()`

### `Ejercicio01ConexionLocal/`

Patron cliente-servidor completo:
- `Servidor.java`: `ServerSocket` en un puerto local, acepta conexiones y las gestiona con hilos
- Los servidores especializados (`ImageServer`, `MusicServer`, `VideoServer`) ilustran como el mismo patron se puede especializar para distintos tipos de recursos

### `Ejercicio02API/`

Llamada a una API REST externa usando `HttpURLConnection` o `URL.openStream()`:
- Realizacion de peticiones GET
- Lectura y procesado de la respuesta JSON

---

## Como ejecutar

```bash
# Compilar Conexion.java
javac -d out Tema18ProgramacionRed/Ejercicio01/Conexion.java
java -cp out Tema18ProgramacionRed.Ejercicio01.Conexion

# Servidor (ejecutar primero en una terminal)
javac -d out Tema18ProgramacionRed/Ejercicio01/Ejercicio01ConexionLocal/*.java
java -cp out Ejercicio01ConexionLocal.Servidor

# Cliente (ejecutar en otra terminal, con el servidor ya corriendo)
# Conectarse con telnet o con el cliente Java correspondiente
```

> **Nota:** Para probar el cliente-servidor, necesitas dos terminales simultaneas — una para el servidor y otra para el cliente.

---

## Referencia — Parsear una URL

```java
URL url = new URL("http://www.ejemplo.com:8080/pagina.html");

url.getProtocol()  // "http"
url.getHost()      // "www.ejemplo.com"
url.getPort()      // 8080
url.getFile()      // "/pagina.html"
url.getPath()      // "/pagina.html"
```
