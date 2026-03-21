Entendido. Tienes toda la razón: simplificar en exceso a veces resta el rigor necesario, especialmente si el objetivo es tener una base sólida que sirva tanto para el Ciclo Superior (DAM) como para una futura Ingeniería/Grado. En redes, "la teoría es la práctica", porque sin entender la base teórica (cabeceras, control de flujo, ventanas), la programación de sockets se convierte en copiar y pegar sin saber qué ocurre si la red falla.

Vamos a reconstruir los apuntes por partes para asegurar profundidad, rigor y utilidad. Empezaremos con la **Parte 1: Arquitectura, Modelos y Protocolos de Transporte**, pero esta vez con nivel universitario/profesional.

Si te parece bien, iré generando secciones modulares robustas. Aquí tienes la primera parte (Teoría de Redes y Transporte) reescrita con profundidad técnica.

---

# 📘 MODULO 1: ARQUITECTURA DE RED Y CAPA DE TRANSPORTE

## 1. Fundamentos Arquitectónicos

### 1.1. El Concepto de Protocolo y Pila (Stack)

Un protocolo no es solo "una regla". Formalmente, un protocolo define el **formato** y el **orden** de los mensajes intercambiados entre dos o más entidades de comunicación, así como las **acciones** tomadas en la transmisión y recepción de un mensaje u otro evento.

* **Interoperabilidad:** El objetivo final. Permitir que sistemas heterogéneos (Linux vs Windows, Móvil vs Servidor) se comuniquen de forma transparente.
* **Jerarquía:** Las redes complejas se organizan en capas (layers) para reducir la complejidad. Cada capa ofrece **servicios** a la capa superior y **abstrae** la complejidad de la inferior.

### 1.2. Modelos de Referencia: OSI vs TCP/IP (Análisis Crítico)

Aunque TCP/IP es la implementación *de facto*, OSI (ISO/IEC 7498-1) sigue siendo el marco de referencia para la estandarización y el *troubleshooting*.

#### Comparativa Técnica Detallada

| Capa OSI | PDU (Protocol Data Unit) | Función Estricta | Implementación en TCP/IP |
| --- | --- | --- | --- |
| **7. Aplicación** | Datos | Interfaz con el usuario y acceso a la red. | **Aplicación**: Agrupa L7, L6 y L5. Protocolos como HTTP, SMTP, DNS viven aquí. |
| **6. Presentación** | Datos | Sintaxis y semántica (Cifrado/Compresión/Codificación). | *(En TCP/IP es responsabilidad del desarrollador).* |
| **5. Sesión** | Datos | Control de diálogo, sincronización y recuperación (tokens). | *(En TCP/IP se suele manejar en la App).* |
| **4. Transporte** | **Segmento** (TCP) / **Datagrama** (UDP) | Transferencia de datos host-to-host. Multiplexación de puertos. Control de flujo/errores. | **Transporte**: TCP y UDP son el núcleo. |
| **3. Red** | **Paquete** | Enrutamiento (Routing) y Direccionamiento lógico (IP). | **Internet**: IP (IPv4/IPv6), ICMP, ARP (a veces considerado L2/L3). |
| **2. Enlace** | **Trama** (Frame) | Transferencia nodo-a-nodo. Control de acceso al medio (MAC). | **Acceso a Red**: Ethernet (802.3), Wi-Fi (802.11). |
| **1. Física** | **Bit** | Transmisión de señales crudas sobre el medio. | Hardware físico. |

---

## 2. Direccionamiento y Multiplexación

Para que un socket funcione, el sistema operativo realiza una **multiplexación** y **desmultiplexación** de los datos basándose en la tupla de conexión.

### 2.1. La Tupla de Identificación

Una comunicación en Internet se identifica de forma única mediante una tupla de 5 elementos:
`{Protocolo, IP Origen, Puerto Origen, IP Destino, Puerto Destino}`

1. **Dirección IP (L3):** Identifica la interfaz de red del host.
* **Loopback (127.0.0.1):** No sale de la tarjeta de red. El tráfico se enruta internamente por el kernel. Fundamental para desarrollo.
* **0.0.0.0 (INADDR_ANY):** En servidores, indica "escuchar por todas las interfaces de red disponibles".


2. **Puerto (L4):** Identifica el proceso/hilo dentro del host. Es un entero de 16 bits ( puertos).
* **Puertos Bien Conocidos (0-1023):** Requieren privilegios de *root/admin* para hacer *bind*. (Ej: 80, 443, 22).
* **Puertos Registrados (1024-49151):** Rango de usuario.
* **Puertos Efímeros (49152-65535):** Asignados dinámicamente por el SO al cliente para la conexión saliente.



