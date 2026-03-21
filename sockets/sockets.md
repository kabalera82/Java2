TCP: Transmission Control Protocol -> Orientado a conexión.
UDP: User Datagram Protocol -> NO ESTA orientado a conexión.
* Orientado a conexión. Quiere decir que antes de enviar datos se establece una conexión
* TCP tiene un correcto control de errores (si se pierde algo se vuelve a mandar) / fiable / garantiza Entrega / garantiza orden /lento
* UDP si se pierde algo no pasa nada. / No fiable / no garantiza entrega / no garantiza orden / rapido
* TCP correcto control de flujo porque estamos todo el rato mandando paquetes.
* Control de congestion, TCP baja la velocidad si se empieza a saturar (mucho mas lento que UDP por las comprobaciones)

IP: Internet Protocol
OSI: Open Systems Interconnection
PSP: Programación de Servicios y Procesos (en contexto DAM)

TCP / IP
- Application
- Transport
- Internet
- Network Acces

Osi Model
- Application
- Presentation
- Session
- Transport
- Network
- Data Link
- Pyshical

OSI → teórico (7 capas)
TCP/IP → práctico (4 capas)

TCP y UDP son protocolos de la capa de transporte del modelo TCP/IP.
Permiten la comunicación entre procesos usando puertos.
TCP = fiable
UDP = rápido

### TCP y UDP (claro, directo, nivel **DAM**)

¿Saber diferenciar entre TCP y UDP?
- TCP pide siempre confirmación. TCP se usa cuando es importante que los datos lleguen correctamente.
- UDP es lineal. UDP se usa cuando prima la velocidad y se acepta la pérdida de datos.

**TCP y UDP**
son protocolos de la **capa de transporte**
del modelo TCP/IP.
Permiten la comunicación entre **procesos** usando **puertos**.

---

## TCP (Transmission Control Protocol)

**TCP es orientado a conexión y fiable.**
Antes de enviar datos, cliente y servidor **establecen una conexión**. Durante la comunicación, TCP **garantiza** que los datos llegan **completos, en orden y sin duplicados**.

Características esenciales:

* Establece conexión (handshake).
* Numera los datos y controla el orden.
* Confirma recepción (ACK).
* Reenvía paquetes perdidos.
* Controla errores y flujo.

Consecuencia:

* **Más seguro**, pero **más lento** que UDP.

Usos típicos:

* Web (HTTP/HTTPS)
* FTP
* Correo
* Bases de datos
* **Sockets TCP en Java (PSP)**

En PSP:

* `ServerSocket` (servidor)
* `Socket` (cliente)
* Normalmente: **un cliente = un hilo**

---

## UDP (User Datagram Protocol)

** UDP es sin conexión y no fiable.**
No se establece conexión previa. Los datos se envían en **datagramas** sin comprobar si llegan ni en qué orden.

Características esenciales:

* No hay conexión.
* No hay confirmación.
* No hay reenvío.
* No garantiza orden ni entrega.

Consecuencia:

* **Muy rápido**, pero **puede perder datos**.

Usos típicos:

* Streaming
* Juegos online
* VoIP
* DNS

En PSP:

* `DatagramSocket`
* `DatagramPacket`
* Uso poco habitual en DAM.

---

## Comparación directa (lo que entra en examen)

| Aspecto            | TCP           | UDP            |
| ------------------ | ------------- | -------------- |
| Conexión           | Sí            | No             |
| Fiabilidad         | Sí            | No             |
| Orden de datos     | Garantizado   | No             |
| Control de errores | Sí            | No             |
| Velocidad          | Menor         | Mayor          |
| Uso en PSP         | Muy frecuente | Poco frecuente |

---

## Idea clave para responder bien

> **TCP** se usa cuando es importante que los datos lleguen correctamente.
> **UDP** se usa cuando prima la velocidad y se acepta la pérdida de datos.

Si quieres, te lo dejo en **respuesta modelo de examen** (4–5 líneas) o en **chuleta de memorización**.


IP identifica máquina
Puerto identifica proceso
Socket = IP + Puerto

