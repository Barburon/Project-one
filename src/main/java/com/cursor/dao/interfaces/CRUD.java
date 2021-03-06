package com.cursor.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

public interface CRUD<T> {
    boolean create(T entity);

    List<T> getAll();

    T findById(int id);

    boolean edit(int id, T entity);

    boolean delete(int id);
}
