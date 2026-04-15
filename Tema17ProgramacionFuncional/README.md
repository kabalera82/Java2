# Tema 17 — Programacion Funcional en Java

> Tema 08 de 2ºDAM | Prerequisito: Java basico (colecciones, generics)

La programacion funcional en Java (Java 8+) permite escribir codigo mas conciso y expresivo mediante **lambdas**, **Streams** y **Optionals**. En lugar de decir *como* hacer algo (bucles, acumuladores), decimos *que* queremos (filtrar, transformar, reducir).

---

## Teoria rapida

### Imperativo vs Declarativo

```java
// Imperativo: describimos el COMO (bucle explicito)
int suma = 0;
for (int n : numeros) {
    if (n > 0) suma += n;
}

// Declarativo: describimos el QUE (stream pipeline)
int suma = numeros.stream()
    .filter(n -> n > 0)
    .mapToInt(Integer::intValue)
    .sum();
```

### Lambda

Una lambda es una funcion anonima: implementacion directa de una **interfaz funcional** (interfaz con un solo metodo abstracto).

```java
// Sintaxis: (parametros) -> cuerpo
Runnable r = () -> System.out.println("Hola");
Comparator<String> c = (a, b) -> a.compareTo(b);
Predicate<Integer> esPar = n -> n % 2 == 0;
Function<String, Integer> longitud = s -> s.length();
```

### Interfaces funcionales principales (`java.util.function`)

| Interfaz | Firma | Descripcion |
|----------|-------|-------------|
| `Predicate<T>` | `T -> boolean` | Comprobacion/filtro |
| `Function<T,R>` | `T -> R` | Transformacion |
| `Consumer<T>` | `T -> void` | Accion sin retorno |
| `Supplier<T>` | `() -> T` | Fabrica/proveedor |
| `BiFunction<T,U,R>` | `(T,U) -> R` | Funcion de dos argumentos |
| `UnaryOperator<T>` | `T -> T` | Transformacion del mismo tipo |

### Stream — pipeline de operaciones

```
Coleccion.stream()
    [operaciones intermedias — devuelven Stream, son lazy]
    .filter(Predicate)      <- filtra elementos
    .map(Function)          <- transforma elementos
    .sorted()               <- ordena
    .distinct()             <- elimina duplicados
    .limit(n)               <- toma los primeros n
    [operacion terminal — consume el stream, produce resultado]
    .collect(Collectors.toList())
    .forEach(Consumer)
    .count()
    .findFirst()
    .reduce(...)
    .anyMatch() / allMatch() / noneMatch()
```

### References to methods (referencias a metodos)

```java
// Lambda equivalente:  s -> System.out.println(s)
lista.forEach(System.out::println);

// Referencia a metodo de instancia
lista.stream().map(String::toUpperCase).collect(toList());

// Referencia a metodo estatico
lista.stream().map(Integer::parseInt).collect(toList());

// Referencia a constructor
lista.stream().map(Persona::new).collect(toList());
```

### Optional — evitar NullPointerException

```java
Optional<String> opt = Optional.of("hola");
Optional<String> vacio = Optional.empty();

opt.isPresent()               // true si hay valor
opt.get()                     // obtiene el valor (lanza exception si vacio)
opt.orElse("default")         // valor o default si vacio
opt.orElseGet(() -> "default")// valor o proveedor si vacio
opt.map(String::toUpperCase)  // transforma si hay valor
opt.filter(s -> s.length > 3) // filtra
opt.ifPresent(System.out::println) // accion si hay valor
```

---

## Ejercicios

### Archivos individuales (nivel basico-intermedio)

| Archivo | Concepto |
|---------|---------|
| `Ejercicio01Password.java` | Validacion de contrasena con `Predicate` |
| `Ejercicio02EsBisiesto.java` | Anio bisiesto con lambda |
| `Ejercicio03EstadisticaBasica.java` | Media, max, min con `IntStream` |
| `Ejercicio04CarreraStrings.java` | Ordenar strings por longitud con `Comparator` lambda |
| `Ejercicio05MostrarHexadecimal.java` | Convertir lista a hexadecimal con `map()` |
| `Ejercicio06TablaMultiplicar.java` | Tabla de multiplicar con `IntStream.range()` |
| `Ejercicio07ParImpar.java` | Separar pares e impares con `filter()` |

### Carpetas (nivel intermedio-avanzado)

| Carpeta | Descripcion |
|---------|------------|
| `Ejercicio01/` | Comparacion imperativo vs declarativo con numeros |
| `Ejercicio02/` | `IAnimal` como interfaz funcional — lambda como implementacion |
| `Ejercicio03/` | `Hilo` con `Comparator` lambda para ordenar |
| `Ejercicio04/` | `Mensajero` — lambda como `Runnable` en hilos |
| `Ejercicio05/` | `IOperador` — interfaz funcional de operacion matematica |
| `Ejercicio06RefenciaMetodo/` | Las 4 formas de referencia a metodo (estatico, instancia, constructor) |
| `Ejercicio07Streams/` | `Ejemplo01`: `forEach` con referencia a metodo; `Ejemplo02`: pipeline completo |
| `Ejercicio08ArrayAsList/` | `Arrays.asList()` + `sort()` + `stream()` con `Coche` |
| `Ejercicio09Optionals/` | `Optional.of()`, `orElse()`, `map()`, `filter()`, `ifPresent()` |
| `Ejercicio10OptionalsLambdas/` | `Cliente` con `Optional` para campos nullable |
| `Ejercicio11ParalelStreams/` | `parallelStream()` — ejecucion paralela de operaciones sobre colecciones |
| `Ejercicio12Repaso/` | Repaso completo: `Producto` con streams, `MainConMap` agrupa con `Collectors.groupingBy()` |

---

## Como ejecutar

```bash
# Ejercicios en carpetas (con package)
javac -d out Tema17ProgramacionFuncional/Ejercicio01/*.java
java -cp out Tema17ProgramacionFuncional.Ejercicio01.Ejercicio01ProgramacionFuncional

# Ejercicios sueltos (sin package)
javac -d out Tema17ProgramacionFuncional/Ejercicio01Password.java
java -cp out Ejercicio01Password
```

---

## Referencia — Patrones tipicos de Stream

```java
List<String> nombres = List.of("Ana", "Luis", "Maria", "Carlos", "Elena");

// Filtrar y recoger
List<String> largos = nombres.stream()
    .filter(n -> n.length() > 4)
    .collect(Collectors.toList());          // ["Maria", "Carlos", "Elena"]

// Transformar
List<Integer> longitudes = nombres.stream()
    .map(String::length)
    .collect(Collectors.toList());          // [3, 4, 5, 6, 5]

// Reducir
int totalCaracteres = nombres.stream()
    .mapToInt(String::length)
    .sum();                                 // 23

// Agrupar
Map<Integer, List<String>> porLongitud = nombres.stream()
    .collect(Collectors.groupingBy(String::length));

// Primero que cumple condicion
Optional<String> primero = nombres.stream()
    .filter(n -> n.startsWith("C"))
    .findFirst();                           // Optional["Carlos"]
```