DATAGRAMA concepto de redes, unidad basica de conexion (o transmision)
- Cada datagrama viaja por su cuenta es como un paquete
- (similar a UDP no garantiza orden puede perderse)

En java hay dos clases datagrama socket y datagramaAquí tienes tus apuntes organizados, completados y estructurados. He mantenido toda la información original, añadiendo las explicaciones técnicas necesarias para conectar los conceptos, ideal para repasar la asignatura de **PSP (Programación de Servicios y Procesos)** y **Redes**.

---

# 📚 Guía de Estudio: Redes y Sockets (Nivel DAM/PSP)

## 1. Contexto: Modelos de Red

Para entender dónde operan TCP y UDP, primero situamos las capas.

### Diferencia clave

* **Modelo OSI (7 Capas):** Es un modelo **teórico** y de referencia conceptual.
* **Modelo TCP/IP (4 Capas):** Es el modelo **práctico** que usa Internet realmente.

### Estructura de Capas

| Modelo TCP/IP (4 Capas)         | Modelo OSI (7 Capas)            |
|---------------------------------|---------------------------------|
| **1. Application** (Aplicación) | 7. Application (Aplicación)<br> 

<br>6. Presentation (Presentación)<br>

<br>5. Session (Sesión) |
| **2. Transport** (Transporte) | **4. Transport (Transporte)** *<-- Aquí viven TCP y UDP* |
| **3. Internet** (Internet) | 3. Network (Red) |
| **4. Network Access** (Acceso a Red) | 2. Data Link (Enlace de datos)<br>

<br>1. Physical (Física) |

---

## 2. Conceptos Fundamentales (La base de todo)

Antes de ver los protocolos, debemos definir cómo se identifican los elementos en la red:

1. **IP (Internet Protocol):**
* Identifica a la **máquina** (host) dentro de la red.
* Es como la dirección postal del edificio.


2. **Puerto (Port):**
* Identifica al **proceso** (programa/aplicación) dentro de esa máquina.
* Es como el número de piso o apartamento.


3. **Socket:**
* Es la combinación de **IP + Puerto**.
* Representa el punto final de una comunicación bidireccional.
* `Socket = IP : Puerto`



---

## 3. Protocolos de Transporte: TCP vs UDP

Ambos pertenecen a la **Capa de Transporte**. Su función es permitir la comunicación entre procesos (aplicaciones) en diferentes máquinas.

### 🐢 TCP (Transmission Control Protocol)

**Definición:** Protocolo **orientado a conexión** y **fiable**.

* **Orientado a conexión:** Antes de enviar cualquier dato, se realiza un "saludo" (handshake) para establecer un canal virtual entre cliente y servidor.
* **Fiabilidad Total:**
* **Control de errores:** Si un paquete se pierde o llega corrupto, se detecta y **se vuelve a enviar**.
* **Garantiza entrega:** Asegura que todo lo que sale, llega.
* **Garantiza orden:** Los paquetes se numeran. Si llegan desordenados, TCP los reordena antes de entregarlos a la aplicación.


* **Control de Flujo:** Evita saturar al receptor si este es más lento que el emisor.
* **Control de Congestión:** TCP detecta si la red está saturada y **baja la velocidad** automáticamente.
* **Desventaja:** Toda esta gestión (comprobaciones, acuses de recibo ACK) lo hace **más lento** y pesado.

### 🐇 UDP (User Datagram Protocol)

**Definición:** Protocolo **NO orientado a conexión** y **no fiable**.

* **Sin conexión:** No hay saludo inicial. Se envían los datos "a ciegas" hacia la IP/Puerto destino.
* **No Fiable (Best Effort):**
* No hay confirmación de recepción (ACK).
* Si se pierde un dato, **no pasa nada** (no se reenvía).
* **No garantiza orden:** Los datos pueden llegar desordenados y la aplicación debe lidiar con ello.


* **Ventaja:** Es extremadamente **rápido** y ligero (poca cabecera).
* **El concepto de Datagrama:**
* Es la unidad básica de transmisión en UDP.
* Cada datagrama viaja por su cuenta (como una carta individual), independiente de los anteriores o siguientes.



---

## 4. Tabla Comparativa (Para el Examen)

