# Acceso a Datos — Ficheros, CSV y JSON

> Tema 06 de 2ºDAM | Prerequisito: Java basico (colecciones, excepciones)

El acceso a datos en Java cubre la lectura y escritura de informacion en diferentes formatos: texto plano, CSV y JSON. Se usa el paquete `java.nio.file` (moderno) junto con `BufferedReader`/`BufferedWriter` para un manejo eficiente de I/O.

---

## Teoria rapida

### Clases principales de I/O en Java

| Clase | Uso |
|-------|-----|
| `Files.newBufferedReader(path, charset)` | Leer fichero de texto linea a linea |
| `Files.newBufferedWriter(path, charset)` | Escribir fichero de texto |
| `Paths.get("ruta/fichero.txt")` | Construir una ruta de forma portable |
| `Path.of("ruta", "fichero.txt")` | Alternativa moderna (Java 11+) |
| `Files.readAllLines(path)` | Leer todas las lineas de golpe (ficheros pequenos) |
| `Files.write(path, lineas)` | Escribir lista de lineas de golpe |

### Patron basico de lectura

```java
Path ruta = Paths.get("data/clientes.txt");

try (BufferedReader br = Files.newBufferedReader(ruta, StandardCharsets.UTF_8)) {
    String linea;
    while ((linea = br.readLine()) != null) {
        // procesar linea
    }
} catch (IOException e) {
    e.printStackTrace();
}
// El try-with-resources cierra el reader automaticamente
```

### Patron basico de escritura

```java
try (BufferedWriter bw = Files.newBufferedWriter(ruta, StandardCharsets.UTF_8)) {
    bw.write("campo1;campo2;campo3");
    bw.newLine();
} catch (IOException e) {
    e.printStackTrace();
}
```

### CSV — valores separados por comas (o punto y coma)

```java
// Leer una linea CSV con 4 campos
String[] partes = linea.split(";", -1);  // -1 para no ignorar campos vacios al final
if (partes.length != 4) continue;        // validar formato antes de parsear

int id     = Integer.parseInt(partes[0]);
String nombre = partes[1];
double saldo  = Double.parseDouble(partes[2]);
```

---

## Estructura

```
AccesoDatos/
├── data/                        <- Ficheros de datos de prueba
│   ├── clientes.txt             <- Texto plano con campos separados por ";"
│   ├── clientes.csv             <- CSV de clientes
│   ├── datos.csv                <- CSV generico
│   ├── biblioteca.json          <- JSON de ejemplo
│   ├── productos.txt            <- Datos para ejercicio02Bonana
│   ├── miArchivo.txt            <- Fichero de prueba generico
│   └── resultado.txt            <- Salida de algun ejercicio
│
├── docs/
│   └── Conexion_de_Java_con_Bases_de_Datos_mediante_JDBC.pdf
│
├── ejercicio01/                 <- Texto plano con patron DAO basico
│   ├── Cliente.java             <- Modelo: id, nombre, email, saldo
│   └── TextoPlano.java          <- Escribe y lee lista de clientes en .txt
│
├── ejercicio02Bonana/           <- CRUD completo de productos (menu interactivo)
│   ├── App.java                 <- Punto de entrada con menu de opciones
│   ├── control/
│   │   └── ControlProducto.java <- Logica: importTxt, listado, anhade, exportTxt, reset
│   └── dao/
│       └── Producto.java        <- Modelo: id, nombre, stockKg, precio
│
└── ejercicio03csv/              <- Utilidades para leer/escribir CSV
    ├── LectorCsv.java           <- Lee un CSV y lo procesa
    ├── CsvUtils.java            <- Utilidades: parsear lineas, validar campos
    ├── CsvTable.java            <- Representa el CSV como tabla en memoria
    └── CsvPrinter.java          <- Imprime la tabla formateada por consola
```

---

## Ejercicios en detalle

### ejercicio01 — Texto plano con DAO basico

Lee y escribe una lista de `Cliente` (id, nombre, email, saldo) en un fichero `.txt`.
- Escritura: `BufferedWriter` con campos separados por `;`
- Lectura: `BufferedReader` + `split(";", -1)` + validacion de formato
- Manejo de `IOException` y `NumberFormatException` separados

### ejercicio02Bonana — CRUD completo con menu

Aplicacion completa de gestion de productos de una fruteria:

| Opcion | Accion |
|--------|--------|
| 1 | Listar todos los productos |
| 2 | Anadir un producto nuevo (con validacion de nombre y valores) |
| 3 | Exportar a fichero `.txt` |
| 4 | Restaurar desde el fichero original |
| 0 | Salir |

> **Nota:** La ruta del fichero usa `Paths.get("AccesoDatos", "data", "productos.txt")` — ejecutar desde la raiz del proyecto.

### ejercicio03csv — Utilidades CSV

Conjunto de clases para leer, representar y mostrar tablas CSV:
- `CsvUtils`: parsea lineas y valida campos
- `CsvTable`: almacena los datos en listas de listas
- `CsvPrinter`: formatea la tabla para impresion por consola

---

## Como ejecutar

```bash
# ejercicio01 (desde la raiz del repo)
javac -d out AccesoDatos/ejercicio01/*.java
java -cp out ejercicio01.TextoPlano

# ejercicio02Bonana
javac -d out AccesoDatos/ejercicio02Bonana/*.java \
             AccesoDatos/ejercicio02Bonana/control/*.java \
             AccesoDatos/ejercicio02Bonana/dao/*.java
java -cp out ejercicio02Bonana.App
```
