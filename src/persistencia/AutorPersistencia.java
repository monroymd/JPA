package persistencia;

import entidades.Autor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AutorPersistencia extends DAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public Autor buscarAutorPorNombre(String nombre) {
        try {
            conectarBD();
            em.getTransaction().begin();
            Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
            em.getTransaction().commit();
            desconcectarBD();
            return autor;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public void guardarAutorPorNombre(String nombre) {
        Autor autor = new Autor();
        try {
            conectarBD();
            autor.setNombre(nombre);
            guardarBD(autor);
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public void editarAutor(String nombreAnterior, String nuevoNombre) {
        try {
            Autor autor = buscarAutorPorNombre(nombreAnterior);
            conectarBD();
            em.getTransaction().begin();
            autor.setNombre(nuevoNombre);
            editarBD(autor);
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public void borrarAutor(Autor autor) {
        try {
            conectarBD();
            em.getTransaction().begin();
            em.remove(autor);
            em.getTransaction().commit();
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }
    
    public void borrarAutorporId(String id) {

        try {
            conectarBD();
            Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id = :id")
                    .setParameter("id", id).getSingleResult();
            borrarAutor(autor);
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }
    
    public void borrarAutorPorNombre(String nombre) {

        try {
            Autor autor = buscarAutorPorNombre(nombre);
            borrarAutor(autor);
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }
    
    public Collection<Autor> listaAutorPorNombre(String nombre){
        Collection<Autor> lista = new ArrayList();
        try {
            conectarBD();
            lista = em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre")
                    .setParameter("nombre", nombre).getResultList();
            desconcectarBD();
            for (Autor aux : lista) {
                System.out.println("Autor: " + aux.getNombre() + " Id: " + aux.getId());
            }
            return lista;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }
        
    public void borrarListaAutorPorNombre(String nombre){
        try {
            Collection<Autor> lista = listaAutorPorNombre(nombre);
            conectarBD();
            for (Autor aux : lista) {
                em.getTransaction().begin();
                em.remove(aux);
                em.getTransaction().commit();
            }
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
            
    
                
            
    }
    
}
    
        
