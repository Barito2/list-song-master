package com.enigma.listsong.repositories;

import com.enigma.listsong.entities.Genre;

import java.util.List;

public interface GenreRepository {

    boolean save(Genre genre);

    boolean update(Genre genre);

    boolean delete(Genre genre);

    Genre findById(Integer id);

    List<Genre> findAll();
}
