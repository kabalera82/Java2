# 📄 Guía de Referencia: Leer y Escribir CSV en Java


## 🧠 ¿Qué es un CSV?

Un CSV es simplemente un archivo de texto donde los datos están separados por comas. Cada línea es una fila, y la primera línea suele ser la cabecera (nombres de columnas).

```
id,nombre,email
1,Ana,ana@correo.com
2,Luis,luis@correo.com
```

---

## 🗂️ Estructura de clases (cómo se organiza el código)

Cuando trabajas con CSV en Java, lo normal es dividirlo en 3 o 4 clases con responsabilidades distintas:

| Clase | ¿Qué hace? |
|---|---|
| `CsvTable` | Guarda los datos en memoria (cabeceras + filas) |
| `CsvUtils` | Parsea (trocea) una línea CSV en campos |
| `CsvPrinter` | Muestra la tabla por pantalla |
| `LectorCsv` / `Main` | Coordina todo: lee el archivo y llama a las demás |

---

## 1️⃣ CsvTable — El "contenedor" de datos

Esta clase es como una tabla de Excel en memoria. Tiene dos cosas:
- Una lista con los nombres de las columnas (`headers`)
- Una lista de filas, donde cada fila es un `Map<NombreColumna, Valor>`

```java
// Estructura interna
List<String> headers = new ArrayList<>();
//  → ["id", "nombre", "email"]

List<Map<String, String>> rows = new ArrayList<>();
//  → [{ "id"="1", "nombre"="Ana", "email"="ana@..." }, ...]
```

### Métodos clave

```java
// Establece las cabeceras (se llama una sola vez, con la primera línea del CSV)
table.setHeaders(List<String> hdrs);

// Añade una fila de datos (se llama por cada línea de datos del CSV)
table.addRow(List<String> valores);

// Devuelve las cabeceras (solo lectura, no se pueden modificar desde fuera)
table.getHeaders();

// Devuelve todas las filas (solo lectura)
table.getRows();

// Cuántas filas hay
table.size();
```

### 💡 Detalle importante: `Collections.unmodifiableList()`

Los getters devuelven una "vista de solo lectura" de la lista interna. Esto protege los datos internos de la clase. Si alguien intenta hacer `.add()` sobre lo que devuelve el getter, saltará una excepción.

```java
public List<String> getHeaders() {
    return Collections.unmodifiableList(headers); // nadie puede modificar esto desde fuera
}
```

### 💡 Detalle importante: `LinkedHashMap` en addRow

Se usa `LinkedHashMap` (en lugar de `HashMap`) porque mantiene el orden en que se insertan las claves. Así, las columnas siempre aparecen en el mismo orden que en el CSV original.

```java
public void addRow(List<String> valores) {
    Map<String, String> row = new LinkedHashMap<>(); // mantiene el orden de inserción
    for (int i = 0; i < headers.size(); i++) {
        String key = headers.get(i);
        String value = (i < valores.size()) ? valores.get(i) : ""; // si faltan valores, ponemos ""
        row.put(key, value);
    }
    rows.add(row);
}
```

---

## 2️⃣ CsvUtils — El "troceador" de líneas

Convierte una línea de texto CSV en una lista de campos. Parece fácil pero tiene truco: los valores pueden ir entre comillas y pueden contener comas dentro.

```
"Ana","Petardos, SL","2300"
```

Aquí hay 3 campos: `Ana`, `Petardos, SL` y `2300`. La coma dentro de las comillas NO es separadora.

### El método parseCSVLine

Recorre carácter a carácter y decide qué hacer con cada uno:

```java
public static List<String> parseCSVLine(String line) {
    List<String> out = new ArrayList<>();
    StringBuilder sb = new StringBuilder(); // acumulador del campo actual
    boolean enComillas = false;             // ¿estamos dentro de un campo entrecomillado?

    for (int i = 0; i < line.length(); i++) {
        char ch = line.charAt(i);

        if (ch == '"') {
            // ¿Es una comilla escapada? (doble comilla dentro de un campo)
            // Ejemplo: "dijo ""hola""" → dijo "hola"
            if (enComillas && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                sb.append('"'); // añadimos una sola comilla
                i++;            // saltamos la segunda comilla
            } else {
                enComillas = !enComillas; // entramos o salimos de comillas
            }
        } else if (ch == ',' && !enComillas) {
            out.add(sb.toString()); // fin del campo → lo guardamos
            sb.setLength(0);        // vaciamos el acumulador
        } else {
            sb.append(ch); // carácter normal → lo añadimos al campo
        }
    }

    out.add(sb.toString()); // último campo (no termina en coma)
    return out;
}
```

