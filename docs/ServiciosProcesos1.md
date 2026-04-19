Aquí tienes las preguntas en formato de test convencional, sin el formato JSON y con sus respectivas respuestas:

**Pregunta 1:** ¿Qué es un hilo (thread)?
* A) Un programa independiente
* B) Una unidad de ejecución dentro de un proceso
* C) Un tipo de socket
* D) Un sistema operativo
* **Respuesta correcta: B.** Un hilo es la unidad básica de ejecución de un programa; comparte los recursos y el espacio de memoria del proceso al que pertenece.

---

**Pregunta 2:** ¿Qué método inicia un hilo en Java?
* A) run()
* B) execute()
* C) start()
* D) init()
* **Respuesta correcta: C.** El método start() notifica a la Máquina Virtual de Java (JVM) que debe crear el hilo y, a continuación, llamar al método run() de manera concurrente.

---

**Pregunta 3:** ¿Cuál es la diferencia principal entre proceso e hilo?
* A) No hay diferencia
* B) El proceso comparte memoria con otros procesos
* C) El hilo comparte memoria con otros hilos del mismo proceso
* D) El hilo es más pesado que el proceso
* **Respuesta correcta: C.** Los procesos son independientes y tienen su propia memoria, mientras que los hilos que pertenecen a un mismo proceso comparten el mismo espacio de memoria.

---

**Pregunta 4:** ¿Qué ocurre si llamas directamente a run() en lugar de start()?
* A) Se crea un nuevo hilo
* B) Se ejecuta en paralelo
* C) Se ejecuta como método normal en el mismo hilo
* D) Da error de compilación
* **Respuesta correcta: C.** Llamar a run() directamente no lanza una nueva línea de ejecución; el código simplemente se ejecuta de forma secuencial en el hilo que lo ha invocado.

---

**Pregunta 5:** ¿Qué método pausa un hilo temporalmente?
* A) wait()
* B) sleep()
* C) stop()
* D) pause()
* **Respuesta correcta: B.** El método sleep() detiene la ejecución del hilo actual durante un número específico de milisegundos sin liberar los bloqueos (locks) que posea.

---

Aquí tienes las preguntas extraídas del formato JSON y organizadas en un formato de test convencional:

**1. ¿Qué es un hilo (thread)?**
* A) Un programa independiente
* B) Una unidad de ejecución dentro de un proceso
* C) Un tipo de socket
* D) Un sistema operativo
* **Respuesta correcta: B.** Un hilo es la unidad básica de ejecución de un programa; comparte los recursos y el espacio de memoria del proceso al que pertenece.

---

**2. ¿Qué método inicia un hilo en Java?**
* A) run()
* B) execute()
* C) start()
* D) init()
* **Respuesta correcta: C.** El método `start()` notifica a la Máquina Virtual de Java (JVM) que debe crear el hilo y, a continuación, llamar al método `run()` de manera concurrente.

---

**3. ¿Cuál es la diferencia principal entre proceso e hilo?**
* A) No hay diferencia
* B) El proceso comparte memoria con otros procesos
* C) El hilo comparte memoria con otros hilos del mismo proceso
* D) El hilo es más pesado que el proceso
* **Respuesta correcta: C.** Los procesos son independientes y tienen su propia memoria, mientras que los hilos que pertenecen a un mismo proceso comparten el mismo espacio de memoria.

---

**4. ¿Qué ocurre si llamas directamente a run() en lugar de start()?**
* A) Se crea un nuevo hilo
* B) Se ejecuta en paralelo
* C) Se ejecuta como método normal
* D) Da error
* **Respuesta correcta: C.** Llamar a `run()` directamente no lanza una nueva línea de ejecución; el código simplemente se ejecuta de forma secuencial en el hilo que lo ha invocado.

---

**5. ¿Qué método pausa un hilo temporalmente?**
* A) wait()
* B) sleep()
* C) stop()
* D) pause()
* **Respuesta correcta: B.** El método `sleep()` detiene la ejecución del hilo actual durante un número específico de milisegundos sin liberar los bloqueos (locks) que posea.

---

