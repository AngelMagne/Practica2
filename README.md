PRACTICA_2

Este repositorio contiene un sistema CRUD desarrollado con Spring Boot para la gestión de información universitaria. El sistema permite administrar estudiantes, materias, inscripciones y usuarios, facilitando la gestión académica y administrativa de una universidad.

Objetivo
Desarrollar una aplicación web robusta que permita realizar operaciones de Crear, Leer, Actualizar y Eliminar (CRUD) sobre los datos principales de una universidad, con control de acceso basado en roles y autenticación segura.

Tecnologías utilizadas
Java 21
Spring Boot 
Spring Data JPA
Spring Security
Spring Validation
PostgreSQL
Redis (para caché)
Maven
⚙️ Configuración principal (application.properties)
Algunas de las configuraciones más relevantes del archivo application.properties son:

# Puerto de la aplicación
server.port=8082
Nota: Asegúrate de modificar el usuario y la contraseña de la base de datos según tu entorno.

👤 Roles de usuario y acceso a endpoints
El sistema utiliza control de acceso basado en roles, definidos en la configuración de seguridad. Los roles principales son:

ADMIN: Acceso total a la administración del sistema.
ESTUDIANTE: Acceso a materias e inscripciones propias.
DOCENTE: Acceso a información de estudiantes y materias.
Acceso a endpoints según roles
Públicos (sin autenticación):

/api/auth/** — Endpoints de autenticación (login, registro, etc.)
Solo ADMIN:

/api/admin/** — Funcionalidades administrativas avanzadas
ADMIN, ESTUDIANTE y DOCENTE:

/api/materias/** — Consultar y gestionar materias
ADMIN y ESTUDIANTE:

/api/inscripciones/** — Consultar y gestionar inscripciones
ADMIN y DOCENTE:

/api/estudiantes/** — Consultar y gestionar información de estudiantes
El sistema utiliza JWT para la autenticación y todas las rutas protegidas requieren un token válido en la cabecera de la solicitud.
