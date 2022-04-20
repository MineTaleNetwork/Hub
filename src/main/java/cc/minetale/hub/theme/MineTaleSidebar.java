package cc.minetale.hub.theme;

import cc.minetale.flame.util.FlamePlayer;
import cc.minetale.sodium.util.Colors;
import cc.minetale.sodium.util.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.scoreboard.Sidebar;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MineTaleSidebar {

    private final static Map<Player, Sidebar> sidebars = new HashMap<>();

    public static void createSidebar(Player player) {
        var sidebar = new Sidebar(Component.text("MineTale Network", Colors.BLUE, TextDecoration.BOLD));

        Arrays.asList(
                new Sidebar.ScoreboardLine("9", Message.scoreboardSeparator(), 9),
                new Sidebar.ScoreboardLine("8", Component.text("Online Players", Colors.BLUE, TextDecoration.BOLD), 8),
                new Sidebar.ScoreboardLine("7", Component.text().append(
                        Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD),
                        Component.text("0/0", NamedTextColor.WHITE)
                ).build(), 7),
                new Sidebar.ScoreboardLine("6", Component.empty(), 6),
                new Sidebar.ScoreboardLine("5", Component.text("Global Rank", Colors.BLUE, TextDecoration.BOLD), 5),
                new Sidebar.ScoreboardLine("4", Component.text().append(
                        Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD),
                        Component.text("Unknown", NamedTextColor.WHITE)
                ).build(), 4),
                new Sidebar.ScoreboardLine("3", Component.empty(), 3),
                new Sidebar.ScoreboardLine("2", Component.text("minetale.cc", Colors.BLUE), 2),
                new Sidebar.ScoreboardLine("1", Message.scoreboardSeparator(), 1)
        ).forEach(sidebar::createLine);

        sidebars.put(player, sidebar);

        MineTaleSidebar.update(player);
        sidebar.addViewer(player);
    }

    public static void update(Player player) {
        var sidebar = sidebars.get(player);

        if(sidebar != null) {
            sidebar.updateLineContent("7", Component.text().append(
                    Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD),
                    Component.text(0 + "/10000", NamedTextColor.WHITE)
            ).build());

            var rank = FlamePlayer.fromPlayer(player).getProfile().getGrant().getRank();

            sidebar.updateLineContent("4", Component.text().append(
                    Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD),
                    Component.text(rank.getName(), rank.getColor())
            ).build());
        }
    }

    public static void remove(Player player) {
        var sidebar = sidebars.get(player);

        if(sidebar != null) {
            sidebar.removeViewer(player);
        }

        sidebars.remove(player);
    }

}
