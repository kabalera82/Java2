## 🌑 Proyecto 01: Backend "Librería Oscura"
## Fase 1
> **Objetivo:** Configuración inicial del servidor, estructura de directorios, variables de entorno y conexión a Base de Datos (MongoDB).
---
## 1.1 Inicialización del Proyecto
Lo primero es crear la carpeta y el archivo de identidad del proyecto (`package.json`).
Abre tu terminal y ejecuta:
```bash
npm init -y
```
Esto generará un `package.json` por defecto. Vamos a dejarlo tal cual lo has diseñado:
**`package.json`**
```
{
  "name": "libreria-oscura",
  "version": "1.0.0",
  "description": "",
  "FizzBuzz": "index.js",
  "scripts": {
    "start": "node index.js",
    "dev": "nodemon index.js"
  },
  "keywords": [],
  "author": "kabalera82",
  "license": "ISC",
  "type": "commonjs",
  "dependencies": {
    "dotenv": "^16.3.1",
    "express": "^4.18.2",
    "mongoose": "^8.0.0"
  },
  "devDependencies": {
    "nodemon": "^3.0.0"
  }
}
```
*(Nota: Las versiones de las dependencias se actualizarán automáticamente a las últimas estables al ejecutar los comandos de instalación a continuación).*

## 1.2. Instalación de Dependencias

Instalamos las librerías necesarias. Separamos las que son para producción de las de desarrollo.

**Dependencias principales (Producción):**

```
npm install express mongoose dotenv
```

**Dependencias de desarrollo (Solo para programar):**

```
npm install -D nodemon
```

## 1.3. Estructura de Directorios y Ficheros

Vamos a crear la arquitectura profesional del proyecto. El archivo principal `index.js` estará en la raíz para facilitar los imports, mientras que la lógica se guarda en `src`.

Ejecuta estos comandos para crear la estructura:

```
# Crear carpetas
mkdir -p src/config
# Crear ficheros vacíos
touch index.js
touch src/config/db.js
touch .env
touch .gitignore
```

La estructura final debe verse así:

```
📁 proyecto/
│
├── 📂 node_modules/      # Librerías y dependencias instaladas (no tocar)
├── 📂 src/               # Código fuente principal
│    └──  ⚙️  config/      # Configuración (ej. Base de datos)
├── 🚀 index.js           # Punto de entrada del servidor
├── 📦 package.json       # Manifiesto del proyecto y scripts
├── 🔑 .env               # Variables de entorno (SENSIBLE - NO COMPARTIR)
└── 🙈 .gitignore         # Archivos que Git debe ignorar
```

## 1.4. Configuración del Entorno (.env y .gitignore)

- 🛡️ El archivo `.gitignore`

Este fichero es vital para no subir "basura" o secretos al repositorio.
Edita el fichero `.gitignore`:

```text
node_modules
.env
```

-  🔐 El archivo `.env`

Aquí guardamos las variables sensibles.
Edita el fichero `.env`:

```env
DB_URL=mongodb+srv://tu_usuario:tu_password@cluster.mongodb.net/libreria-oscura
PORT=8080
```

## 1.5. Configuración de la Base de Datos (Mongoose)

Vamos a configurar la conexión en un archivo separado para mantener el código limpio.
Edita el fichero **`src/config/db.js`**:

```javascript
// Importamos mongoose para conectarnos a MongoDB
const mongoose = require('mongoose');
// función asincrona para conectar a la base de datos
const connectDB = async () => {
    try {
        // Espera a que Mongoose se conecte usando variables de entorno
        await mongoose.connect(process.env.DB_URL);
        // Mensaje de exito
        console.log('🎉🎉🥳🎉🎉 MongoDB connected 🎉🎉🥳🎉🎉');
    } catch (error){
        // En caso de error, lo mostramos en consola
        console.error('🍑🍆 MongoDB connection error:', error);
    }
}
// Exportamos la funcion para usarla en otros archivos.
module.exports = connectDB;
```

## 1.6. Configuración del Servidor (Index.js)
Este es el punto de entrada de la aplicación. Configura Express, Middlewares y levanta el servidor.
Edita el fichero **`index.js`** (en la raíz):

