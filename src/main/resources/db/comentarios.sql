CREATE TABLE IF NOT EXISTS comentarios (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    producto_id UUID NOT NULL REFERENCES productos(id) ON DELETE CASCADE,
    usuario_id  UUID NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    content     TEXT NOT NULL,
    parent_id   UUID REFERENCES comentarios(id) ON DELETE CASCADE,
    created_at  TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_comentarios_producto_id ON comentarios(producto_id);
CREATE INDEX IF NOT EXISTS idx_comentarios_parent_id ON comentarios(parent_id);
