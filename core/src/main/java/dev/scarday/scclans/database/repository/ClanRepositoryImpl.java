package dev.scarday.scclans.database.repository;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Connection;
import java.sql.SQLException;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClanRepositoryImpl implements ClanRepository {
    Connection connection;
    @Override
    public void createTable() {
        val sql = """
                CREATE TABLE IF NOT EXISTS `clan` (
                        `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                        `name` VARCHAR(255) NOT NULL,
                        PRIMARY KEY (`id`)
                );
                """;
        try (val stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: доделать.
}
