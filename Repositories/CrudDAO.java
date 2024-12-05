package ru.kpfu.servlets.Repositories;

import java.util.List;

public interface CrudDAO <T>{
    void save(T t);
    void delete(T t);
    void update(T t);
    List<T> getAll();


}
