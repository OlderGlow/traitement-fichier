package fr.diginamic.dal;

import fr.diginamic.bll.PersistenceManager;
import fr.diginamic.entite.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class CategorieDAO implements DAO<Categorie> {

    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    @Override
    public void create(Categorie objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création de la catégorie", e);
        }
    }

    @Override
    public void update(Categorie objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour de la catégorie");
        }
    }

    @Override
    public void delete(Categorie objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression de la catégorie");
        }
    }

    @Override
    public List<Categorie> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT c FROM Categorie c", Categorie.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de toutes les catégories");
        }
    }

    @Override
    public Categorie selectById(long id) throws DalException {
        try {
            return em.find(Categorie.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de la catégorie");
        }
    }
}
