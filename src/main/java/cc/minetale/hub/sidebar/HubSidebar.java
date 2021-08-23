package cc.minetale.hub.sidebar;

import cc.minetale.mlib.util.ProfileUtil;
import com.customwrld.commonlib.modules.rank.Rank;
import com.customwrld.commonlib.util.MC;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.scoreboard.Sidebar;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class HubSidebar extends Sidebar {

    @Getter private final static Map<UUID, HubSidebar> sidebars = new HashMap<>();
    public final Player player;

    public HubSidebar(Player player) {
        super(Component.text("MineTale Network", MC.CC.GOLD.getTextColor(), TextDecoration.BOLD));

        this.createLine(new Sidebar.ScoreboardLine("9", MC.Style.SCOREBOARD_SEPARATOR, 9));
        this.createLine(new Sidebar.ScoreboardLine("8", Component.text("Online Players", MC.CC.GOLD.getTextColor(), TextDecoration.BOLD), 8));
        this.createLine(new Sidebar.ScoreboardLine("7", Component.text()
                .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                .append(Component.text("???/???", MC.CC.WHITE.getTextColor()))
                .build(), 7));
        this.createLine(new Sidebar.ScoreboardLine("6", Component.empty(), 6));
        this.createLine(new Sidebar.ScoreboardLine("5", Component.text("Global Rank", MC.CC.GOLD.getTextColor(), TextDecoration.BOLD), 5));
        this.createLine(new Sidebar.ScoreboardLine("4", Component.text()
                .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                .append(Component.text("???", MC.CC.WHITE.getTextColor()))
                .build(), 4));
        this.createLine(new Sidebar.ScoreboardLine("3", Component.empty(), 3));
        this.createLine(new Sidebar.ScoreboardLine("2", Component.text("minetale.cc", MC.CC.GOLD.getTextColor()), 2));
        this.createLine(new Sidebar.ScoreboardLine("1", MC.Style.SCOREBOARD_SEPARATOR, 1));

        this.player = player;

        this.addViewer(player);

        this.update();

        sidebars.put(player.getUuid(), this);
    }

    public void update() {
        this.updateLineContent("7", Component.text()
                .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                .append(Component.text(ThreadLocalRandom.current().nextInt(1000) + "/1000", MC.CC.WHITE.getTextColor()))
                .build());

        ProfileUtil.getAssociatedProfile(this.player).thenAccept(profile -> {
            Rank rank = profile.api().getActiveGrant().api().getRank();

            this.updateLineContent("4", Component.text()
                    .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                    .append(Component.text(rank.getName(), rank.api().getRankColor().getTextColor()))
                    .build());
        });
    }

    public void remove() {
        sidebars.remove(this.player.getUuid());
    }
}
