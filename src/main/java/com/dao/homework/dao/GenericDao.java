package com.dao.homework.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, I> {
    T create(T value);

    Optional<T> getById(I id);

    T update(T value);

    boolean delete(I id);

    List<T> getAll();
}
