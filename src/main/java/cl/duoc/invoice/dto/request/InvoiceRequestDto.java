/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.invoice.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceRequestDto {
    @NotNull(message = "la fecha debe ser obligatoria")
    private LocalDate fecha;
    // datos receptor (cliente)
    @NotBlank(message = "La razón social del receptor es obligatoria")
    private String razonSocialReceptor;

    @NotBlank(message = "El giro del receptor es obligatorio")
    private String giroReceptor;

    @NotBlank(message = "La dirección del receptor es obligatoria")
    private String direccionReceptor;

    @NotBlank(message = "El RUT del receptor es obligatorio")
    private String rutReceptor;

    // datos emisor(proveedor)
    @NotBlank(message = "La razón social del emisor es obligatoria")
    private String razonSocialEmisor;

    @NotBlank(message = "El giro del emisor es obligatorio")
    private String giroEmisor;

    @NotBlank(message = "La dirección del emisor es obligatoria")
    private String direccionEmisor;

    @NotBlank(message = "El RUT del emisor es obligatorio")
    private String rutEmisor;

    @Valid
    @NotEmpty(message = "La factura debe tener al menos un producto")
    private List<InvoiceItemRequestDto> items;
}
