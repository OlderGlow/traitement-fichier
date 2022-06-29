package fr.diginamic.entite;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "additif")
public class Additif {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String libelle;

    @ManyToMany(mappedBy = "additifs")
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
