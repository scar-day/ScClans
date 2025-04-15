package dev.scarday.scclans.api;

import dev.scarday.scclans.api.data.ClanData;

public interface Clan {
    ClanData getClan(String name);
    void removeClan(String name);

    boolean isExistsClan(String name);
}
