
---
# 📘  Redes y Sockets 

---
## 🌍 La Suite TCP/IP y el Viaje del Dato

---

## 0. Flashback: ¿Qué es la "Suite" TCP/IP?

A menudo decimos "TCP/IP" como si fuera una sola cosa, pero en realidad es una **pila (stack)** de protocolos. Es un conjunto de reglas que trabajan en equipo.

* **No es solo un protocolo:** Es una familia. Incluye TCP, UDP, IP, HTTP, FTP, ICMP (ping), etc.Capa OSI,Nombre (OSI),PDU (Dato),Función Principal,Capa TCP/IP
  Entiendo perfectamente. Quieres una tabla que no solo sea texto, sino que aproveche las capacidades de Markdown para verse **profesional, limpia y técnica** en cualquier visor (como VS Code, Obsidian o GitHub).

---

## 📊 Arquitectura de Red: Comparativa OSI vs. TCP/IP

| Nivel | Capa OSI | PDU (Unidad) | Protocolos Clave | Dispositivos | Capa TCP/IP |
| --- | --- | --- | --- | --- | --- |
| **7** | **Aplicación** | **Datos** | HTTP, HTTPS, FTP, DNS, SSH | Gateway / Firewall | **1. Aplicación** |
| **6** | **Presentación** | **Datos** | SSL, TLS, ASCII, JPG, PNG | - | **1. Aplicación** |
| **5** | **Sesión** | **Datos** | NetBIOS, RPC, SQL, PAP | - | **1. Aplicación** |
| **4** | **Transporte** | **Segmento** | **TCP** (Fiable), **UDP** (Rápido) | Firewall | **2. Transporte** |
| **3** | **Red** | **Paquete** | **IP** (v4/v6), ICMP, IPSec | **Router** / Switch L3 | **3. Internet** |
| **2** | **Enlace** | **Trama** | Ethernet, WiFi (802.11), ARP | **Switch** / Bridge | **4. Acceso a Red** |
| **1** | **Física** | **Bit** | Señal Eléctrica, Radio, Luz | **Hub** / Repetidor / Cable | **4. Acceso a Red** |

---

```text
       ESTRUCTURA OSI                             ESTRUCTURA TCP/IP
+---------------------------+               +---------------------------+
|    L7 - Aplicación        |               |                           |
+---------------------------+               |                           |
|    L6 - Presentación      |-------------->|       APLICACIÓN          |
+---------------------------+               |   (Datos de Usuario)      |
|    L5 - Sesión            |               |                           |
+---------------------------+               +---------------------------+
|    L4 - Transporte        |-------------->|       TRANSPORTE          |
|    (TCP / UDP)            |               |    (Extremo a Extremo)    |
+---------------------------+               +---------------------------+
|    L3 - Red               |-------------->|       INTERNET            |
|    (IP / Enrutamiento)    |               |      (Direccionamiento)   |
+---------------------------+               +---------------------------+
|    L2 - Enlace de Datos   |               |                           |
+---------------------------+-------------->|      ACCESO A RED         |
|    L1 - Física            |               |    (Hardware / Medio)     |
+---------------------------+               +---------------------------+

```

1. **PDU (Protocol Data Unit):** Es vital que recuerdes que en Capa 4 se llama **Segmento** y en Capa 3 **Paquete**. Los profesores de DAM son muy estrictos con esta terminología en los exámenes.
2. **Encapsulamiento:** En Markdown, puedes añadir una nota indicando que cada capa inferior "envuelve" a la superior.
3. **Dispositivos:** He añadido la columna de dispositivos porque en el módulo de Redes te preguntarán dónde actúa un **Router** (Capa 3) vs un **Switch** (Capa 2).

---

## 1. Zoom a las Capas: El viaje del dato

Para un programador (DAM), el modelo importante es el **TCP/IP (4 capas)**, aunque usamos la terminología del OSI para ser precisos.

Vamos a ver qué pasa en cada capa cuando tú envías un mensaje desde tu Java (`out.println("Hola")`).

> **Concepto Clave: Encapsulación**
> Imagina que el dato va bajando por un ascensor desde tu programa hasta el cable. En cada piso (capa), le ponen un "sobre" nuevo.

### 📱 Capa 4: APLICACIÓN (Application Layer)

* **Equivalencia OSI:** Capas de Aplicación, Presentación y Sesión.
* **¿Qué pasa aquí?** Es donde vive tu código. Aquí los datos tienen sentido para el humano (una web HTML, un JSON, un mensaje de chat).
* **Tu responsabilidad:** Formatear los datos (UTF-8, JSON, XML) y decidir qué protocolo usar (HTTP, FTP) o si usas sockets crudos.
* **Protocolos típicos:** HTTP, DNS, SMTP, FTP, SSH.
* **El dato se llama:** 📄 **DATOS** (Data).