```
//1. Importamos el dotenv y lo configuramos para cargar las varibles de entorno
require('dotenv').config();
//1. Importamos express
const express = require("express");
//1. Importamos el conector de la base de datos
const connectDB = require("./src/config/db");
//1. Creamos una instancia de express
const app = express();
// ------------ MIDDLEWARES -------------------------------------------------
//1. Middleware para parsear JSON en el body de las peticiones
app.use(express.json());
//1. -------------- RUTA BASE HOME ----------------------------------------------
app.get('/', (req, res) => {
    res.status(200).json({
        message: "🐉 Bienvenido a la API de la Dragonlance 🐉",
        author: "kabalera82",
        endpoints: {
            
        }
    });
});
// ------------ RUTAS -------------------------------------------------------
// 1. Ruta no encontrada
app.use((req,res)=>{
    return res.status(404).json({error: '🖕🖕 Ruta no encontrada 🖕🖕'});
})
// ------------ SERVIDOR -----------------------------------------------------
//1. Puerto de recambio por si no funciona la variable de entorno
const PORT = process.env.PORT || 8081;
// 1. Levantamos el servidor
app.listen(PORT, () =>{
    console.log(`🍺🍺🍺🍺 Servidor levantado en http://localhost:${PORT} 🍺🍺🍺🍺`);
})
// ------------ llamadas a las funciones ---------------------------------------
// 1. llamada a la función de conexión a la base de datos
connectDB();
```

## 1.7. Ejecución y Verificación

Para comprobar que todo funciona:

1. Asegúrate de que tu `DB_URL` en el `.env` es correcta.
2. Ejecuta en la terminal:

```
npm run dev
```

**Salida esperada en consola:**

```
[nodemon] starting `node index.js`
🍺🍺🍺🍺 Servidor levantado en http://localhost:8080 🍺🍺🍺🍺
🎉🎉🥳🎉🎉 MongoDB connected 🎉🎉🥳🎉🎉
```


# 🌑 Backend "Librería Oscura" - Fase 2

> **Objetivo:** Definir los Esquemas de Datos (Modelos).
> Aquí le decimos a MongoDB qué forma deben tener nuestros datos, qué campos son obligatorios y cómo se relacionan entre sí (Usuarios con Libros, Libros con Personajes).


## 2.1. Estructura de Carpetas Actualizada

Vamos a crear tres archivos dentro de la carpeta `src/models/`. Tu estructura debe verse así:
```
📁 proyecto/
│
├── 📂 node_modules/            # Librerías y dependencias instaladas (no tocar)
├── 📂 src/                     # Código fuente principal
│   ├── ⚙️  config/              # Configuración de la Base de Datos
│   │   └── db.js
│   │
│   ├── 🧠 controllers/         # Lógica del negocio (lo que hace la app)
│   │
│   ├── 💾 models/              # Esquemas de Mongoose (Estructura de datos)
│   │   ├── user.model.js       # Esquema de Usuarios (Auth y Favoritos)
│   │   ├── book.model.js       # Esquema de Libros (Dragonlance)
│   │   └── character.model.js  # Esquema de Personajes (Héroes y Villanos)
│   └── 🛣️  routes/              # Rutas de la API (Endpoints)
├── 🚀 index.js                 # Punto de entrada del servidor
├── 📦 package.json             # Manifiesto del proyecto y scripts
├── 🔑 .env                     # Variables de entorno (SENSIBLE)
└── 🙈 .gitignore               # Archivos que Git debe ignorar
```


## 2.2. Creación de los Modelos
Vamos a ir de "dentro hacia fuera". Primero creamos los Personajes, luego los Libros (que usan personajes) y finalmente los Usuarios (que usan ambos).
### Paso 1: El Modelo de Personaje
Crea el archivo: `src/models/character.model.js`
Este modelo define a los héroes y villanos (Raistlin, Tanis, etc.).
```
// Importamos mongoose para poder definir el esquema
const mongoose = require('mongoose');

