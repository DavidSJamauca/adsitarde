package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entities.Ciudad;
import co.edu.sena.adsi.jpa.entities.Ciudad_;
import co.edu.sena.adsi.jpa.entities.Departamento;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author David Jamauca
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {

    @PersistenceContext(unitName = "co.edu.sena_AdsiTarde_war_1.0-SNAPSHOT")
    private EntityManager em;
    private Object query;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
public CiudadFacade() {
        super(Ciudad.class);
    }

    public List<Ciudad> findCiudadByNombre(String nombreCiudad, Integer idDepartamento) {
        CriteriaBuilder cb= getEntityManager().getCriteriaBuilder();
       CriteriaQuery<Ciudad> cq = cb.createQuery(Ciudad.class);
       Root<Ciudad> ciudad = cq.from(Ciudad.class);
       Predicate restrictions = cb.conjunction();
       if(idDepartamento !=null){
          restrictions= cb.and(restrictions, cb.equal(ciudad.get(Ciudad_.departamento), new Departamento(idDepartamento))); 
       }
       if (query != null){
           restrictions= cb.and(restrictions, cb.like(ciudad.get(Ciudad_.nombre), "%" + query + "%")); 
       }
       cq.where(restrictions);
       cq.orderBy(cb.asc(ciudad.get(Ciudad_.nombre)));
       TypedQuery<Ciudad> q =em.createQuery(cq);
       try{
           return q.setMaxResults(10).getResultList();
        } catch (NoResultException ex){
           return null;
       }

   
    }  
}
