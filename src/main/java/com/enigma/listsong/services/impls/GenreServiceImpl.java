package com.enigma.listsong.services.impls;

import com.enigma.listsong.entities.Genre;
import com.enigma.listsong.repositories.GenreRepository;
import com.enigma.listsong.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public void addGenre(Genre genre) {
        genreRepository.save(genre);
    }

    @Override
    public void updateGenre(Genre genre) {
        genreRepository.update(genre);
    }

    @Override
    public void deleteGenre(Genre genre) {
        genreRepository.delete(genre);
    }

    @Override
    public Genre findGenreById(Integer id) {
        return genreRepository.findById(id);
    }

    @Override
    public List<Genre> findAllGenre() {
        return genreRepository.findAll();
    }
}
