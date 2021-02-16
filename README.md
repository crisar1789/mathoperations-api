# mathoperations-api
Api rest para calcular operaciones matemáticas

# Build and run api
Desplegar en Docker:
1. Ejecutar el comando -mvn clean install- para generar jar
2. Ejecutar el comando -docker build -t mathoperations-api .- dentro de la carpeta del proyecto para constrir la imagen
3. Ejecutar el comando -docker run -p 9095:9095 -t mathoperations-api- para correr la app

# Uso de la api
Con una herramienta para consumo de servicio rest, adicionar el endpoint http:ip:9095/mathoperations/api/v1/operation y asignar los datos en formato json para cada operación

# Tecnologías, herramientas y librerías utilizadas
- Spring Boot
- H2 Database
- Docker 19.03.1
- Maven 3.6.3
- io.swagger
- javax.validation

# Atributos de calidad
Para esta api, los atributos de calidad más importantes son la seguridad y el rendimiento

# Elasticidad y escalabilidad
Se podría utilizar la herramienta Kubernetes para aprovicionar más ijmágenes de la api en caso de ser necesario, con propiedades especificadas en el archivo application.yml, para poner un mínimo de réplicas de la api 