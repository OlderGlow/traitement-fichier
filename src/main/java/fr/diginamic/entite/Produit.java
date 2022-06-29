package fr.diginamic.entite;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;

    @ManyToOne
    @JoinColumn(name = "marque_id")
    private Marque marque;

    private ScoreNutritionnel scoreNutritionnel;

    @Embedded
    private ValeurNutritionelle valeurNutritionelle;

    @ManyToMany
    @JoinTable(name = "produit_allergene", joinColumns = @JoinColumn(name = "produit_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private Set<Ingredient> ingredients;

    @ManyToMany
    @JoinTable(name = "produit_allergene", joinColumns = @JoinColumn(name = "produit_id"), inverseJoinColumns = @JoinColumn(name = "additif_id"))
    private Set<Additif> additifs;

    @ManyToMany
    @JoinTable(name = "produit_allergene", joinColumns = @JoinColumn(name = "produit_id"), inverseJoinColumns = @JoinColumn(name = "allergene_id"))
    private Set<Allergene> allergenes;

    public ValeurNutritionelle getValeurNutritionelle() {
        return valeurNutritionelle;
    }

    public void setValeurNutritionelle(ValeurNutritionelle valeurNutritionelle) {
        this.valeurNutritionelle = valeurNutritionelle;
    }

    public Marque getMarque() {
        return marque;
    }

    public void setMarque(Marque marque) {
        this.marque = marque;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ScoreNutritionnel getScoreNutritionnel() {
        return scoreNutritionnel;
    }

    public void setScoreNutritionnel(ScoreNutritionnel scoreNutritionnel) {
        this.scoreNutritionnel = scoreNutritionnel;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Set<Additif> getAdditifs() {
        return additifs;
    }

    public void setAdditifs(Set<Additif> additifs) {
        this.additifs = additifs;
    }

    public Set<Allergene> getAllergenes() {
        return allergenes;
    }

    public void setAllergenes(Set<Allergene> allergenes) {
        this.allergenes = allergenes;
    }
}
