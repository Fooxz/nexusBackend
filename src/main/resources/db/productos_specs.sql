ALTER TABLE productos
    ADD COLUMN IF NOT EXISTS specs JSONB DEFAULT '{}'::jsonb;
