package dao;

import java.util.ArrayList;
import java.util.List;

import interfaces.IProveedorDAO;
import jakarta.persistence.*;
import model.Proveedor;
import util.EntityManagerProvider;

public class ProveedorDAO implements IProveedorDAO {

	@Override
	public List<Proveedor> findAll() {
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = emf.createEntityManager();
			Query query = em.createNamedQuery("Proveedor.findAll", Proveedor.class);
			for(Object item: query.getResultList()) {
				if(item instanceof Proveedor) {
					proveedores.add((Proveedor) item);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
		return proveedores;
	}

	@Override
	public void createProveedor(Proveedor proveedor) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(proveedor);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
	}

	@Override
	public void updateProveedor(Proveedor proveedor) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(proveedor);
			em.getTransaction().commit();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			EntityManagerProvider.closeEntityManager(em);
			EntityManagerProvider.closeEntityManagerFactory(emf);
		}
	}

	@Override
	public void deleteProveedor(int idproveedor) {
		EntityManagerFactory emf = null;
		EntityManager em = null;
		try {
			emf = EntityManagerProvider.getEntityManagerFactory("PersistenceUnit");
			em = emf.createEntityManager();
			Query query = em.createQuery("Delete From Proveedor p Where p.idproveedor = :idproveedor");
			query.setParameter("idproveedor", idproveedor);
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
