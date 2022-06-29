package fr.diginamic.dal;

import fr.diginamic.bll.PersistenceManager;
import fr.diginamic.entite.Additif;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class AdditifDAO implements DAO<Additif> {
    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    @Override
    public void create(Additif objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création du additif", e);
        }
    }

    @Override
    public void update(Additif objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour du additif");
        }
    }

    @Override
    public void delete(Additif objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression du additif");
        }
    }

    @Override
    public List<Additif> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT a FROM Additif a", Additif.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les additifs");
        }
    }

    @Override
    public Additif selectById(long id) throws DalException {
        try {
            return em.find(Additif.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de l'additif");
        }
    }

    public Additif selectByLibelle(String libelle) throws DalException {
        try {
            return em.createQuery("SELECT a FROM Additif a WHERE a.libelle = :libelle", Additif.class).setParameter("libelle", libelle).getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}