**6. ¿Qué significa concurrencia?**
* A) Ejecutar procesos en orden
* B) Ejecutar múltiples tareas aparentemente al mismo tiempo
* C) Ejecutar solo un hilo
* D) Ejecutar en red
* **Respuesta correcta: B.** La concurrencia es la capacidad del sistema para gestionar varias tareas de manera que progresan simultáneamente, intercalando su ejecución en la CPU.

---

**7. ¿Qué hace synchronized en Java?**
* A) Ejecuta más rápido
* B) Evita concurrencia
* C) Controla acceso exclusivo a un recurso
* D) Detiene hilos
* **Respuesta correcta: C.** La palabra clave `synchronized` asegura que un bloque o método solo pueda ser ejecutado por un hilo a la vez, proporcionando exclusión mutua para proteger recursos compartidos.

---

**8. ¿Qué método se usa para esperar a otro hilo?**
* A) wait()
* B) join()
* C) sleep()
* D) notify()
* **Respuesta correcta: B.** El método `join()` obliga al hilo actual a pausar su ejecución y esperar hasta que el hilo sobre el que se invoca termine completamente.

---

**9. ¿Qué es un deadlock?**
* A) Un hilo terminado
* B) Un error de compilación
* C) Bloqueo mutuo entre hilos
* D) Un socket cerrado
* **Respuesta correcta: C.** Un interbloqueo o deadlock es una situación donde dos o más hilos se bloquean indefinidamente, ya que cada uno espera un recurso que el otro posee.

---

**10. ¿Qué interfaz permite crear hilos en Java?**
* A) Threadable
* B) Runnable
* C) Executable
* D) CallableThread
* **Respuesta correcta: B.** La interfaz `Runnable` se utiliza para definir el trabajo que ejecutará un hilo implementando el método abstracto `run()`.

---

**11. ¿Qué es un proceso?**
* A) Un hilo
* B) Un programa en ejecución
* C) Un socket
* D) Un puerto
* **Respuesta correcta: B.** Un proceso es la instancia de un programa informático que está siendo ejecutado por el sistema operativo, con su propio espacio de direcciones.

---

**12. ¿Qué clase permite ejecutar procesos externos en Java?**
* A) Thread
* B) ProcessBuilder
* C) RuntimeThread
* D) Executor
* **Respuesta correcta: B.** `ProcessBuilder` (junto con `Runtime`) se utiliza para crear procesos del sistema operativo subyacente y controlar su entorno de ejecución.
  Aquí tienes las preguntas extraídas de los objetos JSON y presentadas en un formato de test limpio y fácil de leer:

**1. ¿Qué método ejecuta un comando del sistema?**
* A) runCommand()
* B) exec()
* C) startProcess()
* D) execute()
* **Respuesta correcta: B.** El método `exec()` de la clase `Runtime` se encarga de ejecutar directamente comandos nativos del sistema operativo.

---

**2. ¿Qué devuelve un proceso hijo al terminar?**
* A) Puerto
* B) Código de salida
* C) IP
* D) Socket
* **Respuesta correcta: B.** Cuando finaliza, un proceso devuelve un código de salida (*exit value*) numérico al sistema operativo; habitualmente, 0 significa que se completó sin errores.

---

**3. ¿Qué característica define a TCP?**
* A) No confiable
* B) Orientado a conexión
* C) Más rápido siempre
* D) Sin conexión
* **Respuesta correcta: B.** TCP (*Transmission Control Protocol*) requiere que se establezca una conexión formal entre el cliente y el servidor antes de empezar a transmitir los datos.

---

**4. ¿Qué característica define a UDP?**
* A) Orientado a conexión
* B) Confiable
* C) No orientado a conexión
* D) Más seguro
* **Respuesta correcta: C.** UDP (*User Datagram Protocol*) envía paquetes independientemente sin establecer conexión previa, por lo que no garantiza que el receptor los reciba.

---

**5. ¿Qué clase se usa para crear un servidor TCP en Java?**
* A) Socket
* B) ServerSocket
* C) DatagramSocket
* D) TCPServer
* **Respuesta correcta: B.** La clase `ServerSocket` en Java se utiliza para abrir un puerto en el servidor y escuchar conexiones entrantes de clientes TCP.

---

**6. ¿Qué clase se usa para un cliente TCP en Java?**
* A) ServerSocket
* B) Socket
* C) DatagramPacket
* D) URL
* **Respuesta correcta: B.** La clase `Socket` se utiliza en el lado del cliente para conectarse y establecer comunicación con un servidor remoto a través de TCP.

