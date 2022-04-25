package cc.minetale.hub.menu;

import cc.minetale.hub.Hub;
import cc.minetale.mlib.canvas.CanvasType;
import cc.minetale.mlib.canvas.Filler;
import cc.minetale.mlib.canvas.Fragment;
import cc.minetale.mlib.canvas.template.Menu;
import cc.minetale.mlib.canvas.template.PaginatedMenu;
import cc.minetale.mlib.util.SoundsUtil;
import cc.minetale.sodium.util.MathUtil;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.item.Enchantment;
import net.minestom.server.item.ItemHideFlag;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.Arrays;

@Getter
public class HubSelectorMenu extends PaginatedMenu {

    public HubSelectorMenu(Player player) {
        super(player, Component.text("Server Selector"), CanvasType.THREE_ROW);
    }

    @Override
    public Fragment[] getPaginatedFragments(Player player) {
        var fragments = new Fragment[1];

//        var instances = new ArrayList<>(Hub.getInstance().getContainer().getSharedInstances());
//        instances.sort(Comparator.comparing(instance -> HubManager.getLobbyId(instance.getUniqueId())));

        var connected = true;

        for(var i = 0; i < fragments.length; i++) {
            fragments[i] = Fragment.of(ItemStack.of(Material.EMERALD, MathUtil.clamp(i, 1, 64))
                    .withDisplayName(Component.text("Lobby #" + i, connected ?
                            Style.style(NamedTextColor.RED, TextDecoration.ITALIC.withState(false)) :
                            Style.style(NamedTextColor.GREEN, TextDecoration.ITALIC.withState(false))))
                    .withLore(Arrays.asList(
                            Component.text("Players: " + 1 + "/150",
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
                    }), event -> {
                if(event.getClickType() == ClickType.LEFT_CLICK) {
                    if(true == true) {
                        SoundsUtil.playErrorSound(player);
                    } else {
                        this.handleClose(player);
                        player.closeInventory();
                        player.teleport(Hub.SPAWN);
//                        player.setInstance(instance);
                    }
                }
            });
        }

        return fragments;
    }

    @Override
    public void close() {}

}
