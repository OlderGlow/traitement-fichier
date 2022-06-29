package fr.diginamic.dal;

import fr.diginamic.bll.PersistenceManager;
import fr.diginamic.entite.Ingredient;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class IngredientDAO implements DAO<Ingredient> {

    EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    @Override
    public void create(Ingredient objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création de l'ingredient", e);
        }
    }

    @Override
    public void update(Ingredient objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour de l'ingredient");
        }
    }

    @Override
    public void delete(Ingredient objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression de l'ingredient");
        }
    }

    @Override
    public List<Ingredient> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT i FROM Ingredient i", Ingredient.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les ingrédients");
        }
    }

    @Override
    public Ingredient selectById(long id) throws DalException {
        try {
            return em.find(Ingredient.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de l'ingredient");
        }
    }
}