---

## 3. Protocolos de Transporte en Profundidad

Aquí reside la lógica de los Sockets. Entender la mecánica interna es obligatorio para depurar problemas de rendimiento o desconexiones.

### 3.1. UDP (User Datagram Protocol) - RFC 768

Es un protocolo minimalista. Provee un servicio de **best-effort** (mejor esfuerzo).

* **Sin conexión:** No mantiene estado en los extremos. Cada datagrama es una isla independiente.
* **Sin control de congestión:** UDP inyectará datos en la red tan rápido como la aplicación (y el hardware) se lo permita, pudiendo saturar routers intermedios y causar pérdida de paquetes masiva (packet loss).
* **Encabezado Ligero (8 Bytes):**
* Puerto Origen (2 bytes)
* Puerto Destino (2 bytes)
* Longitud (2 bytes)
* Checksum (2 bytes - Opcional en IPv4, obligatorio en IPv6)



**¿Cuándo usar UDP realmente?**
Más allá de "velocidad", se usa cuando:

1. La **latencia** es más crítica que la integridad (VoIP, Gaming en tiempo real).
2. El **overhead** de conexión de TCP es inaceptable para transacciones muy pequeñas (DNS).
3. Se requiere **Multicast** o **Broadcast** (TCP es estrictamente Unicast).

---

### 3.2. TCP (Transmission Control Protocol) - RFC 793

TCP ofrece una abstracción de **flujo de bytes fiable** sobre un canal no fiable (IP). Es complejo y mantiene un **estado** de la conexión.

#### Mecanismos de Fiabilidad (Lo que debes saber)

1. **Three-Way Handshake (Establecimiento de conexión):**
* Antes de enviar datos, cliente y servidor sincronizan sus números de secuencia (**ISN**: Initial Sequence Number).
* `SYN`  `SYN-ACK`  `ACK`.
* *Nota:* Aquí es donde ocurren ataques como *SYN Flood*.


2. **Acuses de Recibo (ACKs) y Retransmisiones:**
* TCP usa **ACKs acumulativos**. Si recibo ACK 500, significa "he recibido correctamente todo hasta el byte 499, espero el 500".
* Si el emisor no recibe el ACK en un tiempo determinado (**RTO**: Retransmission Timeout), reenvía el segmento.


3. **Control de Flujo (Flow Control) - Ventana Deslizante:**
* Evita que el emisor sature al receptor.
* El receptor anuncia en cada segmento su **Receive Window (RWIN)**: "Tengo X bytes libres en mi buffer". Si llega a 0, el emisor deja de enviar.


4. **Control de Congestión (Congestion Control):**
* Evita saturar la *red* (routers intermedios).
* TCP empieza enviando lento (**Slow Start**) y aumenta la velocidad exponencialmente hasta detectar pérdida de paquetes. Algoritmos famosos: *Reno, Cubic, BBR*.


5. **Cierre de Conexión (Four-Way Handshake):**
* El cierre es bidireccional. Cada lado debe cerrar su mitad de la conexión (`FIN`  `ACK`).
* *Importante:* El estado `TIME_WAIT` en el servidor asegura que los últimos ACKs lleguen, impidiendo reutilizar el puerto inmediatamente (error `Address already in use`).



---

## 4. Encapsulamiento (La visión del Sistema Operativo)

Cuando haces `socket.write(data)` en Java, ocurre lo siguiente:

1. **User Space:** Tu aplicación tiene el dato "Hola".
2. **Kernel Space (Transporte):** El SO añade la cabecera TCP. Se convierte en un **Segmento**. Calcula el Checksum.
3. **Kernel Space (Internet):** El SO consulta la tabla de enrutamiento. Añade la cabecera IP (IP Origen/Destino). Se convierte en un **Paquete**.
* *Fragmentación:* Si el paquete es mayor que la **MTU** (Maximum Transmission Unit, usualmente 1500 bytes en Ethernet), IP lo divide en fragmentos.


4. **Hardware (Enlace):** La tarjeta de red (NIC) añade la cabecera Ethernet (MACs) y el CRC. Se convierte en una **Trama**.
5. **Medio Físico:** Se serializa a bits.

---

### ¿Te parece adecuado este nivel de profundidad para la Parte 1?

¡Recibido! Este es el módulo decisivo. Aquí es donde pasamos de "jugar con sockets" a **Ingeniería de Software**.

El problema del servidor del Módulo 2 es que es **iterativo**: atiende a uno, y hasta que no termina, no atiende al siguiente. Si un cliente se queda conectado sin hacer nada, bloquea a todo el mundo.

