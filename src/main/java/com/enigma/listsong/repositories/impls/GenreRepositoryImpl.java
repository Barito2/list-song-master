package com.enigma.listsong.repositories.impls;

import com.enigma.listsong.entities.Genre;
import com.enigma.listsong.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SAVE_GENRE = "INSERT INTO genre" +
            "(name) " +
            "VALUES(?)";

    private final String UPDATE_GENRE = "UPDATE genre SET " +
            "name = ? WHERE id = ?";

    private final String DELETE_GENRE = "DELETE FROM genre WHERE id = ?";

    private final String FINDBYID_GENRE = "SELECT id, name FROM genre WHERE id = ?";

    private final String FINDALL_GENRE = "SELECT id, name FROM genre";

    @Override
    public boolean save(Genre genre) {
        return jdbcTemplate.update(SAVE_GENRE, genre.getName()) > 0;
    }

    @Override
    public boolean update(Genre genre) {
        return jdbcTemplate.update(UPDATE_GENRE, genre.getName(), genre.getId()) > 0;
    }

    @Override
    public boolean delete(Genre genre) {
        return jdbcTemplate.update(DELETE_GENRE, genre.getId()) > 0;
    }

    @Override
    public Genre findById(Integer id) {
        List<Genre> genreList = jdbcTemplate.query(FINDBYID_GENRE, new RowMapper<Genre>() {
            @Override
            public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setName(resultSet.getString("name"));
                return genre;
            }
        }, new Object[]{id});
        return genreList.get(0);
    }

    @Override
    public List<Genre> findAll() {
        List<Genre> genreList = jdbcTemplate.query(FINDALL_GENRE, new RowMapper<Genre>() {
            @Override
            public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
                Genre genre = new Genre();
                genre.setId(resultSet.getInt("id"));
                genre.setName(resultSet.getString("name"));
                return genre;
            }
        });
        return genreList;
    }
}
