package cc.minetale.hub.menu;

import cc.minetale.hub.Hub;
import cc.minetale.hub.util.LobbyInstance;
import cc.minetale.mlib.canvas.*;
import cc.minetale.mlib.util.MenuUtil;
import cc.minetale.mlib.util.SoundsUtil;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.item.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

@Getter
public class HubSelectorMenu extends Menu {

    public HubSelectorMenu(Player player) {
        super(player, Component.text("Server Selector"), CanvasType.SIX_ROW);

        setFiller(FillingType.BORDER);

        var instances = new ArrayList<>(LobbyInstance.getHubMap().values());
        instances.sort(Comparator.comparing(LobbyInstance::getLobbyId));

        setFragment(48, MenuUtil.PREVIOUS_PAGE(this));
        setFragment(50, MenuUtil.NEXT_PAGE(this));

        var fragments = new Fragment[instances.size()];
        var pagination = new Pagination(10, 28);

        int i = 0;

        for (var lobbyInstance : instances) {
            var instance = lobbyInstance.getInstance();
            boolean connected = instance.getPlayers().contains(player);

            fragments[i] = Fragment.of(ItemStack.of(Material.EMERALD, ensureRange(i))
                    .withDisplayName(Component.text("Lobby #" + lobbyInstance.getLobbyId(), connected ?
                            Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false)) :
                            Style.style(NamedTextColor.GREEN, TextDecoration.ITALIC.as(false))))
                    .withLore(Arrays.asList(
                            Component.text("Players: " + instance.getPlayers().size() + "/150",
                                    Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                            Component.empty(),
                            connected ?
                                    Component.text("Already connected!",
                                            Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false))) :
                                    Component.text("Click to connect!", Style.style(NamedTextColor.YELLOW,
                                            TextDecoration.ITALIC.as(false)))
                    ))
                    .withMeta(builder -> {
                        if(connected)
                            builder.enchantment(Enchantment.UNBREAKING, (short) 1)
                                    .hideFlag(ItemHideFlag.HIDE_ENCHANTS);

                        return builder;
                    }), e -> {

                if(e.getClickType() == ClickType.LEFT_CLICK) {
                    if(instance == player.getInstance()) {
                        SoundsUtil.playErrorSound(player);
                    } else {
                        this.handleClose(player);
                        player.closeInventory();
                        player.teleport(Hub.getHub().getSpawn());
                        player.setInstance(instance);
                    }
                }
            });

            i++;
        }

        pagination.setFragments(fragments);
        setPagination(pagination);

        openMenu();
    }

    private int ensureRange(int value) {
        return Math.min(Math.max(value, 1), 64);
    }

    @Override
    public void close() {}

}
