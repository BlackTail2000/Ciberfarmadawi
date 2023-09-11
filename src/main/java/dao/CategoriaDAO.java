package dao;

import java.util.ArrayList;
import java.util.List;

import interfaces.ICategoriaDAO;
import jakarta.persistence.*;
import model.Categoria;
import util.EntityManagerProvider;

public class CategoriaDAO implements ICategoriaDAO {

	@Override
	public List<Categoria> findAll() {
		List<Categoria> categorias = new ArrayList<Categoria>();
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = EntityManagerProvider.getEntityManager(emf);
			Query query = em.createNamedQuery("Categoria.findAll", Categoria.class);
			for(Object item: query.getResultList()) {
				if(item instanceof Categoria) {
					categorias.add((Categoria) item);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
		return categorias;
	}

	@Override
	public void createCategoria(Categoria categoria) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = EntityManagerProvider.getEntityManager(emf);
			em.getTransaction().begin();
			em.persist(categoria);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
	}

	@Override
	public void updateCategoria(Categoria categoria) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = EntityManagerProvider.getEntityManager(emf);
			em.getTransaction().begin();
			em.merge(categoria);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
	}

	@Override
	public void deleteCategoria(int idcategoria) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = EntityManagerProvider.getEntityManager(emf);
			Query query = em.createQuery("Delete From Categoria c Where c.idcategoria = :idcategoria");
			query.setParameter("idcategoria", idcategoria);
			em.getTransaction().begin();
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
