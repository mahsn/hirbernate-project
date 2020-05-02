package ProjectDao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Hibernate;

import HibernateProject.hibernate_project.HibernateUtil;

public class StudentDao<E> {

	private EntityManager entityManager = HibernateUtil.getEntityManager();

	public void save(E entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.merge(entity);
		transaction.commit();
	}

	public E search(Long id, Class<E> entity) {
		E result = (E) entityManager.find(entity, id);
		return result;
	}

	public E updateMerge(E entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		E saveEntity = entityManager.merge(entity);
		transaction.commit();

		return saveEntity;
	}

	public void deleteById(E entity) {
		Object id = HibernateUtil.getPrimaryKey(entity);
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		entityManager.createNativeQuery(
				"delete from " + entity.getClass().getSimpleName().toLowerCase() + " where id = " + id).executeUpdate();
		transaction.commit();
	}
	
	public List<E> listData(Class<E> entity) {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		String sql = "from "  + entity.getName();
		List<E> list = entityManager.createQuery(sql).getResultList();
		transaction.commit();
		return list;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
	
	
	
}
