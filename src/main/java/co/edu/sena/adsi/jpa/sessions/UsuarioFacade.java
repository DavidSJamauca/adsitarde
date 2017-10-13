package co.edu.sena.adsi.jpa.sessions;

import co.edu.sena.adsi.jpa.entities.Usuario;
import co.edu.sena.adsi.jpa.entities.Usuario_;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author David Jamauca
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "co.edu.sena_AdsiTarde_war_1.0-SNAPSHOT")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

   public Usuario findUsuarioByEmail(String email){
       CriteriaBuilder cb= getEntityManager().getCriteriaBuilder();
       CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
       Root<Usuario> usuario = cq.from(Usuario.class);
       cq.where(cb.equal(usuario.get(Usuario_.email),email));
       TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
       try{
           return (Usuario) q.getSingleResult();
           
       } catch (NonUniqueResultException ex){
           throw ex;
       } catch (NoResultException ex){
           return null;
       }
   }
    
    public Usuario findUsuarioByNumDocumento(String numDocumento){
       CriteriaBuilder cb= getEntityManager().getCriteriaBuilder();
       CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
       Root<Usuario> usuario = cq.from(Usuario.class);
       cq.where(cb.equal(usuario.get(Usuario_.numDocumento),numDocumento));
       TypedQuery<Usuario> q = getEntityManager().createQuery(cq);
       try{
           return (Usuario) q.getSingleResult();
           
       } catch (NonUniqueResultException ex){
           throw ex;
       } catch (NoResultException ex){
           return null;
       }
   }
}
