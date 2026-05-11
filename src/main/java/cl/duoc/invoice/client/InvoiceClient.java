/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.invoice.client;

import cl.duoc.invoice.dto.response.InvoiceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import 

@Component
@RequiredArgsConstructor
public class InvoiceClient {

    private final WebClient webClient;

    public InvoiceResponseDto getInvoiceId(Integer id) {
        return webClient
                .get()
                .uri("/api/v1/invoices/{id}", id)
                .retrieve()
                .onStatus(
                        status -> status.value() == 404,
                        response -> response -> Mono.error(new RuntimeException("Invoice not found with id: ")))
                .bodyToMono(InvoiceResponseDto.class)
                .block();
    }
}