### Casos que maneja

| Entrada | Resultado |
|---|---|
| `Ana,Luis,Pepe` | `["Ana", "Luis", "Pepe"]` |
| `"Ana","Luis"` | `["Ana", "Luis"]` |
| `"Petardos, SL"` | `["Petardos, SL"]` (la coma no separa) |
| `"dijo ""hola"""` | `["dijo \"hola\""]` (comilla escapada) |

---

## 3️⃣ CsvPrinter — El que imprime la tabla

Muestra la tabla por consola con las columnas alineadas usando espacios. No tiene estado (todo son métodos estáticos), por eso el constructor es privado: no tiene sentido crear un objeto de esta clase.

### Lógica de impresión

1. Calcula el ancho máximo de cada columna (la cabecera o el valor más largo)
2. Imprime la cabecera rellenando con espacios
3. Imprime cada fila rellenando con espacios

```java
public static void print(CsvTable table) {
    List<String> headers = table.getHeaders();
    List<Map<String, String>> rows = table.getRows();

    // 1. Calcular anchos
    int[] widths = new int[headers.size()];
    for (int i = 0; i < headers.size(); i++) {
        widths[i] = headers.get(i).length(); // ancho inicial = longitud de la cabecera
    }
    for (Map<String, String> row : rows) {
        for (int i = 0; i < headers.size(); i++) {
            String val = row.get(headers.get(i));
            if (val != null) {
                widths[i] = Math.max(widths[i], val.length()); // ¿este valor es más largo?
            }
        }
    }

    // 2. Imprimir cabeceras
    for (int i = 0; i < headers.size(); i++) {
        System.out.print(rellenarConEspacios(headers.get(i), widths[i] + 2));
    }
    System.out.println();

    // 3. Imprimir filas
    for (Map<String, String> row : rows) {
        for (int i = 0; i < headers.size(); i++) {
            String val = row.get(headers.get(i));
            System.out.print(rellenarConEspacios(val, widths[i] + 2));
        }
        System.out.println();
    }
}

// Rellena un texto con espacios hasta alcanzar el ancho indicado
private static String rellenarConEspacios(String texto, int ancho) {
    if (texto.length() >= ancho) return texto;
    return texto + " ".repeat(ancho - texto.length());
}
```

---

## 4️⃣ LectorCsv (Main) — El coordinador

Lee el archivo CSV desde disco y lo mete en un `CsvTable`. Aquí es donde se juntan todas las piezas.

### Flujo completo

```
archivo.csv → BufferedReader → líneas → CsvUtils.parseCSVLine() → CsvTable → CsvPrinter
```

### Código comentado

```java
// Define la ruta al archivo CSV
Path RUTA = Path.of("data/clientes.csv");

// Crea la tabla vacía en memoria
CsvTable table = new CsvTable();

// Abre el archivo con codificación UTF-8
try (BufferedReader br = Files.newBufferedReader(RUTA, StandardCharsets.UTF_8)) {

    String linea;

    // Lee la primera línea → son las cabeceras
    linea = br.readLine();
    if (linea == null) {
        System.err.println("El CSV está vacío");
        return;
    }

    // Parsea la línea de cabeceras y la mete en la tabla
    table.setHeaders(CsvUtils.parseCSVLine(linea));

    // Lee el resto de líneas → son las filas de datos
    while ((linea = br.readLine()) != null) {
        table.addRow(CsvUtils.parseCSVLine(linea));
    }

} catch (NoSuchFileException e) {
    System.err.println("Archivo no encontrado: " + RUTA.toAbsolutePath());
} catch (IOException e) {
    e.printStackTrace();
}

// Imprime la tabla por consola
CsvPrinter.print(table);
```

---

## ✍️ Cómo GUARDAR/ESCRIBIR un CSV

Para guardar objetos en CSV necesitas dos operaciones:

### Objeto → línea CSV (`toCsvLine`)

