package fr.diginamic.dal;

import java.util.List;

public interface DAO<T> {
    public void create(T objet) throws DalException;
    public void update(T objet) throws DalException;
    public void delete(T objet)throws DalException;
    public List<T> selectAll() throws DalException;
    public T selectById(long id ) throws DalException;
}
