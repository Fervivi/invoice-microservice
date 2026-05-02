package cl.duoc.invoice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cl.duoc.invoice.model.Invoice;


public interface InvoceRepository extends JpaRepository<Invoice, Long>{

}
