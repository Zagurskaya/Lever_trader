package com.gmail.zagurskaya.repository;

import java.util.List;

public interface GenericRepository<I, T> {

    void persist(T entity);

    void merge(T entity);

    void remove(T entity);

    T findById(I id);

    T getById(I id);

    @SuppressWarnings({"unchecked", "rawtypes"})
    List<T> findAll(int offset, int limit);

    @SuppressWarnings({"unchecked", "rawtypes"})
    List<T> findAll();

    int getCountOfEntities();
}
