package com.framework.template.data;

import com.framework.template.util.JdbcDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by Noop on 26.06.2017.
 */
public class DBSelector {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JdbcDataSource.connectToDB());
}
