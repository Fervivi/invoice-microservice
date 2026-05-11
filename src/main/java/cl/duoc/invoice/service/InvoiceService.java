/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.invoice.service;

import cl.duoc.invoice.dto.request.InvoiceItemRequestDto;
import cl.duoc.invoice.dto.request.InvoiceRequestDto;
import cl.duoc.invoice.dto.response.InvoiceItemResponseDto;
import cl.duoc.invoice.dto.response.InvoiceResponseDto;
import cl.duoc.invoice.model.InvoiceItemModel;
import cl.duoc.invoice.model.InvoiceModel;
import cl.duoc.invoice.repository.InvoiceRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceResponseDto createInvoice(InvoiceRequestDto request) {

        Long nextFolio = invoiceRepository
                .findTopByOrderByFolioDesc()
                .map(invoice -> invoice.getFolio() + 1)
                .orElse(1L);

        InvoiceModel invoice = new InvoiceModel();

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

        List<InvoiceItemModel> items = new ArrayList<>();
        BigDecimal montoNeto = BigDecimal.ZERO;

        for (InvoiceItemRequestDto itemRequest : request.getItems()) {

            InvoiceItemModel item = new InvoiceItemModel();

            item.setCantidad(itemRequest.getCantidad());
            item.setNombreProducto(itemRequest.getNombreProducto());
            item.setPrecioUnitario(itemRequest.getPrecioUnitario());

            BigDecimal subtotal =
                    itemRequest.getPrecioUnitario().multiply(BigDecimal.valueOf(itemRequest.getCantidad()));

            item.setSubtotal(subtotal);
            item.setInvoiceModel(invoice);

            items.add(item);
            montoNeto = montoNeto.add(subtotal);
        }

        invoice.setItems(items);

        BigDecimal iva = montoNeto.multiply(new BigDecimal("0.19"));
        BigDecimal montoTotal = montoNeto.add(iva);

        invoice.setMontoNeto(montoNeto);
        invoice.setIva(iva);
        invoice.setMontoTotal(montoTotal);

        InvoiceModel savedInvoice = invoiceRepository.save(invoice);

        InvoiceResponseDto response = new InvoiceResponseDto();

        response.setId(savedInvoice.getId());
        response.setFolio(savedInvoice.getFolio());
        response.setFecha(savedInvoice.getFecha());

        response.setRazonSocialReceptor(savedInvoice.getRazonSocialReceptor());
        response.setGiroReceptor(savedInvoice.getGiroReceptor());
        response.setDireccionReceptor(savedInvoice.getDireccionReceptor());
        response.setRutReceptor(savedInvoice.getRutReceptor());

        response.setRazonSocialEmisor(savedInvoice.getRazonSocialEmisor());
        response.setGiroEmisor(savedInvoice.getGiroEmisor());
        response.setDireccionEmisor(savedInvoice.getDireccionEmisor());
        response.setRutEmisor(savedInvoice.getRutEmisor());

        response.setMontoNeto(savedInvoice.getMontoNeto());
        response.setIva(savedInvoice.getIva());
        response.setMontoTotal(savedInvoice.getMontoTotal());

        List<InvoiceItemResponseDto> responseItems = new ArrayList<>();

        for (InvoiceItemModel item : savedInvoice.getItems()) {

            InvoiceItemResponseDto itemResponse = new InvoiceItemResponseDto();

            itemResponse.setId(item.getId());
            itemResponse.setCantidad(item.getCantidad());
            itemResponse.setNombreProducto(item.getNombreProducto());
            itemResponse.setPrecioUnitario(item.getPrecioUnitario());
            itemResponse.setSubtotal(item.getSubtotal());

            responseItems.add(itemResponse);
        }

        response.setItems(responseItems);

        return response;
    }

    public List<InvoiceResponseDto> getAllInvoices() {
        List<InvoiceModel> invoices = invoiceRepository.findAll();
        return invoices.stream()
                .map(invoice -> {
                    InvoiceResponseDto response = new InvoiceResponseDto();
                    response.setId(invoice.getId());
                    response.setFolio(invoice.getFolio());
                    response.setFecha(invoice.getFecha());
                    response.setRazonSocialReceptor(invoice.getRazonSocialReceptor());
                    response.setGiroReceptor(invoice.getGiroReceptor());
                    response.setDireccionReceptor(invoice.getDireccionReceptor());
                    response.setRutReceptor(invoice.getRutReceptor());
                    // emisor
                    response.setRazonSocialEmisor(invoice.getRazonSocialEmisor());
                    response.setGiroEmisor(invoice.getGiroEmisor());
                    response.setDireccionEmisor(invoice.getDireccionEmisor());
                    response.setRutEmisor(invoice.getRutEmisor());
                    return response;
                })
                .collect(Collectors.toList());
    }
}
