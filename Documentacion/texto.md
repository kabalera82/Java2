# Tabla de notificaciones community

---

## 1. Creamos la tabla de notificaciones en Prisma ✅ (YA HECHO)

// ℹ️ℹ️ℹ️ Tipos de notificaciones posibles

```prisma
enum notification_type {
  NEW_COMMENT               // Nuevo comentario en tu post
  NEW_REPLY                 // Nueva respuesta a tu comentario
  NEW_REACTION_POST         // Nueva reacción a tu post
  NEW_REACTION_COMMENT      // Nueva reacción a tu comentario
  MODERATION_ALERT          // Alerta de moderación
  
  @@schema("community")
}
```

// ℹ️ℹ️ℹ️ Modelo para las notificaciones

**Ruta:** `prisma/schema.prisma`

```prisma
model notifications {
  id               String            @id @default(dbgenerated("gen_random_uuid()")) @db.Uuid
  recipient_id     String            @db.Uuid
  actor_id         String?           @db.Uuid
  type             notification_type
  
  // 👉 Referencias opcionales dependiendo del tipo de notificación 👈
  post_id          String?           @db.Uuid
  comment_id       String?           @db.Uuid
  
  is_read          Boolean           @default(false)
  created_at       DateTime          @default(now()) @db.Timestamptz(6)

  // 👉 Relaciones 👈
  profiles_recipient profiles          @relation("notifications_recipient", fields: [recipient_id], references: [id], onDelete: Cascade, onUpdate: NoAction)
  profiles_actor     profiles?         @relation("notifications_actor", fields: [actor_id], references: [id], onDelete: SetNull, onUpdate: NoAction)
  posts              posts?            @relation(fields: [post_id], references: [id], onDelete: Cascade, onUpdate: NoAction)
  comments           comments?         @relation(fields: [comment_id], references: [id], onDelete: Cascade, onUpdate: NoAction)

  @@index([recipient_id, is_read, created_at(sort: Desc)], map: "notifications_recipient_read_idx")
  @@schema("community")
}
```

// ℹ️ℹ️ℹ️ Relaciones inversas añadidas:

**En `profiles`:**
```prisma
notifications_received notifications[] @relation("notifications_recipient")
notifications_sent     notifications[] @relation("notifications_actor")
```

**En `posts`:**
```prisma
notifications notifications[]
```

**En `comments`:**
```prisma
notifications notifications[]
```

**Ejecutar migración:**
```bash
npx prisma db push
npx prisma generate
```

---

## 2. Creación de las notificaciones (Backend)

### 2.1. Interceptar la creación de comentarios

**Ruta:** `src/app/api/community/comment/route.ts`

### 2.2. Lógica de "Nuevo Comentario en Post"
Si el comentario **no tiene** `parent_comment_id`:
- Buscamos el `author_id` del post → ese es el `recipient_id`
- NOTA: 👉 si el autor del post es el mismo que está comentando **no creamos notificación**
- Creamos la notificación con el type 👉: `NEW_COMMENT`

### 2.3. Nueva Respuesta al comentario
Si el comentario **tiene** `parent_comment_id`:
- Buscamos el `author_id` del comentario padre → ese es el `recipient_id`
- MISMA NOTA 👉: si el autor del comentario padre es el mismo que está respondiendo **no creamos notificación**
- Creamos la notificación con el type 👉: `NEW_REPLY`

### 📋 Código COMPLETO del archivo `route.ts` después de los cambios:

```typescript
import { NextResponse } from "next/server";
import { createSupabaseClientRoute } from "@/lib/supabase/server";
import { supabaseAdmin } from "@/lib/supabase/supabaseAdmin";

export async function POST(req: Request) {
  const body = await req.json().catch(() => null);
  const postId = String(body?.postId ?? "");
  const text = String(body?.body ?? "").trim();
  const parentCommentId = body?.parentCommentId ? String(body.parentCommentId) : null; // 👈 NUEVO: extraemos parentCommentId del body
  const assetIds = Array.isArray(body?.assetIds) ? body.assetIds.map(String) : [];

  if (!postId || !text) {
    return NextResponse.json({ error: "Invalid payload" }, { status: 400 });
  }

  // ✅ sesión supabase (usuario logueado)
  const supabase = await createSupabaseClientRoute();
  const { data: { user }, error: authErr } = await supabase.auth.getUser();

  if (authErr || !user) {
    return NextResponse.json({ error: "Unauthorised" }, { status: 401 });
  }

  // ✅ insert comment
  const { data, error } = await supabaseAdmin
    .schema("community")
    .from("comments")
    .insert({
      post_id: postId,
      author_id: user.id,
      body: text,
      parent_comment_id: parentCommentId, // 👈 CAMBIO: antes era null hardcodeado, ahora usa la variable
    })
    .select("id,post_id,author_id,parent_comment_id,depth,body,status,deleted_at,reaction_count,created_at,updated_at")
    .single();

  if (error) {
    return NextResponse.json({ error: error.message }, { status: 500 });
  }

  // ✅ NOTIFICACIONES (NUEVO BLOQUE)
  try {
    if (parentCommentId) {
      // 👉 Es una RESPUESTA a un comentario: notificar al autor del comentario padre
      const { data: parentComment } = await supabaseAdmin
        .schema("community")
        .from("comments")
        .select("author_id")
        .eq("id", parentCommentId)
        .single();

      if (parentComment?.author_id && parentComment.author_id !== user.id) {
        await supabaseAdmin.schema("community").from("notifications").insert({
          recipient_id: parentComment.author_id,
          actor_id: user.id,
          type: "NEW_REPLY",
          post_id: postId,
          comment_id: data.id,
        });
      }
    } else {
      // 👉 Es un COMENTARIO nuevo en un post: notificar al autor del post
      const { data: post } = await supabaseAdmin
        .schema("community")
        .from("posts")
        .select("author_id")
        .eq("id", postId)
        .single();

      if (post?.author_id && post.author_id !== user.id) {
        await supabaseAdmin.schema("community").from("notifications").insert({
          recipient_id: post.author_id,
          actor_id: user.id,
          type: "NEW_COMMENT",
          post_id: postId,
          comment_id: data.id,
        });
      }
    }
  } catch (err) {
    console.error("Error creating notification:", err);
    // No bloqueamos la respuesta si falla la notificación
  }

  // ✅ Link assets if present
  if (assetIds.length > 0) {
    const assetLinks = assetIds.map((id: string, index: number) => ({
      comment_id: data.id,
      asset_id: id,
      sort_order: index
    }));

    const { error: assetErr } = await supabaseAdmin
      .schema("community")
      .from("comment_assets")
      .insert(assetLinks);

    if (assetErr) {
      console.error("Error linking assets to comment:", assetErr);
    }
  }

  return NextResponse.json({ comment: data });
}
```

---

## 3. Frontend: Visualización de las notificaciones

### 3.1. Creamos el Server Component para el Badge

**Ruta:** `src/components/layout/NotificationsBadge.tsx` (ARCHIVO NUEVO)

- Consulta con `createSupabaseClientRSC` (Server Component, igual que en `community/(sidebar)/layout.tsx`)
- Icono de campana de `lucide-react` (que ya tenéis instalado)
- Si count > 0 → badge rojo; si count = 0 → sin badge

