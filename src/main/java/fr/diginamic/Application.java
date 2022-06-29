package fr.diginamic;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException, BLLException {
        List<String[]> data = ReadFileManager.readFile();
        CategorieManager categorieManager = CategorieManager.getInstance();
        AdditifManager additifManager = AdditifManager.getInstance();
        AllergeneManager allergeneManager = AllergeneManager.getInstance();
        MarqueManager marqueManager = MarqueManager.getInstance();
        IngredientManager ingredientManager = IngredientManager.getInstance();
        ProduitManager produitManager = ProduitManager.getInstance();

        for (String[] line : data) {
            if (data.indexOf(line) == 0) {
                continue;
            }
            Categorie categorie = new Categorie();
            categorie.setLibelle(line[0].trim().replaceAll("-", " "));
            String[] additifs = line[29].split(",");
            for (String additifStr : additifs) {
                Additif additif = new Additif();
                additif.setLibelle(additifStr.trim());
                additifManager.create(additif);
            }
            String[] allergenes = line[28].split(",");
            for (String allergeneStr : allergenes) {
                Allergene allergene = new Allergene();
                allergene.setLibelle(allergeneStr.trim());
                allergeneManager.create(allergene);
            }
            String[] marques = line[1].split(",");
            for (String marqueStr : marques) {
                Marque marque = new Marque();
                marque.setLibelle(marqueStr.trim());
                marqueManager.create(marque);
            }
            String[] ingredients = line[4].split(",");
            for (String ingredientStr : ingredients) {
                Ingredient ingredient = new Ingredient();
                ingredient.setLibelle(ingredientStr.trim().replace("_", "").replace(".", "").replace("*", ""));
                ingredientManager.create(ingredient);
            }
            categorieManager.create(categorie);
        }
    }
}
