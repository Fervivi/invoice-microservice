package cl.duoc.invoice.model;

import java.time.LocalDate;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String folio;

    //datos receptor (cliente)
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







}