| Característica                 | TCP (Transmission Control Protocol)          | UDP (User Datagram Protocol)                 |
|--------------------------------|----------------------------------------------|----------------------------------------------|
| **Conexión**                   | **Orientado a conexión** (Handshake previo). | **No orientado a conexión** (Envío directo). |
| **Fiabilidad**                 | **Alta** (Garantiza entrega).                | **Baja** (No garantiza entrega).             |
| **Orden**                      | **Garantizado** (Reordena paquetes).         | **No garantizado** (Llegan como lleguen).    |
| **Control Errores**            | Sí (Reenvía si se pierde).                   | No (Si se pierde, se perdió).                |
| **Velocidad**                  | Lento (por la carga administrativa).         | Muy Rápido.                                  |
| **Control Flujo/Congestión**   | Sí (Se adapta a la red).                     | No.                                          |
| **Uso ideal**                  | Web, Email, Transferencia Archivos, BBDD.    | Streaming, Juegos Online, VoIP, DNS.         |

---

## 5. Implementación en Java (Módulo PSP)

En la asignatura de Programación de Servicios y Procesos, esto se traduce en las siguientes clases:

### Para TCP (Sockets de flujo)

Como hay conexión, diferenciamos claramente entre el rol de Servidor (espera) y Cliente (solicita).

* **`ServerSocket`**: Se usa solo en el **Servidor**. Se queda escuchando un puerto (`accept()`) esperando conexiones.
* **`Socket`**: Se usa en el **Cliente** para conectar, y en el Servidor para manejar la conexión una vez aceptada.
* **Modelo de hilos:** En TCP, como la conexión es persistente, habitualmente en el servidor se lanza **un hilo (Thread) por cada cliente** conectado para no bloquear el servicio.

### Para UDP (Sockets de datagrama)

No hay conexión persistente, solo envío de paquetes.

* **`DatagramSocket`**: Es el "buzón". Sirve tanto para enviar como para recibir. No hay distinción fuerte entre cliente/servidor a nivel de clase.
* **`DatagramPacket`**: Es la "carta". Es el objeto que contiene los datos (byte array), la IP y el puerto de destino.

---

## 6. Resumen "Flash" para memorizar

* **IP** = Identifica Máquina.
* **Puerto** = Identifica Proceso.
* **Socket** = IP + Puerto.
* **TCP** = "Burocrático". Lento pero seguro. Pide firma para todo. (Web, Email).
* **UDP** = "Kamikaze". Rápido pero descuidado. Tira los paquetes y espera que lleguen. (Streaming, Juegos).

### Frase clave para responder:

> *"TCP establece conexión y garantiza que los datos lleguen ordenados y sin errores, aunque es más lento. UDP envía datagramas sin establecer conexión; prima la velocidad sobre la fiabilidad, aceptando que puedan perderse datos."*
> 
Un socket es una conexion entre dos programas
1 tcp que es mas sencillo dos clases una cliente y otra servidor

¡Oído cocina! 🧠 Como alumno avanzado (y un poco perfeccionista), he cogido todos esos apuntes sueltos, las ideas cortadas y los códigos a medio hacer, y los he transformado en unos **Apuntes Definitivos para PSP y Redes**.

Aquí tienes todo organizado, completado y explicado para sacar el 10.

---

# 📘 APUNTES MAESTROS: REDES Y SOCKETS (PSP)

## 1. Contexto: Modelos de Red (El Mapa)

Para entender dónde "viven" TCP y UDP, primero ubicamos las capas. Existen dos modelos: el teórico (OSI) y el práctico (TCP/IP).

### Comparativa de Modelos

| Modelo OSI (7 Capas - Teórico) | Modelo TCP/IP (4 Capas - Práctico) |
| --- | --- |
| 7. **Aplicación** (Application) | **1. Aplicación** |
| 6. **Presentación** (Presentation) | *(Englobado en Aplicación)* |
| 5. **Sesión** (Session) | *(Englobado en Aplicación)* |
| 4. **Transporte** (Transport) | **2. Transporte** (Aquí están TCP y UDP) 📍 |
| 3. **Red** (Network) | **3. Internet** (Aquí vive la IP) |
| 2. **Enlace de Datos** (Data Link) | **4. Acceso a Red** |
| 1. **Física** (Physical) | *(Englobado en Acceso a Red)* |

