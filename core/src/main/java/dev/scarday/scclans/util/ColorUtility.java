package dev.scarday.scclans.util;

import lombok.experimental.UtilityClass;
import net.kyori.adventure.text.Component;

@UtilityClass
public class ColorUtility {
    public static Component colorize(String message) {
        return Component.text(message);
    }
}
