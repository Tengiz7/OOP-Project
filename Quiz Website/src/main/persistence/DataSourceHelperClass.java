package src.main.persistence;

import org.apache.commons.dbcp2.BasicDataSource;

public class DataSourceHelperClass {

    private static BasicDataSource dataSource;

    static {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/oop_db");
        dataSource.setUsername("test");
        dataSource.setPassword("password");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
    }

    public static BasicDataSource getDataSource() {
        return dataSource;
    }
}
