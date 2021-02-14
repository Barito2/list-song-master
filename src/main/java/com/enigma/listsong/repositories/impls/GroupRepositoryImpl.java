package com.enigma.listsong.repositories.impls;

import com.enigma.listsong.entities.Group;
import com.enigma.listsong.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GroupRepositoryImpl implements GroupRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final String SAVE_GROUP = "INSERT INTO groubs" +
            "(name) " +
            "VALUES(?)";

    private final String UPDATE_GROUP = "UPDATE groubs SET " +
            "name = ? WHERE id = ?";

    private final String DELETE_GROUP = "DELETE FROM groubs WHERE id = ?";

    private final String FINDBYID_GROUP = "SELECT id, name FROM groubs WHERE id = ?";

    private final String FINDALL_GROUP = "SELECT id, name FROM groubs";

    @Override
    public boolean save(Group group) {
        return jdbcTemplate.update(SAVE_GROUP, group.getName()) > 0;
    }

    @Override
    public boolean update(Group group) {
        return jdbcTemplate.update(UPDATE_GROUP, group.getName(), group.getId()) > 0;
    }

    @Override
    public boolean delete(Group group) {
        return jdbcTemplate.update(DELETE_GROUP, group.getId()) > 0;
    }

    @Override
    public Group findById(Integer id) {
        List<Group> groupList = jdbcTemplate.query(FINDBYID_GROUP, new RowMapper<Group>() {
            @Override
            public Group mapRow(ResultSet resultSet, int i) throws SQLException {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        }, new Object[]{id});
        return groupList.get(0);
    }

    @Override
    public List<Group> findAll() {
        List<Group> groupList = jdbcTemplate.query(FINDALL_GROUP, new RowMapper<Group>() {
            @Override
            public Group mapRow(ResultSet resultSet, int i) throws SQLException {
                Group group = new Group();
                group.setId(resultSet.getInt("id"));
                group.setName(resultSet.getString("name"));
                return group;
            }
        });
        return groupList;
    }
}
