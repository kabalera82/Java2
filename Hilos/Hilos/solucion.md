# Protocolo de Resolución de Ejercicios de Programación
**Objetivo:** Eliminar la "ilusión de competencia". Prohibido escribir código a lo loco. Cada paso es obligatorio hasta dominar la lógica.

---

## FASE 1: Análisis y Despiece (Sin tocar el teclado)
*El objetivo aquí es entender el "Qué" antes del "Cómo".*

- [ ] **1. Lectura Activa:** Leer el enunciado completo dos veces. La primera para tener contexto, la segunda para buscar pistas técnicas.
- [ ] **2. Identificar la tríada (E-P-S):** Escribir en lenguaje natural (español):
    - **Entradas:** ¿Qué datos necesito recibir o crear al principio? (ej. *un array de números, una variable edad*).
    - **Proceso:** ¿Qué manipulación hay que hacer? (ej. *recorrer el array y sumar solo los pares*).
    - **Salidas:** ¿Qué debe devolver o imprimir el programa al final? (ej. *imprimir el total de la suma*).

---

## FASE 2: La Traza de Escritorio (Papel y Boli)
*Si no funciona en papel, no va a funcionar en la máquina. Esta es la fase más importante para crear lógica neuronal.*

- [ ] **1. Escribir el Pseudocódigo o Lógica Base:** Hacer un boceto escrito a mano de los bucles y condicionales.
- [ ] **2. Dibujar la Tabla de Variables:**
    - Crear una columna por cada variable que exista en el problema (incluyendo iteradores como `i` o `j`).
    - Crear una columna final llamada `Consola / Salida`.
- [ ] **3. Ejecución Mental (Paso a Paso):**
    - Leer el código línea a línea.
    - Al cambiar el valor de una variable, **tachar** el valor anterior en la tabla y escribir el nuevo debajo. (No usar goma de borrar, el historial visual es clave).
    - Leer las condiciones (`if`, `while`) en voz alta: *"¿Es la variable 'x' (que vale 3) mayor que 5? Falso. Salto el bloque"*.
- [ ] **4. Predicción:** Apuntar cuál debería ser el resultado final exacto.



---

## FASE 3: Traducción a Código (El Teclado)
*Solo se llega aquí cuando la tabla de papel tiene un resultado lógico.*

- [ ] **1. Transcribir:** Pasar la lógica del papel a sintaxis real (Java o JS).
- [ ] **2. Cuidar la Sintaxis Básica:**
    - **Java:** Tipos de datos estrictos (`int`, `String`), llaves `{}` y puntos y comas `;`.
    - **JS:** Declaraciones correctas (`let`, `const`), cuidado con la coerción de tipos (`==` vs `===`).

---

## FASE 4: Depuración Activa (El Debugger)
*El botón de "Play" (Run) normal está prohibido si el código falla. Se usa siempre el modo Debug.*

- [ ] **1. Poner Breakpoints (Puntos de Interrupción):** Colocar el punto rojo 1 o 2 líneas ANTES de la parte del código que hace el trabajo duro (antes del `for`, o al inicio de la función).
    - *En JS (Navegador):* Puedes escribir la palabra `debugger;` directamente en el código.
- [ ] **2. Lanzar en Modo Debug:** Iniciar el "Bicho" en el IDE (IntelliJ/Eclipse) o abrir las DevTools en el navegador (F12 > Sources).
- [ ] **3. Navegación Quirúrgica:**
    - Usar **Step Over** (Pasar por encima) para avanzar línea a línea.
    - Usar **Step Into** (Entrar) si hay llamadas a métodos/funciones y necesito ver qué pasa dentro.
- [ ] **4. Comparar Realidad vs Papel:**
    - Mantener abierta la pestaña de **Watch / Variables** en el IDE/Navegador.
    - Comparar los valores que muestra el ordenador con los que tachaste en tu tabla de papel.
    - **Identificar la fisura:** En el momento exacto en el que el ordenador muestra un valor diferente al de tu papel, **ahí** está tu fallo de lógica.



---

## FASE 5: Reflexión Final
- [ ] **1. ¿Por qué falló?** Explicar en voz alta o escribir un comentario en el código del estilo: *"// Fallaba porque el bucle for llegaba a <= length y se salía del array (OutOfBounds)"*.
- [ ] **2. Limpieza:** Quitar código muerto, variables sin usar o comentarios inútiles antes de dar el ejercicio por terminado.