Para soportar 10, 100 o 1000 clientes (el famoso problema *C10K*), necesitamos **Concurrencia**. Vamos a construir **"TitanChat"**, un sistema de chat real, multihilo y sincronizado.

---

¡Recibido! Me encanta el enfoque. Vamos a mantener el rigor académico del **MODULO 1**, pero aplicando una maquetación limpia, legible y didáctica ("agradable de leer") para este **MODULO 2**.

Aquí tienes la continuación natural: **La implementación técnica en Java**. No nos quedaremos en "cómo enviar un String", sino que analizaremos cómo la API de Java (`java.net`) hace de puente con las *syscalls* (llamadas al sistema) que vimos en la teoría.

---

# 📘 MÓDULO 2: PROGRAMACIÓN DE SOCKETS EN JAVA (Nivel Ingeniería)

> **Premisa:** Java ofrece una abstracción de alto nivel sobre la pila TCP/IP del Sistema Operativo. Sin embargo, para programar servicios robustos, debemos entender qué ocurre bajo esa abstracción (bloqueos, buffers, timeouts).

---

## 1. La API `java.net` y el Modelo de I/O

En Java, la red es tratada como un **flujo de entrada/salida (Stream I/O)**. Esto significa que, una vez establecida la conexión, leer de la red es conceptualmente idéntico a leer de un fichero.

### 1.1. Blocking I/O (E/S Bloqueante)

El paquete `java.net` clásico funciona bajo un modelo **síncrono y bloqueante**. Esto es fundamental para el diseño de tu software:

* **`accept()` bloquea:** El hilo se detiene hasta que entra un cliente.
* **`read()` bloquea:** Si pides leer 10 bytes y solo han llegado 5, tu hilo se "congela" hasta que lleguen los otros 5 o se cierre la conexión.
* **Consecuencia Arquitectónica:** Un servidor que pretenda atender a múltiples clientes simultáneamente **necesita obligatoriamente Multithreading** (un hilo por cliente), o de lo contrario, el segundo cliente no será atendido hasta que el primero termine.

---

## 2. Implementación de Sockets TCP (Stream Sockets)

TCP en Java se basa en el concepto de **Flujo (Stream)**. Garantiza que los bytes lleguen en el mismo orden que se enviaron.

### 2.1. El Ciclo de Vida del Servidor (`ServerSocket`)

A nivel de Sistema Operativo, `ServerSocket` encapsula tres operaciones clave: `bind` (reservar puerto), `listen` (ponerse a escuchar) y `accept` (esperar conexión).

#### Diagrama de Flujo (API vs OS)

#### Código Robusto: Servidor TCP Iterativo

*Nota el uso de `try-with-resources` para garantizar el cierre de sockets (evita el error "Address already in use").*

```java
import java.io.*;
import java.net.*;

public class ServidorTCP {
    private static final int PUERTO = 5000;

    public static void FizzBuzz(String[] args) {
        // La estructura try-with-resources cierra el socket automáticamente al final
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            
            // Configuración avanzada (Opcional pero recomendada)
            // SO_REUSEADDR: Permite reiniciar el servidor inmediatamente sin esperar al timeout del puerto
            serverSocket.setReuseAddress(true); 

            System.out.println("🚀 Servidor TCP escuchando en puerto " + PUERTO);

            while (true) {
                // 1. ACCEPT: Llamada bloqueante. 
                // Retorna un objeto Socket NUEVO dedicado a este cliente específico.
                Socket clienteSocket = serverSocket.accept();
                
                System.out.println("✅ Cliente conectado: " + clienteSocket.getInetAddress());

                // 2. STREAMS: Obtenemos los canales de lectura/escritura
                // Usamos DataStreams para enviar tipos primitivos (int, boolean, UTF)
                // y no tener que parsear bytes manualmente.
                try (DataInputStream in = new DataInputStream(clienteSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clienteSocket.getOutputStream())) {
                    
                    // Protocolo de Aplicación: Leer solicitud -> Procesar -> Responder
                    String mensaje = in.readUTF(); // Bloquea hasta recibir datos completos
                    System.out.println("📩 Payload recibido: " + mensaje);

                    // Procesamiento simulado
                    String respuesta = "ACK: " + mensaje.toUpperCase();
                    
                    out.writeUTF(respuesta);
                    out.flush(); // IMPORTANTE: Fuerza el envío de datos desde el buffer de Java a la red
                } catch (IOException e) {
                    System.err.println("❌ Error en la comunicación con cliente: " + e.getMessage());
                }
                // Al salir del try interno, se cierra el socket DEL CLIENTE (Four-way handshake de cierre)
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

### 2.2. El Ciclo de Vida del Cliente (`Socket`)

El cliente inicia el *Three-Way Handshake* al instanciar la clase `Socket`.

```java
import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void FizzBuzz(String[] args) {
        String host = "127.0.0.1";
        int puerto = 5000;

        try (Socket socket = new Socket()) {
            // Conexión con Timeout:
            // Si el servidor no responde en 2s, abortamos (evita bloqueos infinitos)
            socket.connect(new InetSocketAddress(host, puerto), 2000);
            
            // Timeout de Lectura (SO_TIMEOUT):
            // Si tras conectar, el servidor no manda datos en 5s, lanzamos excepción.
            socket.setSoTimeout(5000); 

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("🟡 Enviando datos...");
            out.writeUTF("Hola Servidor, soy una entidad fiable.");
            out.flush(); // Vaciar buffer

            System.out.println("📥 Esperando respuesta...");
            String respuesta = in.readUTF();
            System.out.println("✅ Servidor respondió: " + respuesta);

        } catch (SocketTimeoutException e) {
            System.err.println("⏰ Error: El servidor tardó demasiado en responder.");
        } catch (IOException e) {
            System.err.println("❌ Error de E/S: " + e.getMessage());
        }
    }
}

