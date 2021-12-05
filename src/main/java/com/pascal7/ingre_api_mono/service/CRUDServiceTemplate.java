package com.pascal7.ingre_api_mono.service;

import java.util.List;

public interface CRUDServiceTemplate<T> {

    T create(T t);
    T getById(String id);
    T update(T t);
    List<T> getAll();
    void delete(String id);
}
