package fr.diginamic.bll;

import fr.diginamic.dal.*;
import fr.diginamic.entite.*;

import java.util.*;

public class MarqueManager {
    private static volatile MarqueManager instance;
    private static MarqueDAO marqueDAO;

    private MarqueManager() {
        marqueDAO = DAOFactory.getMarqueDAO();
    }

    public static MarqueManager getInstance() {
        if (instance == null) {
            synchronized (MarqueManager.class) {
                if (instance == null) {
                    instance = new MarqueManager();
                }
            }
        }
        return instance;
    }

    public List<Marque> selectAll() throws BLLException {
        try {
            return marqueDAO.selectAll();
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération des marques", e);
        }
    }

    public Marque selectById(int id) throws BLLException {
        try {
            return marqueDAO.selectById(id);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la récupération de la marque", e);
        }
    }

    public Marque create(Marque marque) throws BLLException {
        try {
            if(marqueDAO.selectByLibelle(marque.getLibelle()) == null && marque.getLibelle() != null) {
                marqueDAO.create(marque);
            } else {
                return marqueDAO.selectByLibelle(marque.getLibelle());
            }
        } catch (DalException e) {
            throw new BLLException("Erreur lors de l'insertion de la marque", e);
        }
        return marque;
    }

    public void update(Marque marque) throws BLLException {
        try {
            marqueDAO.update(marque);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la mise à jour de la marque", e);
        }
    }

    public void delete(Marque marque) throws BLLException {
        try {
            marqueDAO.delete(marque);
        } catch (DalException e) {
            throw new BLLException("Erreur lors de la suppression de la marque", e);
        }
    }
}
