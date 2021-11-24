# Servicio de Marvel
Con el paso de los años la biblioteca marvel de comics, necesita tener actualizado
todo el listado de escritores, editores y coloristas de cómics que han estado
involucrados en las historias de los siguientes integrantes de los Vengadores (Iron
Man, Captain America). Así como todos los demás héroes que a través de cada
cómic han interactuado con cada uno de ellos. Esto hay que actualizarlo diario, ya
que hay que pagarles regalías respectivas a los escritores, editores y coloristas.

# Pre-requisitors de configuración del sistema
## 1. Clona este repositorio
Abre tu terminal y ejecuta el siguiente comando
````
git clone https://github.com/iroslehu/marvel.git
````
En tu directorio ``marvel`` se creara un directorio y scripts de ejecución

## 2. Instalar Java y Maven
Instala java 8 o la mas actual y Apache Maven 3.6.0 en tu sistema.
Este funcionara bien en java 9 a java 11.

## 3. Configuracion archivo de propiedades
Para la configuracion de la db se actualiza el archivo de propiedades con tus datos. 

### Base de datos MySql
Accesa a tu base de datos de MySql y crea la db `marvel`
````
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/marvel
spring.datasource.username=root
spring.datasource.password=admin
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
````

### Marvel Keys
Para la configuracion del acceso a el api de `marvel` actualiza los siguientes datos.
````
marvel.public.key=99130759403697245434e5bad8191c1478d5
marvel.private.key=a83ec3bbad92b237fdf4c13dfg5546dc57
marvel.url=http://gateway.marvel.com/v1/public/
````


## 4. Ejecuta Marvel Service
Ejecuta los siguientes Scripts
````
run bash ``assemble.sh`` script
````
Se creara el siguiente archivo ``marvel-service-0.0.1-SNAPSHOT.jar`` en el directorio ``target``. 
````
run bash ``avengers.sh`` script
````
La ejecucion del script anterior creara el servicio en el puerto 80

## Endpoints del servicio

| API                                                                                                        | Method | Api-gateway request                                 |
|------------------------------------------------------------------------------------------------------------|:-------|:----------------------------------------------------|
|Sincroniza todos los datos de mervel, obteniendo los characteres y comics de cada uno de ellos. (Este proceso tarda ya que por cada personaje se obtienen los datos de todos los comis y cradores del mismo)            |GET     |``http://localhost/marvel/suync``                    |
|Obtendremos los otros héroes con los cuales nuestro personaje ha interactuado en cada uno de los cómics.    |GET     |``http://localhost/marvel/characters/{super heroe}`` |   
|Obtendremos los editores, escritores y coloristas que han estado involucrados en los cómics del personaje.  |GET     |``http://localhost/marvel/colaborator/{super heroe}``| 
|Elimina los datos sincronizados.  |GET     |``http://localhost/marvel/delete``| 

# Copyright & License

MIT License.
