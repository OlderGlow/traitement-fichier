package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class ProduitManager {
    private static volatile ProduitManager instance;
    private static ProduitDAO produitDAO;

    private ProduitManager() {
        produitDAO = DAOFactory.getProduitDAO();
    }

    public static ProduitManager getInstance() {
        if (instance == null) {
            synchronized (ProduitManager.class) {
                if (instance == null) {
                    instance = new ProduitManager();
                }
            }
        }
        return instance;
    }

    public List<Produit> selectAll() throws BLLException {
        try {
            return produitDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des produits", e);
        }
    }

    public Produit selectById(int id) throws BLLException {
        try {
            return produitDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération du produit", e);
        }
    }

    public void create(Produit produit) throws BLLException {
        try {
            if(produit.getId() == null) {
                produitDAO.create(produit);
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion du produit", e);
        }
    }

    public void update(Produit produit) throws BLLException {
        try {
            produitDAO.update(produit);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour du produit", e);
        }
    }

    public void delete(Produit produit) throws BLLException {
        try {
            produitDAO.delete(produit);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression du produit", e);
        }
    }
}