// Definimos el esquema (la estructura de los datos)
const characterSchema = new mongoose.Schema({
    // --- 1. Identificación Básica ---
    name: { 
        type: String,       // El tipo de dato es Texto
        required: true,     // ES OBLIGATORIO: No puede haber personaje sin nombre
        unique: true,       // ES ÚNICO: No puede haber dos personajes con el mismo nombre exacto
        trim: true          // Limpia espacios: " Raistlin " se guarda como "Raistlin"
    },
    nickname: { type: String, trim: true, default: null }, // Si no envían nada, se guarda como null
    // --- 2. Rasgos ---
    race: { 
        type: String, required: true, trim: true, // Obligatorio saber la raza
        // ENUM: Solo permitimos estos valores exactos. Si envían "Hobbit", dará error.
        enum: ['Human', 'Elf', 'Dwarf', 'Kender', 'Gnome', 'Draconian', 'Minotaur', 'Half-Elf', 'Other']
    },
    class: { type: String, required: true, trim: true },  
    role: { 
        type: String, trim: true, default: null // Rol narrativo (Protagonista, Antagonista)
    },
    faction: { type: String, trim: true }, // Facción (ej: Túnicas Negras)
    // Alineamiento con valores restringidos (D&D)
    alignment: {
        type: String, trim: true, default: 'Neutral',
        enum: ['Good', 'Neutral', 'Evil'] // Solo Bien, Neutral o Mal
    },
    // --- 3. Info Extra ---
    description: { type: String, trim: true },    
    avatar: { 
        type: String,       // URL de la imagen
        default: 'https://via.placeholder.com/150' // Imagen por defecto si no suben una
    },
    // --- 4. RELACIÓN CON LIBROS --- Un personaje aparece en una lista de libros
    books: [{
        type: mongoose.Schema.Types.ObjectId, // Guardamos el ID de Mongo del libro
        ref: 'Book'                           // Referencia al modelo 'Book' (debe coincidir con el export)
    }]
}, { timestamps: true, versionKey: false }); // Crea campos 'createdAt', 'updatedAt' y elimina '__v'
// Creamos el modelo basado en el esquema
const Character = mongoose.model('Character', characterSchema);
// Exportamos el modelo para usarlo en controladores
module.exports = Character;
```

### Paso 2.3. El Modelo de Libro
Crea el archivo: `src/models/book.model.js`
Este modelo incluye la relación con los personajes que acabamos de crear.
```
// Importamos Mongoose
const mongoose = require('mongoose');
// Definimos el esquema del Libro
const bookSchema = new mongoose.Schema({
    // --- 1. Datos Esenciales ---
    title: { 
        type: String, 
        required: true,     // El título es obligatorio
        trim: true          // Elimina espacios al inicio y final
    },
    authors: [{             // Es un Array [] porque puede haber varios autores
        type: String, 
        required: true,     
        trim: true 
    }],
    // --- 2. Orden de Lectura ---
    series: {               // Saga (Ej: "Crónicas de la Dragonlance")
        type: String, 
        trim: true 
    },
    volume: {               // Número en la saga (Ej: 1)
        type: Number 
    },
    year: {                 // Año de publicación
        type: Number 
    },
    // --- 3. Visuales ---
    poster: { 
        type: String,       // URL de la portada
        // Si no hay portada, usamos esta imagen genérica de Cloudinary
        default: 'https://res.cloudinary.com/dw6qgshkz/image/upload/v1769602366/nodisponible.jpg' 
    },
    synopsis: { 
        type: String, 
        trim: true 
    },
    // --- 4. RELACIÓN CON PERSONAJES --- Array de IDs que apuntan al modelo 'Character'   
    characters: [{ 
        type: mongoose.Schema.Types.ObjectId, // IMPORTANTE: Usar Schema.Types.ObjectId
        ref: 'Character'                      // Debe coincidir con el nombre del modelo Character
    }]
}, {
    timestamps: true,       // Añade fecha de creación y modificación
    versionKey: false       // Limpia el objeto visualmente quitando la versión
});
// Creamos el modelo 'Book'
const Book = mongoose.model('Book', bookSchema);
// Exportamos el modelo
module.exports = Book;
```

### Paso 2.4. El Modelo de Usuario
Crea el archivo: `src/models/user.model.js`
Este modelo gestiona la autenticación y las listas de favoritos (tanto de libros como de personajes).
```
// Importamos Mongoose
const mongoose = require('mongoose');
// Definimos el esquema del Usuario
const userSchema = new mongoose.Schema({
    username: { 
        type: String, 
        required: true,     // OBLIGATORIO: Nombre de usuario
        unique: true,       // No pueden repetirse nombres
        trim: true 
    },
    password: { 
        type: String, 
        required: true      // OBLIGATORIO: Contraseña (se guardará encriptada más adelante)
    },
    email: { 
        type: String, 
        required: false,    // No es obligatorio registrarse con email
        unique: true,       // Pero si pone uno, no puede estar repetido
        trim: true,
        sparse: true        // VITAL: Permite que haya muchos usuarios con email 'null' sin dar error de duplicado
    },
    role: { 
        type: String, 
        enum: ['user', 'admin'], // Solo puede ser 'user' o 'admin'
        default: 'user',         // Por defecto es un usuario normal
        required: false
    },
    avatar: { 
        type: String, 
        default: 'https://via.placeholder.com/150', // Avatar por defecto
        required: false
    },
    favoriteBooks: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Book',        // Relación con el modelo Book
        required: false
    }],
    favoriteCharacters: [{
        type: mongoose.Schema.Types.ObjectId,
        ref: 'Character',   // Relación con el modelo Character
        required: false
    }]
}, {
    timestamps: true,       // Fechas automáticas
    versionKey: false       // Limpieza
});
// Exportamos el modelo 'User'
module.exports = mongoose.model('User', userSchema);
```

### Resumen de la Fase 2
Con estos archivos creados, ya tenemos la "columna vertebral" de la base de datos.
1. **Character:** Define la raza, clase y alineamiento.
2. **Book:** Se relaciona con Character (qué personajes salen en el libro).
3. **User:** Se relaciona con ambos (qué libros y personajes le gustan).

# 🌑 Backend "Librería Oscura" - Fase 3 - Controladores (GET) y sus Rutas

* Crea los siguientes archivos dentro de src/controllers/.
    - character.controllers.js
    - book.controllers.js
      Estos archivos contendrán la lógica para manejar las peticiones relacionadas con Personajes y Libros. CRUD básico (Crear, Leer, Actualizar, Borrar).

* Crea los siguientes archivos dentro de src/routes/.
    - character.routes.js
    - book.routes.js
      Estos archivos definirán las rutas de la API para Personajes y Libros, conectándolas con los controladores correspondientes.

```
📁 proyecto/
│
├── ⚙️config/
│   └── db.js
├── 🕹️ controllers/
│   ├── book.controller.js      <-- Crearemos estos archivos
│   └── character.controller.js <-- Crearemos estos archivos
├── 🙈 models/
│   ├── book.model.js
│   ├── character.model.js
│   └── user.model.js
└── 🛣️ routes/
    ├── book.routes.js          <-- Crearemos estos archivos
    └── character.routes.js     <-- Crearemos estos archivos
