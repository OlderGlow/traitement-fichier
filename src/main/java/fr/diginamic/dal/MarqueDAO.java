package fr.diginamic.dal;

import fr.diginamic.bll.PersistenceManager;
import fr.diginamic.entite.Marque;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MarqueDAO implements DAO<Marque> {
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    @Override
    public void create(Marque objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création de la marque", e);
        }
    }

    @Override
    public void update(Marque objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour de la marque");
        }
    }

    @Override
    public void delete(Marque objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression de la marque");
        }
    }

    @Override
    public List<Marque> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT m FROM Marque m", Marque.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de toutes les marques");
        }
    }

    @Override
    public Marque selectById(long id) throws DalException {
        try {
            return em.find(Marque.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de la marque");
        }
    }
}