### 🚚 Capa 3: TRANSPORTE (Transport Layer)

* **Equivalencia OSI:** Capa de Transporte.
* **¿Qué pasa aquí?** Aquí llegan **TCP y UDP**.
1. Se cogen los "Datos" de arriba y se parten en trozos manejables.
2. Se les pega una etiqueta con los **PUERTOS** (Origen y Destino).
3. Si es TCP, se prepara el control de errores.


* **Función clave:** Comunicación **Host-to-Host** (de programa a programa).
* **El dato se llama:** 📦 **SEGMENTO** (TCP) o **DATAGRAMA** (UDP).

### 🌐 Capa 2: INTERNET (Internet Layer)

* **Equivalencia OSI:** Capa de Red (Network).
* **¿Qué pasa aquí?** Aquí manda el protocolo **IP**.
1. Coge el Segmento/Datagrama y lo mete en otro sobre.
2. Le pega la dirección **IP Origen** y **IP Destino**.
3. Decide la ruta (**Routing**): *"¿Por dónde mando esto para llegar a China?"*.


* **Función clave:** Direccionamiento lógico y enrutamiento (el GPS de la red).
* **El dato se llama:** ✉️ **PAQUETE** (Packet).

### 🔌 Capa 1: ACCESO A RED (Network Access Layer)

* **Equivalencia OSI:** Enlace de Datos + Física.
* **¿Qué pasa aquí?** Pasamos del software al hardware.
1. Coge el Paquete IP y lo mete en el último sobre.
2. Le añade las direcciones físicas (**MAC Address**) de tu tarjeta de red y la del router.
3. Convierte todo eso en **señales eléctricas, luz u ondas de radio** (0s y 1s).


* **Función clave:** Entrega física al siguiente dispositivo conectado.
* **El dato se llama:** 🎞️ **TRAMA** (Frame) y finalmente **BITS**.

---

## 🧩 Resumen Visual de la Encapsulación

*(Importante para examen)*

Fíjate cómo el mensaje va "engordando" al bajar porque cada capa añade su cabecera (Header):

```text
[ TUS DATOS "HOLA"                          ]  <-- Capa Aplicación (Datos)
[ Puerto        |       TUS DATOS           ]  <-- Capa Transporte (Segmento)
[ IP            |   Puerto      | TUS DATOS ]  <-- Capa Internet (Paquete)
[ MAC | IP | Puerto | TUS DATOS | Checksum  ]  <-- Capa Acceso (Trama)
      |      |        |
      V      V        V
  (Cable / WiFi / Fibra) --------------------> 010101001... (Bits físicos)
```

---

* **Si falla la conexión (`Connection Refused`):** Sabes que el problema llegó a la **Capa de Transporte** (el puerto está cerrado o el firewall lo bloquea).
* **Si da `Host Unreachable`:** El problema está en la **Capa de Internet** (IP mal puesta o router caído).
* **Si tu programa va lento:** Puede ser culpa de **TCP** (Capa Transporte) haciendo demasiadas retransmisiones por una mala red física (Capa Acceso).


---

# 🏛️ El Modelo OSI (Open Systems Interconnection)

> **Importancia:** Aunque en la práctica usamos TCP/IP, el modelo OSI es el **estándar universal** para entender las redes y diagnosticar problemas. Es un modelo teórico de 7 capas que desglosa cómo viaja la información desde una aplicación hasta el cable físico.

---

## 🔽 1. Capas Superiores (Responsabilidad del Software)

*Aquí el dato sigue siendo "información útil" para el usuario y es gestionado por el Sistema Operativo y la Aplicación.*

### **7. Capa de APLICACIÓN (Application)**

* **Función:** Es la interfaz entre el usuario y la red. No es el programa en sí (no es Chrome), sino los protocolos que usa el programa para comunicarse.
* **Misión:** Iniciar la comunicación.
* **Protocolos:** HTTP, DNS, SMTP (email), FTP.
* **Unidad de datos (PDU):** **DATOS**.

### **6. Capa de PRESENTACIÓN (Presentation)**

* **Función:** El "Traductor". Se asegura de que la información sea legible por el sistema receptor.
* **Misiones clave:**
* **Formateo:** (ASCII, JPG, MP4).
* **Cifrado:** (SSL/TLS en HTTPS).
* **Compresión:** (ZIP, GZIP).


* **Unidad de datos (PDU):** **DATOS** (formateados/cifrados).