index.js
```
---

## 3.1. Controlador de Personajes (`character.controller.js`)

Primero creamos el controlador de Personajes con las operaciones básicas de CRUD.
Empezamos con dos funciones: Obtener todos los personajes y obtener un personaje por ID. (GET)

**Fichero:** `src/controllers/character.controllers.js`

```
//3. Creamos el controlador de Personajes
// Importamos el modelo Character
const Character = require('../models/character.model');
// Importamos el modelo Book
require('../models/book.model');
//3. --- Controlador para obtener todos los personajes ---- (GET /characters) ---
const getCharacters = async (req, res, next) => {
    // Envolvemos en try-catch para controlar los errores
    try {
        // Buscamos todos los personajes en la base de datos y populamos 
        // populate es un metodo de Mongoose que reemplaza el campo de ObjectId con el documento real referenciado
        const characters = await Character.find().populate('books');
        // Devolvemos los personajes encontrados
        return res.status(200).json(characters);
    } catch (error){
        // Si hay error pasamos al siguiente middleware de manejo de errores
        return next(error);
    }
};
//3. -- Controlador para obtener un personaje por ID -- (GET /characters/:id) --
const getCharacterById = async (req, res, next) => {
    try{
        // Obtenemos el ID desde los parámetros de la URL
        const { id } = req.params;
        // buscamos el personaje por su ID y populamos books
        const character = await Character.findById(id).populate('books');
        // Si no encontramos el personaje devolvemos un 404
        if (!character) {
            return res.status(404).json({ message: "Personaje no encontrado" });
        }
        return res.status(200).json(character);
    } catch (error) {
        return next(error);
    }
};
//3. --- Exportamos los controladores ---
module.exports = {
    getCharacters,
    getCharacterById
};
```

---

## 3.2. Creacion de las rutas de personajes (`characters.routes.js`)
Tendremos que crear las rutas para que el servidor sepa qué hacer cuando recibe una petición a ciertas URLs.

**Fichero:** `src/routes/characters.routes.js`

```
// 3. Creamos las rutas del modelo Character
//3. Importamos express
const express = require('express');
//3. Importamos las funciones y las rutas de los controladores
const {
    getCharacters,
    getCharacterById,
} = require('../controllers/characters.controllers');
//3. Creamos un router de express
const charactersRouter = express.Router();
//3. Definimos las rutas y las conectamos con sus controladores
// Endpoint: /characters
// GET /characters - Obtener todos los personajes
// localhost:8080/characters
charactersRouter.get('/', getCharacters);
// GET /characters/:id - Obtener un personaje por ID
// localhost:8080/characters/elidelpersonaje
charactersRouter.get('/:id', getCharacterById);
module.exports = charactersRouter;
```

## 3.3. Controlador de Libros (`books.controllers.js`)

**Fichero:** `src/controllers/books.controllers.js`

```
// 3. Creamos el controlador de Libros
// Importamos el modelo Book
const Book = require('../models/book.model');
// Importamos el modelo Character para el populate
require('../models/character.model');

