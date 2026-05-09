/*
 * Copyright © 2026 DuocUC FullStack 1
 * Eduardo Bray
 * Rodrigo Callealta
 * Fernando Villalobos
 */
package cl.duoc.invoice.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false, unique = true)
    private Long folio;

    // datos receptor (cliente)
    @Column(nullable = false)
    private String razonSocialReceptor;

    @Column(nullable = false)
    private String giroReceptor;

    @Column(nullable = false)
    private String direccionReceptor;

    @Column(nullable = false)
    private String rutReceptor;

    // datos emisor(proveedor)
    @Column(nullable = false)
    private String razonSocialEmisor;

    @Column(nullable = false)
    private String giroEmisor;

    @Column(nullable = false)
    private String direccionEmisor;

    @Column(nullable = false)
    private String rutEmisor;

    @Column(nullable = false)
    private BigDecimal montoNeto;

    @Column(nullable = false)
    private BigDecimal iva;

    @Column(nullable = false)
    private BigDecimal montoTotal;

    @OneToMany(mappedBy = "invoiceModel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<InvoiceItemModel> items = new ArrayList<>();
}
