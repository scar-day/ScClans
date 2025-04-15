package dev.scarday.scclans.configuration;

import dev.scarday.scclans.configuration.data.ConnectionDatabase;
import eu.okaeri.configs.OkaeriConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Configuration extends OkaeriConfig {
    ConnectionDatabase database = new ConnectionDatabase();
}
