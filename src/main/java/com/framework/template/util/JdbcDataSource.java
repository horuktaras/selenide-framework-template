package com.framework.template.util;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.FileNotFoundException;

/**
 * Created by Noop on 20.06.2017.
 */
public class JdbcDataSource {


    public static DataSource connectToDB() {
        try {
            DriverManagerDataSource dataSource = new DriverManagerDataSource(
                    PropertyReader.loadProperty("db.url"),
                    PropertyReader.loadProperty("db.user.name"),
                    PropertyReader.loadProperty("db.user.pass")
            );
            dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            return dataSource;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DataSource connectToSpecifiedDB(boolean isSourceDB) {
        try {
            if (isSourceDB) {
                DriverManagerDataSource dataSource = new DriverManagerDataSource(
                        PropertyReader.loadProperty("db.url"),
                        PropertyReader.loadProperty("db.user.name"),
                        PropertyReader.loadProperty("db.user.pass")
                );
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                return dataSource;
            } else {
                DriverManagerDataSource dataSource = new DriverManagerDataSource(
                        PropertyReader.loadProperty("db2.url"),
                        PropertyReader.loadProperty("db2.user.name"),
                        PropertyReader.loadProperty("db2.user.pass")
                );
                dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
                return dataSource;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
