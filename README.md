Título: AluraBooks - Aplicación de gestión de libros con API de Gutendex

Descripción:

AluraBooks es una aplicación de gestión de libros que utiliza la API de Gutendex para obtener información sobre libros clásicos. La aplicación permite a los usuarios buscar libros por título, autor, idioma y otros criterios, y también proporciona información detallada sobre cada libro, incluyendo su título, autor, fecha de publicación, género, etc.

La aplicación utiliza una arquitectura de microservicios, con un servicio de API que se encarga de obtener los datos de la API de Gutendex y un servicio de negocio que se encarga de procesar y almacenar los datos en una base de datos. La aplicación también utiliza una interfaz de usuario basada en consola para interactuar con los usuarios.

Características:


    Búsqueda de libros por título, autor, idioma y otros criterios

    Información detallada sobre cada libro, incluyendo título, autor, fecha de publicación, género, etc.

    Almacenamiento de datos en una base de datos

    Interfaz de usuario basada en consola

    Utiliza la API de Gutendex para obtener información sobre libros clásicos


Tecnologías utilizadas:


    Java 17

    Spring Boot

    Spring Data JPA

    Hibernate

    API de Gutendex

    JSON

    ![image](https://github.com/user-attachments/assets/74914a69-3838-4ec1-aea5-ac170dad545d)

![image](https://github.com/user-attachments/assets/f19f4315-8ff7-47b0-bdd6-a44b8cb5d5b8)

![image](https://github.com/user-attachments/assets/ef0d44c4-8ff7-48c5-a69c-89f60fae2470)

Un problema a NOTAR que Autores que tiene DIFERENTE ID y se asume que son diferentes autores pero tiene diferentes libro , diferentes EDICIONES

El problema de REPETIR autores se defe que en la API que se usa algunos autores tiene DIFERENTES ID :
![image](https://github.com/user-attachments/assets/81992443-d929-4e2c-800a-ea81c661fd8b)

![image](https://github.com/user-attachments/assets/1dc669e0-0fba-41c3-b6d1-3d5a2334655f)

![image](https://github.com/user-attachments/assets/f1b9d871-4125-4835-8abd-bc109908f2e9)

![image](https://github.com/user-attachments/assets/a05cd15f-a606-4722-ac01-39fcb43a7c8a)