### **5. Capa de SESIÓN (Session)**

* **Función:** El "Jefe de Sala".
* **Misión:** Establecer, mantener y cerrar la sesión entre dos máquinas. Controla el diálogo y puede guardar "puntos de control" para reanudar descargas interrumpidas.
* **Unidad de datos (PDU):** **DATOS**.

---

## 🔽 2. Capas Intermedias (El Transporte)

*Aquí empieza la logística del envío.*

### **4. Capa de TRANSPORTE (Transport)** 📍 *(Aquí viven TCP y UDP)*

* **Función:** Fiabilidad y Comunicación Host-a-Host.
* **Misiones clave:**
* **Segmentación:** Divide los datos grandes en trozos manejables.
* **Direccionamiento de Servicio:** Usa **PUERTOS** para saber a qué proceso va el dato (ej. puerto 80 web, 5000 app Java).


* **Unidad de datos (PDU):**
* **SEGMENTO** (si es TCP).
* **DATAGRAMA** (si es UDP).

---

## 🔽 3. Capas Inferiores (Red y Hardware)

*Aquí decidimos la ruta y tocamos el hardware.*

### **3. Capa de RED (Network)**

* **Función:** Direccionamiento Lógico y Enrutamiento (Routing).
* **Misión:** Decidir la mejor ruta para llegar al destino final a través de internet.
* **Elemento Clave:** Dirección **IP** (Origen y Destino).
* **Hardware:** **Routers**.
* **Unidad de datos (PDU):** **PAQUETE**.

### **2. Capa de ENLACE DE DATOS (Data Link)**

* **Función:** Entrega Local (Salto a salto).
* **Misión:** Mover la información de una tarjeta de red a la siguiente (ej: de tu PC al Router WiFi). Garantiza que el cable no tenga errores.
* **Elemento Clave:** Dirección Física (**MAC Address**).
* **Hardware:** **Switches**.
* **Unidad de datos (PDU):** **TRAMA** (Frame).

### **1. Capa FÍSICA (Physical)**

* **Función:** Transmisión Binaria.
* **Misión:** Convertir la información en señales físicas (electricidad, luz, ondas de radio).
* **Hardware:** Cables, Hubs, Fibra óptica.
* **Unidad de datos (PDU):** **BIT**.

---

## 🔄 Resumen del Proceso de Encapsulación

Al bajar desde tu programa hasta el cable, cada capa añade su "sobre" (cabecera). El nombre del dato cambia:

| Capa | Nombre del Dato (PDU) | Identificador Clave |
| --- | --- | --- |
| **7, 6, 5** (App/Pres/Ses) | **DATOS** | Información útil |
| **4** (Transporte) | **SEGMENTO** | **Puerto** (ej. 8080) |
| **3** (Red) | **PAQUETE** | **IP** (ej. 192.168.1.1) |
| **2** (Enlace) | **TRAMA** | **MAC** (ej. AA:BB:CC...) |
| **1** (Física) | **BITS** | Señal eléctrica (010101) |

> **Visualmente:**
> `[ Trama [ Paquete [ Segmento [ Datos ] ] ] ]`

---

## 🧠 Reglas Mnemotécnicas

Para recordar el orden de abajo (1) a arriba (7):

> "**F**erviente **E**studiante **R**ecuerda **T**odos **S**us **P**asos **A**diós"
> 1. **F**ísica
> 2. **E**nlace
> 3. **R**ed
> 4. **T**ransporte
> 5. **S**esión
> 6. **P**resentación
> 7. **A**plicación
>
>

---

## ⚖️ Diferencia con TCP/IP (Resumen)

El modelo **TCP/IP** (4 capas) es más práctico y agrupa las capas OSI:

1. **Capa de Aplicación (TCP/IP)** = Aplicación + Presentación + Sesión (OSI).
2. **Capa de Transporte (TCP/IP)** = Transporte (OSI).
3. **Capa de Internet (TCP/IP)** = Red (OSI).
4. **Capa de Acceso a Red (TCP/IP)** = Enlace + Física (OSI).
## 1. Contexto: ¿Dónde estamos? (Modelos de Red)

Para entender TCP y UDP, primero hay que ubicarlos en el mapa. Existen dos modelos: el teórico (OSI) y el práctico (TCP/IP).

### Comparativa de Modelos

