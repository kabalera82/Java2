# Plants vs Zombies en Java — Guía de Refuerzo de Programación

> Proyecto: **Plants vs Zombies** | Lenguaje: **Java** | UI: **Java Swing**
> Objetivo: entender el código, reforzar conceptos y explorar cómo escalar el proyecto

---

## Índice

1. [Visión General del Proyecto](#1-visión-general-del-proyecto)
2. [Estructura del Proyecto](#2-estructura-del-proyecto)
3. [Conceptos de POO aplicados](#3-conceptos-de-poo-aplicados)
4. [Módulo: Tablero de Juego (Map.java)](#4-módulo-tablero-de-juego-mapjava)
5. [Módulo: Plantas — Herencia y Polimorfismo](#5-módulo-plantas--herencia-y-polimorfismo)
6. [Módulo: Zombies — Clases Abstractas](#6-módulo-zombies--clases-abstractas)
7. [Módulo: Proyectiles — Colisiones](#7-módulo-proyectiles--colisiones)
8. [Módulo: Sistema de Sol — Eventos de Ratón](#8-módulo-sistema-de-sol--eventos-de-ratón)
9. [Módulo: Game Loop — Hilos y Temporizadores](#9-módulo-game-loop--hilos-y-temporizadores)
10. [Módulo: UI, Menú y Música](#10-módulo-ui-menú-y-música)
11. [Módulo: Dificultad y Oleadas](#11-módulo-dificultad-y-oleadas)
12. [Flujo Completo del Juego](#12-flujo-completo-del-juego)
13. [Compilación y Ejecución](#13-compilación-y-ejecución)
14. [Puntos de Escalado del Proyecto](#14-puntos-de-escalado-del-proyecto)
15. [Introducción de Base de Datos](#15-introducción-de-base-de-datos)

---

## 1. Visión General del Proyecto

Este proyecto es una **versión simplificada de Plants vs Zombies** escrita completamente en Java. Incluye:

- 4 tipos de plantas (Sunflower, Peashooter, Wall-nut, CherryBomb)
- 4 tipos de zombies con diferentes estadísticas
- Sistema de música y efectos de sonido
- Animaciones por frames con imágenes
- Niveles de dificultad configurables
- UI completa con menú, tienda de plantas y tablero de juego

### Mecánica Central

```
Jugador coloca plantas en la cuadrícula
        ↓
Plantas generan sol / atacan zombies
        ↓
Zombies avanzan desde la derecha por carriles
        ↓
Si un zombie llega al final → Game Over
        ↓
Si todos los zombies son eliminados → Victoria
```

> **Por qué es buen proyecto de aprendizaje:** combina POO (herencia, polimorfismo, clases abstractas),
> programación dirigida por eventos (Swing, MouseListener), concurrencia básica (Timer/Game Loop),
> y estructuras de datos (ArrayList con iteradores seguros).

---

## 2. Estructura del Proyecto

```
Plants-vs-Zombies/
├── src/
│   ├── Main.java                # Punto de entrada
│   ├── GamePanel.java           # Panel principal (Game Loop)
│   ├── Map.java                 # Estado global: plantas, zombies, proyectiles
│   ├── plants/
│   │   ├── Plant.java           # Clase base ABSTRACTA
│   │   ├── Sunflower.java
│   │   ├── Peashooter.java
│   │   ├── WallNut.java
│   │   └── CherryBomb.java
│   ├── zombies/
│   │   ├── Zombie.java          # Clase base ABSTRACTA
│   │   ├── NormalZombie.java
│   │   ├── ConeheadZombie.java
│   │   ├── BucketheadZombie.java
│   │   └── FlagZombie.java
│   ├── entities/
│   │   ├── Projectile.java
│   │   ├── Sun.java
│   │   └── LawnMower.java
│   └── ui/
│       ├── MenuPanel.java
│       ├── ShopPanel.java
│       └── GameImages.java
├── Image/                       # Sprites y fondos
└── music/                       # Archivos de audio (.wav)
```

**Observación pedagógica:** la separación en paquetes (`plants/`, `zombies/`, `ui/`) refleja el
principio de **separación de responsabilidades**. Cada paquete agrupa clases con un propósito común.

---

## 3. Conceptos de POO aplicados

Este proyecto es un caso de uso real de los pilares de la Programación Orientada a Objetos:

| Pilar | Dónde se aplica |
|-------|-----------------|
| **Abstracción** | `Plant` y `Zombie` son clases abstractas que definen el contrato (`update`, `draw`) sin implementarlo |
| **Herencia** | `Sunflower`, `Peashooter`, `WallNut` extienden `Plant`; cada zombie extiende `Zombie` |
| **Polimorfismo** | El game loop llama `p.update(map)` y `p.draw(g)` sin saber qué tipo concreto es cada planta |
| **Encapsulación** | Los atributos (`health`, `attack`, `x`, `y`) son `protected`/`private`, accesibles solo por getters |

### El polimorfismo en acción

```java
// GamePanel.java — el loop no sabe si p es Sunflower, Peashooter o WallNut
for (Plant p : map.getAllPlants()) {
    p.update(map);  // cada planta hace lo suyo
    p.draw(g);      // cada planta se dibuja a sí misma
}
```

Esto es posible porque todas heredan de `Plant` y todas implementan `update()` y `draw()`.
Si añadimos una planta nueva, el game loop **no cambia**. Solo creamos la nueva subclase.

### Patrón MVC adaptado a juegos

```
┌─────────────────────────────────────────────┐
│                  VISTA (UI)                  │
│  MenuPanel  │  GamePanel  │  ShopPanel       │
└──────────────────┬──────────────────────────┘
                   │ eventos / render
┌──────────────────▼──────────────────────────┐
│             CONTROLADOR                      │
│  GamePanel (Game Loop + MouseListener)       │
└──────────────────┬──────────────────────────┘
                   │ lee / modifica
┌──────────────────▼──────────────────────────┐
│               MODELO (Map.java)              │
│  List<Plant>  │  List<Zombie>  │  List<Sun>  │
│  List<Projectile>  │  int sunPoints          │
└─────────────────────────────────────────────┘
```

**Regla clave MVC:** la Vista nunca modifica el Modelo directamente. El Controlador (GamePanel)
actúa de intermediario. Esto hace el código más fácil de mantener y testear.

---

## 4. Módulo: Tablero de Juego (Map.java)

`Map.java` es el **modelo central** del juego. Contiene el estado completo de la partida.

```java
public class Map {
    // Cuadrícula 5x9 (filas x columnas)
    private static final int ROWS = 5;
    private static final int COLS = 9;

    // Tamaño de cada celda en píxeles
    public static final int CELL_WIDTH = 80;
    public static final int CELL_HEIGHT = 90;

    // Offset del tablero en pantalla
    public static final int BOARD_X = 80;
    public static final int BOARD_Y = 100;

    // Listas de entidades activas
    private List<Plant> allPlants = new ArrayList<>();
    private List<Zombie> allZombies = new ArrayList<>();
    private List<Projectile> allProjectiles = new ArrayList<>();
    private List<Sun> allSunshine = new ArrayList<>();
    private List<LawnMower> allLawnMowers = new ArrayList<>();

    // Sol disponible del jugador
    private int sunPoints = 50;

    // Convierte coordenadas de pantalla a celda de cuadrícula
    public int[] screenToGrid(int x, int y) {
        int col = (x - BOARD_X) / CELL_WIDTH;
        int row = (y - BOARD_Y) / CELL_HEIGHT;
        return new int[]{row, col};
    }

    // Verifica si una celda está ocupada
    public boolean isCellOccupied(int row, int col) {
        for (Plant p : allPlants) {
            if (p.getRow() == row && p.getCol() == col) return true;
        }
        return false;
    }
}
```

**Conceptos a reforzar:**
- `static final` → constantes de clase, compartidas por todas las instancias
- `ArrayList<>` con genéricos → colección tipada, sin casting
- La conversión `screenToGrid` es un ejemplo de **mapeo de coordenadas** (muy común en videojuegos)

### Dibujando la cuadrícula

```java
// En GamePanel.paintComponent(Graphics g)
private void drawGrid(Graphics g) {
    g.drawImage(backgroundImg, 0, 0, null); // Fondo del jardín

    // Dibujar celdas (útil para debug)
    g.setColor(new Color(0, 100, 0, 50));
    for (int row = 0; row < 5; row++) {
        for (int col = 0; col < 9; col++) {
            int x = Map.BOARD_X + col * Map.CELL_WIDTH;
            int y = Map.BOARD_Y + row * Map.CELL_HEIGHT;
            g.drawRect(x, y, Map.CELL_WIDTH, Map.CELL_HEIGHT);
        }
    }
}
```

---

## 5. Módulo: Plantas — Herencia y Polimorfismo

### Clase base `Plant.java` (abstracta)

```java
public abstract class Plant {
    protected int row, col;          // Posición en cuadrícula
    protected double x, y;           // Posición en pantalla (píxeles)
    protected int health;            // Puntos de vida
    protected int cost;              // Costo en sol
    protected int attack;            // Daño por golpe
    protected int attackCooldown;    // Frames entre ataques
    protected int frameTimer;        // Contador para animaciones
    protected int currentFrame;      // Frame actual del sprite
    protected boolean alive = true;

    // Cada planta DEBE implementar estos métodos
    public abstract void update(Map map);
    public abstract void draw(Graphics g);

    // Método concreto compartido por todas las subclases
    public void takeDamage(int dmg) {
        health -= dmg;
        if (health <= 0) alive = false;
    }
}
```

**Por qué `abstract`:** no tiene sentido instanciar una `Plant` genérica. Siempre queremos
un tipo concreto. Los métodos `abstract` **fuerzan** a las subclases a implementar su comportamiento.

**`protected` vs `private`:** usamos `protected` para que las subclases accedan directamente
a los atributos. Si fuesen `private`, cada subclase necesitaría getters/setters del padre.

### Sunflower — Genera sol

```java
public class Sunflower extends Plant {
    private int sunTimer = 0;
    private static final int SUN_INTERVAL = 600; // cada 600 frames (~10 seg a 60 FPS)

    public Sunflower(int row, int col) {
        this.row = row;
        this.col = col;
        this.health = 300;
        this.cost = 50;
        this.attack = 0;
        this.x = Map.BOARD_X + col * Map.CELL_WIDTH;
        this.y = Map.BOARD_Y + row * Map.CELL_HEIGHT;
    }

    @Override
    public void update(Map map) {
        sunTimer++;
        if (sunTimer >= SUN_INTERVAL) {
            Sun sun = new Sun((int)x + 20, (int)y - 20, false);
            map.getAllSunshine().add(sun);
            sunTimer = 0;
        }
        // Avanzar frame de animación
        frameTimer++;
        if (frameTimer % 8 == 0) {
            currentFrame = (currentFrame + 1) % totalFrames;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(frames[currentFrame], (int)x, (int)y, null);
    }
}
```

**Conceptos:**
- `@Override` → anotación que confirma que estamos sobreescribiendo un método del padre
- El temporizador de frames (`frameTimer % 8`) es un patrón de **animación por frames** muy habitual
- La Sunflower **crea objetos** (`new Sun(...)`) y los añade al modelo — responsabilidad bien definida

### Peashooter — Dispara proyectiles

```java
public class Peashooter extends Plant {
    private int shootTimer = 0;
    private static final int SHOOT_INTERVAL = 120; // cada 2 seg (a 60 FPS)

    public Peashooter(int row, int col) {
        this.row = row;
        this.col = col;
        this.health = 300;
        this.cost = 100;
        this.attack = 20;
        this.x = Map.BOARD_X + col * Map.CELL_WIDTH;
        this.y = Map.BOARD_Y + row * Map.CELL_HEIGHT;
    }

    @Override
    public void update(Map map) {
        // Solo dispara si hay un zombie en el mismo carril — usa Stream API
        boolean zombieInLane = map.getAllZombies().stream()
            .anyMatch(z -> z.getRow() == this.row && z.isAlive());

        if (zombieInLane) {
            shootTimer++;
            if (shootTimer >= SHOOT_INTERVAL) {
                Projectile pea = new Projectile(x + 60, y + 20, row, attack);
                map.getAllProjectiles().add(pea);
                shootTimer = 0;
            }
        }
    }
}
```

**Stream API:** `.stream().anyMatch(...)` es programación funcional en Java.
Equivale a un bucle `for` con un `if`, pero más expresivo. Refuerza el tema de lambdas.

### WallNut — Defensa pura

```java
public class WallNut extends Plant {
    public WallNut(int row, int col) {
        this.row = row;
        this.col = col;
        this.health = 4000; // Mucha vida, no ataca
        this.cost = 50;
        this.attack = 0;
    }
    // Los zombies la "comen" antes de avanzar
}
```

**Reflexión:** WallNut demuestra que el polimorfismo permite que `attack = 0` sea una
implementación válida. No todo tiene que atacar — la interfaz (`Plant`) es más general que
cualquier subtipo.

---

## 6. Módulo: Zombies — Clases Abstractas

### Clase base `Zombie.java`

```java
public abstract class Zombie {
    protected int row;           // Carril (0-4)
    protected double x;          // Posición horizontal
    protected double y;          // Posición vertical (fija según carril)
    protected int health;
    protected int attack;
    protected double speed;      // Píxeles por frame
    protected boolean alive = true;
    protected boolean isEating = false;
    protected Plant targetPlant = null;

    protected static final int SPAWN_X = 900; // Fuera de la pantalla

    public abstract void update(Map map);
    public abstract void draw(Graphics g);

    public void move() {
        if (!isEating) {
            x -= speed; // Avanza hacia la izquierda
        }
    }

    public void eatPlant(Plant plant) {
        this.isEating = true;
        this.targetPlant = plant;
    }

    public void takeDamage(int dmg) {
        health -= dmg;
        if (health <= 0) {
            alive = false;
            isEating = false;
        }
    }

    public int getRow() { return row; }
    public boolean isAlive() { return alive; }
}
```

### Tipos de Zombie — Sobrescritura de atributos

```java
public class NormalZombie extends Zombie {
    public NormalZombie(int row) {
        this.row = row;
        this.x = SPAWN_X;
        this.y = Map.BOARD_Y + row * Map.CELL_HEIGHT + 10;
        this.health = 200;
        this.attack = 10;
        this.speed = 0.5;
    }
}

public class ConeheadZombie extends Zombie {
    public ConeheadZombie(int row) {
        this.row = row;
        this.health = 370; // El cono absorbe más daño
        this.attack = 10;
        this.speed = 0.5;
    }
}

public class BucketheadZombie extends Zombie {
    public BucketheadZombie(int row) {
        this.row = row;
        this.health = 650;
        this.attack = 10;
        this.speed = 0.5;
    }
}
```

**Jerarquía completa:**

```
Zombie (abstracta)
├── NormalZombie      → vida: 200, speed: 0.5
├── ConeheadZombie    → vida: 370, speed: 0.5
├── BucketheadZombie  → vida: 650, speed: 0.5
└── FlagZombie        → marca inicio de oleada
```

### Detección de colisión zombie-planta

```java
private void checkZombiePlantCollision(Map map) {
    for (Zombie z : map.getAllZombies()) {
        if (!z.isAlive()) continue;

        for (Plant p : map.getAllPlants()) {
            if (!p.isAlive()) continue;
            if (z.getRow() != p.getRow()) continue;

            // Colisión por rectángulos (AABB - Axis-Aligned Bounding Box)
            if (z.getX() <= p.getX() + Map.CELL_WIDTH &&
                z.getX() + 40 >= p.getX()) {
                z.eatPlant(p);
                break;
            }
        }
    }
}
```

**AABB (Axis-Aligned Bounding Box):** la técnica de colisión más sencilla. Dos rectángulos
colisionan si se solapan en ambos ejes (X e Y). Aquí el eje Y ya está filtrado por carril.

---

## 7. Módulo: Proyectiles — Colisiones

```java
public class Projectile {
    private double x, y;
    private int lane;
    private int damage;
    private double speed = 5.0;
    private boolean active = true;

    public Projectile(double x, double y, int lane, int damage) {
        this.x = x;
        this.y = y;
        this.lane = lane;
        this.damage = damage;
    }

    public void move() {
        x += speed; // Vuela hacia la derecha
    }

    public void draw(Graphics g) {
        g.drawImage(peaSprite, (int)x, (int)y, null);
    }

    public boolean hits(Zombie z) {
        return z.getRow() == this.lane
            && x + 16 >= z.getX()
            && x <= z.getX() + 40;
    }

    public boolean isActive() { return active && x < 900; }
    public void deactivate() { active = false; }
    public int getDamage() { return damage; }
}
```

### Iterador seguro para eliminar durante el bucle

```java
private void processProjectiles(Map map) {
    Iterator<Projectile> pIt = map.getAllProjectiles().iterator();
    while (pIt.hasNext()) {
        Projectile proj = pIt.next();
        proj.move();

        boolean hit = false;
        for (Zombie z : map.getAllZombies()) {
            if (proj.hits(z)) {
                z.takeDamage(proj.getDamage());
                hit = true;
                break;
            }
        }

        if (hit || !proj.isActive()) {
            pIt.remove(); // Eliminar usando el Iterator, NO la lista directamente
        }
    }

    // removeIf con lambda — alternativa moderna al Iterator
    map.getAllZombies().removeIf(z -> !z.isAlive());
}
```

**Concepto crítico — ConcurrentModificationException:**
Si intentas hacer `list.remove(obj)` dentro de un `for-each` sobre esa misma lista,
Java lanza `ConcurrentModificationException`. La solución es usar `Iterator.remove()`
o `removeIf()` (que internamente usa un iterador seguro).

---

## 8. Módulo: Sistema de Sol — Eventos de Ratón

```java
public class Sun {
    private double x, y;
    private double targetY;
    private double fallSpeed = 1.5;
    private boolean collected = false;
    private boolean fromSky;
    private static final int CLICK_RADIUS = 25;

    public Sun(int x, int startY, boolean fromSky) {
        this.x = x;
        this.y = startY;
        this.fromSky = fromSky;
        this.targetY = fromSky ? 150 + Math.random() * 200 : startY + 60;
    }

    public void fall() {
        if (y < targetY) y += fallSpeed;
    }

    // Detección de click por distancia euclidiana
    public boolean collect(int mouseX, int mouseY) {
        double dist = Math.sqrt(Math.pow(mouseX - x, 2) + Math.pow(mouseY - y, 2));
        if (dist <= CLICK_RADIUS) {
            collected = true;
            return true;
        }
        return false;
    }

    public boolean isCollected() { return collected; }

    public void draw(Graphics g) {
        if (!collected) g.drawImage(sunSprite, (int)x, (int)y, null);
    }
}
```

**Distancia euclidiana:** `√((x2-x1)² + (y2-y1)²)` — el teorema de Pitágoras aplicado
a coordenadas 2D. Útil para colisiones circulares (más precisa que AABB para objetos redondos).

### Manejo de eventos del ratón

```java
// En MouseListener del GamePanel
@Override
public void mouseClicked(MouseEvent e) {
    int mx = e.getX(), my = e.getY();

    // 1. Intentar recoger sol
    for (Sun sun : map.getAllSunshine()) {
        if (sun.collect(mx, my)) {
            map.setSunPoints(map.getSunPoints() + 25);
            break;
        }
    }

    // 2. Colocar planta seleccionada
    if (selectedPlant != null) {
        int[] cell = map.screenToGrid(mx, my);
        int row = cell[0], col = cell[1];

        if (row >= 0 && row < 5 && col >= 0 && col < 9
                && !map.isCellOccupied(row, col)
                && map.getSunPoints() >= selectedPlant.getCost()) {

            Plant newPlant = createPlant(selectedPlant.getType(), row, col);
            map.getAllPlants().add(newPlant);
            map.setSunPoints(map.getSunPoints() - selectedPlant.getCost());
        }
    }
}
```

**MouseListener** es un **patrón Observer**: el sistema notifica al GamePanel cuando ocurre
un evento de ratón. El GamePanel reacciona sin necesidad de polling activo.

---

## 9. Módulo: Game Loop — Hilos y Temporizadores

El **game loop** es el corazón del juego. Usa `javax.swing.Timer` para actualizar y redibujar
cada ~16ms (≈60 FPS). Este Timer corre en el Event Dispatch Thread (EDT) de Swing.

```java
public class GamePanel extends JPanel implements ActionListener, MouseListener {
    private Map map;
    private Timer gameTimer;
    private boolean gameOver = false;
    private boolean victory = false;

    private int sunFallTimer = 0;
    private int zombieSpawnTimer = 0;
    private int waveNumber = 0;

    public GamePanel() {
        map = new Map();
        setPreferredSize(new Dimension(900, 600));
        addMouseListener(this);

        gameTimer = new Timer(16, this); // dispara actionPerformed cada 16ms
        gameTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !victory) {
            update();
            repaint(); // llama a paintComponent en el EDT
        }
    }

    private void update() {
        // 1. Hacer caer sol del cielo periódicamente
        sunFallTimer++;
        if (sunFallTimer >= 400) {
            int rx = 100 + (int)(Math.random() * 700);
            map.getAllSunshine().add(new Sun(rx, 0, true));
            sunFallTimer = 0;
        }

        // 2. Actualizar soles (caída + expiración)
        for (Sun s : map.getAllSunshine()) s.fall();
        map.getAllSunshine().removeIf(s -> s.isCollected() || s.isExpired());

        // 3. Actualizar plantas
        for (Plant p : map.getAllPlants()) p.update(map);
        map.getAllPlants().removeIf(p -> !p.isAlive());

        // 4. Proyectiles y colisiones
        processProjectiles(map);

        // 5. Zombies
        for (Zombie z : map.getAllZombies()) z.update(map);
        checkZombiePlantCollision(map);

        // 6. Spawnear oleadas
        spawnZombies();

        // 7. Condiciones de fin
        checkGameOver();
        checkVictory();

        // 8. Cortacéspedes
        for (LawnMower lm : map.getAllLawnMowers()) lm.update(map);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar en orden: fondo → plantas → proyectiles → zombies → UI
        for (Sun s : map.getAllSunshine()) s.draw(g);
        for (Plant p : map.getAllPlants()) p.draw(g);
        for (Projectile proj : map.getAllProjectiles()) proj.draw(g);
        for (Zombie z : map.getAllZombies()) z.draw(g);
        for (LawnMower lm : map.getAllLawnMowers()) lm.draw(g);

        drawHUD(g);

        if (gameOver) drawGameOver(g);
        if (victory) drawVictory(g);
    }
}
```

**Conexión con el tema de Hilos:** `javax.swing.Timer` es una forma segura de programación
concurrente en Swing. El callback `actionPerformed` corre en el EDT, evitando condiciones de
carrera con el pintado. Si usásemos un `Thread` propio para el game loop, necesitaríamos
`SwingUtilities.invokeLater()` para modificar la UI de forma segura.

### Carga de animaciones por frames

```java
public class GameImages {
    public static Image[] loadFrames(String folder, String prefix, int count) {
        Image[] frames = new Image[count];
        for (int i = 0; i < count; i++) {
            String path = "Image/" + folder + "/" + prefix + i + ".png";
            frames[i] = new ImageIcon(path).getImage();
        }
        return frames;
    }
}

// Uso en Sunflower:
private Image[] frames = GameImages.loadFrames("sunflower", "sf_", 18);
private int totalFrames = 18;
```

**Patrón:** cargar todos los frames al inicio y guardarlos en un array. Acceder por índice
es O(1). Evita leer del disco en cada frame del juego (que sería muy lento).

---

## 10. Módulo: UI, Menú y Música

### Menú principal (`MenuPanel.java`)

```java
public class MenuPanel extends JPanel {
    private JButton startBtn;
    private JComboBox<String> difficultyBox;

    public MenuPanel(JFrame frame) {
        setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel bg = new JLabel(new ImageIcon("Image/menu_bg.png"));
        add(bg, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        startBtn = new JButton("¡JUGAR!");
        difficultyBox = new JComboBox<>(new String[]{"Fácil", "Normal", "Difícil"});

        startBtn.addActionListener(e -> {
            String diff = (String) difficultyBox.getSelectedItem();
            GamePanel gamePanel = new GamePanel(diff);
            frame.setContentPane(gamePanel);
            frame.revalidate(); // actualiza el layout del JFrame
        });

        btnPanel.add(difficultyBox);
        btnPanel.add(startBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
```

**Lambda en ActionListener:** `e -> { ... }` es la sintaxis lambda de Java 8+.
Antes se usaba una clase anónima:
```java
startBtn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) { ... }
});
```
La lambda es equivalente pero mucho más concisa.

### Sistema de música (`MusicPlayer.java`)

```java
import javax.sound.sampled.*;

public class MusicPlayer {
    private Clip clip;

    public void play(String filepath) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filepath));
            clip = AudioSystem.getClip();
            clip.open(ais);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) clip.stop();
    }

    public void playEffect(String filepath) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(new File(filepath));
            Clip effect = AudioSystem.getClip();
            effect.open(ais);
            effect.start(); // Sin loop — para efectos de sonido
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

### HUD (Heads-Up Display)

```java
private void drawHUD(Graphics g) {
    g.setColor(Color.YELLOW);
    g.setFont(new Font("Arial", Font.BOLD, 20));
    g.drawImage(sunIcon, 10, 10, 40, 40, null);
    g.drawString(String.valueOf(map.getSunPoints()), 55, 35);

    int shopX = 10, shopY = 60;
    for (PlantCard card : plantCards) {
        if (card.isSelected()) g.setColor(Color.YELLOW);
        else g.setColor(Color.DARK_GRAY);
        g.fillRoundRect(shopX, shopY, 60, 80, 8, 8);
        g.drawImage(card.getIcon(), shopX + 5, shopY + 5, 50, 50, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString(card.getCost() + " sol", shopX + 10, shopY + 70);
        shopX += 70;
    }
}
```

---

## 11. Módulo: Dificultad y Oleadas

### Configuración de dificultad — objeto de configuración

```java
public class DifficultyConfig {
    public final int maxWaves;
    public final int zombiesPerWave;
    public final double spawnInterval;
    public final double zombieSpeedMult;

    public static DifficultyConfig EASY   = new DifficultyConfig(3,  5,  800, 0.7);
    public static DifficultyConfig NORMAL = new DifficultyConfig(5,  8,  600, 1.0);
    public static DifficultyConfig HARD   = new DifficultyConfig(8, 12,  400, 1.4);

    public DifficultyConfig(int waves, int zombies, int interval, double speed) {
        this.maxWaves = waves;
        this.zombiesPerWave = zombies;
        this.spawnInterval = interval;
        this.zombieSpeedMult = speed;
    }
}
```

**Patrón objeto-configuración:** en lugar de pasar 4 parámetros sueltos al GamePanel,
los agrupamos en un objeto. Facilita añadir nuevas opciones sin cambiar la firma del constructor.

### Spawner de zombies

```java
private void spawnZombies() {
    zombieSpawnTimer++;

    if (waveNumber >= config.maxWaves) return;
    if (zombieSpawnTimer < config.spawnInterval) return;

    zombieSpawnTimer = 0;
    waveNumber++;

    for (int i = 0; i < config.zombiesPerWave; i++) {
        int row = (int)(Math.random() * 5);
        Zombie z = createZombieForWave(waveNumber, row);
        map.getAllZombies().add(z);
    }
}

// Método fábrica — decide qué tipo de zombie crear según la oleada
private Zombie createZombieForWave(int wave, int row) {
    if (wave <= 2) return new NormalZombie(row);
    if (wave <= 4) return Math.random() < 0.5
        ? new NormalZombie(row) : new ConeheadZombie(row);
    if (wave <= 6) return new ConeheadZombie(row);
    return new BucketheadZombie(row);
}
```

**Patrón Factory Method:** `createZombieForWave` decide qué subclase instanciar.
El resto del código solo ve `Zombie`, sin preocuparse del tipo concreto.

### Cortacésped de emergencia

```java
public class LawnMower {
    private int row;
    private double x;
    private boolean activated = false;
    private static final double MOWER_SPEED = 8.0;

    public LawnMower(int row) {
        this.row = row;
        this.x = Map.BOARD_X - 50;
    }

    public void update(Map map) {
        if (!activated) {
            for (Zombie z : map.getAllZombies()) {
                if (z.getRow() == row && z.getX() <= Map.BOARD_X + 10) {
                    activated = true;
                    break;
                }
            }
        }

        if (activated) {
            x += MOWER_SPEED;
            map.getAllZombies().removeIf(
                z -> z.getRow() == row && z.getX() <= x + 40
            );
        }
    }
}
```

---

## 12. Flujo Completo del Juego

```
                    ┌─────────────┐
                    │  Inicio     │
                    │  (Main.java)│
                    └──────┬──────┘
                           │ crea JFrame + MenuPanel
                    ┌──────▼──────┐
                    │  MenuPanel  │
                    │  Selección  │
                    │  dificultad │
                    └──────┬──────┘
                           │ click "Jugar"
                    ┌──────▼──────────────┐
                    │    GamePanel        │
                    │  (Timer 16ms activo)│
                    └──────┬──────────────┘
                           │
          ┌────────────────┼────────────────┐
          │                │                │
   ┌──────▼──────┐  ┌──────▼──────┐  ┌─────▼───────┐
   │ MouseEvent  │  │  update()   │  │ paintComp() │
   │ (click)     │  │  cada 16ms  │  │ cada 16ms   │
   └──────┬──────┘  └──────┬──────┘  └─────────────┘
          │                │
          │         ┌──────▼──────────────────────┐
          │         │ 1. Caer sol del cielo        │
          │         │ 2. Actualizar plantas        │
          │         │ 3. Mover proyectiles         │
          │         │ 4. Mover zombies             │
          │         │ 5. Detectar colisiones       │
          │         │ 6. Spawnear oleada           │
          │         │ 7. Chequear fin de juego     │
          │         └──────────────────────────────┘
          │
   ┌──────▼──────────────────────────────────┐
   │ Recoger sol → map.sunPoints += 25       │
   │ Colocar planta → map.allPlants.add()    │
   └──────┬──────────────────────────────────┘
          │
          ┌────────────┴────────────┐
          │                         │
   ┌──────▼──────┐           ┌──────▼──────┐
   │  GAME OVER  │           │   VICTORIA  │
   │  (zombie    │           │  (oleadas   │
   │   llegó)    │           │   superadas)│
   └─────────────┘           └─────────────┘
```

---

## 13. Compilación y Ejecución

### Desde la línea de comandos

```bash
# Compilar todos los .java
javac -d bin src/**/*.java

# Ejecutar
java -cp bin Main

# Crear JAR ejecutable
jar cfe PlantsVsZombies.jar Main -C bin .

# Ejecutar el JAR
java -jar PlantsVsZombies.jar
```

### Desde Eclipse

1. `File → Import → Existing Projects into Workspace`
2. Seleccionar la carpeta del proyecto
3. Click derecho en `Main.java` → `Run As → Java Application`

### Desde IntelliJ IDEA

1. `File → Open` → seleccionar carpeta del proyecto
2. Marcar `src` como **Sources Root** (click derecho → Mark Directory as)
3. Ejecutar `Main.java` con el botón ▶

---

## 14. Puntos de Escalado del Proyecto

Una vez que el juego básico está terminado, estos son los **vectores de mejora** naturales,
ordenados de menor a mayor complejidad:

### Nivel 1 — Ampliación de contenido (sin cambios estructurales)

| Mejora | Cómo hacerla | Concepto reforzado |
|--------|-------------|-------------------|
| Nuevas plantas | Crear subclase de `Plant`, implementar `update` y `draw` | Herencia |
| Nuevos zombies | Crear subclase de `Zombie` con distintos stats | Herencia |
| Más niveles | Crear nuevos `DifficultyConfig` o ficheros de configuración | Objeto-Config |
| Animaciones de muerte | Añadir estado `dying` con frame específico | Máquina de estados |

### Nivel 2 — Mejoras de jugabilidad

| Mejora | Cómo hacerla | Concepto reforzado |
|--------|-------------|-------------------|
| Modo noche | Flag `boolean nightMode` en `Map`, sin sol del cielo | Estado global |
| Tabla de puntuaciones en memoria | `List<ScoreEntry>` ordenada en `Map` | Colecciones, Comparable |
| Pausa del juego | `gameTimer.stop()` / `gameTimer.start()` | Control de Timer |
| Guardar partida en fichero | Serializar `Map` con `ObjectOutputStream` | Java I/O |

```java
// Ejemplo: guardar estado en fichero
public void saveGame(Map map) throws IOException {
    try (ObjectOutputStream oos = new ObjectOutputStream(
            new FileOutputStream("savegame.dat"))) {
        oos.writeObject(map); // Map debe implementar Serializable
    }
}

public Map loadGame() throws IOException, ClassNotFoundException {
    try (ObjectInputStream ois = new ObjectInputStream(
            new FileInputStream("savegame.dat"))) {
        return (Map) ois.readObject();
    }
}
```

### Nivel 3 — Refactoring arquitectónico

| Mejora | Descripción | Concepto reforzado |
|--------|-------------|-------------------|
| Patrón Observer real | `GamePanel` suscrito a eventos del `Map` en lugar de polling | Patrones de diseño |
| Sistema de eventos | Cola de eventos (`EventBus`) para desacoplar módulos | Colas, Productor-Consumidor |
| Migración a JavaFX | `AnimationTimer` en lugar de `javax.swing.Timer`, `Canvas` y `GraphicsContext` | JavaFX |
| Hilos separados para lógica y render | `Thread` propio para el game loop + `SwingUtilities.invokeLater` | Concurrencia real |

```java
// Game Loop con Thread propio (alternativa al Timer)
public class GameLoop implements Runnable {
    private static final long TARGET_FPS = 60;
    private static final long FRAME_TIME = 1000 / TARGET_FPS;
    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            long start = System.currentTimeMillis();
            update();
            SwingUtilities.invokeLater(() -> panel.repaint()); // seguro para Swing
            long elapsed = System.currentTimeMillis() - start;
            long sleep = FRAME_TIME - elapsed;
            if (sleep > 0) {
                try { Thread.sleep(sleep); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            }
        }
    }
}
```

---

## 15. Introducción de Base de Datos

Cuando el proyecto está terminado, el paso natural es **persistir datos** más allá del ciclo
de vida de la aplicación. Aquí es donde entra una base de datos.

### ¿Qué datos tiene sentido guardar?

| Dato | Descripción | Tabla sugerida |
|------|-------------|---------------|
| Jugadores | Nombre, fecha de registro | `jugadores` |
| Puntuaciones | Jugador, puntos, dificultad, fecha | `puntuaciones` |
| Partidas guardadas | Estado del juego serializado o en columnas | `partidas` |
| Estadísticas | Zombies eliminados, plantas usadas por partida | `estadisticas` |

### Opción A — SQLite con JDBC (más sencilla, sin servidor)

SQLite es una base de datos en un solo fichero. Perfecta para proyectos de escritorio.

```java
// Añadir el driver al classpath: sqlite-jdbc-x.x.x.jar
import java.sql.*;

public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:pvz_scores.db";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public void createTables() {
        String sql = """
            CREATE TABLE IF NOT EXISTS jugadores (
                id      INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre  TEXT NOT NULL,
                fecha   TEXT NOT NULL
            );
            CREATE TABLE IF NOT EXISTS puntuaciones (
                id           INTEGER PRIMARY KEY AUTOINCREMENT,
                jugador_id   INTEGER NOT NULL,
                puntos       INTEGER NOT NULL,
                dificultad   TEXT NOT NULL,
                fecha        TEXT NOT NULL,
                FOREIGN KEY (jugador_id) REFERENCES jugadores(id)
            );
        """;
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

### Opción B — MySQL/MariaDB (con servidor, más realista para aplicaciones multiusuario)

```java
// Driver: mysql-connector-java-x.x.x.jar
private static final String DB_URL  = "jdbc:mysql://localhost:3306/pvz";
private static final String DB_USER = "root";
private static final String DB_PASS = "1234";

public Connection connect() throws SQLException {
    return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
}
```

El código de operaciones (INSERT, SELECT…) es **idéntico** independientemente del motor.
JDBC abstrae las diferencias entre bases de datos.

### CRUD de puntuaciones

```java
public class ScoreDAO {  // DAO = Data Access Object

    // CREATE — guardar nueva puntuación
    public void saveScore(int jugadorId, int puntos, String dificultad) {
        String sql = "INSERT INTO puntuaciones (jugador_id, puntos, dificultad, fecha) " +
                     "VALUES (?, ?, ?, ?)";
        try (Connection conn = db.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, jugadorId);
            ps.setInt(2, puntos);
            ps.setString(3, dificultad);
            ps.setString(4, LocalDate.now().toString());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ — top 10 puntuaciones
    public List<String> getTopScores() {
        List<String> results = new ArrayList<>();
        String sql = """
            SELECT j.nombre, p.puntos, p.dificultad, p.fecha
            FROM puntuaciones p
            JOIN jugadores j ON j.id = p.jugador_id
            ORDER BY p.puntos DESC
            LIMIT 10
        """;
        try (Connection conn = db.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                results.add(rs.getString("nombre") + " — " +
                            rs.getInt("puntos") + " pts (" +
                            rs.getString("dificultad") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}
```

**PreparedStatement vs Statement:**
Siempre usar `PreparedStatement` cuando hay datos del usuario. Previene **SQL Injection**:

```java
// PELIGROSO — nunca hacer esto:
stmt.executeQuery("SELECT * FROM jugadores WHERE nombre = '" + nombre + "'");

// SEGURO — el driver escapa los parámetros:
ps = conn.prepareStatement("SELECT * FROM jugadores WHERE nombre = ?");
ps.setString(1, nombre);
```

### Integración con el juego — flujo completo con BBDD

```
Inicio
  └─ Pantalla de login/registro
       └─ Buscar jugador en BBDD (SELECT)
            ├─ Existe → cargar su historial
            └─ Nuevo  → INSERT en jugadores

Partida en curso
  └─ Al terminar (victoria o game over)
       └─ Calcular puntuación final
            └─ INSERT en puntuaciones

Pantalla de ranking
  └─ SELECT top 10 con JOIN
       └─ Mostrar en JTable o panel Swing
```

### Mostrar el ranking en Swing con JTable

```java
public JPanel buildRankingPanel(ScoreDAO dao) {
    List<String[]> rows = dao.getTopScoresAsRows(); // [nombre, puntos, dificultad, fecha]
    String[] columns = {"Jugador", "Puntos", "Dificultad", "Fecha"};

    DefaultTableModel model = new DefaultTableModel(
        rows.toArray(new String[0][]), columns
    );
    JTable table = new JTable(model);
    table.setEnabled(false); // solo lectura

    JPanel panel = new JPanel(new BorderLayout());
    panel.add(new JLabel("TOP 10", SwingConstants.CENTER), BorderLayout.NORTH);
    panel.add(new JScrollPane(table), BorderLayout.CENTER);
    return panel;
}
```

### Resumen de tecnologías de BBDD según escenario

| Escenario | Tecnología recomendada | Por qué |
|-----------|------------------------|---------|
| Proyecto local, un solo jugador | **SQLite + JDBC** | Sin instalación de servidor |
| Proyecto de clase, varios equipos | **MySQL/MariaDB + JDBC** | Servidor compartido, más realista |
| Aplicación web futura | **PostgreSQL + JPA/Hibernate** | ORM elimina SQL manual |
| Datos no estructurados | **MongoDB** | Documentos JSON, flexible |

---

## Resumen de Clases Clave

| Clase | Responsabilidad |
|-------|-----------------|
| `Main.java` | Punto de entrada, crea el JFrame |
| `Map.java` | Estado global del juego (todas las listas) |
| `GamePanel.java` | Game Loop, render, manejo de input |
| `Plant.java` | Clase base abstracta de plantas |
| `Zombie.java` | Clase base abstracta de zombies |
| `Projectile.java` | Guisantes y proyectiles |
| `Sun.java` | Tokens de sol recolectables |
| `LawnMower.java` | Defensa de emergencia por carril |
| `MusicPlayer.java` | Reproducción de audio |
| `GameImages.java` | Carga y caché de sprites |
| `DifficultyConfig.java` | Parámetros de dificultad por nivel |
| `ScoreDAO.java` | (escalado) Acceso a BBDD para puntuaciones |
| `DatabaseManager.java` | (escalado) Conexión y creación de tablas |

---

*Guía de refuerzo de programación — Plants vs Zombies en Java con Swing*
