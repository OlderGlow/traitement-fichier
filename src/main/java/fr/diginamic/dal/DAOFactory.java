package fr.diginamic.dal;

import fr.diginamic.entite.*;

public class DAOFactory {
    public static DAO<Produit> getProduitDAO() {
        return new ProduitDAO();
    }

    public static DAO<Additif> getAdditifDAO() {
        return new AdditifDAO();
    }

    public static DAO<Allergene> getAllergeneDAO() {
        return new AllergeneDAO();
    }

    public static DAO<Categorie> getCategorieDAO() {
        return new CategorieDAO();
    }

    public static DAO<Ingredient> getIngredientDAO() {
        return new IngredientDAO();
    }

    public static DAO<Marque> getMarqueDAO() {
        return new MarqueDAO();
    }
}
