package cl.duoc.invoice.service;

import org.springframework.stereotype.Service;

import cl.duoc.invoice.dto.request.InvoiceRequestDto;
import cl.duoc.invoice.dto.response.InvoiceResponseDto;
import cl.duoc.invoice.model.Invoice;
import cl.duoc.invoice.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceResponseDto createInvoice(InvoiceRequestDto request){

         Invoice invoice = new Invoice();

        invoice.setFecha(request.getFecha());
        invoice.setFolio(request.getFolio());
        invoice.setRazonSocialReceptor(request.getRazonSocialReceptor());
        invoice.setGiroReceptor(request.getGiroReceptor());
        invoice.setDirecionReceptor(request.getDirecionReceptor());
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
        response.setDirecionReceptor(savedInvoice.getDirecionReceptor());
        response.setRutReceptor(savedInvoice.getRutReceptor());
        response.setRazonSocialEmisor(savedInvoice.getRazonSocialEmisor());
        response.setGiroEmisor(savedInvoice.getGiroEmisor());
        response.setDireccionEmisor(savedInvoice.getDireccionEmisor());
        response.setRutEmisor(savedInvoice.getRutEmisor());

        return response;



    }

    





}
