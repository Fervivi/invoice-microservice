/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.invoice.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequestDto {

    private LocalDate fecha;
    // datos receptor (cliente)
    private String razonSocialReceptor;
    private String giroReceptor;
    private String direccionReceptor;
    private String rutReceptor;
    // datos emisor(proveedor)
    private String razonSocialEmisor;
    private String giroEmisor;
    private String direccionEmisor;
    private String rutEmisor;
}
