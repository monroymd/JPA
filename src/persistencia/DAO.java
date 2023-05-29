package persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DAO<T> {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPALibreriaPU");
    private EntityManager em = emf.createEntityManager();
    
    
    protected void conectarBD(){
        if (!em.isOpen()) {
            em = emf.createEntityManager();
        }
    }
    
    protected void desconcectarBD(){
        if (em.isOpen()) {
            em.close();
        }
    }
    
    protected void guardarBD(T objeto){
        conectarBD();
        em.getTransaction().begin();
        em.persist(objeto);
        em.getTransaction().commit();
        desconcectarBD();
    }
    
    protected void editarBD(T objeto){
        conectarBD();
        em.getTransaction().begin();
        em.merge(objeto);
        em.getTransaction().commit();
        desconcectarBD();
    }
    protected void borrarBD(T objeto){
        conectarBD();
        em.getTransaction().begin();
        em.remove(objeto);
        em.getTransaction().commit();
        desconcectarBD();
    }
    
    
}
