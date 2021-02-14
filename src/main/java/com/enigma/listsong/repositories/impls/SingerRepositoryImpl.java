package com.enigma.listsong.repositories.impls;

import com.enigma.listsong.entities.Group;
import com.enigma.listsong.entities.Singer;
import com.enigma.listsong.models.singer.SingerModel;
import com.enigma.listsong.repositories.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class SingerRepositoryImpl implements SingerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SAVE_SINGER = "INSERT INTO singer" +
            "(date_birth, description, name, group_id) " +
            "VALUES(?, ?, ?, ?)";

    private final String UPDATE_SINGER = "UPDATE singer SET " +
            "date_birth = ?, description = ?, name = ?, group_id = ? WHERE id = ?";

    private final String DELETE_SINGER = "DELETE FROM singer WHERE id = ?";

    private final String FINDBYID_SINGER = "SELECT * FROM singer s JOIN groubs g ON s.id = g.id WHERE s.id = ?";

    private final String FINDALL_SINGER = "SELECT * FROM singer s JOIN groubs g ON s.id = g.id";

    @Override
    public boolean save(SingerModel singer) {
        return jdbcTemplate.update(SAVE_SINGER,singer.getDate_birth(), singer.getDescription(),singer.getName(), singer.getGroupId()) > 0;
    }

    @Override
    public boolean update(SingerModel singer) {
        return jdbcTemplate.update(UPDATE_SINGER, singer.getDate_birth(), singer.getDescription(),singer.getName(), singer.getGroupId(), singer.getId()) > 0;
    }

    @Override
    public boolean delete(Singer singer) {
        return jdbcTemplate.update(DELETE_SINGER, singer.getId()) > 0;
    }

    @Override
    public Singer findById(Integer id) {
        List<Singer> singerList = jdbcTemplate.query(FINDBYID_SINGER, new RowMapper<Singer>() {
            @Override
            public Singer mapRow(ResultSet resultSet, int i) throws SQLException {
                Singer singer = new Singer();
                singer.setId(resultSet.getInt("id"));
                singer.setDate_birth(resultSet.getString("date_birth"));
                singer.setDescription(resultSet.getString("description"));
                singer.setName(resultSet.getString("name"));

                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                singer.setGroup(group);

                return singer;
            }
        }, new Object[]{id});
        return singerList.get(0);
    }

    @Override
    public List<Singer> findAll() {
        List<Singer> singerList = jdbcTemplate.query(FINDALL_SINGER, new RowMapper<Singer>() {
            @Override
            public Singer mapRow(ResultSet resultSet, int i) throws SQLException {
                Singer singer = new Singer();
                singer.setId(resultSet.getInt("id"));
                singer.setDate_birth(resultSet.getString("date_birth"));
                singer.setDescription(resultSet.getString("description"));
                singer.setName(resultSet.getString("name"));

                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                singer.setGroup(group);

                return singer;
            }
        });
        return singerList;
    }
}
