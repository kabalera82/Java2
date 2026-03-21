
---

# 📝 Chuleta de Estilos: Markdown ➜ CSS

Esta tabla te servirá para saber exactamente qué etiqueta (`selector`) tienes que escribir en tu configuración de **Custom CSS** para cambiar el aspecto de tus apuntes.

### 1. Estructura y Títulos

| Elemento Markdown | Etiqueta HTML (Selector CSS) | Propósito Sugerido |
| --- | --- | --- |
| `# Título 1` | `h1` | Título principal del tema. |
| `## Título 2` | `h2` | Secciones importantes. |
| `### Título 3` | `h3` | Subsecciones. |
| `####` a `######` | `h4`, `h5`, `h6` | Niveles menores de jerarquía. |
| `Texto normal` | `p` | El cuerpo del texto (párrafos). |
| `---` | `hr` | Línea divisoria horizontal. |

### 2. Formato de Texto

| Elemento Markdown | Etiqueta HTML (Selector CSS) | Propósito Sugerido |
| --- | --- | --- |
| `**Negrita**` | `strong` | Resaltar conceptos clave (ej: **TCP**). |
| `*Cursiva*` | `em` | Énfasis suave o términos en otros idiomas. |
| `~~Tachado~~` | `del` | Errores o información obsoleta. |
| `==Resaltado==` | `mark` | Efecto de "subrayador" fluorescente. |
| `[Enlace](url)` | `a` | Cambiar color de los hipervínculos. |

### 3. Listas y Tareas

| Elemento Markdown | Etiqueta HTML (Selector CSS) | Propósito Sugerido |
| --- | --- | --- |
| `- Lista` | `ul` (lista) / `li` (ítem) | Listas de puntos (viñetas). |
| `1. Lista` | `ol` (lista) / `li` (ítem) | Procesos paso a paso. |
| `- [ ] Tarea` | `input[type="checkbox"]` | Checkboxes de tareas pendientes. |

### 4. Bloques Especiales

| Elemento Markdown | Etiqueta HTML (Selector CSS) | Propósito Sugerido |
| --- | --- | --- |
| `> Cita` | `blockquote` | Notas importantes, avisos o "OJO". |
| ``Código`` | `code` | Comandos o términos técnicos cortos. |
| ````Bloque```` | `pre code` | Fragmentos de código o configuración. |

### 5. Tablas (Estructura Completa)

| Elemento Markdown | Etiqueta HTML (Selector CSS) | Propósito Sugerido |
| --- | --- | --- |
| ` | Tabla | ` |
| ` | Cabecera | ` |
| ` | Celda | ` |

---

### 🎨 Plantilla CSS "Lista para usar"

Si quieres copiar y pegar un estilo equilibrado en tu IntelliJ, usa este bloque. Está optimizado para lectura técnica:

```css
/* Configuración General */
body { font-family: 'Segoe UI', sans-serif; color: #333; line-height: 1.5; }

/* Títulos: Color azul y línea decorativa */
h1, h2 { color: #2c3e50; border-bottom: 1px solid #eaecef; padding-bottom: 0.3em; }

/* Negritas: Color diferente para que salten a la vista */
strong { color: #d35400; font-weight: bold; }

/* Citas: Estilo de "Nota Informativa" */
blockquote { 
    margin: 1em 0; 
    padding: 10px 20px; 
    background: #f9f9f9; 
    border-left: 5px solid #3498db; 
    color: #555; 
}

/* Código: Fondo gris claro y letra monoespaciada */
code { 
    background-color: #f3f3f3; 
    padding: 2px 4px; 
    border-radius: 4px; 
    font-family: 'JetBrains Mono', monospace; 
}

/* Tablas: Bordes finos y color alterno en filas */
table { border-collapse: collapse; width: 100%; margin: 10px 0; }
th { background-color: #3498db; color: white; padding: 8px; }
td { border: 1px solid #ddd; padding: 8px; }
tr:nth-child(even) { background-color: #f2f2f2; }

```

---

