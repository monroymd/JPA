package persistencia;

import entidades.Autor;
import entidades.Editorial;
import entidades.Libro;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class LibroPersistencia extends DAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void crearLibro(String nombre, String ed, String autor) {

        Editorial editorial = new Editorial(ed);
        Autor au = new AutorPersistencia().buscarAutorPorNombre(autor);
        Libro libro = new Libro(nombre, au, editorial);
        
        try {
            guardarBD(libro);
        } catch (Exception e) {
            throw e;
        }

    }

    public Libro buscarLibroPorTitulo(String titulo) {
        try {
            conectarBD();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo")
                    .setParameter("titulo", titulo).getSingleResult();
            desconcectarBD();
            return libro;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public Libro buscarLibroPorIsbn(String isbn) {

        try {
            conectarBD();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn")
                    .setParameter("isbn", isbn).getSingleResult();
            desconcectarBD();
            return libro;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public void borrarLibroPorTitulo(String titulo) {
        try {
            borrarBD(buscarLibroPorTitulo(titulo));
        } catch (Exception e) {
            throw e;
        }

    }

    public void borrarLibroPorIsbn(String isbn) {
        try {
            borrarBD(buscarLibroPorIsbn(isbn));
        } catch (Exception e) {
            throw e;
        }

    }
    
    public void borrarListaLibroPorEditorial(String nombre) {
        try {
            ArrayList <Libro> lista = buscarLibroPorEditorial(nombre);
            
            for (Libro aux : lista) {
                borrarBD(aux);
            }            
        } catch (Exception e) {
            throw e;
        }

    }

    public void editarLibroPorTitulo(String tituloAnterior, String tituloNuevo) throws Exception {
        try {
            Libro libro = buscarLibroPorTitulo(tituloAnterior);
            libro.setTitulo(tituloNuevo);
            editarBD(libro);
        } catch (Exception e) {
            throw new Exception("el titulo del libro no existe");
        }

    }

    public void editarLibroPorIsbn(String isbnAnterior, String isbnNuevo) throws Exception {
        try {
            Libro libro = buscarLibroPorIsbn(isbnAnterior);
            libro.setIsbn(isbnNuevo);
            editarBD(libro);
        } catch (Exception e) {
            throw new Exception("el titulo del libro no existe");
        }

    }
    
    public ArrayList <Libro> buscarLibroPorAutor(String nombre){
        try {
            conectarBD();
            ArrayList <Libro> listaLibros = (ArrayList <Libro>) em.createQuery("SELECT l FROM Libro l WHERE l.autor = :nombre")
                    .setParameter("nombre", nombre).getResultList();
            return listaLibros;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }
    
    public ArrayList <Libro> buscarLibroPorEditorial(String nombre){
        try {
            conectarBD();
            ArrayList <Libro> listaLibros = (ArrayList <Libro>) em.createQuery("SELECT l FROM Libro l WHERE l.editorial = :nombre")
                    .setParameter("nombre", nombre).getResultList();
            return listaLibros;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
        
    }

}
