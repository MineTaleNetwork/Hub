package cc.minetale.hub.menu;

import cc.minetale.commonlib.util.MC;
import cc.minetale.flame.util.MenuUtil;
import cc.minetale.hub.Hub;
import cc.minetale.hub.util.cooldown.Cooldown;
import cc.minetale.hub.util.LobbyInstance;
import cc.minetale.hub.util.cooldown.CooldownManager;
import cc.minetale.hub.util.cooldown.CooldownType;
import cc.minetale.mlib.fabric.ClickableItem;
import cc.minetale.mlib.fabric.FabricInventory;
import cc.minetale.mlib.fabric.content.FabricContents;
import cc.minetale.mlib.fabric.content.FabricProvider;
import cc.minetale.mlib.fabric.content.Pagination;
import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.event.inventory.InventoryOpenEvent;
import net.minestom.server.instance.SharedInstance;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.inventory.click.ClickType;
import net.minestom.server.item.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Getter
public class HubSelectorMenu implements FabricProvider {

    private final FabricInventory inventory;

    public HubSelectorMenu() {
        this.inventory = FabricInventory.builder()
                .provider(this)
                .type(InventoryType.CHEST_5_ROW)
                .build();
    }

    @Override
    public void init(Player player, FabricContents contents) {
        contents.fill(MenuUtil.FILLER);
        contents.fill(MenuUtil.AIR, 9, 35);

        List<LobbyInstance> lobbyInstances = new ArrayList<>(LobbyInstance.getHubMap().values());

        lobbyInstances.sort(Comparator.comparing(LobbyInstance::getLobbyId));

        ClickableItem[] items = new ClickableItem[lobbyInstances.size()];

        int i = 0;

        for (LobbyInstance lobbyInstance : lobbyInstances) {
            SharedInstance instance = lobbyInstance.getInstance();
            boolean connected = instance.getPlayers().contains(player);

            items[i] = ClickableItem.of(ItemStack.of(Material.EMERALD)
                    .withDisplayName(Component.text("Lobby #" + lobbyInstance.getLobbyId(), connected ? Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false)) : Style.style(NamedTextColor.GREEN, TextDecoration.ITALIC.as(false))))
                    .withLore(Arrays.asList(
                            Component.text("Players: " + instance.getPlayers().size() + "/150", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                            Component.empty(),
                            connected ? Component.text("Already connected!", Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false))) : Component.text("Click to connect!", Style.style(NamedTextColor.YELLOW, TextDecoration.ITALIC.as(false)))
                    ))
                    .withMeta(builder -> {
                        if(connected)
                            builder.enchantment(Enchantment.UNBREAKING, (short) 1).hideFlag(ItemHideFlag.HIDE_ENCHANTS);

                        return builder;
                    }), e -> {
                Player clicker = e.getPlayer();

                if(e.getClickType() == ClickType.LEFT_CLICK) {
                    if(!clicker.getInstance().getUniqueId().equals(instance.getUniqueId())) {
                        CooldownManager manager = Hub.getHub().getCooldownManager();

                        Cooldown cooldown = manager.getCooldownByType(player.getUuid(), CooldownType.LOBBY_SWITCH);

                        if(cooldown != null && !cooldown.hasExpired()) {
                            player.sendActionBar(Component.text("You are on cooldown for another " + cooldown.getSecondsRemaining() + " seconds.", NamedTextColor.RED));
                            player.playSound(Sound.sound(Key.key("block.note_block.bass"), Sound.Source.MASTER, 1F, 0.5F));
                        } else {
                            manager.putCooldown(player.getUuid(), CooldownType.LOBBY_SWITCH);
                            clicker.setInstance(instance);
                        }

                        this.inventory.close(player);
                    }
                }
            });

            i++;
        }

        Pagination pagination = contents.getPagination();

        pagination.setItems(items);
        pagination.setItemsPerPage(27);
        pagination.addToIterator(contents.iterator(9));

        MenuUtil.addPreviousPage(39, this.inventory, contents, "Lobbies");
        MenuUtil.addNextPage(41, this.inventory, contents, "Lobbies");
    }

    @Override
    public void open(InventoryOpenEvent event, FabricContents contents) {
        Inventory inventory = event.getInventory();

        if(inventory != null)
            MenuUtil.setPaginatedTitle(inventory, contents.getPagination(), "Lobbies");
    }
}
