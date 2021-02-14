package com.enigma.listsong.services;

import com.enigma.listsong.entities.Genre;

import java.util.List;

public interface GenreService {

    void addGenre(Genre genre);

    void updateGenre(Genre genre);

    void deleteGenre(Genre genre);

    Genre findGenreById(Integer id);

    List<Genre> findAllGenre();
}
