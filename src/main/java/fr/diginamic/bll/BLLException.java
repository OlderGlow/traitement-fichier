package fr.diginamic.bll;

public class BLLException extends Exception {
    public BLLException() {
    }

    public BLLException(String message) {
        super("Erreur BLL : " + message);
    }

    public BLLException(String message, Throwable cause) {
        super("Erreur BLL : " + message, cause);
    }
}
