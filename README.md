# Invoice Microservice

## Descripción

Invoice Microservice es un microservicio desarrollado con Spring Boot enfocado en la gestión de facturas dentro de una arquitectura basada en microservicios.

Este servicio permite:

- Crear facturas
- Consultar facturas
- Gestionar productos asociados a una factura
- Aplicar reglas de negocio relacionadas al cálculo de montos
- Validar información de entrada
- Manejar excepciones centralizadamente
- Proteger endpoints mediante JWT
- Persistir información en MySQL utilizando JPA + Hibernate
- Ejecutar migraciones mediante Flyway
- Consumir otros microservicios mediante WebClient

---

# Funcionalidades principales

- Creación de facturas
- Consulta de facturas por folio
- Persistencia relacional con JPA
- Validaciones de datos
- Manejo centralizado de errores
- Seguridad JWT
- Comunicación entre microservicios
- Migraciones con Flyway

---

# Tecnologías utilizadas

- Java 25
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Spring Security
- JWT (java-jwt)
- Spring Validation
- Spring WebFlux (WebClient)
- Flyway
- MySQL
- Docker
- phpMyAdmin
- Lombok
- Maven
- Swagger / OpenAPI

---

# Dependencias principales

El proyecto utiliza las siguientes dependencias:

- spring-boot-starter-webmvc
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-validation
- spring-boot-starter-webflux
- spring-boot-starter-flyway
- mysql-connector-j
- java-jwt
- springdoc-openapi-starter-webmvc-ui
- lombok
- flyway-mysql

---

# Arquitectura del proyecto

El microservicio sigue el patrón CSR (Controller - Service - Repository).

```text
src/main/java/cl/duoc/invoice
│
├── client
├── config
├── controller
├── dto
│   ├── request
│   └── response
├── exception
├── model
├── repository
├── security
├── service
└── InvoiceApplication.java