// 3. --- Controlador para obtener todos los libros ---- ( GET /books ) ---
const getBooks = async (req, res, next) => {
    // Envolvemos en try-catch para controlar los errores
    try {
        //Buscamos todos lo libros en la base de datos
        // y los populamos: cambiar el id por el objeto completo refenciado
        const books = await Book.find().populate('characters');
        // Devolvemos los libros encontrados
        return  res.status(200).json(books);
    } catch (error){
        // Si hay error pasamos al siguiente middleware de manejo de errores
        return next(error);
    }
};
//3. --- Controlador para obtener un libro por ID --- ( GET /books/:id ) --- IGNORE
const getBookById = async(req,res, next) => {
    // Abrimos el bloque try catch
    try{
        // Obtenemos el Id desde los parametros de la URL
        const { id } = req.params;
        // Buscamos el libro por su ID y populamos con los personajes
        const book = await Book.findById(id).populate('characters');
        // si no encontramos el libro devolvemos un 404
        if(!book){
            return res.status(404).json({ message: "Libro no encontrado" });
        }
        // Si lo encontramos devolvemos el libro
        return res.status(200).json(book);
    }
    catch (error){
        return next (error);
    }
};
//3. --- Exportamos los controladores ---
module.exports = {
    getBooks,
    getBookById
};
```

## 3.4. Rutas de los libros (`books.routes.js`)

**Fichero:** `src/routes/books.routes.js`

```
// 3. Creamos las rutas del modelo Book
//3. Importamos express
const express = require('express');
//3. Importamos las funciones y las rutas de los controladores
const {
    getBooks,
    getBookById
} = require('../controllers/books.controllers');
//3. Creamos un router de express
const booksRouter = express.Router();
// 3. Definimos las rutas y las conectamos con sus controladores
// Endpoint: /books
// GET /books - Obtener todos los libros
// localhost:8080/books
booksRouter.get('/', getBooks);
// 3. GET /books/:id - Obtener un libro por ID
// localhost:8080/books/eliddellibro
booksRouter.get('/:id', getBookById);
// Exportamos el router
module.exports = booksRouter;
```

**Fichero:** `src/index.js`

## 3.5. Insercion en index.js
```
const connectDB = require("./src/config/db");
// 3. Importamos las rutas de los personajes
const charactersRouter = require("./src/routes/characters.routes");
// 3. Importamos las rutas de los libros
const booksRouter = require("./src/routes/books.routes");
const app = express();

// ============ INSTRUCCIONES PARA EL SERVIDOR EJECUTADAS EN CASCADA 
app.use(express.json());
//3 Ruta Base (Home) - Documentación simple
app.get('/', (req, res) => {
    res.status(200).json({
        message: "🐉 Bienvenido a la API de la Dragonlance 🐉",
        author: "Dogster",
        endpoints: {
            all_characters: "http://localhost:8080/characters",
            character_by_id: "http://localhost:8080/characters/:id",
            character_by_name: "http://localhost:8080/characters/name/:name",
            all_books: "http://localhost:8080/books"
        }
    });
});

// 3. Montamos las rutas de personajes en el path /
app.use("/characters", charactersRouter);
// 3. Montamos las rutas de libros en el path /books
app.use("/books", booksRouter);
// ------------ MIDDLEWARES DE ERROR -------IMPORTANTE AL FINAL 
//3. Middleware para manejar errores generales (Para el next(error))
app.use((err, req, res, next) => {
    console.error('Error:', err);
    res.status(500).json({ 
        error: 'Error interno del servidor',
        message: err.message 
    });
});
```

# 🌑 Backend "Librería Oscura" - Fase EXTRA - Semillas
## 3.6. SEEDS

**Fichero:** `src/seeds/`

  - Habrá que tener en cuenta que estos ficheros son solo para poder iniciar la base de datos con datos de prueba.
  - Todos los datos que existan en la base de datos se borrarán automaticamente antes de insertar los datos de prueba.
  - Estos ficheros no se deben subir al repositorio ni a producción.
  - Crearemos un archivo para cada Schema del que queramos hacer seed (Personajes, Libros y Usuarios).

### 3.6.1 Seed de Personajes (`character.seed.js`)
**Fichero:** `src/seeds/character.seed.js`

```
const characters = [
  {
    "name": "Raistlin Majere",
    "nickname": null,
    "race": "Human",
    "class": "Mage",
    "role": null,
    "faction": "Orders of High Sorcery (Black Robes)",
    "alignment": "Evil",
    "description": "An ambitious mage seeking godhood and mastery over time.",
    "avatar": "https://res.cloudinary.com/dw6qgshkz/image/upload/v1769602366/nodisponible.jpg",
    "books": []
  },
  {
    .......... resto del contenido ..........
  },
  {
    "name": "Tanis Half-Elven",
    "nickname": null,
    "race": "Half-Elf",
    "class": "Warrior",
    "role": "Leader of the Companions",
    "faction": "Companions of the Lance",
    "alignment": "Good",
    "description": "A divided leader who guides the Companions of the Lance.",
    "avatar": "https://res.cloudinary.com/dw6qgshkz/image/upload/v1769602366/nodisponible.jpg",
    "books": []
  }
]
module.exports = characters;
```

### 3.6.2. Seed de Libros (`books.seed.js`)

**Fichero:** `src/seeds/books.seed.js`

- Exactamente igual que el anterior, pero con los datos de los libros.
- El json en una constante y se exporta.


### 3.6.3 Seed
Codigo para la inserción automática de los datos en la base de datos.

**Fichero:** `src/seeds/seed.js`

```
// --- 3. IMPORTACIONES Y DEPENDENCIAS ---
// Importamos la librería Mongoose para poder hablar con la base de datos MongoDB.
const mongoose = require("mongoose");
// Traemos el Modelo de 'Character' (el plano/esquema) para poder crear y buscar personajes.
const Character = require("../models/character.model");
// Traemos el Modelo de 'Book' para poder crear libros.
const Book = require("../models/book.model");
// Importamos el JSON donde tenemos los datos "en bruto" de los personajes (solo texto).
const charactersData = require("./characters"); 
// Importamos el JSON de los libros. 
const booksData = require("./books");           
// Cargamos la librería 'dotenv' para poder leer variables ocultas del fichero .env (como la contraseña de la DB).
require('dotenv').config(); 