---

**7. ¿Qué protocolo garantiza la entrega de datos?**
* A) UDP
* B) TCP
* C) IP
* D) HTTP
* **Respuesta correcta: B.** TCP emplea mecanismos de verificación de entrega y acuse de recibo, de modo que retransmite la información si se pierde por el camino.

---

**8. ¿Qué protocolo es más rápido pero menos fiable?**
* A) TCP
* B) UDP
* C) HTTP
* D) FTP
* **Respuesta correcta: B.** Al evitar la fase de establecimiento de conexión y la verificación de entrega, UDP es mucho más ligero y rápido, aunque puede haber pérdida de paquetes.

---

**9. ¿Qué clase se usa para gestionar conexiones UDP en Java?**
* A) Socket
* B) ServerSocket
* C) DatagramSocket
* D) TCPPacket
* **Respuesta correcta: C.** `DatagramSocket` es el *endpoint* utilizado en Java para enviar y recibir paquetes sueltos (datagramas) mediante el protocolo UDP.

---

**10. ¿Qué tipo de objeto se envía usando UDP en Java?**
* A) Streams
* B) DatagramPacket
* C) Files
* D) Threads
* **Respuesta correcta: B.** La información en UDP se empaqueta dentro de objetos `DatagramPacket`, que contienen los datos (en forma de array de bytes) y la dirección de destino.

Aquí tienes las preguntas extraídas del formato JSON y organizadas en un formato de test convencional:

**1. ¿Qué método despierta un hilo en espera?**
* A) wake()
* B) notify()
* C) resume()
* D) interrupt()
* **Respuesta correcta: B.** El método `notify()` hace que un hilo que había invocado `wait()` en un objeto determinado despierte y pase al estado preparado.

---

**2. ¿Cuál es la diferencia entre notify() y notifyAll()?**
* A) No hay diferencia
* B) notify despierta a todos
* C) notifyAll despierta a todos
* D) notify bloquea
* **Respuesta correcta: C.** `notify()` escoge y despierta a un único hilo de los que están en la cola de espera de un monitor, mientras que `notifyAll()` los despierta a todos.

---

**3. ¿Dónde deben usarse wait() y notify()?**
* A) En cualquier sitio
* B) Dentro de synchronized
* C) Solo en main
* D) En sockets
* **Respuesta correcta: B.** Es obligatorio invocar estos métodos de coordinación mientras el hilo posee el cerrojo (*lock*) del objeto, es decir, desde un contexto sincronizado.

---

**4. ¿Qué es una condición de carrera (race condition)?**
* A) Carrera de CPU
* B) Error de red
* C) Acceso concurrente incorrecto
* D) Bloqueo total
* **Respuesta correcta: C.** Ocurre cuando múltiples hilos leen y escriben en datos compartidos simultáneamente, y el resultado final depende del orden en el que se ejecuta el código.

---

**5. ¿Qué hace yield()?**
* A) Termina hilo
* B) Cede CPU
* C) Bloquea
* D) Duplica hilo
* **Respuesta correcta: B.** El método `yield()` sugiere al planificador del sistema operativo que el hilo actual está dispuesto a ceder su tiempo de procesador a otros hilos.

---

**6. ¿Qué indica isAlive()?**
* A) Si el hilo está en espera
* B) Si el hilo sigue ejecutándose
* C) Si está bloqueado
* D) Si terminó correctamente
* **Respuesta correcta: B.** `isAlive()` evalúa si un hilo ha comenzado su ejecución y aún no ha finalizado, devolviendo un valor booleano.

---

**7. ¿Qué es un puerto en redes?**
* A) Dirección IP
* B) Canal lógico de comunicación
* C) Protocolo
* D) Socket físico
* **Respuesta correcta: B.** Un puerto es un *endpoint* lógico (un número) que el sistema operativo utiliza para dirigir el tráfico de red al proceso o servicio correcto.

---

**8. ¿Qué es un socket?**
* A) Un hilo
* B) Un puerto
* C) Punto de comunicación entre dos máquinas
* D) Un proceso
* **Respuesta correcta: C.** Un socket representa un punto final lógico que permite la comunicación bidireccional entre dos aplicaciones que operan en la red.