package dev.scarday.scclans.database.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import dev.scarday.scclans.configuration.data.ConnectionDatabase;
import dev.scarday.scclans.database.DatabaseManager;
import dev.scarday.scclans.database.type.DatabaseType;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.val;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MySQLImpl implements DatabaseManager {
    private final ConnectionDatabase database;
    HikariDataSource dataSource;

    @Override
    public void connect() {
        val host = database.getHost();
        val port = host.split(":").length > 1 ? Integer.parseInt(host.split(":")[1]) : 3306;
        val url = String.format("jdbc:mariadb://%s:%d/%s?useSSL=false&allowPublicKeyRetrieval=true", database.getHost(), port, database.getDatabase());

        val config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(database.getUsername());
        config.setPassword(database.getPassword());
        config.setDriverClassName("org.mariadb.jdbc.Driver");
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000);
        config.setIdleTimeout(600000);
        config.setMaxLifetime(1800000);
        config.setPoolName("ScClans-MariaDB");

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
        return DatabaseType.MYSQL;
    }
}
