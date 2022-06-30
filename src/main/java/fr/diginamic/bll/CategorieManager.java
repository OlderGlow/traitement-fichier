package fr.diginamic.bll;

import fr.diginamic.dal.CategorieDAO;
import fr.diginamic.dal.DAO;
import fr.diginamic.dal.DAOFactory;
import fr.diginamic.dal.DalException;
import fr.diginamic.entite.Categorie;

import java.util.List;

public class CategorieManager {
    private static volatile CategorieManager instance;
    private static CategorieDAO categorieDAO;

    private CategorieManager() {
        categorieDAO = DAOFactory.getCategorieDAO();
    }

    public static CategorieManager getInstance() {
        if (instance == null) {
            synchronized (CategorieManager.class) {
                if (instance == null) {
                    instance = new CategorieManager();
                }
            }
        }
        return instance;
    }

    public List<Categorie> selectAll() throws BLLException {
        try {
            return categorieDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des catégories", e);
        }
    }

    public Categorie selectById(int id) throws BLLException {
        try {
            return categorieDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de la catégorie", e);
        }
    }

    public Categorie create(Categorie categorie) throws BLLException {
        try {
            if(categorieDAO.selectByLibelle(categorie.getLibelle()) == null) {
                categorieDAO.create(categorie);
            } else {
                return categorieDAO.selectByLibelle(categorie.getLibelle());
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion de la catégorie", e);
        }
        return categorie;
    }

    public void update(Categorie categorie) throws BLLException {
        try {
            categorieDAO.update(categorie);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour de la catégorie", e);
        }
    }

    public void delete(Categorie categorie) throws BLLException {
        try {
            categorieDAO.delete(categorie);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression de la catégorie", e);
        }
    }

    public Categorie getCategorieByLibelle(String libelle) throws BLLException {
        try {
            return categorieDAO.selectByLibelle(libelle);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de la catégorie", e);
        }
    }

}
