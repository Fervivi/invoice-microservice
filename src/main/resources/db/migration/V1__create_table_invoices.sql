CREATE TABLE invoices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE NOT NULL,
    folio BIGINT NOT NULL UNIQUE,

    razon_social_receptor VARCHAR(255) NOT NULL,
    giro_receptor VARCHAR(255) NOT NULL,
    direccion_receptor VARCHAR(255) NOT NULL,
    rut_receptor VARCHAR(20) NOT NULL,

    razon_social_emisor VARCHAR(255) NOT NULL,
    giro_emisor VARCHAR(255) NOT NULL,
    direccion_emisor VARCHAR(255) NOT NULL,
    rut_emisor VARCHAR(20) NOT NULL,

    monto_neto DECIMAL(19, 2) NOT NULL,
    iva DECIMAL(19, 2) NOT NULL,
    monto_total DECIMAL(19, 2) NOT NULL
);

CREATE TABLE invoice_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cantidad INT NOT NULL,
    nombre_producto VARCHAR(255) NOT NULL,
    precio_unitario DECIMAL(19, 2) NOT NULL,
    subtotal DECIMAL(19, 2) NOT NULL,
    invoice_id BIGINT NOT NULL,

    CONSTRAINT fk_invoice_items_invoice
        FOREIGN KEY (invoice_id)
        REFERENCES invoices(id)
        ON DELETE CASCADE
);