package fr.diginamic.dal;

import fr.diginamic.bll.PersistenceManager;
import fr.diginamic.entite.Allergene;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AllergeneDAO implements DAO<Allergene> {

    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();
    @Override
    public void create(Allergene objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création de l'allergene", e);
        }
    }

    @Override
    public void update(Allergene objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour de l'allergene");
        }
    }

    @Override
    public void delete(Allergene objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression de l'allergene");
        }
    }

    @Override
    public List<Allergene> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT a FROM Allergene a", Allergene.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les allergènes");
        }
    }

    @Override
    public Allergene selectById(long id) throws DalException {
        try {
            return em.find(Allergene.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de l'allergene");
        }
    }
}