> **Nota de examen:** TCP y UDP son protocolos de la **Capa de Transporte**. Su misión es comunicar **procesos** (programas) entre máquinas distintas.

---

## 2. Conceptos Básicos: Los Identificadores

Si no entiendes esto, no puedes programar sockets.

1. **IP (Internet Protocol):**
* Identifica a la **MÁQUINA** (Host) en la red.
* *Analogía:* Es la dirección del edificio (ej. Calle Falsa 123).


2. **PUERTO (Port):**
* Identifica al **PROCESO** (Aplicación) dentro de esa máquina.
* Rango: 0 a 65535. (Los menores de 1024 son reservados, como el 80 para Web).
* *Analogía:* Es el número de piso o puerta (ej. 3º B).


3. **SOCKET:**
* Es la combinación de **IP + PUERTO**.
* **Definición:** Punto final de una comunicación lógica.
* *Fórmula:* `Socket = 192.168.1.50 : 5000`



---

## 3. Protocolos de Transporte: La Gran Batalla

### 🐢 TCP (Transmission Control Protocol)

**"El Burocrático"**. Su prioridad es la fiabilidad, no la velocidad.

* **Orientado a Conexión:**
* Antes de enviar datos, hace un *Handshake* (saludo de 3 vías): "Hola, ¿estás?", "Sí estoy", "Vale, hablo".


* **Fiabilidad Total:**
* **Control de Errores:** Si un paquete se pierde o llega corrupto, **se vuelve a enviar**.
* **Garantiza Entrega:** Asegura que el receptor tiene todos los datos.
* **Garantiza Orden:** Numera los paquetes. Si llegan desordenados (3, 1, 2), TCP los ordena (1, 2, 3) antes de dárselos a la App.


* **Gestión de Tráfico (Lo que estaba incompleto en tus notas):**
* **Control de Flujo:** Evita saturar al *receptor*. Si el servidor es lento procesando, TCP le dice al cliente "frena un poco".
* **Control de Congestión:** Evita saturar la *red*. Si detecta que internet va mal (paquetes perdidos), baja la velocidad automáticamente.


* **Desventaja:** Es **lento** y pesado por tanta comprobación.

### 🐇 UDP (User Datagram Protocol)

**"El Kamikaze"**. Su prioridad es la velocidad, no la exactitud.

* **NO Orientado a Conexión:**
* No saluda. Lanza los datos directamente a la IP/Puerto destino. "Aquí te va esto, cógelo si puedes".


* **No Fiable (Best Effort):**
* Si un paquete se pierde, **se perdió**. No se reenvía.
* **No garantiza orden:** Los paquetes pueden llegar mezclados.


* **Datagrama:**
* Es la unidad básica de UDP (el paquete). Cada datagrama es independiente, viaja por su cuenta.


* **Ventaja:** Es muy **rápido** y ligero (poca cabecera). Ideal para tiempo real.

---

## 4. Implementación en Java (PSP) - Código Corregido

En tus notas, el código "Servidor" usaba `new Socket()`, lo cual lo convierte en un Cliente. Aquí tienes la implementación correcta y separada.

### Las Clases de Java (Diferencias Clave)

* **`ServerSocket`**: Se usa **SOLO en el Servidor**. Su función es `accept()` (esperar a que alguien llame).
* **`Socket`**: Se usa en el **Cliente** (para llamar) y en el **Servidor** (como resultado de aceptar una llamada).

### A. El Servidor (El que espera)

Debe ejecutarse primero.

