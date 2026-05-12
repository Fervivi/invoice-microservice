INSERT INTO invoices (
    fecha,
    folio,
    razon_social_receptor,
    giro_receptor,
    direccion_receptor,
    rut_receptor,
    razon_social_emisor,
    giro_emisor,
    direccion_emisor,
    rut_emisor,
    monto_neto,
    iva,
    monto_total
) VALUES
(
    '2026-05-12',
    1,
    'Comercial Los Andes SpA',
    'Venta de productos tecnológicos',
    'Av. Providencia 1234, Santiago',
    '76.123.456-7',
    'Invoice Company SpA',
    'Servicios informáticos',
    'Av. Apoquindo 4567, Las Condes',
    '77.987.654-3',
    150000,
    28500,
    178500
),
(
    '2026-05-12',
    2,
    'Distribuidora Sur Ltda',
    'Distribución de insumos',
    'Camino El Alba 900, Santiago',
    '78.555.444-1',
    'Invoice Company SpA',
    'Servicios informáticos',
    'Av. Apoquindo 4567, Las Condes',
    '77.987.654-3',
    80000,
    15200,
    95200
);

INSERT INTO invoice_items (
    cantidad,
    nombre_producto,
    precio_unitario,
    subtotal,
    invoice_id
) VALUES
(
    2,
    'Monitor LED 24 pulgadas',
    75000,
    150000,
    1
),
(
    1,
    'Teclado mecánico',
    45000,
    45000,
    2
),
(
    1,
    'Mouse inalámbrico',
    35000,
    35000,
    2
);