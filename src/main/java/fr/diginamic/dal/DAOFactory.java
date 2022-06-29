package fr.diginamic.dal;

import fr.diginamic.entite.*;

public class DAOFactory {
    public static ProduitDAO getProduitDAO() {
        return new ProduitDAO();
    }

    public static AdditifDAO getAdditifDAO() {
        return new AdditifDAO();
    }

    public static AllergeneDAO getAllergeneDAO() {
        return new AllergeneDAO();
    }

    public static CategorieDAO getCategorieDAO() {
        return new CategorieDAO();
    }

    public static IngredientDAO getIngredientDAO() {
        return new IngredientDAO();
    }

    public static MarqueDAO getMarqueDAO() {
        return new MarqueDAO();
    }
}
