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
import cl.duoc.invoice.exception.ResourceNotFoundException;
import cl.duoc.invoice.model.InvoiceItemModel;
import cl.duoc.invoice.model.InvoiceModel;
import cl.duoc.invoice.repository.InvoiceRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceResponseDto createInvoice(InvoiceRequestDto request) {

        Long nextFolio = getNextFolio();

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

        List<InvoiceItemModel> items = request.getItems().stream()
                .map(itemRequest -> mapToItemModel(itemRequest, invoice))
                .collect(Collectors.toList());

        invoice.setItems(items);

        BigDecimal montoNeto = calculateMontoNeto(items);
        BigDecimal iva = montoNeto.multiply(new BigDecimal("0.19"));
        BigDecimal montoTotal = montoNeto.add(iva);

        invoice.setMontoNeto(montoNeto);
        invoice.setIva(iva);
        invoice.setMontoTotal(montoTotal);

        InvoiceModel savedInvoice = invoiceRepository.save(invoice);

        return mapToResponseDto(savedInvoice);
    }

    public List<InvoiceResponseDto> getAllInvoices() {
        return invoiceRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    public InvoiceResponseDto getInvoiceByFolio(Long folio) {
        InvoiceModel invoice = invoiceRepository
                .findByFolio(folio)
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con folio: " + folio));

        return mapToResponseDto(invoice);
    }

    private Long getNextFolio() {
        return invoiceRepository
                .findTopByOrderByFolioDesc()
                .map(invoice -> invoice.getFolio() + 1)
                .orElse(1L);
    }

    private InvoiceItemModel mapToItemModel(InvoiceItemRequestDto itemRequest, InvoiceModel invoice) {
        InvoiceItemModel item = new InvoiceItemModel();

        BigDecimal subtotal = itemRequest.getPrecioUnitario().multiply(BigDecimal.valueOf(itemRequest.getCantidad()));

        item.setCantidad(itemRequest.getCantidad());
        item.setNombreProducto(itemRequest.getNombreProducto());
        item.setPrecioUnitario(itemRequest.getPrecioUnitario());
        item.setSubtotal(subtotal);
        item.setInvoiceModel(invoice);

        return item;
    }

    private BigDecimal calculateMontoNeto(List<InvoiceItemModel> items) {
        return items.stream().map(InvoiceItemModel::getSubtotal).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private InvoiceResponseDto mapToResponseDto(InvoiceModel invoice) {
        InvoiceResponseDto response = new InvoiceResponseDto();

        response.setId(invoice.getId());
        response.setFolio(invoice.getFolio());
        response.setFecha(invoice.getFecha());

        response.setRazonSocialReceptor(invoice.getRazonSocialReceptor());
        response.setGiroReceptor(invoice.getGiroReceptor());
        response.setDireccionReceptor(invoice.getDireccionReceptor());
        response.setRutReceptor(invoice.getRutReceptor());

        response.setRazonSocialEmisor(invoice.getRazonSocialEmisor());
        response.setGiroEmisor(invoice.getGiroEmisor());
        response.setDireccionEmisor(invoice.getDireccionEmisor());
        response.setRutEmisor(invoice.getRutEmisor());

        response.setMontoNeto(invoice.getMontoNeto());
        response.setIva(invoice.getIva());
        response.setMontoTotal(invoice.getMontoTotal());

        List<InvoiceItemResponseDto> items =
                invoice.getItems().stream().map(this::mapToItemResponseDto).collect(Collectors.toList());

        response.setItems(items);

        return response;
    }

    private InvoiceItemResponseDto mapToItemResponseDto(InvoiceItemModel item) {
        InvoiceItemResponseDto itemResponse = new InvoiceItemResponseDto();

        itemResponse.setId(item.getId());
        itemResponse.setCantidad(item.getCantidad());
        itemResponse.setNombreProducto(item.getNombreProducto());
        itemResponse.setPrecioUnitario(item.getPrecioUnitario());
        itemResponse.setSubtotal(item.getSubtotal());

        return itemResponse;
    }
}
