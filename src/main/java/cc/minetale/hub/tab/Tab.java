package cc.minetale.hub.tab;

import cc.minetale.hub.Hub;
import cc.minetale.postman.StringUtil;
import cc.minetale.sodium.util.Colors;
import cc.minetale.sodium.util.Message;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.network.packet.server.play.PlayerInfoPacket;

import java.util.*;

@Getter
public class Tab {

    private final Set<Player> viewers = new HashSet<>();
    private final TabEntry[][] entries = new TabEntry[4][20];

    public Tab() {
        for(var row = 0; row < 4; row++) {
            for(var col = 0; col < 20; col++) {
                entries[row][col] = new TabEntry(TabEntry.DEFAULT_SKIN, Component.empty());
            }
        }
    }

    public static void initializeTab() {
        for(var row = 0; row < 4; row++) {
            for(var col = 0; col < 20; col++) {
                var id = row + "-" + (col < 10 ? "0" : "") + col;
                var team = MinecraftServer.getTeamManager().createTeam(id);

                team.addMember(id);
            }
        }
    }

    public void update(int x, int y, Component display) {
        var entry = entries[x][y];
        entry.setDisplay(display);

        if(viewers.size() != 0) {
            for (var viewer : viewers) {
                viewer.sendPacket(updateEntryPacket(entry.getUuid(), entry.getDisplay()));
            }
        }
    }

    public void update(int x, int y, Player player, Component display) {
        player.sendPacket(updateEntryPacket(entries[x][y].getUuid(), display));
    }

    public void show(Player player) {
        viewers.add(player);

        for(var row = 0; row < 4; row++) {
            for(var col = 0; col < 20; col++) {
                var id = row + "-" + (col < 10 ? "0" : "") + col;
                var entry = entries[row][col];

                player.sendPacket(addEntryPacket(id, entry));
            }
        }
    }

    private static PlayerInfoPacket updateEntryPacket(UUID uuid, Component display) {
        var playerEntry = new PlayerInfoPacket.UpdateDisplayName(
                uuid,
                display
        );

        return new PlayerInfoPacket(PlayerInfoPacket.Action.UPDATE_DISPLAY_NAME, Collections.singletonList(playerEntry));
    }

    private static PlayerInfoPacket addEntryPacket(String name, TabEntry entry) {
        var skin = entry.getSkin();

        var playerEntry = new PlayerInfoPacket.AddPlayer(
                entry.getUuid(),
                name,
                Collections.singletonList(new PlayerInfoPacket.AddPlayer.Property("textures", skin.textures(), skin.signature())),
                GameMode.CREATIVE,
                0,
                entry.getDisplay()
        );

        return new PlayerInfoPacket(PlayerInfoPacket.Action.ADD_PLAYER, Collections.singletonList(playerEntry));
    }

    public static Component header() {
        return Component.join(JoinConfiguration.separator(Component.newline()), Arrays.asList(
                Component.empty(),
                Component.text("MineTale Network", Colors.BLUE, TextDecoration.BOLD),
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
                            Component.text("Lobby-1", Colors.BLUE),
                            Component.text(" (", NamedTextColor.GRAY),
                            Component.text("Ping: " + player.getLatency() + "ms", Colors.BLUE),
                            Component.text(")", NamedTextColor.GRAY)
                    ),
                    Component.text().append(
                            Component.text("There are currently ", NamedTextColor.GRAY),
                            Component.text(String.valueOf(instance.getPlayers().size()), Colors.BLUE),
                            Component.text(" players connected", NamedTextColor.GRAY)
                    ),
                    Component.empty()
            ));
        } else {
            return Component.empty();
        }
    }

}
