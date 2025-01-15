package com.example.LiteraturAlura.principal;

import com.example.LiteraturAlura.model.DatosLibro;
import com.example.LiteraturAlura.model.Libro;
import com.example.LiteraturAlura.model.Autor;
import com.example.LiteraturAlura.repository.BookRepository;
import com.example.LiteraturAlura.service.ConsumoAPI;
import com.example.LiteraturAlura.service.ConvierteDatos;
import com.example.LiteraturAlura.model.ListaDatosLibro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {
    private final Scanner teclado = new Scanner(System.in);
    private static final String URL_BASE = "https://gutendex.com/books/";
    private final BookRepository repositorio;
    private final ConsumoAPI consumoApi;
    private final ConvierteDatos conversor;


    @Autowired
    public Principal(BookRepository repositorio, ConsumoAPI consumoApi, ConvierteDatos conversor) {
        this.repositorio = repositorio;
        this.consumoApi = consumoApi;
        this.conversor = conversor;
    }

     public void muestraElMenu() {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println("""
                     *** MENÚ PRINCIPAL ***
                    1. Buscar libro por título
                    2. Listar todos los libros(en BASE DE DATOS)
                    3. Buscar libros por idioma
                    4. Listar todos los autores(en BASE DE DATOS)
                    5. Buscar autores vivos en un año
                    6. Listar autores (en BASE DE DATOS)
                    0. Salir
                     Seleccione una opción:
                    """);

            opcion = leerEntero("Elige una opción: ");
            
                switch (opcion) {
                    case 1 -> buscarLibroPorTitulo();
                    case 2 -> listarTodosLosLibros();
                    case 3 -> buscarLibrosPorIdioma();
                    case 4 -> listarTodosLosAutores();
                    case 5 -> buscarAutoresVivosEnAnio();
                     case 6 -> listarAutoresUnicos();
                    case 0 -> System.out.println("Cerrando la aplicación...");
                    default -> System.out.println("Opción inválida");
                }
            
        }
    }

   private void buscarLibroPorTitulo() {
        String titulo = leerLinea("Escribe el título del libro que deseas buscar: ");
        String url = URL_BASE + "?search=" + titulo.replace(" ", "%20");
         String json = consumoApi.obtenerDatos(url);

         if ("Documentation".equals(json)) {
              System.out.println("La API ha respondido con la documentación. Por favor, intenta de nuevo más tarde.");
             return;
         }
        ListaDatosLibro listaDatosLibro = conversor.obtenerDatos(json, ListaDatosLibro.class);

         if (listaDatosLibro == null || listaDatosLibro.results() == null || listaDatosLibro.results().isEmpty()) {
                DatosLibro datosLibro = conversor.obtenerDatos(json, DatosLibro.class);
                 
                Optional<Libro> libroExistente = repositorio.findById(datosLibro.id());
               if(libroExistente.isPresent()){
                   System.out.println("El libro con título " + datosLibro.title() + " ya se encuentra en la base de datos.");
                   return;
               }
                   Libro libro = new Libro(datosLibro);
                   repositorio.save(libro);
                   System.out.println("Libro: " + libro.getTitulo() + " ID: " + libro.getId());
                    System.out.println("Libro encontrado y guardado en la base de datos");
            }
            else{
                List<DatosLibro> resultados = listaDatosLibro.results();
                 for (DatosLibro datosLibro : resultados) {
                        Optional<Libro> libroExistente = repositorio.findById(datosLibro.id());
                        if(libroExistente.isPresent()){
                             System.out.println("El libro con título " + datosLibro.title() + " ya se encuentra en la base de datos.");
                            continue;
                        }
                        String shortenedTitle = datosLibro.title();
                         if(shortenedTitle.length() > 500){
                          shortenedTitle = shortenedTitle.substring(0, 500);
                        }
                         DatosLibro datosLibroShortened = new DatosLibro(datosLibro.id(), shortenedTitle, datosLibro.subjects(), datosLibro.authors(), datosLibro.translators(), datosLibro.bookshelves(), datosLibro.languages(), datosLibro.copyright(), datosLibro.mediaType(), datosLibro.formats(), datosLibro.downloadCount());
                         Libro libro = new Libro(datosLibroShortened);
                     repositorio.save(libro);
                   System.out.println("Libro: " + libro.getTitulo() + " ID: " + libro.getId());
                }
                 System.out.println("Libros encontrados y guardados en la base de datos");
            }

    }

    private void listarTodosLosLibros() {
        List<Libro> libros = repositorio.findAll().stream()
                   .sorted(Comparator.comparing(Libro::getTitulo))
                   .collect(Collectors.toList());

        if (libros.isEmpty()) {
            System.out.println("No hay libros en la base de datos.");
        } else {
             libros.forEach(l -> System.out.println("Libro: " + l.getTitulo() + " ID: " + l.getId()));
        }
    }

    private void buscarLibrosPorIdioma() {
        String idioma = leerLinea("Escribe el idioma de los libros que deseas buscar (ej: en, fr, es): ");
        String url = URL_BASE + "?languages=" + idioma;
          String json = consumoApi.obtenerDatos(url);
        if ("Documentation".equals(json)){
            System.out.println("La API ha respondido con la documentación. Por favor, intenta de nuevo más tarde.");
            return;
        }
        ListaDatosLibro listaDatosLibro = conversor.obtenerDatos(json, ListaDatosLibro.class);
          if (listaDatosLibro == null || listaDatosLibro.results() == null || listaDatosLibro.results().isEmpty()){
              DatosLibro datosLibro = conversor.obtenerDatos(json, DatosLibro.class);
              if(datosLibro!=null){
                 Optional<Libro> libroExistente = repositorio.findById(datosLibro.id());
                  if(libroExistente.isPresent()){
                     System.out.println("El libro con título " + datosLibro.title() + " ya se encuentra en la base de datos.");
                      return;
                   }
                     Libro libro = new Libro(datosLibro);
                  repositorio.save(libro);
                  System.out.println("Libro: " + libro.getTitulo() + " ID: " + libro.getId());
                 System.out.println("Libro encontrado y guardado en la base de datos");
               }
              else {
                  System.out.println("No se encontraron libros para el idioma: " + idioma);
             }
        } else{
           List<DatosLibro> resultados = listaDatosLibro.results();
             for (DatosLibro datosLibro : resultados) {
                 Optional<Libro> libroExistente = repositorio.findById(datosLibro.id());
                  if(libroExistente.isPresent()){
                       System.out.println("El libro con título " + datosLibro.title() + " ya se encuentra en la base de datos.");
                      continue;
                   }
                     Libro libro = new Libro(datosLibro);
                  repositorio.save(libro);
                     System.out.println("Libro: " + libro.getTitulo() + " ID: " + libro.getId());
            }
             System.out.println("Libros encontrados y guardados en la base de datos");
       }

    }

     private void listarTodosLosAutores() {
        List<Libro> todosLibros = repositorio.findAll();
         if (todosLibros.isEmpty()) {
            System.out.println("No hay libros registrados, por lo tanto no hay autores.");
            return;
        }

        todosLibros.stream()
                    .flatMap(libro -> libro.getAutores().stream())
                     .distinct()
                     .sorted(Comparator.comparing(Autor::getNombre))
                    .forEach(autor -> System.out.println("Autor: " + autor.getNombre() + ", ID: " + autor.getId()));

    }

    private void buscarAutoresVivosEnAnio() {
        int anio = leerEntero("Escribe el año que quieres buscar: ");
         String url = URL_BASE + "?author_year_start=" + anio;
         String json = consumoApi.obtenerDatos(url);
        if ("Documentation".equals(json)){
             System.out.println("La API ha respondido con la documentación. Por favor, intenta de nuevo más tarde.");
            return;
        }

         ListaDatosLibro listaDatosLibro = conversor.obtenerDatos(json, ListaDatosLibro.class);
        if(listaDatosLibro == null || listaDatosLibro.results() == null || listaDatosLibro.results().isEmpty()){
            DatosLibro datosLibro = conversor.obtenerDatos(json, DatosLibro.class);
             if (datosLibro!= null){
                 Optional<Libro> libroExistente = repositorio.findById(datosLibro.id());
                   if(libroExistente.isPresent()){
                       System.out.println("El libro con título " + datosLibro.title() + " ya se encuentra en la base de datos.");
                        return;
                    }
                 Libro libro = new Libro(datosLibro);
               repositorio.save(libro);
                System.out.println("Libro: " + libro.getTitulo() + " ID: " + libro.getId());
                System.out.println("Libro encontrado y guardado en la base de datos");
             }
            else{
                System.out.println("No se encontraron autores vivos en ese año");
            }
        }
         else {
            List<DatosLibro> resultados = listaDatosLibro.results();
             for (DatosLibro datosLibro : resultados) {
                    Optional<Libro> libroExistente = repositorio.findById(datosLibro.id());
                   if(libroExistente.isPresent()){
                        System.out.println("El libro con título " + datosLibro.title() + " ya se encuentra en la base de datos.");
                        continue;
                    }
                   Libro libro = new Libro(datosLibro);
                  repositorio.save(libro);
                  System.out.println("Libro: " + libro.getTitulo() + " ID: " + libro.getId());
             }
             System.out.println("Libros encontrados y guardados en la base de datos");
         }

    }
     private void listarAutoresUnicos() {
          List<Libro> allBooks = repositorio.findAll();
         if (allBooks.isEmpty()) {
             System.out.println("No hay libros registrados, por lo tanto no hay autores.");
              return;
         }
         Set<Autor> uniqueAuthors = new HashSet<>();
         Set<Long> uniqueAuthorIds = new HashSet<>();
         for (Libro book : allBooks) {
             if (book.getAutores() != null && !book.getAutores().isEmpty()) {
                 for (Autor author : book.getAutores()) {
                     if (uniqueAuthorIds.add(author.getId())) {
                         uniqueAuthors.add(author);
                     }
                 }
             }
         }
        uniqueAuthors.stream()
                .sorted(Comparator.comparing(Autor::getNombre))
                .forEach(author -> System.out.println("Autor: " + author.getNombre() + ", ID: " + author.getId()));
     }
    private String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return teclado.nextLine();
    }

    private int leerEntero(String mensaje) {
        System.out.print(mensaje);
        while (!teclado.hasNextInt()) {
            System.out.println("Entrada inválida. Introduce un número entero.");
            teclado.next();
            System.out.print(mensaje);
        }
        int numero = teclado.nextInt();
        teclado.nextLine();
        return numero;
    }
}