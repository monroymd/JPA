package persistencia;

import entidades.Editorial;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EditorialPersistencia extends DAO {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALibreriaPU");
    private final EntityManager em = emf.createEntityManager();
    
    public void crearEditorial(String nombre){
        
        try {
            Editorial ed = new Editorial();
            ed.setNombre(nombre);
            conectarBD();
            em.getTransaction().begin();
            guardarBD(ed);
            em.getTransaction().commit();
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
        
    }
    
    public String traerIdEditorial (String nombre){
        
        try {
            conectarBD();
            em.getTransaction().begin();
            Editorial ed = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
                    .setParameter("nombre", nombre).getSingleResult();
            em.getTransaction().commit();
            return ed.getId();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
        
    }
    
    public void editarEditorial (String nombreAnterior, String nombreNuevo){
        
        try {
            Editorial ed = new Editorial();
            conectarBD();
            ed = (Editorial) em.createQuery("SELECT e FROM Editorial e WHERE e.nombre = :nombre")
                    .setParameter("nombre", nombreAnterior).getSingleResult();
            ed.setNombre(nombreNuevo);
            desconcectarBD();
            editarBD(ed);
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
        
    }
    
    public void borrarEditorial(String nombre){
        try {
            conectarBD();
            em.getTransaction().begin();
            em.remove(nombre);
            em.getTransaction().commit();
            desconcectarBD();
        } catch (Exception e) {
            desconcectarBD();
            throw e;
        }
        
        
    }
    
    
    

}
