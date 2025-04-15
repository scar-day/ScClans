package dev.scarday.scclans.database.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.scarday.scclans.database.DatabaseManager;
import dev.scarday.scclans.database.type.DatabaseType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SQLiteImpl implements DatabaseManager {
    private final File pathToDatabase;
    HikariDataSource dataSource;

    @Override
    public void connect() {
        val config = new HikariConfig();
        config.setJdbcUrl(pathToDatabase.getAbsolutePath());
        config.setDriverClassName("org.sqlite.JDBC");
        config.setMaximumPoolSize(1);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setPoolName("ScClans-SQLITE");

        dataSource = new HikariDataSource(config);
    }

    @Override
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    @Override
    public void close() {
        if (dataSource != null) {
            dataSource.close();
        }
    }

    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.SQLITE;
    }
}
