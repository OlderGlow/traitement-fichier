package fr.diginamic.entite;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "allergene")
public class Allergene {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String libelle;

    @ManyToMany(mappedBy = "allergenes")
    private Set<Produit> produits;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