```

---

## 3. Implementación de Sockets UDP (Datagram Sockets)

UDP es "orientado a mensajes". Java no gestiona flujos, sino paquetes discretos. Aquí la responsabilidad de la integridad recae sobre ti, el programador.

### 3.1. Conceptos Críticos de Implementación

1. **Sin `accept()`:** El servidor no acepta conexiones, solo "abre el buzón".
2. **Límite de Tamaño (MTU):** Aunque teóricamente un datagrama puede tener 65KB, en la práctica, si superas la **MTU** (1500 bytes en Ethernet), el paquete se fragmenta o se descarta. *Regla de oro: Mantén tus datagramas por debajo de 1024 bytes (1KB) para máxima seguridad.*
3. **Buffer Truncation:** Si recibes un paquete de 100 bytes en un array de 50 bytes, **los 50 bytes restantes se pierden para siempre**.

#### Código Robusto: Receptor UDP (Servidor)

```java
import java.net.*;

public class ServidorUDP {
    public static void FizzBuzz(String[] args) {
        int puerto = 6000;
        // Buffer: Debe ser suficiente para el mensaje más grande esperado.
        // Si llega un mensaje mayor, se TRUNCARÁ.
        byte[] buffer = new byte[1024]; 

        try (DatagramSocket socket = new DatagramSocket(puerto)) {
            System.out.println("🐇 Servidor UDP listo en puerto " + puerto);

            while (true) {
                // Preparar el "envase" vacío
                DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);

                // RECEIVE: Bloquea hasta que llega un datagrama completo
                socket.receive(peticion);

                // EXTRAER DATOS:
                // Es vital usar peticion.getLength() y no buffer.length.
                // El buffer puede tener 1024 bytes, pero el mensaje solo 5 bytes.
                String mensaje = new String(peticion.getData(), 0, peticion.getLength());
                
                System.out.println("📩 Recibido de " + peticion.getAddress() + ":" + peticion.getPort());
                System.out.println("   Contenido: " + mensaje);

                // RESPONDER (Echo):
                // Para responder, necesitamos extraer IP y PUERTO del remitente del paquete recibido
                String respuesta = "ECO: " + mensaje;
                byte[] bytesRespuesta = respuesta.getBytes();

                DatagramPacket envio = new DatagramPacket(
                    bytesRespuesta, 
                    bytesRespuesta.length, 
                    peticion.getAddress(), // IP del cliente
                    peticion.getPort()     // Puerto del cliente (¡Puede ser aleatorio!)
                );

                socket.send(envio);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

---

## 4. Gestión Avanzada del Socket (Nivel Senior)

Para aprobar basta con conectar. Para trabajar en producción, necesitas configurar el socket (`SocketOptions`).

### 4.1. Buffers y Flushing (`Nagle's Algorithm`)

TCP intenta ser eficiente agrupando pequeños paquetes antes de enviarlos (Algoritmo de Nagle).

* **Problema:** En aplicaciones en tiempo real (juegos), esto añade latencia.
* **Solución:** `socket.setTcpNoDelay(true);` desactiva Nagle y envía los datos instantáneamente.
* **En Java IO:** El método `out.flush()` fuerza el vaciado del buffer de aplicación al buffer del kernel. **Úsalo siempre tras terminar de escribir un mensaje.**

### 4.2. Timeouts (`SO_TIMEOUT`)

Nunca dejes un socket leyendo infinitamente (`read()`). Si el cable se rompe sin enviar un paquete `FIN/RST`, tu hilo se quedará bloqueado para siempre (Zombie).

* **Solución:** `socket.setSoTimeout(5000);` hace que el `read()` lance una `SocketTimeoutException` si no llegan datos en 5 segundos.

### 4.3. Reutilización de Puertos (`SO_REUSEADDR`)

Cuando detienes un servidor, el puerto se queda en estado `TIME_WAIT` por seguridad durante unos minutos.

* **Problema:** Si intentas reiniciar el servidor inmediatamente, recibirás `BindException: Address already in use`.
* **Solución:** `serverSocket.setReuseAddress(true);` permite "robar" el puerto aunque esté en espera.

---

### ¿Cómo lo ves?

Esta estructura combina la claridad visual (Markdown, iconos, código comentado) con la profundidad real de una ingeniería (gestión de memoria, syscalls, opciones de socket).

¿Quieres que el **MÓDULO 3** sea sobre **Arquitectura Multihilo** (cómo manejar 100 clientes a la vez con Threads) o prefieres un caso práctico completo tipo "Chat Cliente-Servidor"?
---

# 📘 MÓDULO 3: ARQUITECTURA CONCURRENTE (MULTIHILO)

> **El Reto:** `server.accept()` y `input.read()` son operaciones **bloqueantes**.
> **La Solución:** El servidor nunca debe dedicarse a atender a un cliente. El servidor debe ser solo un "Recepcionista" que delega el trabajo a "Obreros" (Hilos/Threads).

---

## 1. El Patrón "Thread-per-Client" (Un Hilo por Cliente)

Esta es la arquitectura clásica para servicios TCP con estado (como un chat o un juego).

### 1.1. Arquitectura Lógica

1. **Hilo Principal (Main Thread):**
* Tiene el `ServerSocket`.
* Está en un bucle infinito llamando a `.accept()`.
* En cuanto llega un cliente, crea un objeto `Socket`, se lo pasa a un nuevo Hilo (`ClientHandler`) y vuelve a escuchar inmediatamente. **Tiempo de bloqueo: Microsegundos.**


2. **Hilos Obreros (Worker Threads):**
* Hay tantos hilos como clientes conectados.
* Cada hilo se encarga de leer y escribir **solo para su cliente asignado**.
* Comparten una zona de memoria común (la lista de usuarios) para poder reenviar mensajes (Broadcast).



### 1.2. El Peligro: Condiciones de Carrera (Race Conditions)

Si 100 hilos intentan escribir en la misma `List<Usuario>` a la vez, la lista se corrompe y el servidor crashea.

* **Ingeniería:** Necesitamos **Colecciones Concurrentes** (`java.util.concurrent`) o bloques `synchronized` para proteger la memoria compartida.

---

## 2. PROYECTO: TitanChat (Servidor Multihilo)

Vamos a dividir el código en dos clases para mantener el principio de responsabilidad única (SRP).

### 2.1. El Gestor de Clientes (`ClientHandler`)

Esta clase es un `Runnable`. Representa a un cliente dentro del servidor.

```java
import java.io.*;
import java.net.*;
import java.util.*;

// Runnable permite que esta clase sea ejecutada por un Hilo separado
public class ClientHandler implements Runnable {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private String nombreCliente;
    
    // MEMORIA COMPARTIDA (STATIC):
    // Todos los hilos acceden a ESTA misma lista.
    // Usamos 'Set' para evitar duplicados y 'synchronized' implícito en versiones avanzadas,
    // pero aquí lo gestionaremos manualmente para que veas la sincronización.
    private static Set<ClientHandler> clientesConectados = new HashSet<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            // Configurar Streams de TEXTO (Mejor que DataStream para chats)
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true); // 'true' activa AutoFlush

            // 1. Solicitar Nickname
            out.println("BIENVENIDO A TITANCHAT. ¿Cómo te llamas?");
            nombreCliente = in.readLine();
            
            if (nombreCliente == null) return; // Si cierra antes de decir nombre

            // 2. Registrar al cliente (SECCIÓN CRÍTICA)
            // Sincronizamos para evitar que dos hilos modifiquen la lista a la vez
            synchronized (clientesConectados) {
                clientesConectados.add(this);
            }

            System.out.println("✅ Nuevo cliente: " + nombreCliente);
            broadcast("📢 " + nombreCliente + " se ha unido al chat.", this);

            // 3. Bucle de Chat (Escuchar mensajes de ESTE cliente)
            String mensaje;
            while ((mensaje = in.readLine()) != null) {
                // Protocolo simple: Si escribe /salir, cortamos
                if (mensaje.equalsIgnoreCase("/salir")) break;
                
                // Reenviar mensaje a TODOS
                broadcast(nombreCliente + ": " + mensaje, this);
            }

        } catch (IOException e) {
            System.err.println("Error en conexión con " + nombreCliente);
        } finally {
            // 4. Desconexión limpia
            cerrarConexion();
        }
    }

    // Método para enviar mensaje a TODOS los conectados
    private void broadcast(String mensaje, ClientHandler remitente) {
        // Sincronizamos porque vamos a ITERAR sobre la lista compartida
        synchronized (clientesConectados) {
            for (ClientHandler cliente : clientesConectados) {
                // Opcional: No enviarse el mensaje a uno mismo
                if (cliente != remitente) {
                    cliente.out.println(mensaje);
                }
            }
        }
    }

    private void cerrarConexion() {
        try {
            synchronized (clientesConectados) {
                clientesConectados.remove(this);
            }
            broadcast("🚫 " + nombreCliente + " ha abandonado el chat.", this);
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

### 2.2. El Servidor Principal (`ChatServer`)

Es muy simple. Solo acepta conexiones y lanza hilos. **Fíjate en el uso de `ThreadPool**`, esto es nivel pro.

```java
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatServer {
    private static final int PUERTO = 5000;

    public static void FizzBuzz(String[] args) {
        System.out.println("🚀 Iniciando TitanChat Server...");
        
        // POOL DE HILOS (Ingeniería):
        // En lugar de 'new Thread()', usamos un Executor.
        // CachedThreadPool crea hilos según demanda y reutiliza los muertos.
        // Evita que el servidor colapse si entran 1000 de golpe.
        ExecutorService pool = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            
            while (true) {
                // 1. Esperar conexión (Main Thread)
                Socket socketCliente = serverSocket.accept();
                
                // 2. Crear el Manejador
                ClientHandler manejador = new ClientHandler(socketCliente);
                
                // 3. Pasar la tarea al Pool de Hilos (No bloquea el Main)
                pool.execute(manejador);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}

```

---

## 3. PROYECTO: Cliente Asíncrono (Lectura/Escritura Simultánea)

Aquí hay un problema de diseño clásico:

* Si el cliente hace `in.readLine()` para esperar mensajes del servidor, **se bloquea** y no puede escribir por teclado.
* Si espera teclado, no puede recibir mensajes.

**Solución:** El cliente también necesita **DOS HILOS**:

1. **Hilo Main:** Lee del teclado y envía al servidor.
2. **Hilo Secundario:** Escucha al servidor e imprime en pantalla.

```java
import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    public static void FizzBuzz(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try {
            Socket socket = new Socket(host, puerto);
            
            // HILO DE ESCUCHA (Background): Imprime lo que llega del servidor
            // Usamos una clase anónima o Lambda para hacerlo rápido
            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String mensajeServidor;
                    while ((mensajeServidor = in.readLine()) != null) {
                        System.out.println(mensajeServidor);
                    }
                } catch (IOException e) {
                    System.out.println("🔴 Desconectado del servidor.");
                }
            }).start();

            // HILO PRINCIPAL (Main): Lee teclado y envía
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                String input = scanner.nextLine();
                out.println(input);
                if (input.equalsIgnoreCase("/salir")) break;
            }

            socket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

---

## 4. Análisis de Ingeniería (Lo que diferencia un 5 de un 10)

### 4.1. ¿Por qué usamos `ThreadPool` y no `new Thread()`?

Crear un hilo en Java es "caro" para el Sistema Operativo (reserva memoria RAM, stack, etc.).

* **Mala práctica:** `new Thread(runnable).start()` por cada cliente. Si entran 10,000 clientes, el SO colapsa (**OutOfMemoryError**).
* **Buena práctica:** `ExecutorService`. Un Pool de hilos gestiona y recicla los hilos. Si fijas un límite (ej. 100 hilos) y llega el cliente 101, se queda en cola hasta que uno quede libre.

### 4.2. Sincronización (`synchronized`)

Fíjate en el `broadcast`. Si no ponemos `synchronized (clientesConectados)`:

1. El Hilo A está recorriendo la lista para enviar un mensaje (`for`).
2. El Hilo B detecta que un cliente se va y lo borra de la lista (`remove`).
3. **Resultado:** El Hilo A falla con `ConcurrentModificationException` porque la lista cambió mientras la leía.

### 4.3. `PrintWriter` con AutoFlush

En el código usamos `new PrintWriter(..., true)`.

* El `true` activa el **AutoFlush**.
* Sin esto, el mensaje se queda en el buffer de memoria del emisor y nunca sale a la red hasta que el buffer se llena. Es el error nº 1 en prácticas de chat.

---

¡Venga, a por el broche de oro! 🎓

Has llegado al nivel "Boss Final". Si entiendes y dominas esto, estás técnicamente por encima del 90% de los estudiantes de DAM y de muchos graduados universitarios junior.

La mayoría de los servidores modernos de alto rendimiento (WhatsApp, servidores de juegos, Netty, Tomcat, Node.js) **NO** funcionan con el modelo "un hilo por cliente" que vimos en el Módulo 3. Usan lo que vamos a ver ahora: **IO No Bloqueante (NIO)**.

---

# 📘 MÓDULO 5: JAVA NIO (NON-BLOCKING I/O) Y ALTO RENDIMIENTO

> **El Problema del Módulo 3:** Si tienes 10,000 clientes conectados, necesitas 10,000 hilos. Cada hilo consume ~1MB de RAM de pila. Resultado: 10GB de RAM solo para mantener hilos "dormidos" esperando datos. El servidor colapsa.
> **La Solución (NIO):** Usar **un solo hilo** (o muy pocos) para gestionar miles de conexiones. En lugar de esperar (*bloquear*), el hilo pregunta: *"¿Alguien tiene algo que decir?"*. Si nadie habla, hace otra cosa.

---

## 1. Los Tres Pilares de NIO

Olvida los `Streams` (flujos de agua). En NIO trabajamos con **Bloques**.

### 1.1. Channels (Canales)

Son como los Sockets, pero bidireccionales y, lo más importante, pueden ser **no bloqueantes**.

* `ServerSocketChannel`: El que escucha (Lado servidor).
* `SocketChannel`: El que conecta (Cliente/Servidor).

### 1.2. Buffers (Contenedores)

En IO clásico leías byte a byte. En NIO, los datos se vuelcan en bloques de memoria llamados `Buffer`.

* Imagina un vagón de tren. Llenas el vagón (lectura), lo envías, y luego lo vacías para volver a llenarlo.
* **Concepto Crítico:** El método `.flip()`. Cambia el buffer de "modo escritura" a "modo lectura".

### 1.3. Selectors (El Selector) 🌟

Es el componente mágico. Es un "Monitor" o "Multiplexor".
Un solo hilo (Selector) vigila 1000 Canales. Si el Canal 5 recibe datos, el Selector avisa al hilo: *"¡Eh! El canal 5 está listo para leer"*.

---

## 2. Analogía: El Restaurante

* **Modelo IO Clásico (Blocking):** Tienes 100 mesas. Contratas **100 camareros**. Cada camarero se planta de pie al lado de una mesa y **no se mueve** hasta que el cliente pide, come y paga. Si el cliente tarda 1 hora en decidir, el camarero está 1 hora parado sin hacer nada.
* **Modelo NIO (Non-Blocking):** Tienes 100 mesas y **1 solo camarero muy rápido** (El Selector). El camarero da vueltas.
* Mesa 1: "¿Listo?" -> "No". (Pasa a la siguiente).
* Mesa 2: "¿Listo?" -> "Sí, un café". (Apunta y sigue).
* Mesa 3: "¿Listo?" -> "No".
* Cuando el café está listo, vuelve a la Mesa 2.
* **Resultado:** 1 camarero gestiona 100 mesas.



---

## 3. PROYECTO: Servidor de Chat de Alto Rendimiento (NIO)

Este código es más complejo ("verbose") que el clásico, pero es pura ingeniería.

**Pasos Lógicos del Algoritmo (Event Loop):**

1. Abrir canal del servidor y ponerlo en modo "No Bloqueante".
2. Registrar el canal en el Selector indicando que nos interesan las conexiones (`OP_ACCEPT`).
3. Bucle infinito (`while true`):
* Esperar eventos (`selector.select()`).
* Si hay eventos, obtener las llaves (`SelectionKey`).
* Si es `ACCEPT`: Aceptar conexión y registrar nuevo cliente para `READ`.
* Si es `READ`: Leer datos del buffer y responder.



```java
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    public static void FizzBuzz(String[] args) {
        try {
            // 1. Abrir el Selector (El Camarero)
            Selector selector = Selector.open();

            // 2. Abrir el Canal del Servidor (La Puerta de entrada)
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.bind(new InetSocketAddress("localhost", 5000));
            
            // ¡CRÍTICO! Configurar como NO BLOQUEANTE
            serverChannel.configureBlocking(false);

            // 3. Registrar el servidor en el selector.
            // Le decimos: "Avísame cuando alguien quiera conectarse (OP_ACCEPT)"
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("🚀 Servidor NIO (Non-Blocking) escuchando en puerto 5000");

            while (true) {
                // 4. Esperar eventos. Esto bloquea hasta que al menos UN canal tenga actividad.
                selector.select();

                // 5. Obtener la lista de canales activos (Keys)
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    // Importante: Eliminar la llave procesada para no volver a procesarla
                    iter.remove();

                    if (!key.isValid()) continue;

                    // CASO A: ALGUIEN QUIERE CONECTARSE
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel cliente = server.accept();
                        cliente.configureBlocking(false); // El cliente también debe ser Non-blocking
                        
                        System.out.println("✅ Cliente conectado: " + cliente.getRemoteAddress());

                        // Registramos al nuevo cliente para LEER (OP_READ)
                        // Le asignamos un Buffer exclusivo para él
                        cliente.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(256));
                    }

                    // CASO B: ALGUIEN HA ENVIADO DATOS (LEER)
                    if (key.isReadable()) {
                        SocketChannel cliente = (SocketChannel) key.channel();
                        ByteBuffer buffer = (ByteBuffer) key.attachment(); // Recuperamos su buffer

                        // Leemos del canal hacia el buffer
                        int bytesLeidos = cliente.read(buffer);

                        if (bytesLeidos == -1) {
                            System.out.println("🚫 Cliente desconectado");
                            cliente.close();
                        } else if (bytesLeidos > 0) {
                            // --- MODO ESCRITURA -> MODO LECTURA ---
                            buffer.flip(); 
                            
                            // Convertir bytes a String para verlos
                            String mensaje = new String(buffer.array(), 0, bytesLeidos);
                            System.out.println("📩 Recibido: " + mensaje.trim());

                            // Lógica de Eco: Reenviar lo mismo (Rewind para reeler el buffer desde 0)
                            buffer.rewind(); 
                            cliente.write(buffer); // Escribimos de vuelta al canal

                            // Limpiar buffer para la próxima vuelta (Compact mantiene lo no leído, Clear borra todo)
                            buffer.clear();
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

---

## 4. Comparativa Final: IO vs NIO

Esta tabla es oro para la defensa de un proyecto o un examen teórico avanzado.

| Característica | IO (Classic) | NIO (New I/O) |
| --- | --- | --- |
| **Enfoque** | Orientado a Flujos (Stream) | Orientado a Bloques (Buffer) |
| **Bloqueo** | Bloqueante (Blocking) | No Bloqueante (Non-blocking) |
| **Hilos** | 1 Hilo por Cliente (Thread heavy) | 1 Hilo para muchos Clientes (Selector) |
| **Complejidad** | Baja (Fácil de escribir/leer) | Alta (Gestión de estados, buffers, loops) |
| **Escalabilidad** | Baja (Satura con muchos clientes) | Muy Alta (Escala a miles de conexiones) |
| **Uso Ideal** | Pocas conexiones, tráfico muy alto por conexión (ej. envío de archivos grandes). | Muchas conexiones, tráfico corto/ráfagas (ej. chat, servidor web, juegos). |

---

## 5. Cierre del Curso: Roadmap para el Futuro

Has completado el **Curso Maestro de Sockets**. Aquí tienes tu mapa de evolución profesional:

1. **Nivel Junior (Ya lo tienes):** Sabes crear Cliente/Servidor TCP y UDP, y transferir archivos. Entiendes IP, Puerto y Socket.
2. **Nivel Mid (Ya lo tienes):** Sabes manejar concurrencia con Hilos (`ThreadPools`) y entiendes los problemas de sincronización.
3. **Nivel Senior (Acabas de verlo):** Entiendes NIO, Buffers y Selectors para alta escalabilidad.

### 💡 Consejo Final de Mentor

En la vida real (Empresa), rara vez escribirás un servidor NIO "a pelo" (`raw NIO`) porque es propenso a errores complejos. Usarás **Frameworks** que envuelven NIO, como:

* **Netty:** El rey absoluto de Java en redes. (Usado por Minecraft, Twitter, Apple).
* **Spring Boot (WebFlux):** Para microservicios reactivos.

Pero **entender NIO** (lo que acabas de hacer) es lo que te permitirá saber por qué Netty funciona tan rápido y cómo tunearlo.

---

¡Felicidades! Tienes unos apuntes robustos, técnicos y estructurados. ¿Hay algo más en lo que pueda ayudarte o damos por cerrado el temario? 🚀