package util;

import jakarta.persistence.*;

public class EntityManagerProvider {
	
	public static EntityManagerFactory getEntityManagerFactory(String PersistenceUnit) {
		EntityManagerFactory emf = null;
		try {
			emf = Persistence.createEntityManagerFactory(PersistenceUnit);
			System.out.println("Entity Manager Factory Open.");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return emf;
	}
	
	public static EntityManager getEntityManager(EntityManagerFactory emf) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			System.out.println("Entity Manager Open.");
		} catch(Exception e) {
			e.printStackTrace();
		}
		return em;
	}
	
	public static void closeEntityManagerFactory(EntityManagerFactory emf) {
		try {
			emf.close();
			System.out.println("Entity Manager Factory Closed.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void closeEntityManager(EntityManager em) {
		try {
			em.close();
			System.out.println("Entity Manager Closed.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
