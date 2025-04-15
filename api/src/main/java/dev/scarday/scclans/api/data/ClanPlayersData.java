package dev.scarday.scclans.api.data;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClanPlayersData {
    UUID uuid;

    int kills;
    int deaths;
}