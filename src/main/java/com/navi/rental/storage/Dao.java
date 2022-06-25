package com.navi.rental.storage;

import java.util.List;
import java.util.Optional;

public interface Dao {
    <T> Optional<T> get(String id);

    <T> List<T> getAll();

    <T> boolean save(T t);

    <T> void delete(T t);

    <T> void update(T t, String[] params);


}