```java
package tcp;
import java.io.*;
import java.net.*;

public class Servidor {
    public static void FizzBuzz(String[] args) {
        final int PUERTO = 5000;
        ServerSocket servidor = null;
        Socket sc = null; // Este socket representará al cliente que se conecte
        DataInputStream in;
        DataOutputStream out;

        try {
            // 1. Abrimos el puerto (Binding)
            servidor = new ServerSocket(PUERTO);
            System.out.println("🟢 SERVIDOR INICIADO: Esperando clientes...");

            // 2. Bucle infinito para atender clientes siempre
            while (true) {
                // 3. BLOQUEO: accept() detiene el programa hasta que alguien llama
                sc = servidor.accept(); 
                System.out.println("🤝 Cliente conectado desde: " + sc.getInetAddress());

                // 4. Establecemos tuberías (Input/Output)
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                // 5. Leemos mensaje del cliente (También bloqueante)
                String mensaje = in.readUTF();
                System.out.println("📩 Mensaje recibido: " + mensaje);

                // 6. Respondemos
                out.writeUTF("¡Hola! He recibido tu mensaje correctamente.");

                // 7. Cerramos SOLO la conexión con este cliente
                sc.close();
                System.out.println("❌ Cliente desconectado. Esperando al siguiente...\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

### B. El Cliente (El que llama)

Se ejecuta después.

```java
package tcp;
import java.io.*;
import java.net.*;

public class Cliente {
    public static void FizzBuzz(String[] args) {
        final String HOST = "127.0.0.1"; // Localhost
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;

        try {
            // 1. Intentamos conectar (Handshake)
            System.out.println("🟡 Intentando conectar al servidor...");
            Socket sc = new Socket(HOST, PUERTO); // Si falla aquí -> Excepción
            System.out.println("✅ ¡Conectado!");

            // 2. Tuberías
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            // 3. Enviamos mensaje
            out.writeUTF("Hola servidor, soy el cliente.");

            // 4. Esperamos respuesta (Bloqueante)
            String respuesta = in.readUTF();
            System.out.println("📥 Servidor dice: " + respuesta);

            // 5. Cerramos conexión
            sc.close();

        } catch (IOException e) {
            System.out.println("⛔ Error: No se encuentra el servidor.");
        }
    }
}

