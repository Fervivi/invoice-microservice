/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.invoice.service;

import cl.duoc.invoice.dto.request.InvoiceRequestDto;
import cl.duoc.invoice.dto.response.InvoiceResponseDto;
import cl.duoc.invoice.model.Invoice;
import cl.duoc.invoice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceResponseDto createInvoice(InvoiceRequestDto request) {

        Long nextFolio = invoiceRepository
                .findTopByOrderByFolioDesc()
                .map(lastInvoice -> lastInvoice.getFolio() + 1)
                .orElse(1L);

        Invoice invoice = new Invoice();

        invoice.setFolio(nextFolio);

        invoice.setFecha(request.getFecha());
        invoice.setRazonSocialReceptor(request.getRazonSocialReceptor());
        invoice.setGiroReceptor(request.getGiroReceptor());
        invoice.setDireccionReceptor(request.getDireccionReceptor());
        invoice.setRutReceptor(request.getRutReceptor());
        invoice.setRazonSocialEmisor(request.getRazonSocialEmisor());
        invoice.setGiroEmisor(request.getGiroEmisor());
        invoice.setDireccionEmisor(request.getDireccionEmisor());
        invoice.setRutEmisor(request.getRutEmisor());

        Invoice savedInvoice = invoiceRepository.save(invoice);

        InvoiceResponseDto response = new InvoiceResponseDto();

        response.setFecha(savedInvoice.getFecha());
        response.setFolio(savedInvoice.getFolio());
        response.setRazonSocialReceptor(savedInvoice.getRazonSocialReceptor());
        response.setGiroReceptor(savedInvoice.getGiroReceptor());
        response.setDireccionReceptor(savedInvoice.getDireccionReceptor());
        response.setRutReceptor(savedInvoice.getRutReceptor());
        response.setRazonSocialEmisor(savedInvoice.getRazonSocialEmisor());
        response.setGiroEmisor(savedInvoice.getGiroEmisor());
        response.setDireccionEmisor(savedInvoice.getDireccionEmisor());
        response.setRutEmisor(savedInvoice.getRutEmisor());

        return response;
    }
}
