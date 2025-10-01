# Sistema de Reservas

API REST para la gestión de turnos y reservas desarrollada con Spring Boot.

## Tecnologías


- **Oracle Database**
- **Maven**
- **Swagger/OpenAPI**

## Configuración

### Base de Datos
```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=SYSTEM
spring.datasource.password=D3v3l0pm3nt
```

### Ejecutar la aplicación

```bash
mvn spring-boot:run
```

## API Endpoints

### Generar Turnos
```http
POST /api/turnos/generar
```

**Request:**
```json
{
  "fechaInicio": "2025-10-02T00:00:00.000Z",
  "fechaFin": "2025-10-02T23:59:59.999Z",
  "idServicio": 1
}
```

**Response:**
```json
{
  "codigo": 0,
  "mensaje": "Turnos generados exitosamente",
  "turnos": [
    {
      "nombreServicio": "Corte de pelo",
      "nombreComercio": "Comercio A",
      "fecha": "2025-10-02",
      "horaInicio": "2025-10-02T08:00:00",
      "horaFin": "2025-10-02T09:00:00"
    }
  ]
}
```

## Documentación API

Swagger UI disponible en: `http://localhost:8080/swagger-ui.html`

## Pruebas

```bash
mvn test
```

## Estructura del Proyecto

```
src/
├── main/java/com/example/reserva/
│   ├── controller/     # Controladores REST
│   ├── dto/           # DTOs de request/response
│   ├── service/       # Lógica de negocio
│   └── config/        # Configuraciones
└── test/              # Pruebas unitarias
```
