package com.framework.template.data.dao.user;

import com.framework.template.util.JdbcDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by Noop on 20.06.2017.
 */
public class UserDTOImpl implements UserDTO {
    private JdbcTemplate jdbcTemplateSource = new JdbcTemplate(getDataSource(true));
    private JdbcTemplate jdbcTemplateTarget = new JdbcTemplate(getDataSource(false));

    private DataSource getDataSource(boolean isSourceDB) {
        return JdbcDataSource.connectToSpecifiedDB(isSourceDB);
    }

    private RowMapper<User> rowMapper = ((rs, rowNum) -> {
        User user = new User();
        user.setUserId(rs.getInt("id_user"));
        user.setFirstName(rs.getString("firstname"));
        user.setLastName(rs.getString("lastname"));
        user.setEmail(rs.getString("email"));
        return user;
    });


    @Override
    public User getUserByID(boolean isSourceDB, int userId) {
        String sql = "SELECT * FROM users WHERE id_user=?";
        if (isSourceDB) {
            return jdbcTemplateSource.queryForObject(sql, rowMapper, userId);
        } else return jdbcTemplateTarget.queryForObject(sql, rowMapper, userId);
    }

    @Override
    public List<User> getUsersByName(boolean isSourceDB, String query) {
        String sql = "SELECT * FROM users WHERE concat(users.firstname, ' ', users.lastname) LIKE '%?%';";
        if (isSourceDB) {
            return jdbcTemplateSource.query(sql, rowMapper, query);
        } else return jdbcTemplateTarget.query(sql, rowMapper, query);
    }

    @Override
    public List<User> getUsersByEmail(boolean isSourceDB, String email) {
        String sql = "SELECT * FROM users WHERE email = '?';";
        if (isSourceDB) {
            return jdbcTemplateSource.query(sql, rowMapper, email);
        } else {
            return jdbcTemplateTarget.query(sql, rowMapper, email);
        }
    }

    @Override
    public List<User> getUsers(boolean isSourceDB) {
        String sql = "SELECT * FROM users;";
        if (isSourceDB) {
            return jdbcTemplateSource.query(sql, rowMapper);
        } else {
            return jdbcTemplateTarget.query(sql, rowMapper);
        }
    }
}
