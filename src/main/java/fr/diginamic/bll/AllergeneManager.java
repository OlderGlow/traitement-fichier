package fr.diginamic.bll;

import fr.diginamic.dal.AllergeneDAO;
import fr.diginamic.dal.DAOFactory;
import fr.diginamic.dal.DalException;
import fr.diginamic.entite.Allergene;

import java.util.List;

public class AllergeneManager {
    private static volatile AllergeneManager instance;
    private static AllergeneDAO allergeneDAO;

    private AllergeneManager() {
        allergeneDAO = DAOFactory.getAllergeneDAO();
    }

    public static AllergeneManager getInstance() {
        if (instance == null) {
            synchronized (AllergeneManager.class) {
                if (instance == null) {
                    instance = new AllergeneManager();
                }
            }
        }
        return instance;
    }

    public List<Allergene> selectAll() throws BLLException {
        try {
            return allergeneDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des allergènes", e);
        }
    }

    public Allergene selectById(int id) throws BLLException {
        try {
            return allergeneDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de l'allergène", e);
        }
    }

    public Allergene create(Allergene allergene) throws BLLException {
        try {
            if(allergeneDAO.selectByLibelle(allergene.getLibelle()) == null && allergene.getLibelle() != null) {
                allergeneDAO.create(allergene);
            } else {
                return null;
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion de l'allergène", e);
        }
        return allergene;
    }

    public void update(Allergene allergene) throws BLLException {
        try {
            allergeneDAO.update(allergene);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour de l'allergène", e);
        }
    }

    public void delete(Allergene allergene) throws BLLException {
        try {
            allergeneDAO.delete(allergene);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression de l'allergène", e);
        }
    }
}
