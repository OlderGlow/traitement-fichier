package fr.diginamic.bll;

import fr.diginamic.dal.AdditifDAO;
import fr.diginamic.dal.DAOFactory;
import fr.diginamic.dal.DalException;
import fr.diginamic.entite.Additif;

import java.util.List;

public class AdditifManager {
    private static volatile AdditifManager instance;
    private static AdditifDAO additifDAO;

    private AdditifManager() {
        additifDAO = DAOFactory.getAdditifDAO();
    }

    public static AdditifManager getInstance() {
        if (instance == null) {
            synchronized (AdditifManager.class) {
                if (instance == null) {
                    instance = new AdditifManager();
                }
            }
        }
        return instance;
    }

    public List<Additif> selectAll() throws BLLException {
        try {
            return additifDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des additifs", e);
        }
    }

    public Additif selectById(int id) throws BLLException {
        try {
            return additifDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de l'additif", e);
        }
    }

    public void create(Additif additif) throws BLLException {
        try {
            if(additifDAO.selectByLibelle(additif.getLibelle()) == null && additif.getLibelle() != null) {
                additifDAO.create(additif);
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion de l'additif", e);
        }
    }

    public void update(Additif additif) throws BLLException {
        try {
            additifDAO.update(additif);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour de l'additif", e);
        }
    }

    public void delete(Additif additif) throws BLLException {
        try {
            additifDAO.delete(additif);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression de l'additif", e);
        }
    }

}
