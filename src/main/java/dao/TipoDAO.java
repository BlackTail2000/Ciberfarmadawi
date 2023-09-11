package dao;

import java.util.ArrayList;
import java.util.List;

import interfaces.ITipoDAO;
import jakarta.persistence.*;
import model.Tipo;
import util.EntityManagerProvider;

public class TipoDAO implements ITipoDAO {

	@Override
	public List<Tipo> findAll() {
		List<Tipo> tipos = new ArrayList<Tipo>();
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = EntityManagerProvider.getEntityManager(emf);
			Query query = em.createNamedQuery("Tipo.findAll", Tipo.class);
			for(Object item: query.getResultList()) {
				if(item instanceof Tipo) {
					tipos.add((Tipo) item);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
		return tipos;
	}

	@Override
	public void createTipo(Tipo tipo) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = EntityManagerProvider.getEntityManager(emf);
			em.getTransaction().begin();
			em.persist(tipo);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
	}

	@Override
	public void updateTipo(Tipo tipo) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = EntityManagerProvider.getEntityManager(emf);
			em.getTransaction().begin();
			em.merge(tipo);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
	}

	@Override
	public void deleteTipo(int idtipo) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = EntityManagerProvider.getEntityManager(emf);
			em.getTransaction().begin();
			Query query = em.createQuery("Delete From Tipo t Where t.idtipo = :idtipo");
			query.setParameter("idtipo", idtipo);
			query.executeUpdate();
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
	}

}
