package io.haufe.beercatalogueservice.service;

import io.haufe.beercatalogueservice.models.Users;

import java.util.Collection;
import java.util.Optional;

public interface IService<T> {
    Collection<T> findAll();

    Optional<T> findById(Long id);

    T saveOrUpdate(T t);

    String deleteById(Long id);

    abstract T findbyString(String t);
}