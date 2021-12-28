package cc.minetale.hub.tab;

import cc.minetale.hub.Hub;
import cc.minetale.hub.manager.HubManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;

import java.util.Arrays;

public class Tab {

    public static Component header() {
        return Component.join(JoinConfiguration.separator(Component.newline()), Arrays.asList(
                Component.empty(),
                Component.text("MineTale Network", NamedTextColor.GOLD, TextDecoration.BOLD),
                Component.text().append(
                        Component.text("    www.minetale.cc", NamedTextColor.GRAY),
                        Component.text(" * ", NamedTextColor.DARK_GRAY),
                        Component.text("store.minetale.cc    ", NamedTextColor.GRAY)
                ),
                Component.empty()
        ));
    }

    public static Component footer(Player player) {
        var instance = player.getInstance();
        var container = Hub.getInstance().getContainer();

        if(instance != null) {
            return Component.join(JoinConfiguration.separator(Component.newline()), Arrays.asList(
                    Component.empty(),
                    Component.text().append(
                            Component.text("    Lobby-" + HubManager.getLobbyId(instance.getUniqueId()), NamedTextColor.GOLD),
                            Component.text(" (", NamedTextColor.GRAY),
                            Component.text("Ping: " + player.getLatency() + "ms", NamedTextColor.GOLD),
                            Component.text(")", NamedTextColor.GRAY),
                            Component.text(" * ", NamedTextColor.DARK_GRAY),
                            Component.text("minetale.cc/discord    ", NamedTextColor.GOLD)
                    ),
                    Component.text().append(
                            Component.text("    There are currently ", NamedTextColor.GRAY),
                            Component.text(String.valueOf(instance.getPlayers().size()), NamedTextColor.GOLD),
                            Component.text(" players connected    ", NamedTextColor.GRAY)
                    ),
                    Component.empty()
            ));
        } else {
            return Component.empty();
        }
    }

}
