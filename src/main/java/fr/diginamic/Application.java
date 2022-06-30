package fr.diginamic;

import fr.diginamic.bll.*;
import fr.diginamic.entite.*;

import java.io.IOException;
import java.util.*;

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
            Set<Ingredient> ingredientsSet = new HashSet<>();
            Set<Allergene> allergenesSet = new HashSet<>();
            Set<Additif> additifsSet = new HashSet<>();
            if (data.indexOf(line) == 0) {
                continue;
            }
            Categorie categorie = new Categorie();
            categorie.setLibelle(line[0].trim().replaceAll("-", " "));
            categorie = categorieManager.create(categorie);
            String[] additifs = line[29].split(",");
            for (String additifStr : additifs) {
                Additif additif = new Additif();
                additif.setLibelle(additifStr.trim());
                additif = additifManager.create(additif);
                additifsSet.add(additif);
            }
            String[] allergenes = line[28].split(",");
            for (String allergeneStr : allergenes) {
                Allergene allergene = new Allergene();
                allergene.setLibelle(allergeneStr.trim());
                allergene = allergeneManager.create(allergene);
                allergenesSet.add(allergene);
            }
                Marque marque = new Marque();
                marque.setLibelle(line[1].trim());
                marque = marqueManager.create(marque);
            String[] ingredients = line[4].split(",|-|;");
            for (String ingredientStr : ingredients) {
                Ingredient ingredient = new Ingredient();
                int max = Math.min(ingredientStr.length(), 254);
                ingredient.setLibelle(ingredientStr.substring(0, max).trim().replace("_", "").replace(".", "").replace("*", ""));
                ingredient = ingredientManager.create(ingredient);
                ingredientsSet.add(ingredient);
            }
            Produit produit = new Produit();
            produit.setMarque(marque);
            produit.setCategorie(categorie);
            produit.setAllergenes(allergenesSet);
            produit.setAdditifs(additifsSet);
            produit.setIngredients(ingredientsSet);
            produit.setScoreNutritionnel(ScoreNutritionnel.valueOf(line[3].trim().toUpperCase()));
            ValeurNutritionelle valeurNutritionelle = new ValeurNutritionelle();
            valeurNutritionelle.setEnergies(line[5].trim());
            valeurNutritionelle.setGraisses(line[6].trim());
            valeurNutritionelle.setSucres(line[7].trim());
            valeurNutritionelle.setFibres(line[8].trim());
            valeurNutritionelle.setProteines(line[9].trim());
            valeurNutritionelle.setSel(line[10].trim());
            valeurNutritionelle.setVitA(line[11].trim());
            valeurNutritionelle.setVitD(line[12].trim());
            valeurNutritionelle.setVitE(line[13].trim());
            valeurNutritionelle.setVitK(line[14].trim());
            valeurNutritionelle.setVitC(line[15].trim());
            valeurNutritionelle.setVitB1(line[16].trim());
            valeurNutritionelle.setVitB2(line[17].trim());
            valeurNutritionelle.setVitPP(line[18].trim());
            valeurNutritionelle.setVitB6(line[19].trim());
            valeurNutritionelle.setVitB9(line[20].trim());
            valeurNutritionelle.setVitB12(line[21].trim());
            valeurNutritionelle.setCalcium(line[22].trim());
            valeurNutritionelle.setMagnesium(line[23].trim());
            valeurNutritionelle.setIron(line[24].trim());
            valeurNutritionelle.setFer(line[25].trim());
            valeurNutritionelle.setBetaCarotene(line[26].trim());
            valeurNutritionelle.setHuildeDePalme(Boolean.parseBoolean(line[27].trim()));
            produit.setValeurNutritionelle(valeurNutritionelle);
            produitManager.create(produit);
        }
    }
}
