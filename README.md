rf#  Prueba Técnica - Esteban Sanabria

Este proyecto corresponde a una **prueba técnica de desarrollo backend** utilizando **Java con Spring Boot** y **Docker Compose**.  
El objetivo principal es implementar un conjunto de microservicios funcionales para la gestión de inventario y productos, siguiendo buenas prácticas de arquitectura, documentación y despliegue.

---

##  Tecnologías Utilizadas

- **Java 17**
- **Spring Boot 3.x**
  - Spring Web
  - Spring Data JPA
  - Spring Validation
- **PostgreSQL**
- **Docker & Docker Compose**
- **Maven**

---

##  Estructura del Proyecto

```bash
Prueba_Tecnica_EstebanSanabria/
│
├── inventario_service/        # Microservicio de Inventario
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
│
├── productos_service/         # Microservicio de Productos
│   ├── src/
│   ├── pom.xml
│   └── Dockerfile
│
├── docker-compose.yml         # Orquestación de servicios
├── README.md
└── .gitignore
