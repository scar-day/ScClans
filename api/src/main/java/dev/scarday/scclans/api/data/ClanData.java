package dev.scarday.scclans.api.data;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClanData {
    int id;
    String name;

    List<ClanPlayersData> players;
}