```tsx
import { createSupabaseClientRSC } from "@/lib/supabase/server";
import { Bell } from "lucide-react";
import Link from "next/link";

export default async function NotificationsBadge() {
  const supabase = await createSupabaseClientRSC();
  const { data: { user } } = await supabase.auth.getUser();

  if (!user) return null;

  // Contamos las notificaciones no leídas
  const { count } = await (supabase as any)
    .schema("community")
    .from("notifications")
    .select("*", { count: "exact", head: true })
    .eq("recipient_id", user.id)
    .eq("is_read", false);

  const notificationCount = count || 0;

  return (
    <Link
      href="/community/notifications"
      className="relative flex items-center justify-center w-10 h-10 text-gray-400 hover:text-primary transition-all duration-300"
    >
      <Bell size={20} className="drop-shadow-[0_0_8px_rgba(0,255,213,0.3)]" />
      {notificationCount > 0 && (
        <span className="absolute top-2 right-2 flex h-4 w-4 items-center justify-center rounded-full bg-red-500 text-[10px] font-bold text-white animate-pulse shadow-[0_0_10px_#ef4444]">
          {notificationCount > 9 ? "+9" : notificationCount}
        </span>
      )}
    </Link>
  );
}
```

---

## 4. Integración en el Header

> ⚠️ **IMPORTANTE:** `Header.tsx` es un Client Component (`'use client'`).
> `NotificationsBadge` es un Server Component (`async function`).
> **No se puede importar un Server Component dentro de un Client Component directamente.**
> La solución: pasar el badge como `React.ReactNode` a través de props desde el layout (Server) → AppShell → Header.

### 4.1. Modificar `Header.tsx`

**Ruta:** `src/components/layout/Header.tsx`

**Cambio 1 — Línea 14:** Añadir la prop `notifications`
```diff
-export default function Header() {
+export default function Header({ notifications }: { notifications?: React.ReactNode }) {
```

**Cambio 2 — Línea 128-131 (Desktop):** Insertar `{notifications}` antes de `<ProfileDropdown>`
```diff
           {t("layout.header.join")}
          </Link>

+          {/* 🔔 Notificaciones */}
+          {notifications}
+
           {/* 🔹 Perfil */}
           <ProfileDropdown onLogout={handleLogout} />
```

**Cambio 3 — Línea 186-188 (Mobile):** Insertar `{notifications}` antes de `<ProfileDropdown>`
```diff
+            <div className="pt-2">
+              {notifications}
+            </div>
+
             <div className="pt-2">
               <ProfileDropdown onLogout={handleLogout} />
             </div>
```

---

### 4.2. Modificar `AppShell.tsx`

**Ruta:** `src/components/layout/AppShell.tsx`

**Cambio 1 — Props:** Añadir `notifications`
```diff
 export default function AppShell({
   children,
+  notifications,
   HeaderComponent,
   hideHeader,
   hideFooter,
 }: {
   children: ReactNode;
+  notifications?: ReactNode;
   HeaderComponent?: ComponentType<any>;
   hideHeader?: boolean;
   hideFooter?: boolean;
 }) {
```

**Cambio 2 — Línea 31:** Pasar `notifications` al Header
```diff
-      {!hideHeader && <HeaderToUse />}
+      {!hideHeader && <HeaderToUse notifications={notifications} />}
```

---

### 4.3. Modificar `layout.tsx` (RootLayout)

**Ruta:** `src/app/layout.tsx`

**Cambio 1 — Imports:** Añadir el import del badge
```diff
 import AppShell from "@/components/layout/AppShell";
+import NotificationsBadge from "@/components/layout/NotificationsBadge";
```

**Cambio 2 — Línea 75:** Pasar `notifications` al `<AppShell>`
```diff
-          <AppShell>{children}</AppShell>
+          <AppShell notifications={<NotificationsBadge />}>{children}</AppShell>
```

---

## 5. 🔐 Notas finales: Supabase, RLS y cómo dejar todo listo

### 5.1. Crear el tipo ENUM en Supabase

`prisma db push` crea la tabla y las columnas pero **a veces el enum `notification_type` no se crea correctamente en el schema `community`**. Si al insertar recibes un error tipo `invalid input value for enum`, ejecuta esto manualmente en el **SQL Editor de Supabase**:

