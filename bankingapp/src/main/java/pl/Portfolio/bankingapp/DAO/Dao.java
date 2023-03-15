package pl.Portfolio.bankingapp.DAO;

import java.util.List;

public interface Dao <T> {
    T getById(int id);
    List<T> getAll();
    int save(List<T> t);
    int update(T t);
    int delete(int id);
}
