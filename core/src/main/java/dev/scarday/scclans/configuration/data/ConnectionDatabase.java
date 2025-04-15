package dev.scarday.scclans.configuration.data;

import eu.okaeri.configs.OkaeriConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jetbrains.annotations.Nullable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ConnectionDatabase extends OkaeriConfig {
    String type = "SQLITE";
    String host; // 127.0.0.1:3306 - пример
    String database;
    String username;
    String password;

    @Nullable
    String sqlitePath = "{absolutePath}/database.db";
}