```sql
-- Crear el enum en el schema community (si prisma db push no lo hizo)
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'notification_type') THEN
    CREATE TYPE community.notification_type AS ENUM (
      'NEW_COMMENT',
      'NEW_REPLY',
      'NEW_REACTION_POST',
      'NEW_REACTION_COMMENT',
      'MODERATION_ALERT'
    );
  END IF;
END $$;
```

### 5.2. Row Level Security (RLS)

> ⚠️ **MUY IMPORTANTE:** En Supabase, si RLS está activado en una tabla y no hay políticas, **NADIE puede leer ni escribir** (excepto `supabaseAdmin` que usa la service_role key y bypasea RLS).

**¿Qué significa para nosotros?**
- El **INSERT** desde `route.ts` usa `supabaseAdmin` → **no le afecta RLS** ✅
- El **SELECT** desde `NotificationsBadge.tsx` usa `createSupabaseClientRSC` (anon key) → **SÍ le afecta RLS** ⚠️

**Tienes 2 opciones:**

**Opción A: Habilitar RLS y crear políticas (recomendado para producción)**

Ejecuta en el **SQL Editor de Supabase**:

```sql
-- 1. Activar RLS en la tabla notifications
ALTER TABLE community.notifications ENABLE ROW LEVEL SECURITY;

-- 2. SELECT: Un usuario solo puede ver SUS propias notificaciones
CREATE POLICY "Users can view own notifications"
  ON community.notifications
  FOR SELECT
  USING (recipient_id = auth.uid());

-- 3. UPDATE: Un usuario solo puede marcar como leídas SUS notificaciones
CREATE POLICY "Users can mark own notifications as read"
  ON community.notifications
  FOR UPDATE
  USING (recipient_id = auth.uid())
  WITH CHECK (recipient_id = auth.uid());

-- 4. INSERT: Solo el service_role (supabaseAdmin) puede insertar
--    No hace falta política porque supabaseAdmin bypasea RLS.
--    Si en el futuro quieres insertar desde el cliente, añade:
-- CREATE POLICY "Service role can insert notifications"
--   ON community.notifications
--   FOR INSERT
--   WITH CHECK (true);

-- 5. DELETE: Un usuario puede borrar SUS notificaciones (opcional)
CREATE POLICY "Users can delete own notifications"
  ON community.notifications
  FOR DELETE
  USING (recipient_id = auth.uid());
```

**Opción B: Desactivar RLS (más rápido para desarrollo)**

```sql
ALTER TABLE community.notifications DISABLE ROW LEVEL SECURITY;
```

> 👉 Recomendación: usa la **Opción B** para probar que todo funciona, y luego activa la **Opción A** antes de subir a producción.

### 5.3. ✅ Checklist final para dejar todo listo

```
[ ] 1. Prisma schema actualizado (enum + model + relaciones inversas)
[ ] 2. npx prisma db push (crea la tabla en Supabase)
[ ] 3. npx prisma generate (regenera el cliente)
[ ] 4. Verificar en Supabase Dashboard → Table Editor → schema "community" → que existe la tabla "notifications"
[ ] 5. Ejecutar SQL de RLS (Opción A o B)
[ ] 6. Verificar en Supabase Dashboard → Authentication → Policies → que aparecen las políticas de "notifications"
[ ] 7. Modificar route.ts (backend de comentarios)
[ ] 8. Crear NotificationsBadge.tsx (nuevo archivo)
[ ] 9. Modificar Header.tsx (añadir prop notifications)
[ ] 10. Modificar AppShell.tsx (pasar notifications)
[ ] 11. Modificar layout.tsx (inyectar <NotificationsBadge />)
[ ] 12. Probar: crear un comentario con un usuario → verificar que aparece la notificación en la tabla
[ ] 13. Probar: entrar con el otro usuario → verificar que aparece el badge rojo en el header
```
