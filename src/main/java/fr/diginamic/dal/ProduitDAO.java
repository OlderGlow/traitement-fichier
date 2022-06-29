package fr.diginamic.dal;

import fr.diginamic.bll.PersistenceManager;
import fr.diginamic.entite.Produit;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class ProduitDAO implements DAO<Produit> {

    private final EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
    private final EntityManager em = emf.createEntityManager();

    @Override
    public void create(Produit objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.persist(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la création du produit", e);
        }
    }

    @Override
    public void update(Produit objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.merge(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la mise à jour du produit");
        }
    }

    @Override
    public void delete(Produit objet) throws DalException {
        try {
            em.getTransaction().begin();
            em.remove(objet);
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la suppression du produit");
        }
    }

    @Override
    public List<Produit> selectAll() throws DalException {
        try {
            return em.createQuery("SELECT p FROM Produit p", Produit.class).getResultList();
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération de tous les produits");
        }
    }


    @Override
    public Produit selectById(long id) throws DalException {
        try {
            return em.find(Produit.class, id);
        } catch (Exception e) {
            throw new DalException("Erreur lors de la récupération du produit");
        }
    }

}