```

---

## ⚡ RESUMEN FLASH (Para el repaso de 5 min)

* **OSI (7 capas) vs TCP/IP (4 capas):** Nosotros trabajamos en la capa de **Transporte**.
* **Socket** = IP (Máquina) + Puerto (Proceso).
* **TCP vs UDP:**
* **TCP:** Lento, seguro, ordenado, con conexión. (Web, Email).
* **UDP:** Rápido, inseguro, desordenado, sin conexión. (Juegos, Streaming).


* **Datagrama:** Unidad básica de UDP (paquete independiente).
* **Clases Java TCP:**
* `ServerSocket`: El portero del servidor (`accept()`).
* `Socket`: El teléfono para hablar (usado por cliente y servidor).

¡Excelente! Tu resumen es muy acertado. Basándome en eso, voy a completar y estructurar mejor la parte final sobre sockets.

---

## 7. Sockets: La Conexión entre Programas

**Definición:** Un **socket** es un **extremo de comunicación bidireccional** que conecta dos programas (procesos) a través de una red. Es la interfaz que usa un programa para enviar y recibir datos.

### 📡 Sockets TCP (Orientados a Conexión / Flujo)
Son más "sencillos" de programar a nivel lógico porque el protocolo ya maneja la fiabilidad. Se usan dos clases principales:

1. **`ServerSocket` (Lado Servidor):**
    * Se crea en el servidor y se queda **escuchando (`accept()`)** en un puerto específico.
    * Cuando un cliente se conecta, el `accept()` devuelve un objeto **`Socket`** para esa conexión particular.

2. **`Socket` (Lado Cliente y Conexión Aceptada):**
    * En el **Cliente:** Se usa para **conectar** a la IP y Puerto del servidor.
    * En el **Servidor:** Se usa para **comunicarse** con un cliente concreto, una vez aceptada la conexión (el objeto devuelto por `accept()`).

**Flujo de trabajo:**
1. **Servidor** crea un `ServerSocket` y llama a `accept()` (se bloquea esperando).
2. **Cliente** crea un `Socket` con la IP/puerto del servidor (esto inicia la conexión).
3. **Servidor** acepta la conexión, obteniendo un nuevo `Socket` para ese cliente.
4. Ambos lados obtienen **streams** de entrada/salida (`getInputStream()`, `getOutputStream()`) de su `Socket` y comienzan a intercambiar datos como si fuera un archivo o consola.
5. Al finalizar, se cierran los streams y los sockets.

**Característica Clave:** La conexión es **persistente y dedicada**. Por eso, el servidor normalmente usa **un hilo por cliente** para manejar múltiples conexiones simultáneas sin bloquearse.

### 📨 Sockets UDP (Sin Conexión / Datagrama)
No hay una "conexión" establecida. Se envían **datagramas** (paquetes) independientes.

Se usan principalmente dos clases:
1. **`DatagramSocket`:** Representa el buzón de envío/recepción. Se crea atado a un puerto (si se va a recibir) o a cualquier puerto libre (si solo se va a enviar).
2. **`DatagramPacket`:** Representa el paquete de datos. Contiene:
    * Un array de bytes con los datos.
    * La longitud de los datos.
    * La dirección IP y puerto de destino (para enviar).
    * La dirección IP y puerto de origen (al recibir).

**Flujo de trabajo:**
1. Se crea un `DatagramSocket`.
2. Se crea un `DatagramPacket` con los datos y la dirección de destino (IP + Puerto).
3. Se envía el paquete con `socket.send(packet)`.
4. Para recibir, se crea un `DatagramPacket` vacío (con un buffer) y se llama a `socket.receive(packet)` (se bloquea hasta llegar algo).
5. El mismo `DatagramSocket` puede enviar y recibir paquetes de/para múltiples direcciones.

**Característica Clave:** No hay streams. Cada `DatagramPacket` es una unidad independiente. El orden y la entrega no están garantizados.

---

## 8. Resumen Final y Esquema Visual

### Esquema Mental: Comunicación por Sockets

```
[PROGRAMA A] <--(Socket: IP_A:Puerto_X)---- Conexión de Red ----(Socket: IP_B:Puerto_Y)--> [PROGRAMA B]
```

### ¿Cuándo usar cada uno en PSP (Java)?

| Aspecto            | Sockets TCP (`ServerSocket` y `Socket`)                                                               | Sockets UDP (`DatagramSocket` y `DatagramPacket`)                                                        |
|--------------------|-------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------|
| **Paradigma**      | **Cliente-Servidor** claro. Conexión dedicada.                                                        | **Punto a punto** o **multidifusión**. Sin conexión.                                                     |
| **Complejidad**    | **Más sencillo** para lógica de aplicación, porque la red es transparente (parece un flujo de datos). | **Más complejo** para la aplicación, que debe manejar pérdidas, orden y delimitar mensajes.              |
| **Uso en DAM/PSP** | **Muy frecuente.** Ejercicios de chat, servidores de archivos, conexiones a bases de datos.           | **Poco frecuente.** Se ve para aplicaciones específicas como descubrimiento de servicios o simulaciones. |
| **Código Típico**  | Usa `BufferedReader`/`PrintWriter` sobre los streams.                                                 | Usa `byte[]` buffers y manejo manual de direcciones en los `DatagramPacket`.                             |

### Frase de Cierre para el Examen:
> "Un socket es el punto final de una comunicación entre dos programas en red. **TCP**, orientado a conexión, usa `ServerSocket` (en servidor) y `Socket` (en cliente), creando un canal fiable y ordenado ideal para aplicaciones cliente-servidor. **UDP**, sin conexión, usa `DatagramSocket` y `DatagramPacket` para enviar datagramas independientes, priorizando velocidad donde la pérdida es aceptable, como en streaming o videojuegos."

---

**¡Listo!** Con esto tienes los conceptos de **OSI, TCP/IP, TCP, UDP, Puertos, IP, Sockets y su implementación en Java (PSP)** completamente organizados, explicados y listos para estudiar.



# Ejercicio: Cliente/Servidor TCP en Java (Sockets)

> **Contexto (Procesos y Servicios)**: en este paquete tienes dos clases: `Servidor` y `Cliente`. Juntas implementan una comunicación **Cliente → Servidor** (normalmente por **TCP**) usando **sockets**.

⚠️ **Nota importante**  
Ahora mismo **no puedo leer el contenido** de tus archivos `Cliente.java` y `Servidor.java` desde la ruta subida (`/mnt/data/...`) en este chat, así que **no puedo hacer una revisión literal línea por línea de TU código exacto**.  
Aun así, te dejo una **guía README lista** (enunciado + explicación típica línea por línea + mejoras/puntos extra) que encaja con el 95% de implementaciones `Cliente/Servidor` en Java.  
Si pegas aquí el código de ambas clases, te lo devuelvo **100% adaptado** a tus líneas reales (sin inventar nada).

---

## 🎯 Enunciado del ejercicio

Implementa una aplicación Cliente/Servidor en Java mediante **sockets TCP**:

- El **Servidor**:
   - Arranca escuchando en un **puerto** fijo (ej: `5000`).
   - Acepta la conexión de **un cliente** (versión básica).
   - Lee un mensaje enviado por el cliente.
   - Responde al cliente con un mensaje (por ejemplo, confirmación o “eco”).
   - Muestra por consola eventos relevantes: arranque, conexión, mensaje recibido, respuesta enviada y cierre.

- El **Cliente**:
   - Se conecta a `localhost` (o IP) y al puerto del servidor.
   - Envía un mensaje (por teclado o fijo).
   - Lee la respuesta del servidor.
   - Muestra por consola lo que envía y lo que recibe.
   - Cierra recursos correctamente.

📌 **Objetivo didáctico**
- Entender el modelo **bloqueante** de TCP: `accept()` espera cliente, `readLine()` espera datos.
- Practicar **Streams** (`InputStream/OutputStream`) y **cierre de recursos**.
- Distinguir responsabilidades: servidor escucha/atiende; cliente solicita/consume.

---

## 🧠 Flujo general (mapa rápido)

1. `Servidor` crea `ServerSocket(puerto)`
2. `Servidor` llama `accept()` → espera a un cliente
3. `Cliente` crea `Socket(host, puerto)` → conecta
4. Ambos crean streams (entrada/salida)
5. `Cliente` escribe → `Servidor` lee
6. `Servidor` responde → `Cliente` lee
7. Ambos cierran recursos

---

## ✅ Cómo ejecutar (modo típico)

1. Compila el proyecto (IDE o terminal).
2. **Ejecuta primero `Servidor`**
3. Luego ejecuta `Cliente`

**Ejemplo de salida esperada (orientativa):**
- Servidor:
   - “Servidor escuchando en puerto 5000…”
   - “Cliente conectado desde …”
   - “Recibido: Hola”
   - “Enviado: OK”
- Cliente:
   - “Conectado a servidor…”
   - “Enviado: Hola”
   - “Respuesta: OK”

---

## 🔍 Explicación línea por línea (estructura típica)

> Como no he podido leer tu archivo, aquí explico **lo habitual** que aparece en `Servidor` y `Cliente`.  
> Cuando pegues tus clases, lo ajusto a tus líneas exactas.

### 1) `Servidor.java` (típico)

#### `import ...`
- Importa clases como:
   - `java.net.ServerSocket`, `java.net.Socket` → red y sockets.
   - `java.io.*` → streams y lectores/escritores.

#### `public class Servidor { ... }`
- Define la clase ejecutable del lado servidor.

#### `public static void FizzBuzz(String[] args) { ... }`
- Punto de entrada.
- Aquí se monta el servidor y se gestiona **una sesión** (en la versión básica).

#### `int puerto = 5000;`
- Puerto donde el servidor escucha.
- **Debe coincidir** con el puerto al que se conecta el cliente.

#### `ServerSocket serverSocket = new ServerSocket(puerto);`
- Abre un “puerto de escucha” en la máquina.
- Si el puerto está ocupado, lanzará excepción.

#### `System.out.println("Escuchando...");`
- Mensaje informativo para saber que el servidor está listo.

#### `Socket socketCliente = serverSocket.accept();`
- **Línea clave**: bloquea la ejecución hasta que un cliente se conecte.
- Cuando entra un cliente, devuelve un `Socket` “ya conectado”.

#### Streams de entrada/salida (variante A: texto por líneas)
Ejemplo típico:
- `BufferedReader in = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));`
   - Permite leer texto del cliente con `readLine()` de forma cómoda.
- `PrintWriter out = new PrintWriter(socketCliente.getOutputStream(), true);`
   - Permite enviar texto al cliente con `println()`.
   - El `true` activa `autoFlush` (envía inmediatamente).

#### `String msg = in.readLine();`
- **Bloqueante**: espera hasta que el cliente envíe una línea terminada en `\n`.
- Si el cliente cierra, puede devolver `null`.

#### `out.println("OK: " + msg);`
- Respuesta del servidor al cliente (eco/confirmación).
- Importante: si no hay `flush` (o `autoFlush=true`), el cliente puede quedarse esperando.

#### Cierre de recursos
- `socketCliente.close();`
- `serverSocket.close();`
- Recomendable: **try-with-resources** para garantizar cierre aunque haya error.

✅ **Por qué así**
- TCP es una comunicación “por canal” (conexión), y el servidor gestiona conexiones con `accept()`.

---

### 2) `Cliente.java` (típico)

#### `String host = "localhost"; int puerto = 5000;`
- Dirección del servidor y puerto de destino.

#### `Socket socket = new Socket(host, puerto);`
- Abre conexión TCP con el servidor.
- Si el servidor no está levantado, fallará (Connection refused).

#### Streams (igual que en servidor)
- Entrada:
   - `BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));`
- Salida:
   - `PrintWriter out = new PrintWriter(socket.getOutputStream(), true);`

#### Mensaje a enviar
Variante común:
- Mensaje fijo: `String msg = "Hola";`
  o
- Por teclado:
   - `Scanner sc = new Scanner(System.in);`
   - `String msg = sc.nextLine();`

#### `out.println(msg);`
- Envía el texto al servidor (una línea).
- Con `autoFlush=true` sale inmediatamente.

#### `String respuesta = in.readLine();`
- Lee la respuesta del servidor.
- Bloquea hasta recibir una línea.

#### `System.out.println(respuesta);`
- Muestra respuesta al usuario.

#### `socket.close();`
- Cierra la conexión TCP.

✅ **Por qué así**
- El cliente inicia la conexión y “consume” el servicio del servidor.

---

## 🧩 Errores típicos (y cómo detectarlos)

- **No coincide el puerto** → no conecta.
- **Servidor no está arrancado** → `Connection refused`.
- **No hay flush** (o no usas `autoFlush=true`) → el receptor se queda esperando.
- **Usar `readLine()` sin enviar `\n`** → se queda bloqueado.
- **No cerrar recursos** → puertos ocupados y fugas.

---

## ⭐ Puntuación extra / ampliaciones (ideas para subir nota)

1. **Atender múltiples clientes**
   - Bucle `while(true)` en el servidor y por cada `accept()` crear un `Thread`/`Runnable`.
2. **Protocolo simple**
   - Comandos tipo: `TIME`, `ECHO hola`, `BYE`.
3. **Control de cierre limpio**
   - Si el cliente envía `BYE`, el servidor responde y cierra esa sesión.
4. **Manejo robusto de excepciones**
   - Mensajes claros y cierre garantizado.
5. **Try-with-resources**
   - Sustituir `close()` manual por:
      - `try (ServerSocket ss = ...; Socket s = ...; ...) { ... }`

---

## ✅ Checklist del alumno (autocorrección)

- [ ] Arranco servidor antes que cliente.
- [ ] Puerto igual en ambas clases.
- [ ] Puedo enviar un mensaje y recibir respuesta.
- [ ] No se queda bloqueado (flush / \n).
- [ ] Cierro socket y streams.
- [ ] Consola muestra eventos clave.

---

## 📌 Para el profe (si quieres evaluar rápido)

- Funciona conexión: 2 pts
- Envía/recibe mensaje: 3 pts
- Cierre de recursos: 2 pts
- Control de errores: 2 pts
- Extra (multicliente/protocolo): +1/+2

---

### Si quieres la versión PERFECTA (literal línea por línea)
Pega aquí el contenido de `Cliente.java` y `Servidor.java` y te genero:
- Enunciado ajustado a lo que hacen exactamente
- Explicación real **línea por línea** sin suposiciones
- Comentarios y “puntos extra” aplicables a TU implementación
