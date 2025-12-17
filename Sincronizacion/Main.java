public class Main {

    public static void main(String[] args) {

/*
    HILOS EN JAVA: TIPOS PRINCIPALES Y CUÁNDO USARLOS
    =================================================

    1. Thread (clase)
    -----------------
    Qué es:
      - Es una clase de Java que representa directamente un hilo de ejecución.
      - Cada objeto Thread es un hilo que se puede arrancar con el método start().

    Cómo se usa:
      - Se crea una subclase que extiende Thread y se sobreescribe el método run().
      - Luego se crea un objeto de esa subclase y se llama a start() para que el código
        de run() se ejecute en paralelo.

    Ventajas:
      - Sencillo de entender en ejemplos pequeños.
      - Todo el código del hilo está concentrado en la clase que extiende Thread.

    Desventajas:
      - Menos flexible: si una clase ya extiende de otra, no puede extender también de Thread
        (Java solo permite herencia simple).
      - Mezcla la lógica de negocio (lo que hace el hilo) con la gestión del hilo en sí.

    Cuándo usarlo:
      - Ejemplos sencillos, ejercicios de aprendizaje y casos muy pequeños.
      - En aplicaciones reales, no suele ser la opción recomendada.


    2. Runnable (interfaz) <-
    ----------------------
    Qué es:
      - Es una interfaz funcional que representa una tarea que se puede ejecutar en un hilo.
      - Define el método run(), que contiene el código que se quiere ejecutar en paralelo.

    Cómo se usa:
      - Se crea una clase que implementa Runnable o una lambda que implementa run().
      - Se pasa ese Runnable al constructor de Thread: new Thread(miRunnable).start().

    Ventajas:
      - Separa la tarea (lógica que se ejecuta) del hilo que la ejecuta.
      - La clase que implementa Runnable puede seguir heredando de otra clase.
      - Permite reutilizar la misma tarea en distintos hilos o en un ExecutorService.

    Desventajas:
      - No devuelve resultado ni permite lanzar excepciones checked directamente
        (para eso está Callable).

    Cuándo usarlo:
      - Es la opción recomendada en Java básico para crear tareas concurrentes.
      - Ideal cuando solo necesitas ejecutar código en paralelo sin devolver un resultado.


    3. Callable (interfaz, paquete java.util.concurrent)
    ----------------------------------------------------
    Qué es:
      - Es una interfaz similar a Runnable, pero con dos diferencias importantes:
          * El método call() puede devolver un valor.
          * call() puede lanzar excepciones checked.
      - Se usa cuando la tarea concurrente tiene que producir un resultado o
        puede fallar de forma controlada.

    Cómo se usa:
      - Normalmente no se ejecuta con new Thread(...), sino con un ExecutorService.
      - Cuando se envía un Callable al ExecutorService, éste devuelve un Future,
        que permite obtener el resultado más adelante (future.get()).

    Ventajas:
      - Permite recuperar resultados de las tareas en paralelo.
      - Permite gestionar excepciones checked dentro de la tarea.

    Desventajas:
      - Requiere usar ExecutorService y Future, lo que añade algo más de complejidad.

    Cuándo usarlo:<----------------------------------------------------------
      - Cuando necesitas que una tarea concurrente devuelva datos.
      - Cuando quieres ejecutar operaciones costosas en segundo plano y luego
        recoger el resultado.


    4. Executor y ExecutorService (framework de ejecución de tareas)
    ----------------------------------------------------------------
    Qué son:
      - Son interfaces y clases del paquete java.util.concurrent que gestionan
        un "pool" de hilos (conjunto de hilos reutilizables).
      - En vez de crear y destruir hilos manualmente, se envían tareas (Runnable
        o Callable) a un ExecutorService, que se encarga de ejecutarlas.

    Cómo se usan:
      - Se crea un ExecutorService, por ejemplo con Executors.newFixedThreadPool(n).
      - Se envían tareas con submit() o execute():
          * submit() devuelve un Future (para Runnable o Callable).
          * execute() simplemente lanza la tarea sin Future.
      - Cuando ya no se van a enviar más tareas, se llama a shutdown() para cerrar el pool.

    Ventajas:
      - Gestiona el número de hilos de forma eficiente (reutiliza hilos).
      - Facilita el db del ciclo de vida de los hilos (inicio, finalización, cierre).
      - Es el enfoque más usado en aplicaciones reales con mucha concurrencia.

    Desventajas:
      - Es algo más complejo que crear hilos "a mano".
      - Requiere aprender el modelo de tareas, pools y Futures.

    Cuándo usarlo:<-------------------------------------------------------------------------------------------
      - En aplicaciones medianas o grandes.
      - Cuando hay muchas tareas cortas o repetitivas.
      - Cuando quieres un db fino del rendimiento y de los recursos.


    RESUMEN DE CUÁNDO USAR CADA UNO
    -------------------------------
    - Ejercicios y ejemplos muy simples:
        * Runnable + new Thread(...).start()
        * Heredar de Thread solo con fines didácticos.

    - Programas reales / muchas tareas concurrentes:
        * Runnable o Callable + ExecutorService.
        * Extender Thread casi nunca es necesario.

    - Tareas que NO necesitan devolver resultado:
        * Runnable.

    - Tareas que SÍ necesitan devolver resultado o lanzar excepciones checked:
        * Callable + ExecutorService + Future.


    PROBLEMAS TÍPICOS EN CONCURRENCIA
    =================================

    1. Condición de carrera (Race Condition)
    ----------------------------------------
    Definición:
      - Se produce cuando dos o más hilos acceden y modifican a la vez
        un mismo recurso compartido, y el resultado final depende del
        orden de ejecución “al azar” de esos hilos.

    Ocurre cuando:
      - Varios hilos comparten una misma variable u objeto mutable.
      - Al menos uno de ellos escribe en ese recurso.
      - No hay sincronización (synchronized, locks, clases atómicas, etc.).

    Consecuencias:
      - El programa a veces da un resultado y otras veces otro, sin cambiar el código.
      - Los errores son intermitentes y difíciles de reproducir.

    Cómo evitarlo (idea general):
      - Proteger las secciones críticas con synchronized, locks, variables atómicas
        u otras técnicas de sincronización.
      - Reducir al mínimo el estado compartido mutable.


    2. Inconsistencia de datos
    --------------------------
      - Cuando varios hilos escriben sobre la misma variable, unas escrituras
        pueden pisar a otras.
      - Se pierden actualizaciones: una escritura “borra” cambios previos
        realizados por otro hilo.
      - El estado final no es coherente, es decir, no refleja realmente todas
        las operaciones que se han realizado.
      - Este problema suele ser consecuencia directa de una condición de carrera.


    3. Lectura / Escritura intercalada
    ----------------------------------
      - Los hilos van intercalando sus instrucciones entre sí.
      - Ejemplo clásico:
          * Hilo A imprime de 'a' a 'z'.
          * Hilo B imprime de 1 a 30.
        En vez de ver primero todas las letras y luego todos los números,
        la salida aparece mezclada (intercalamiento).
      - No siempre es un error, pero hay que tenerlo en cuenta: en concurrencia
        no se puede suponer un orden fijo de ejecución si no se controla con
        sincronización o mecanismos de coordinación.


    4. Problemas de rendimiento
    ---------------------------
      - Crear demasiados hilos puede provocar más coste que beneficio:
          * Cambios de contexto constantes entre hilos.
          * Más consumo de memoria.
          * Peor rendimiento global si el número de hilos es muy superior
            al número de núcleos disponibles.
      - Si muchos hilos acceden al mismo recurso (por ejemplo, el mismo fichero
        o la misma conexión), se produce un cuello de botella:
          * Todos compiten por el mismo recurso.
          * Se generan esperas largas y baja eficiencia.
          * Si no se sincroniza correctamente, se añaden además problemas
            de consistencia de datos.

      Idea importante:
          - “Más hilos” no significa “más rápido” automáticamente.
          - Hay que ajustar el número de hilos y el diseño de la concurrencia
            al hardware y al tipo de tarea (CPU, disco, red, etc.).


    5. Deadlock (Interbloqueo)
    --------------------------
      - Situación en la que dos o más hilos se quedan bloqueados para siempre,
        esperando recursos que nunca se liberan.

      Ejemplo simplificado:
          * Hilo A tiene el recurso 1 y quiere el recurso 2.
          * Hilo B tiene el recurso 2 y quiere el recurso 1.
        Ninguno puede avanzar porque cada uno espera al otro:
        los hilos quedan "congelados".

      Cuándo suelen aparecer:
          - Cuando se usan varios locks o monitores al mismo tiempo.
          - Cuando no se sigue un orden fijo para adquirir y liberar los recursos.
          - Cuando se combinan sincronizaciones diferentes sin un diseño claro.

      Cómo reducir el riesgo:
          - Establecer un orden global para adquirir los locks.
          - Evitar secciones críticas demasiado largas.
          - Diseñar la sincronización de forma simple y predecible.
*/
        /*
        1. ZONA CRÍTICA (sección crítica)
    ---------------------------------
    Definición:
      - Parte del código donde uno o varios hilos acceden a un recurso compartido
        (variable, objeto, colección, fichero, BD, etc.) que se puede modificar.
      - Si dos hilos ejecutan esa parte a la vez, pueden aparecer:
          * condiciones de carrera,
          * inconsistencia de datos,
          * resultados aleatorios.

    synchronized
    ---------------
    Qué es:
      - Palabra clave de Java para sincronizar el acceso a un recurso compartido.
      - Usa un "monitor" asociado a un objeto (lock implícito).
      - Garantiza que solo UN hilo a la vez puede entrar en la sección marcada.
      - La zona crítica es "el trozo de código que NO puede ejecutarse
        simultáneamente por varios hilos sobre el mismo recurso compartido".
      - La sincronización (synchronized, Lock, etc.) se aplica precisamente
        para proteger esta zona crítica.
        zona critica

        /*
    synchronized en métodos, synchronized(this) y synchronized static
    ================================================================

    1. synchronized en MÉTODO DE INSTANCIA
    --------------------------------------
    Forma típica:
        synchronized void miMetodo() { ... }

    ¿Qué significa?
      - El MÉTODO entero es zona crítica.
      - El candado (monitor) que se usa es el propio objeto: this.
      - Solo UN hilo a la vez puede ejecutar ese método sobre EL MISMO objeto.

    Importante:
      - Si dos hilos llaman al mismo método synchronized sobre el MISMO objeto,
        uno entra y el otro espera.
      - Si llaman al mismo método pero sobre DISTINTOS objetos, cada objeto
        tiene su propio candado, así que se pueden ejecutar en paralelo.

    Equivalencia:
      - A nivel de comportamiento es igual que:
            void miMetodo() {
                synchronized (this) {
                    // cuerpo del método
                }
            }


    2. synchronized(this) en un BLOQUE
    ----------------------------------
    Forma típica:
        void miMetodo() {
            // código NO crítico

            synchronized (this) {
                // zona crítica: acceso a recurso compartido
            }

            // más código NO crítico
        }

    ¿Qué aporta respecto al método synchronized?
      - Mismo candado (this), PERO:
          * Solo sincronizas una parte del método, no todo.
          * El código fuera del bloque synchronized no queda bloqueado.
      - Esto da un db más fino (mejor rendimiento si la zona crítica
        es pequeña y el método hace más cosas).

    Idea:
      - synchronized void miMetodo()       → todo el método bloqueado en this.
      - synchronized(this) { ... }         → solo un fragmento del método bloqueado en this.


    3. synchronized static (métodos estáticos sincronizados)
    --------------------------------------------------------
    Forma típica:
        synchronized static void miMetodoEstatico() { ... }

    ¿Qué significa?
      - El método es estático (no va ligado a una instancia concreta, sino a la clase).
      - El candado (monitor) YA NO ES this (no hay this), sino la clase:
            NombreDeLaClase.class
      - Solo UN hilo a la vez puede ejecutar ese método estático sincronizado
        para TODA la clase, independientemente de la instancia.

    Equivalencia:
      - Es equivalente a:
            static void miMetodoEstatico() {
                synchronized (NombreDeLaClase.class) {
                    // cuerpo del método
                }
            }

    Diferencias clave:
      - synchronized en método de instancia:
          * Bloquea POR OBJETO (this).
          * Dos hilos pueden ejecutar el mismo método synchronized en dos objetos
            diferentes al mismo tiempo (cada uno tiene su propio candado).

      - synchronized static:
          * Bloquea POR CLASE (NombreDeLaClase.class).
          * Afecta a TODAS las instancias de esa clase: solo un hilo a la vez
            puede entrar en cualquier método estático synchronized de esa clase.


    4. ¿Cuándo usar cada uno?
    -------------------------
    - synchronized en método de instancia:
        * Cuando quieres proteger datos que pertenecen a una instancia concreta.
        * Cada objeto tiene su propio estado y su propio candado.
        * Ejemplo típico: un contador dentro de un objeto, una lista interna, etc.

    - synchronized(this) en bloque:
        * Cuando solo una parte del método es realmente zona crítica.
        * Cuando quieres reducir el tiempo que el candado está ocupado
          (mejor rendimiento).
        * Mismo efecto de exclusión que un método synchronized, pero más fino.

    - synchronized static (o synchronized(NombreDeLaClase.class)):
        * Cuando proteges datos compartidos por TODA la clase (variables estáticas).
        * Cuando quieres que la exclusión sea global a todas las instancias.
        * Ejemplo típico: contador estático global, caché estática, recursos
          compartidos a nivel de clase.

    Resumen mental rápido:
      - synchronized void m()      → bloqueo por objeto (this), todo el método.
      - synchronized(this) { ... } → bloqueo por objeto (this), solo un bloque.
      - synchronized static m()    → bloqueo por clase (NombreDeLaClase.class),
                                     afecta a todas las instancias.


         */

    }

}
