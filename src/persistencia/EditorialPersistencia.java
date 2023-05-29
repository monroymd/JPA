package persistencia;

import entidades.Editorial;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EditorialPersistencia extends DAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALibreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void crearEditorial(String nombre) {

        try {
            Editorial ed = new Editorial(nombre);
            guardarBD(ed);
        } catch (Exception e) {
            throw e;
        }

    }

    public Editorial buscarEditorialPorId(String id) {

        try {
            conectarBD();
            Editorial ed = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.id = :id")
                    .setParameter("id", id).getSingleResult();
            desconcectarBD();
            return ed;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }

    }

    public Editorial buscarEditorialPorNombre(String nombre) {

        try {
            conectarBD();
            Editorial ed = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
            desconcectarBD();
            return ed;
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }

    }

    public void editarEditorialPorNombre(String nombreAnterior, String nombreNuevo) {

        try {
            Editorial ed = buscarEditorialPorNombre(nombreAnterior);
            ed.setNombre(nombreNuevo);
            editarBD(ed);
        } catch (Exception e) {
            throw e;
        }

    }

    public void borrarEditorial(String nombre) {
        try {
            borrarBD(buscarEditorialPorNombre(nombre));
        } catch (Exception e) {
            throw e;
        }

    }

}
