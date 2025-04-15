package dev.scarday.scclans.database;

import dev.scarday.scclans.database.type.DatabaseType;

import java.sql.Connection;

public interface DatabaseManager {
    void connect();
    Connection getConnection();
    void close();
    DatabaseType getDatabaseType();
}
