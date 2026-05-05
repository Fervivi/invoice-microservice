package cl.duoc.invoice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import cl.duoc.invoice.dto.request.InvoiceRequestDto;
import cl.duoc.invoice.dto.response.InvoiceResponseDto;
import cl.duoc.invoice.service.InvoiceService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<InvoiceResponseDto> createInvoice(@RequestBody InvoiceRequestDto request) {
        InvoiceResponseDto response = invoiceService.createInvoice(request);
        return ResponseEntity.ok(response);
    }
    



}