| Modelo OSI (7 Capas - Teórico) | Modelo TCP/IP (4 Capas - Práctico) |
| --- | --- |
| 7. **Aplicación** (Application) | **1. Aplicación** |
| 6. **Presentación** (Presentation) | *(Englobada en Aplicación)* |
| 5. **Sesión** (Session) | *(Englobada en Aplicación)* |
| 4. **Transporte** (Transport) | **2. Transporte** (Aquí viven TCP y UDP) 📍 |
| 3. **Red** (Network) | **3. Internet** (Aquí vive la IP) |
| 2. **Enlace de Datos** (Data Link) | **4. Acceso a Red** |
| 1. **Física** (Physical) | *(Englobada en Acceso a Red)* |

> **Nota de examen:** TCP y UDP son protocolos de la **Capa de Transporte**. Su misión es permitir la comunicación entre **procesos** (programas) que se ejecutan en máquinas distintas.

---

## 2. Conceptos Fundamentales: La Santísima Trinidad (IP, Puerto, Socket)

Para que dos programas hablen, necesitamos responder a tres preguntas: ¿Quién es? (IP), ¿Qué programa es? (Puerto) y ¿Cómo conecto? (Socket).

### 📍 1. La Dirección IP (Internet Protocol)

> **Definición:** Es el identificador lógico de una **MÁQUINA** (interfaz de red) dentro de una red.

* **Función:** Permite encontrar el dispositivo físico en el mundo (o en tu red local).
* **Formato (IPv4):** 4 números del 0 al 255 separados por puntos.
* Ejemplo: `192.168.1.50`


* **⚠️ Concepto Vital para DAM (Localhost):**
* **IP:** `127.0.0.1` (o el alias `localhost`).
* **Significado:** "Yo mismo". Se usa para cuando el Cliente y el Servidor están corriendo en el mismo ordenador (típico en desarrollo/exámenes).


* **En Java:** Se maneja con la clase `java.net.InetAddress`.

---

### 🚪 2. El Puerto (Port)

> **Definición:** Es un número que identifica a un **PROCESO** (Aplicación/Servicio) específico dentro de esa máquina.

* **Función:** Cuando el dato llega a la máquina (IP), el Sistema Operativo usa el puerto para saber a qué programa entregárselo.
* **Rango Total:** `0` a `65535` (16 bits).
* **Clasificación (¡Importante para no romper nada!):**

| Rango | Tipo | Descripción |
| --- | --- | --- |
| **0 - 1023** | **Bien Conocidos** (System) | Reservados para el sistema y protocolos estándar (Web, Mail). **NO USAR** en tus prácticas. |
| **1024 - 49151** | **Registrados** (User) | Rango para aplicaciones de usuario (MySQL, Minecraft, etc.). **AQUÍ PROGRAMAMOS NOSOTROS**. |
| **49152 - 65535** | **Dinámicos** | Efímeros. El S.O. los asigna automáticamente a los clientes para la conexión de salida. |

* **Ejemplos clásicos:**
* `80`: Web (HTTP)
* `443`: Web Segura (HTTPS)
* `3306`: Base de datos MySQL
* `5000 / 8080`: Típicos para pruebas en Java/Tomcat.



---

### 🔌 3. El Socket

> **Definición:** Es la abstracción de software que sirve como **PUNTO FINAL** (Endpoint) de una comunicación.

* **La Fórmula:** `Socket = Dirección IP + Número de Puerto` + (Protocolo TCP/UDP).
* **Funcionamiento:**
* Es como un "enchufe" virtual. Tu programa Java "enchufa" un Socket a un puerto y el S.O. le deriva todo lo que llegue ahí.
* Permite un flujo de datos bidireccional (Entrada/Salida).


* **En Java:**
* `ServerSocket`: Para esperar conexiones (escuchar).
* `Socket`: Para iniciar o gestionar una conexión activa.



---

### 🏢 Analogía Definitiva: El Edificio de Oficinas

Para visualizarlo, imagina un edificio gigante de empresas:

```text
       MÁQUINA (Tu PC)  ->  EDIFICIO "TORRE EUROPA"
       ============================================
              |
[DIRECCIÓN]   |  Dirección: Calle Falsa 123
   (IP)       |  (IP: 192.168.1.15)
              |
       --------------------------------------------
              |
 [PUERTOS]    |  Oficina 80:   Recepción Web (http)
(Procesos)    |  Oficina 3306: Archivo de Datos (MySQL)
              |  Oficina 5000: TU EMPRESA JAVA (Tu ServerSocket)
              |
       --------------------------------------------
              |
 [SOCKET]     |  El Teléfono dentro de la Oficina 5000
(Conexión)    |  (Es lo que usas para hablar con el exterior)
              |

```

> **Resumen del viaje:**
> El cartero (Red) llega a la **Calle Falsa 123 (IP)**, entra al edificio y busca la **Oficina 5000 (Puerto)**. Allí, alguien descuelga el **teléfono (Socket)** y empieza la conversación.

