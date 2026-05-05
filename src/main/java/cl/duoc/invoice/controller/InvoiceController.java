package cl.duoc.invoice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.invoice.dto.request.InvoiceRequestDto;
import cl.duoc.invoice.dto.response.InvoiceResponseDto;
import cl.duoc.invoice.service.InvoiceService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

import java.net.ResponseCache;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1/invonce")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping
    public ResponseEntity<InvoiceResponseDto> getInvoice(@RequestBody InvoiceRequestDto request) {
        InvoiceResponseDto response = new invoiceService.mapToInvoiceResponseDto(request);
        return ResponseEntity.ok(response);
    }
    



}
