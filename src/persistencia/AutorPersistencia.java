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
            Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
            desconcectarBD();
            return autor;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public Autor buscarAutorPorId(String id) {
        try {
            conectarBD();
            Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.id :id")
                    .setParameter("id", id).getSingleResult();
            desconcectarBD();
            return autor;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public void guardarAutorPorNombre(String nombre) {
        Autor autor = new Autor(nombre);
        try {
            guardarBD(autor);
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
    }

    public void editarAutor(String nombreAnterior, String nuevoNombre) {
        try {
            Autor autor = buscarAutorPorNombre(nombreAnterior);
            autor.setNombre(nuevoNombre);
            editarBD(autor);
        } catch (Exception e) {
            throw e;
        }
    }

    public void borrarAutorporId(String id) {

        try {
            borrarBD(buscarAutorPorId(id));
        } catch (Exception e) {
            throw e;
        }
    }

    public void borrarAutorPorNombre(String nombre) {

        try {
            borrarBD(buscarAutorPorNombre(nombre));
        } catch (Exception e) {
            throw e;
        }
    }

    public ArrayList<Autor> listaAutorPorNombre(String nombre) {
        try {
            conectarBD();
            ArrayList<Autor> lista = (ArrayList<Autor>) em.createQuery("SELECT a FROM Autor a WHERE a.nombre = :nombre")
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

    public void borrarListaAutorPorNombre(String nombre) {
        try {
            ArrayList<Autor> lista = listaAutorPorNombre(nombre);
            for (Autor aux : lista) {
                borrarBD(aux);
            }
        } catch (Exception e) {
            throw e;
        }

    }

}
