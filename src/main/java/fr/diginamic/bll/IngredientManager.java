package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class IngredientManager {
    private static volatile IngredientManager instance;
    private static IngredientDAO ingredientDAO;

    private IngredientManager() {
        ingredientDAO = DAOFactory.getIngredientDAO();
    }

    public static IngredientManager getInstance() {
        if (instance == null) {
            synchronized (IngredientManager.class) {
                if (instance == null) {
                    instance = new IngredientManager();
                }
            }
        }
        return instance;
    }

    public List<Ingredient> selectAll() throws BLLException {
        try {
            return ingredientDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des ingrédients", e);
        }
    }

    public Ingredient selectById(int id) throws BLLException {
        try {
            return ingredientDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de l'ingrédient", e);
        }
    }

    public Ingredient create(Ingredient ingredient) throws BLLException {
        try {
            if(ingredientDAO.selectByLibelle(ingredient.getLibelle()) == null && ingredient.getLibelle() != null) {
                ingredientDAO.create(ingredient);
            } else {
                return null;
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion de l'ingrédient", e);
        }
        return ingredient;
    }

    public void update(Ingredient ingredient) throws BLLException {
        try {
            ingredientDAO.update(ingredient);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour de l'ingrédient", e);
        }
    }

    public void delete(Ingredient ingredient) throws BLLException {
        try {
            ingredientDAO.delete(ingredient);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression de l'ingrédient", e);
        }
    }
}
