package fr.diginamic;

import fr.diginamic.bll.PersistenceManager;
import fr.diginamic.bll.ReadFileManager;
import fr.diginamic.entite.Categorie;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException {
        List<String[]> data = ReadFileManager.readFile();
        EntityManagerFactory emf = PersistenceManager.getInstance().getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();
        List<String> categoriesLibelle = em.createQuery("SELECT c.libelle FROM Categorie c", String.class).getResultList();

        em.getTransaction().begin();
        for (String[] line : data) {
            if (data.indexOf(line) == 0) {
                continue;
            }
            Categorie categorie = new Categorie();
            categorie.setLibelle(line[0]);
            if (!categoriesLibelle.contains(categorie.getLibelle())) {
                em.persist(categorie);
                categoriesLibelle.add(categorie.getLibelle());
            }
        }
        em.getTransaction().commit();
    }
}