---
Vamos a aterrizar toda esa teoría de "Burocracia vs Kamikaze" en código Java real. En PSP (Programación de Servicios y Procesos), la teoría vale el 30%, pero **el código es lo que aprueba el examen**.

---

## 3. Implementación en Java: Del Concepto al Código

No solo hay que saber qué hacen, sino **qué clases de Java** lo hacen.

### 🐢 A. TCP: Sockets de Flujo (Stream Sockets)

**Mentalidad:** Piensa en una **llamada telefónica**. Tienes que descolgar, establecer conexión, hablar y colgar. Si se corta, se acabó.

**Clases Clave:**

1. **`ServerSocket`**: El portero. Solo espera en el servidor.
2. **`Socket`**: El teléfono. Lo usan ambos (cliente y servidor) para hablar.

#### Código Ejemplo: Servidor TCP (El que espera)

```java
import java.io.*;
import java.net.*;

public class ServidorTCP {
    public static void FizzBuzz(String[] args) {
        // Puerto > 1024 para no chocar con el sistema
        final int PUERTO = 5000; 
        
        try {
            // 1. ANUNCIO: Abrimos el puerto
            ServerSocket server = new ServerSocket(PUERTO);
            System.out.println("🐢 TCP: Esperando a alguien en el puerto " + PUERTO);

            // 2. ESPERA (BLOQUEANTE): El programa se detiene aquí hasta que alguien llame
            Socket cliente = server.accept(); 
            System.out.println("✅ ¡Conexión establecida con " + cliente.getInetAddress() + "!");

            // 3. TUBERÍAS: Preparamos los canales de comunicación
            // DataInputStream para recibir datos primitivos (int, string, etc.)
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // 4. LEER: Esperamos el mensaje del cliente
            String mensaje = entrada.readUTF();
            System.out.println("📩 Recibido: " + mensaje);

            // 5. RESPONDER: Le decimos algo de vuelta
            salida.writeUTF("Recibido alto y claro. Cambio y corto.");

            // 6. CERRAR: Muy importante liberar recursos
            cliente.close();
            server.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

#### Código Ejemplo: Cliente TCP (El que llama)

```java
import java.io.*;
import java.net.*;

public class ClienteTCP {
    public static void FizzBuzz(String[] args) {
        final String HOST = "localhost"; // O la IP: "192.168.1.XX"
        final int PUERTO = 5000;

        try {
            System.out.println("🐢 TCP: Intentando conectar...");
            
            // 1. CONEXIÓN: Al hacer 'new', intenta el Handshake. Si falla, lanza excepción.
            Socket socket = new Socket(HOST, PUERTO); 
            
            // 2. TUBERÍAS
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

            // 3. ESCRIBIR: Mandamos mensaje
            salida.writeUTF("¡Hola Servidor! Soy el Cliente.");

            // 4. LEER: Esperamos respuesta
            String respuesta = entrada.readUTF();
            System.out.println("📥 Servidor dice: " + respuesta);

            // 5. CERRAR
            socket.close();

        } catch (IOException e) {
            System.out.println("❌ Error: No se pudo conectar (¿Servidor apagado?)");
        }
    }
}
```

---

### 🐇 B. UDP: Sockets de Datagrama

**Mentalidad:** Piensa en **enviar cartas**. Escribes la dirección en el sobre, lo tiras al buzón y te olvidas. No sabes si llegó, ni cuándo.

**Clases Clave:**

1. **`DatagramSocket`**: El buzón. Sirve para enviar Y recibir.
2. **`DatagramPacket`**: El sobre. Contiene los datos (`byte[]`) y la dirección (`IP + Puerto`).

#### Código Ejemplo: Receptor UDP (Servidor)

```java
import java.net.*;

