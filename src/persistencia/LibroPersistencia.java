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

        Libro libro = new Libro();
        Editorial editorial = new Editorial();
        editorial.setNombre(ed);
        Autor au = new AutorPersistencia().buscarAutorPorNombre(autor);

        try {
            conectarBD();
            em.getTransaction().begin();
            libro.setTitulo(nombre);
            libro.setEditorial(editorial);
            libro.setAutor(au);
            em.getTransaction().commit();
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }

    }

    public Libro buscarLibroPorTitulo(String titulo) {
        try {
            conectarBD();
            em.getTransaction().begin();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo")
                    .setParameter("titulo", titulo).getSingleResult();
            em.getTransaction().commit();
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
            em.getTransaction().begin();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn")
                    .setParameter("isbn", isbn).getSingleResult();
            em.getTransaction().commit();
            desconcectarBD();
            return libro;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public void borrarLibroPorTitulo(String titulo) {
        try {
            Libro libro = buscarLibroPorTitulo(titulo);
            conectarBD();
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }

    }

    public void borrarLibroPorIsbn(String isbn) {
        try {
            Libro libro = buscarLibroPorIsbn(isbn);
            conectarBD();
            em.getTransaction().begin();
            em.remove(libro);
            em.getTransaction().commit();
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }

    }

    public void editarLibroPorTitulo(String tituloAnterior, String tituloNuevo) throws Exception {
        try {
            Libro libro = buscarLibroPorTitulo(tituloAnterior);
            editarBD(libro);
        } catch (Exception e) {
            throw new Exception("el titulo del libro no existe");
        }

    }

    public void editarLibroPorIsbn(String isbnAnterior, String isbnNuevo) throws Exception {
        try {
            Libro libro = buscarLibroPorIsbn(isbnAnterior);
            editarBD(libro);
        } catch (Exception e) {
            throw new Exception("el titulo del libro no existe");
        }

    }
    
    public Collection <Libro> buscarLibroPorAutor(String nombre){
        Collection<Libro> listaLibros = new ArrayList();
        try {
            conectarBD();
            listaLibros = em.createQuery("SELECT l FROM Libro l WHERE l.autor = :nombre")
                    .setParameter("nombre", nombre).getResultList();
            return listaLibros;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }
    
    public Collection <Libro> buscarLibroPorEditorial(String nombre){
        Collection<Libro> listaLibros = new ArrayList();
        try {
            conectarBD();
            listaLibros = em.createQuery("SELECT l FROM Libro l WHERE l.editorial = :nombre")
                    .setParameter("nombre", nombre).getResultList();
            return listaLibros;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
        
    }

}
