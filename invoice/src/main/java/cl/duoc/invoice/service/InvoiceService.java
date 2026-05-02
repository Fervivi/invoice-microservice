package cl.duoc.invoice.service;


import java.time.LocalDate;

import org.springframework.stereotype.Service;

import cl.duoc.invoice.dto.response.InvoiceResponseDto;
import cl.duoc.invoice.model.Invoice;
import cl.duoc.invoice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    private InvoiceResponseDto mapToInvoiceResponseDto(Invoice invoice){

        InvoiceResponseDto invoiceResponseDto = new InvoiceResponseDto();

        invoiceResponseDto.setFecha(invoice.getFecha());
        invoiceResponseDto.setFolio(invoice.getFolio());
        invoiceResponseDto.setRazonSocialReceptor(invoice.getRazonSocialReceptor());
        invoiceResponseDto.setGiroReceptor(invoice.getGiroReceptor());
        invoiceResponseDto.setDirecionReceptor(invoice.getDirecionReceptor());
        invoiceResponseDto.setRutReceptor(invoice.getRutReceptor());
        invoiceResponseDto.setRazonSocialEmisor(invoice.getRazonSocialEmisor());
        invoiceResponseDto.setGiroEmisor(invoice.getGiroReceptor());
        invoiceResponseDto.setDireccionEmisor(invoice.getDireccionEmisor());
        invoiceResponseDto.setRutEmisor(invoice.getRutEmisor());

        return invoiceResponseDto;





    }





}
