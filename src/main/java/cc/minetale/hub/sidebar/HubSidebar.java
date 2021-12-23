package cc.minetale.hub.sidebar;

import cc.minetale.commonlib.util.MC;
import cc.minetale.flame.util.FlamePlayer;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.scoreboard.Sidebar;

import java.util.*;

public class HubSidebar extends Sidebar {

    @Getter private final static Map<UUID, HubSidebar> sidebars = new HashMap<>();
    public final Player player;

    public HubSidebar(Player player) {
        super(Component.text("MineTale Network", NamedTextColor.GOLD, TextDecoration.BOLD));

        createLine(new Sidebar.ScoreboardLine("9", MC.SEPARATOR_32, 9));
        createLine(new Sidebar.ScoreboardLine("8", Component.text("Online Players", NamedTextColor.GOLD, TextDecoration.BOLD), 8));
        createLine(new Sidebar.ScoreboardLine("7", Component.text().append(
                Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD),
                Component.text("0/0", NamedTextColor.WHITE)
        ).build(), 7));
        createLine(new Sidebar.ScoreboardLine("6", Component.empty(), 6));
        createLine(new Sidebar.ScoreboardLine("5", Component.text("Global Rank", NamedTextColor.GOLD, TextDecoration.BOLD), 5));
        createLine(new Sidebar.ScoreboardLine("4", Component.text().append(
                Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD),
                Component.text("Unknown", NamedTextColor.WHITE)
        ).build(), 4));
        createLine(new Sidebar.ScoreboardLine("3", Component.empty(), 3));
        createLine(new Sidebar.ScoreboardLine("2", Component.text("minetale.cc", NamedTextColor.GOLD), 2));
        createLine(new Sidebar.ScoreboardLine("1", MC.SEPARATOR_32, 1));

        this.player = player;

        this.addViewer(player);
        this.update();

        sidebars.put(player.getUuid(), this);
    }

    public void update() {
        updateLineContent("7", Component.text().append(
                Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD),
                Component.text(0 + "/10000", NamedTextColor.WHITE)
        ).build());

        var rank = FlamePlayer.fromPlayer(this.player).getProfile().getGrant().getRank();

        updateLineContent("4", Component.text().append(
                Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD),
                Component.text(rank.getName(), rank.getColor())
        ).build());
    }

    public void remove() {
        this.removeViewer(this.player);
        sidebars.remove(this.player.getUuid());
    }

}