public class ReceptorUDP {
    public static void FizzBuzz(String[] args) {
        final int PUERTO = 6000;
        // Buffer: Espacio reservado para recibir la carta (ej. 1KB)
        byte[] buffer = new byte[1024]; 

        try {
            // 1. BUZÓN: Abrimos el socket en el puerto
            DatagramSocket socket = new DatagramSocket(PUERTO);
            System.out.println("🐇 UDP: Esperando paquetes...");

            // 2. SOBRE VACÍO: Preparamos un paquete para rellenarlo con lo que llegue
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);

            // 3. RECIBIR (BLOQUEANTE): Se queda quieto hasta que llega algo
            socket.receive(paqueteRecibido);

            // 4. PROCESAR: Convertimos los bytes recibidos a String
            // OJO: Hay que usar el tamaño real recibido (.getLength()), no todo el buffer
            String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            
            System.out.println("📩 Mensaje de " + paqueteRecibido.getAddress() + ": " + mensaje);

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

#### Código Ejemplo: Emisor UDP (Cliente)

```java
import java.net.*;

public class EmisorUDP {
    public static void FizzBuzz(String[] args) {
        final int PUERTO_DESTINO = 6000;
        final String IP_DESTINO = "localhost";

        try {
            // 1. BUZÓN: Sin puerto específico, el SO nos da uno libre cualquiera
            DatagramSocket socket = new DatagramSocket();

            // 2. DATOS: Preparamos el mensaje en bytes
            String mensaje = "¡Paquete a la velocidad de la luz!";
            byte[] buffer = mensaje.getBytes();
            InetAddress direccion = InetAddress.getByName(IP_DESTINO);

            // 3. SOBRE LLENO: Datos + Longitud + IP + Puerto
            DatagramPacket paqueteEnviado = new DatagramPacket(
                buffer, 
                buffer.length, 
                direccion, 
                PUERTO_DESTINO
            );

            // 4. ENVIAR: ¡Fium!
            socket.send(paqueteEnviado);
            System.out.println("📤 Paquete lanzado (No sabemos si llegará).");

            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

---

## 🎯 Puntos Clave de Examen (La Diferencia)


| Característica | TCP (Código) | UDP (Código) |
| --- | --- | --- |
| **Clase Principal** | `Socket` y `ServerSocket` | `DatagramSocket` (para ambos) |
| **Envío de Datos** | Mediante **Streams** (`getInputStream` / `getOutputStream`) | Mediante **Paquetes** (`DatagramPacket`) |
| **Conexión** | `server.accept()` (Espera conexión) | No existe `accept()`. Solo `receive()`. |
| **Tipo de Dato** | Flujo continuo (como leer un fichero). | Bloques discretos (Arrays de bytes). |
| **Orden** | `readUTF()` lee exactamente lo que envió `writeUTF()`. | Tienes que convertir `byte[]` a `String` manualmente. |

### Errores Típicos

1. **En UDP:** Olvidar que `DatagramPacket` necesita un array de bytes (`byte[]`), no un String directo.
2. **En TCP:** Olvidar hacer el `accept()` en el servidor. Sin `accept`, nadie entra.
3. **Puertos:** Intentar usar un puerto ocupado o menor de 1024.
4. **Localhost:** Escribir mal la IP (`127.0.0.1` es seguro).
5. **Excepciones:** No rodear el código de redes con `try-catch` (Java te obliga, es `Checked Exception`).
---

¡Perfecto! Vamos a blindar este apartado 4.

La tabla que tenías estaba bien, pero para **asegurar el 10** en un examen de DAM/PSP, necesitamos añadir detalles técnicos que marquen la diferencia (como el tamaño de la cabecera o el tipo de transmisión).

Aquí tienes la versión **"Senior Developer"** de la comparativa, lista para copiar a tu Markdown. He añadido una sección de **"Casos de Uso para Examen"** que suele caer mucho (tipo test o justificación).

---

## 4. Comparativa Técnica: TCP vs UDP (La Tabla Definitiva)

> ** TCP es fiabilidad y conexión. UDP es velocidad y simplicidad. **

| Característica | 🐢 TCP (Transmission Control Protocol) | 🐇 UDP (User Datagram Protocol) |
| --- | --- | --- |
| **Conexión** | **Orientado a conexión**. Requiere *Handshake* (SYN, SYN-ACK, ACK) antes de hablar. | **Sin conexión**. Envía los datos directamente sin avisar ("Fire and Forget"). |
| **Fiabilidad** | **Absoluta**. Garantiza que el dato llega. Usa Acuses de Recibo (ACKs). | **Nula (Best Effort)**. No garantiza entrega. Si se pierde, no avisa. |
| **Orden** | **Garantizado**. Reordena los paquetes al llegar (Secuenciación). | **No garantizado**. Llegan en el orden que la red quiera. |
| **Control de Flujo** | **Sí**. Evita saturar al receptor (Ventana deslizante). | **No**. Envía a la velocidad que pueda el emisor. |
| **Peso (Cabecera)** | **Pesado (20 Bytes mín.)**. Mucha información de control. | **Ligero (8 Bytes)**. Mínima sobrecarga. |
| **Transmisión** | **Byte Stream**. Flujo continuo de datos (leído como un chorro de agua). | **Datagramas**. Paquetes discretos e independientes (leído por bloques). |
| **Tipos de Envío** | Solo **Unicast** (1 a 1). | Soporta **Unicast**, **Multicast** y **Broadcast**. |
| **Velocidad** | **Lento**. Debido a los ACKs y el control de errores. | **Muy Rápido**. Sin esperas ni confirmaciones. |
| **Uso Ideal** | Web (HTTP), Email (SMTP), Transferencia Archivos (FTP), SSH. | Streaming (YouTube), VoIP (Zoom), Juegos Online, DNS. |

---

### 🧠 Análisis de "Por qué"

**"¿Por qué TCP es más lento?"**

1. **Handshake:** Pierde tiempo al inicio estableciendo la conexión.
2. **ACKs:** Cada paquete enviado requiere una confirmación de vuelta.
3. **Tamaño de Cabecera:** TCP añade **20 bytes** de "burocracia" a cada paquete. UDP solo añade **8 bytes**.
* *UDP es mucho más eficiente en el uso del ancho de banda.*

---

### 📝 Guía Rápida para Preguntas de Examen (Situaciones)


* **Escenario 1: "Estamos descargando un archivo .zip de internet."**
* **Protocolo:** **TCP**.
* **Por qué:** Necesitamos **integridad**. Si falta un solo bit, el archivo está corrupto y no se puede abrir. La velocidad es secundaria.


* **Escenario 2: "Estamos viendo un directo en Twitch/YouTube."**
* **Protocolo:** **UDP** (o variantes sobre UDP).
* **Por qué:** Primamos la **velocidad**. Si se pierde un paquete (un frame de vídeo), el usuario ve un pequeño pixelado o salto, pero el vídeo sigue. Si usáramos TCP y se pierde un paquete, el vídeo se congelaría hasta recuperarlo (buffering), arruinando la experiencia en vivo.


* **Escenario 3: "Un juego FPS (Shooter) multijugador enviando la posición del jugador."**
* **Protocolo:** **UDP**.
* **Por qué:** Importa la posición **actual**. No me sirve de nada recibir (retransmitida por TCP) la posición de hace 2 segundos; ese jugador ya se ha movido.


* **Escenario 4: "Enviar un correo electrónico."**
* **Protocolo:** **TCP** (SMTP).
* **Por qué:** El texto debe llegar completo y ordenado.


* **TCP es como una Carta Certificada:** Tienes que firmar que la has recibido. Si no estás, el cartero vuelve otro día. Seguro, pero lento.
* **UDP es como una Postal:** La tiras al buzón y confías en correos. Es rapidísimo y barato, pero puede que nunca llegue o llegue arrugada.

---
¡Perfecto! Vamos a rematar la faena. Este apartado es crítico porque en el examen práctico **es donde se ven los errores de concepto**. Si confundes la forma de enviar datos en TCP con la de UDP, el ejercicio suele valer 0.

Aquí tienes el **Apartado 5** pulido, estructurado y con las advertencias necesarias para un estudiante de DAM.

---

## 5. Implementación en Java: Las Clases Maestras (API `java.net`)

En Java la implementación cambia radicalmente dependiendo de si elegimos el camino "fiable" (TCP) o el "rápido" (UDP).

### 🐢 A. Sockets TCP (Orientados a Conexión)

En TCP, la red se comporta como una **tubería continua**. Una vez conectados, enviamos y recibimos datos como si estuviéramos escribiendo en un archivo local.

#### 1. Roles Claros

Como hay conexión, **hay jerarquía**:

* **Servidor:** Es pasivo. Espera a que le llamen.
* **Cliente:** Es activo. Inicia la llamada.

#### 2. Las Clases Clave

| Clase | Rol | Misión Principal |
| --- | --- | --- |
| **`ServerSocket`** | **Solo Servidor** | "El Portero". Su única misión es escuchar el puerto y aceptar clientes. |
| **`Socket`** | **Cliente y Servidor** | "El Teléfono". Representa la conexión activa. Por aquí viajan los datos. |

#### 3. El Flujo de Trabajo (Snippet Mejorado)

**Lado Servidor:**

```java
try (ServerSocket server = new ServerSocket(5000)) { // 1. Abrir Puerto
    System.out.println("🐢 Esperando clientes...");
    
    // 2. BLOQUEO: El programa se detiene en .accept() hasta que alguien entra
    Socket cliente = server.accept(); 
    
    // 3. TUBERÍAS (Streams): Usamos I/O Streams estándar
    DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
    salida.writeUTF("¡Hola desde TCP!"); // Enviamos texto
    
    cliente.close(); // 4. Cerrar la conexión individual
}

```

**Lado Cliente:**

```java
// 1. CONEXIÓN: Al hacer 'new', se realiza el Handshake automáticamente
try (Socket socket = new Socket("127.0.0.1", 5000)) {
    
    // 2. TUBERÍAS: Obtenemos el input para leer
    DataInputStream entrada = new DataInputStream(socket.getInputStream());
    
    // 3. LEER: Leemos del stream (bloqueante)
    String mensaje = entrada.readUTF(); 
    System.out.println("Recibido: " + mensaje);
}

```

---

### 🐇 B. Sockets UDP (Sin Conexión)

En UDP no hay tuberías ni conexión fija. Todo funciona enviando **paquetes independientes** (Datagramas). Es como enviar cartas postales: necesitas un sobre, escribir la dirección y tirarlo al buzón.

#### 1. Roles Difusos

No hay una diferencia estricta a nivel de clases entre Cliente y Servidor. Ambos usan las mismas herramientas, solo cambia quién envía primero.

#### 2. Las Clases Clave

| Clase | Analogía | Misión Principal |
| --- | --- | --- |
| **`DatagramSocket`** | **El Buzón** | Sirve tanto para **enviar** (`.send()`) como para **recibir** (`.receive()`). |
| **`DatagramPacket`** | **La Carta (Sobre)** | Es el objeto que transporta los datos (`byte[]`) + la Dirección IP + el Puerto. |

#### 3. El Flujo de Trabajo (Snippet Mejorado)

**Para ENVIAR (Emisor):**

```java
// 1. PREPARAR DATOS: UDP solo entiende de BYTES, no de Strings directos
byte[] mensajeBytes = "Hola UDP".getBytes();
InetAddress ipDestino = InetAddress.getByName("localhost");
int puertoDestino = 6000;

// 2. EL SOBRE (Packet): Datos + Longitud + IP + Puerto
DatagramPacket sobre = new DatagramPacket(mensajeBytes, mensajeBytes.length, ipDestino, puertoDestino);

// 3. EL BUZÓN (Socket): Creamos y enviamos
DatagramSocket buzon = new DatagramSocket(); // Puerto aleatorio para salir
buzon.send(sobre);
buzon.close();

```

**Para RECIBIR (Receptor):**

```java
// 1. EL BUZÓN: Debemos escuchar en un puerto fijo
DatagramSocket buzon = new DatagramSocket(6000);
byte[] buffer = new byte[1024]; // Espacio para recibir (1KB)

// 2. SOBRE VACÍO: Preparamos un packet "contenedor"
DatagramPacket sobreVacio = new DatagramPacket(buffer, buffer.length);

// 3. RECIBIR: Se bloquea hasta que llega una carta
buzon.receive(sobreVacio);

// 4. LEER: Convertir bytes a String (Cuidado: usar .getLength(), no buffer.length)
String mensaje = new String(sobreVacio.getData(), 0, sobreVacio.getLength());

```

---

### ⚠️ Resumen de Diferencias (La Chuleta de Código)

Esto es lo que debes tener en la cabeza al programar en el examen:

| Concepto | En TCP usas... | En UDP usas... |
| --- | --- | --- |
| **Comunicación** | `InputStream` / `OutputStream` | `byte[]` arrays |
| **Unidad de envío** | Datos continuos (`writeUTF`, `println`) | `DatagramPacket` (bloques) |
| **Esperar datos** | `in.read()` o `readLine()` | `socket.receive(packet)` |
| **Clase de escucha** | `ServerSocket` (tiene método `accept`) | `DatagramSocket` (no tiene `accept`) |
| **Dirección Destino** | Se define al crear el `Socket` (Cliente) | Se define en cada `DatagramPacket` |

> **🔥 Pro Tip para el Examen:**
> * En **TCP**, el error más común es olvidar hacer `flush()` si usas buffers de salida, o no cerrar los streams.
> * En **UDP**, el error clásico es crear el `String` usando todo el buffer (`new String(buffer)`) en lugar de solo lo recibido (`new String(data, 0, length)`), lo que llena tu mensaje de basura o espacios vacíos.
>
>
---

## 6. Resumen "Flash" para el examen

> **Pregunta:** ¿Diferencia principal?
> **Respuesta:** **TCP** establece una conexión y garantiza que los datos lleguen ordenados y sin errores (lento pero seguro). **UDP** envía paquetes (datagramas) sin conexión ni comprobaciones, priorizando la velocidad (rápido pero puede perder datos).

### Chuleta de conceptos:

* **IP:** ¿Quién es? (Máquina)
* **Puerto:** ¿Qué programa es? (Proceso)
* **Socket:** IP + Puerto.
* **TCP:** Burocracia, fiabilidad, `ServerSocket`.
* **UDP:** Velocidad, datagramas, `DatagramSocket`.

---

