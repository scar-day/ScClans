package dev.scarday.scclans.api.data;

import lombok.Data;

import java.util.List;

@Data
public class ClanData {
    int id;
    String name;

    List<ClanPlayersData> players;
}