// --- . FUNCIÓN PRINCIPAL (ASÍNCRONA) ---
//'async' porque trabajar con bases de datos tarda tiempo.
const seedDatabase = async () => {
  try {
    // --- PASO A: CONEXIÓN ---
    console.log("🔌 Intentando conectar a MongoDB...");
    // Le decimos a Mongoose que se conecte a la URL que tienes guardada en tu archivo .env (DB_URL).
    // "Espera aquí hasta que te conectes antes de seguir".
    await mongoose.connect(process.env.DB_URL);
    console.log("✅ Conectado a la base de datos.");

    // --- PASO B: LIMPIEZA (BORRÓN Y CUENTA NUEVA) ---
    // Intentamos borrar la colección completa de 'Character' para no duplicar datos cada vez que lancemos la semilla.
    // Usamos .catch() para que, si la colección no existe (la primera vez), no de error y el script siga.
    await Character.collection.drop().catch(() => console.log("⚠️ No había personajes para borrar."));
    // Hacemos lo mismo con la colección de 'Book'. Borramos todo lo antiguo.
    await Book.collection.drop().catch(() => console.log("⚠️ No había libros para borrar."));
    console.log("🧹 Limpieza completada. Base de datos vacía.");

    // --- PASO C: INSERTAR PERSONAJES (LO FÁCIL) ---
    // Cogemos el JSON de personajes y lo metemos de golpe en la base de datos.
    // IMPORTANTE: Guardamos el resultado en 'createdCharacters'. 
    // Ahora esta variable contiene los personajes CON EL _id QUE MONGO LES ACABA DE ASIGNAR.
    const createdCharacters = await Character.insertMany(charactersData);
    console.log(`🧙‍♂️ Se han creado ${createdCharacters.length} personajes en la DB.`);

    // --- PASO D: PREPARAR LOS LIBROS (LA MAGIA DE LAS RELACIONES) ---
    // Creamos una lista vacía donde iremos metiendo los libros ya "arreglados" (con IDs en vez de nombres).
    const booksToInsert = [];
    // Empezamos un bucle: Vamos a ir libro por libro del JSON original.
    for (const bookInfo of booksData) {      
      // Creamos una lista temporal para guardar los IDs de los personajes que salen en ESTE libro concreto.
      const characterIds = [];
      // Preguntamos: "¿Este libro tiene una lista de nombres de personajes y no está vacía?"
      if (bookInfo.characters && bookInfo.characters.length > 0) {       
        // Si hay personajes, recorremos esa lista de nombres (ej: "Raistlin", "Tanis").
        for (const charName of bookInfo.characters) {
          // AQUÍ ESTÁ EL TRUCO: Buscamos dentro de 'createdCharacters' (que tenemos en memoria del PASO C).
          // Buscamos al personaje cuyo 'name' coincida con el nombre que pone en el libro.
          const foundChar = createdCharacters.find(c => c.name === charName);
          // Si lo encontramos...
          if (foundChar) {
            // ...cogemos su '_id' (que generó Mongo) y lo metemos en nuestra lista temporal de IDs.
            characterIds.push(foundChar._id); 
          } else {
            // Si no lo encontramos (quizás hay una errata en el nombre), avisamos por consola pero no fallamos.
            console.warn(`⚠️ Aviso: El personaje "${charName}" del libro "${bookInfo.title}" no existe en la DB.`);
          }
        }
      }
      // Ahora construimos el objeto del libro definitivo.
      booksToInsert.push({
        ...bookInfo, // Copiamos todo lo que traía el JSON (título, autor, sinopsis...)
        characters: characterIds // PERO machacamos el campo 'characters'. Antes eran nombres, ahora son IDs.
      });
    }

    // --- PASO E: INSERTAR LIBROS ---
    // Ahora que tenemos los libros con las relaciones (IDs) bien puestas, los guardamos todos de golpe.
    // También guardamos el resultado en 'createdBooks' para usarlo en el siguiente paso.
    const createdBooks = await Book.insertMany(booksToInsert);
    console.log(`📚 Se han creado ${createdBooks.length} libros con sus relaciones.`);

    // --- PASO F: RELACIÓN INVERSA (ACTUALIZAR PERSONAJES) ---
    // Ahora los libros saben qué personajes tienen, pero los personajes NO saben en qué libros salen.
    // Vamos a arreglar eso. Recorremos los libros que acabamos de crear.
    for (const book of createdBooks) {
      // Le decimos a la DB: "Actualiza MUCHOS personajes".
      await Character.updateMany(
        // FILTRO: "Busca todos los personajes cuyo _id esté en la lista 'characters' de este libro".
        { _id: { $in: book.characters } },  
        // ACCIÓN: "Haz un push (añade) el _id de este libro dentro del array 'books' del personaje".
        { $push: { books: book._id } }     
      );
    }
    console.log("🔄 Relaciones bidireccionales (Personaje <-> Libro) actualizadas.");
  } catch (error) {
    // Si algo falla en cualquier punto del 'try', saltamos aquí y mostramos el error.
    console.error("❌ Ha ocurrido un error fatal en la semilla:", error);
  } finally {

    // --- PASO G: CERRAR ---
    // Pase lo que pase (haya ido bien o mal), cerramos la conexión para que la terminal no se quede colgada.
    await mongoose.disconnect();
    console.log("👋 Desconectado de MongoDB. Script finalizado.");
  }
};
// Llamamos a la función para que empiece la fiesta.
seedDatabase();
```

# 🌑 Backend "Librería Oscura" - Fase 4 - Create (POST)

## 4.1. Añadir la Funcion Create al controlador de Personajes
**Fichero:** `src/controllers/character.controller.js`

```javascript
//4. --- Controlador para crear un nuevo personaje ---- (POST /characters) ---
const createCharacter =  async (req, res, next) => {
    try{
        // creamos un nuevo personaje con los datos del body
        const newCharacter = new Character(req.body);
        // Guardamos el personaje en la base de datos
        const createdCharacter = await newCharacter.save();
        // Devolvemos el personaje creado
        return res.status(201).json(createdCharacter);
    } catch (error){
        // Validamos si es un error de validación de Mongoose
        return next(error);
    }
};
//4. -- Añadimos cada controlador al export en este caso crear personaje --
module.exports = {
    getCharacters,
    getCharacterById,
    createCharacter
};
```

## 4.2. Añadir la Ruta POST al archivo de rutas de personajes
**Fichero:** `src/routes/characters.routes.js`

```
//4. Importamos las funciones createCharacter
const {
    getCharacters,
    getCharacterById,
    createCharacter
} = require('../controllers/characters.controllers');

