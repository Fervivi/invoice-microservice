package cl.duoc.invoice.model;

import java.time.LocalDate;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Invoice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate fecha;
    private String folio;
    //datos receptor (cliente)
    private String razonSocialReceptor;
    private String giroReceptor;
    private String direcionReceptor;
    private String rutReceptor;
    // datos emisor(proveedor)
    private String razonSocialEmisor;
    private String giroEmisor;
    private String direccionEmisor;
    private String rutEmisor;







}
