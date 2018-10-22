## README.me

### Arquitecta de software
> [Xinyu.Li](https://github.com/L-Xinyu)  

## Tecnologías necesarias
* Java
* Maven
* GitHub

## Diseño de entidades
![articulos-architecture-diagram](https://github.com/L-Xinyu/APAW-ECP2-XinyuLi/blob/develop/UML-XinyuLi.png)

### Responsabilidades
#### Dispatcher
* Centraliza las peticiones y hace de repartidor
* Recupera los datos de la petición y los pasa como parámetros de método
* Captura las excepciones y las convierte en errores Http
#### restControllers
* Define el path del recurso
* Valida la entrada
* Traspasa la petición a los controladores de la capa de negocio
#### businessControllers
* Procesa la petición, apoyándose en los DAO’s
* Crea las entidades a partir de los DTO’s
* Gestiona la respuesta a partir de las entidades. Delega en los DTO’s la creación a partir de la entidad
#### daos
* Gestionan la BD
#### entities
* Son las entidades persistentes en la BD

## API
### POST /temas
#### Parámetros del cuerpo
- `name`: String (**requerido**)
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
---
### PUT /temas/{id}
#### Parámetros del cuerpo
- `name`: String (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
--- 
### POST /comentarios
#### Parámetros del cuerpo
- `negative`: Boolean (**requerido**)
- `description`: String (**requerido**)
- `date`: LocalDateTime
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
---
### POST /articulos
#### Parámetros del cuerpo
- `name`: String (**requerido**)
- `category`: Category
- `date`: LacalDteTime
#### Respuesta
- 200 OK 
  - `id`: String
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /articulos
#### Respuesta
- 200 OK 
  - `[ {id:String,name:String} ]`
---
### DELETE /articulos/{id}
#### Respuesta
- 200 OK 
---
### POST /articulos/{id}/escritor
#### Parámetros del cuerpo
- `escritor`: Integer (**requerido**)
#### Respuesta
- 200 OK 
- 403 BAD_REQUEST
- 404 NOT_FOUND
---
### GET /articulos/{id}/average
#### Respuesta
- 200 OK 
  - `average`: Double
- 404 NOT_FOUND
---
### GET /articulos/search?q=average:>=3
#### Respuesta
- 200 OK
  - `[ {id:String,name:String} ]`
- 403 BAD_REQUEST
---