//4. POST /characters - Crear un nuevo personaje
// localhost:8080/characters
charactersRouter.post('/', createCharacter);
```

## 4.3. Añadir la Funcion Create al controlador de Libros (`book.controller.js`)
**Fichero:** `src/controllers/book.controller.js`
```
// 4. --- controlador para crear un nuevo liubro ---- ( POST /books ) ---
const createBook = async( req, res, next) => {
    try {
        // Creamos el nuevo libro con los datos del body
        const newBook = new Book (req.body);
        // Guardamos el libro en la base de datos;
        const createdBook = await newBook.save();
        // Devolvemos el libro creado
        return res.status(201).json(createdBook);
    } catch (error) {
        return next (error);
    }
};
//4. -- Añadimos cada controlador al export en este caso crear libro --
module.exports = {
    getBooks,
    getBookById,
    createBook
};
```

## 4.4. Añadir la Ruta POST al archivo de rutas de libros (`book.routes.js`)
**Fichero:** `src/routes/books.routes.js`
```
//4. Importamos la funciones createBook
const {
    getBooks,
    getBookById,
    createBook
} = require('../controllers/books.controllers');
// 4. POST /books - Crear un nuevo libro
// localhost:8080/books
booksRouter.post('/', createBook);
```

# 🌑 Backend "Librería Oscura" - Fase 5 - Update (PUT)

## 5.1. Añadir la Funcion Update al controlador de Personajes
**Fichero:** `src/controllers/characters.controllers.js`

```
//5.1 --- Controlador para actualizar un personaje ---- (PUT /characters/:id) ---
const updateCharacter = async (req, res, next) => {
    try {
        // Obtenemos el ID desde los parametros de la URL
        const { id } = req.params;
        // Buscamos y actualizamos
        // Tomamos tres argumentos: ID, Datos Nuevos,configuración
        const updatedCharacter = await Character.findByIdAndUpdate(
            id,
            req.body,
            { new: true }    
        ).populate('books');
        // Si no encontramos el personaje devolvemos un 404
        if (!updatedCharacter) {
            return res.status(404).json({ error: '🕵️🕵️🕵️ Personaje no encontrado 🕵️🕵️🕵️' });
        }
        // Devolvemos el personaje actualizado
        return res.status(200).json(updatedCharacter);
    } catch (error) {
        return next(error);
    }
    //5. -- Añadimos el controlador de updateCharacter --
    module.exports = {
        getCharacters,
        getCharacterById,
        createCharacter,
        updateCharacter
    };
```

## 5.2. Añadir la Ruta PUT al archivo de rutas de personajes
**Fichero:** `src/routes/characters.routes.js`
```
//5.2 Importamos la función updateCharacter
const {
    getCharacters,
    getCharacterById,
    createCharacter,
    updateCharacter // <--- IMPORTAMOS ESTO
} = require('../controllers/characters.controllers');
// 5. PUT /characters/:id - Actualizar un personaje
// localhost:8080/characters/elidelpersonaje
charactersRouter.put('/:id', updateCharacter);
```

# 🌑 Backend "Librería Oscura" - Fase 6 - Delete (DELETE)

## 6.1. Añadir la Funcion Delete al controlador de Libros (`books.controllers.js`)
**Fichero:** `src/controllers/books.controllers.js`
```javascript
//6. --- Controlador para eliminar un libro ---- ( DELETE /books/:id ) 
const deleteBook = async ( req, res, next) => {
    try {
        // Obtenemos el Libro por id desde los parámetros de la URL
        const deletedBook = await Book.findByIdAndDelete(req.params.id);
        // Si no existe el Libro devolvemos un 404
        if(!deletedBook){
            return res.status(404).json({ error: '🕵️🕵️🕵️ Libro no encontrado 🕵️🕵️🕵️' });
        }
        // Devolvemos un mensaje de éxito
        return res.status(200).json({ message: '😶‍🌫️😶‍🌫️😶‍🌫️ Libro eliminado correctamente 😶‍🌫️😶‍🌫️😶‍🌫️' });
    } catch (error) {
        return next(error);
    }
};
//6. -- Añadimos el controlador de deleteBook --
module.exports = {
    getBooks,
    getBookById,
    createBook,
    updateBook,
    deleteBook
};
```

### 6.2. Añadir la Ruta DELETE al archivo de rutas de libros (`books.routes.js`)
**Fichero:** `src/routes/books.routes.js`
```
//6. Importamos la función deleteBook
const {
    getBooks,
    getBookById,
    createBook,
    updateBook,
    deleteBook
} = require('../controllers/books.controllers');
// 6. DELETE /books/:id - Eliminar un libro
// localhost:8080/books/eliddellibro
booksRouter.delete('/:id', deleteBook);
```

### 6.3. Creacion de la Documentación en Home vía json endopoinst
* Cuando Terminamos el CRUD de Personajes y Libros pasamos a crear una pequeña documentación en el endpoint raíz `/` del servidor.
  **Fichero:** `src/index.js`
```
// 6.3 Ruta Home con documentación básica de la API
app.get('/', (req, res) => {
    res.status(200).json({
        message: "🐉 Bienvenido a la API de la Dragonlance 🐉",
        author: "kabalera82",
        documentation: {
            characters: [
            { method: "GET", url: "http://localhost:8080/characters", description: "Ver todos los personajes" },
                { method: "GET", url: "http://localhost:8080/characters/:id", description: "Ver personaje por ID" },
                { method: "GET", url: "http://localhost:8080/characters/name/:name", description: "Buscar por nombre" },
                { method: "POST", url: "http://localhost:8080/characters", description: "Crear personaje nuevo" },
                { method: "PUT", url: "http://localhost:8080/characters/:id", description: "Editar personaje" },
                { method: "DELETE", url: "http://localhost:8080/characters/:id", description: "Eliminar personaje" }
            ],
            books: [
                { method: "GET", url: "http://localhost:8080/books", description: "Ver todos los libros" },
                { method: "GET", url: "http://localhost:8080/books/:id", description: "Ver libro por ID" },
                { method: "POST", url: "http://localhost:8080/books", description: "Crear libro nuevo" },
                { method: "PUT", url: "http://localhost:8080/books/:id", description: "Editar libro" },
                { method: "DELETE", url: "http://localhost:8080/books/:id", description: "Eliminar libro" }
            ]
        }
    });
});
```



