package dev.scarday.scclans.api.data;

import lombok.Data;

import java.util.UUID;

@Data
public class ClanPlayersData {
    UUID playerUUID;

    int kills;
    int deaths;
}