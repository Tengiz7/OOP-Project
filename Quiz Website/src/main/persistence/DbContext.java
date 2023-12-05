package main.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import main.domain.ObjectMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbContext {
    private BasicDataSource dataSource;

    public DbContext() {
        this.dataSource = src.main.persistence.DataSourceHelperClass.getDataSource();
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