```java
// Ejemplo: una transacción con id, tipo, importe, fecha
private String toCsvLine(Transaccion t) {
    return String.join(",",
        t.getId(),
        t.getTipo(),
        String.valueOf(t.getImporte()), // double → String con punto decimal
        t.getFecha().toString()          // LocalDate → "2025-03-15"
    );
}
```

### Línea CSV → Objeto (`fromCsvLine`)

```java
private Transaccion fromCsvLine(String linea) {
    String[] campos = linea.split(",");
    String id      = campos[0];
    String tipo    = campos[1];
    double importe = Double.parseDouble(campos[2]);
    LocalDate fecha = LocalDate.parse(campos[3]); // "2025-03-15" → LocalDate
    return new Transaccion(id, tipo, importe, fecha);
}
```

### Escribir el CSV completo en disco

La estrategia más simple: reescribir el archivo entero cada vez.

```java
private void guardarTodo(List<Transaccion> lista, Path ruta) throws IOException {
    try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
        // Escribir cabecera
        bw.write("id,tipo,importe,fecha");
        bw.newLine();

        // Escribir cada objeto como una línea
        for (Transaccion t : lista) {
            bw.write(toCsvLine(t));
            bw.newLine();
        }
    }
}
```

---

## 🛡️ Manejo de errores al trabajar con archivos

| Excepción | Cuándo ocurre | Qué hacer |
|---|---|---|
| `NoSuchFileException` | El archivo no existe | Avisar al usuario, empezar con lista vacía |
| `IOException` | Error genérico de lectura/escritura | Mostrar el error con `e.printStackTrace()` |

### Comprobar si el archivo existe antes de leer

```java
if (Files.exists(ruta)) {
    // leer el archivo
} else {
    // empezar con lista vacía, no hay nada que leer
}
```

---

## 🔁 Patrón completo de repositorio CSV

Este es el patrón estándar para un repositorio que persiste datos en CSV:

```java
public class CsvTransaccionRepo {

    private final Path ruta;
    private final List<Transaccion> lista = new ArrayList<>();

    public CsvTransaccionRepo(Path ruta) throws IOException {
        this.ruta = ruta;
        if (Files.exists(ruta)) {
            cargarDesdeArchivo(); // al arrancar, cargamos todo en memoria
        }
    }

    // LEER desde disco → memoria (solo al arrancar)
    private void cargarDesdeArchivo() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
            br.readLine(); // saltar cabecera
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(fromCsvLine(linea));
            }
        }
    }

    // GUARDAR: añadir a memoria + reescribir el CSV
    public void guardar(Transaccion t) throws IOException {
        lista.add(t);
        guardarTodo();
    }

    // ELIMINAR: quitar de memoria + reescribir el CSV
    public void eliminar(String id) throws IOException {
        lista.removeIf(t -> t.getId().equals(id));
        guardarTodo();
    }

    // LISTAR: devolver desde memoria (sin releer el archivo)
    public List<Transaccion> listarTodas() {
        return Collections.unmodifiableList(lista);
    }

    // ESCRIBIR el archivo entero
    private void guardarTodo() throws IOException {
        Files.createDirectories(ruta.getParent()); // crea la carpeta si no existe
        try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
            bw.write("id,tipo,importe,fecha");
            bw.newLine();
            for (Transaccion t : lista) {
                bw.write(toCsvLine(t));
                bw.newLine();
            }
        }
    }
}
```

---

## 📌 Resumen rápido: herramientas de Java para CSV

| Tarea | Herramienta Java |
|---|---|
| Abrir archivo para **leer** | `Files.newBufferedReader(path, UTF_8)` |
| Leer línea a línea | `BufferedReader.readLine()` |
| Abrir archivo para **escribir** | `Files.newBufferedWriter(path, UTF_8)` |
| Escribir una línea | `bw.write(texto)` + `bw.newLine()` |
| Gestionar rutas | `Path.of("carpeta/archivo.csv")` |
| Comprobar si existe | `Files.exists(path)` |
| Crear carpetas si no existen | `Files.createDirectories(path)` |
| Codificación siempre UTF-8 | `StandardCharsets.UTF_8` |
| Convertir fecha a String | `LocalDate.toString()` → `"2025-03-15"` |
| Parsear String a fecha | `LocalDate.parse("2025-03-15")` |

---

> 💡 **Regla de oro**: el `try-with-resources` (`try (BufferedReader br = ...)`) cierra el archivo automáticamente al salir del bloque, aunque haya un error. Úsalo siempre.
