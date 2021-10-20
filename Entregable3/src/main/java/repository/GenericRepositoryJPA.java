package repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

//https://www.arquitecturajava.com/java-generic-repository-y-jpa/
public class GenericRepositoryJPA<T> implements GenericRepository<T>{
 protected EntityManager em;
    private Class<T> type;
 
    public EntityManager getEntityManager() {
        return em;
    }
 
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.em = entityManager;
    }
 
    public GenericRepositoryJPA() {
    	//prepara mi clase <T> recibida del extends de la clase específica
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class) pt.getActualTypeArguments()[0];
    }
 
    public void agregar(final T t) {             
        em.persist(t);
    }
 
    public void borrar(final Object objeto) {
         em.remove(em.merge(objeto));
    }
 
    public T buscar(final Object id) {
        return (T) em.find(type, id);
    }
 
    public void actualizar(final T t) {
        em.merge(t);
    }
 
    public Iterable<T> buscarTodos() {
         CriteriaBuilder cb = this.em.getCriteriaBuilder();
         CriteriaQuery<T> criteriaQuery = cb.createQuery(type);
         Root<T> root = criteriaQuery.from(type);
         criteriaQuery.select(root);
         TypedQuery<T> query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
