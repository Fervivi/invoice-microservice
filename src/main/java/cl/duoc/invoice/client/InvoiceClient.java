/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.invoice.client;

import cl.duoc.invoice.dto.response.InvoiceResponseDto;
import cl.duoc.invoice.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class InvoiceClient {

    private final WebClient webClientInvoice;

    public InvoiceResponseDto getInvoiceByFolio(Long folio) {
        return webClientInvoice
                .get()
                .uri("/api/v1/invoices/{folio}", folio)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> Mono.error(new ResourceNotFoundException("Invoice not found with folio: " + folio)))
                .bodyToMono(InvoiceResponseDto.class)
                .block();
    }
}
