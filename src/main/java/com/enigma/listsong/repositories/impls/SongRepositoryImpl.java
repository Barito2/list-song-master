package com.enigma.listsong.repositories.impls;

import com.enigma.listsong.entities.Genre;
import com.enigma.listsong.entities.Group;
import com.enigma.listsong.entities.Singer;
import com.enigma.listsong.entities.Song;
import com.enigma.listsong.models.song.SongModel;
import com.enigma.listsong.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SongRepositoryImpl implements SongRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SAVE_SINGER = "INSERT INTO song" +
            "(name, year, singer_id, genre_id) " +
            "VALUES(?, ?, ?, ?)";

    private final String UPDATE_SINGER = "UPDATE song SET " +
            "name = ?, year = ?, singer_id = ?, genre_id = ? WHERE id = ?";

    private final String DELETE_SINGER = "DELETE FROM song WHERE id = ?";

    private final String FINDBYID_SINGER = "SELECT so.id AS idSong, so.name AS nameSong" +
            ", year, si.id AS idSinger, si.name AS singerName, date_birth, description, " +
            " ge.id AS idGenre, ge.name AS genreName, " +
            "gr.id AS idGroup, gr.name AS nameGroup FROM song so JOIN singer si ON so.id = si.id " +
            "JOIN genre ge ON so.id = ge.id JOIN groubs gr ON si.id = gr.id WHERE so.id = ?";

    private final String FINDALL_SINGER = "SELECT so.id AS idSong, so.name AS nameSong" +
            ", year, si.id AS idSinger, si.name AS singerName, date_birth, description, " +
            " ge.id AS idGenre, ge.name AS genreName, " +
            "gr.id AS idGroup, gr.name AS nameGroup FROM song so JOIN singer si ON so.id = si.id " +
            "JOIN genre ge ON so.id = ge.id JOIN groubs gr ON si.id = gr.id";

    @Override
    public boolean save(SongModel singer) {
        return jdbcTemplate.update(SAVE_SINGER,singer.getName(), singer.getYear(), singer.getSingerId(), singer.getGenreId()) > 0;
    }

    @Override
    public boolean update(SongModel singer) {
        return jdbcTemplate.update(UPDATE_SINGER, singer.getName(), singer.getYear(), singer.getSingerId(), singer.getGenreId(), singer.getId()) > 0;
    }

    @Override
    public boolean delete(Song singer) {
        return jdbcTemplate.update(DELETE_SINGER, singer.getId()) > 0;
    }

    @Override
    public Song findById(Integer id) {
        List<Song> singerList = jdbcTemplate.query(FINDBYID_SINGER, new RowMapper<Song>() {
            @Override
            public Song mapRow(ResultSet resultSet, int i) throws SQLException {
                Song song = new Song();
                song.setId(resultSet.getInt("idSong"));
                song.setName(resultSet.getString("nameSong"));
                song.setYear(resultSet.getString("year"));

                Singer singer = new Singer();
                singer.setId(resultSet.getInt("idSinger"));
                singer.setDate_birth(resultSet.getString("date_birth"));
                singer.setDescription(resultSet.getString("description"));
                singer.setName(resultSet.getString("singerName"));

                Group group = new Group();
                group.setId(resultSet.getInt("idGroup"));
                group.setName(resultSet.getString("nameGroup"));
                singer.setGroup(group);
                song.setSinger(singer);

                Genre genre = new Genre();
                genre.setId(resultSet.getInt("idGenre"));
                genre.setName(resultSet.getString("genreName"));
                song.setGenre(genre);

                return song;
            }
        }, new Object[]{id});
        return singerList.get(0);
    }

    @Override
    public List<Song> findAll() {
        List<Song> singerList = jdbcTemplate.query(FINDALL_SINGER, new RowMapper<Song>() {
            @Override
            public Song mapRow(ResultSet resultSet, int i) throws SQLException {
                Song song = new Song();
                song.setId(resultSet.getInt("idSong"));
                song.setName(resultSet.getString("nameSong"));
                song.setYear(resultSet.getString("year"));

                Singer singer = new Singer();
                singer.setId(resultSet.getInt("idSinger"));
                singer.setDate_birth(resultSet.getString("date_birth"));
                singer.setDescription(resultSet.getString("description"));
                singer.setName(resultSet.getString("singerName"));

                Group group = new Group();
                group.setId(resultSet.getInt("idGroup"));
                group.setName(resultSet.getString("nameGroup"));
                singer.setGroup(group);
                song.setSinger(singer);

                Genre genre = new Genre();
                genre.setId(resultSet.getInt("idGenre"));
                genre.setName(resultSet.getString("genreName"));
                song.setGenre(genre);

                return song;
            }
        });
        return singerList;
    }
}
