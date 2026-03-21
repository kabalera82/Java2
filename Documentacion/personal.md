## PASO 1: Estructura y Dependencias Mínimas

# Estructura de Carpetas: Crea esto manualmente en tu IDE:

    src/
    └── FizzBuzz/
    ├── java/com/tuapp/
    │   ├── Main.java             <-- Punto de entrada (Arranca el servidor)
    │   ├── config/               <-- Conexión JDBC y Configuración Drive
    │   ├── model/                <-- (M) Tus objetos Java (POJOs)
    │   ├── dao/                  <-- Data Access Object (Tu "Repository" manual)
    │   └── controller/           <-- (C) Manejadores HTTP
    └── resources/
    └── credentials.json          <-- Llave de Google

# pom.xml (Solo librerías esenciales):

```
<dependencies>
    <dependency>
        <groupId>com.google.apis</groupId>
        <artifactId>google-api-services-drive</artifactId>
        <version>v3-rev20230822-2.0.0</version>
    </dependency>
    <dependency>
        <groupId>com.google.auth</groupId>
        <artifactId>google-auth-library-oauth2-http</artifactId>
        <version>1.19.0</version>
    </dependency>

    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
        <version>2.2.224</version>
    </dependency>
    
    <dependency>
        <groupId>com.google.code.gson</groupId>
        <artifactId>gson</artifactId>
        <version>2.10.1</version>
    </dependency>
</dependencies>
```

## PASO 2: El Modelo (Model) - Clase POJO
Aquí definimos el objeto puro. Sin anotaciones raras. Escribiremos los getters y setters a mano para que veas cómo se encapsula el dato.

Archivo: src/FizzBuzz/java/com/tuapp/model/Transaccion.java

