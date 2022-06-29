package fr.diginamic.bll;

import fr.diginamic.dal.DAO;
import fr.diginamic.dal.DAOFactory;
import fr.diginamic.dal.DalException;
import fr.diginamic.entite.Categorie;

import java.util.List;

public class CategorieManager {
    private static volatile CategorieManager instance;
    private static DAO<Categorie> categorieDAO;

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

}
