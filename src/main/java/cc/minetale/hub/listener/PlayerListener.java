package cc.minetale.hub.listener;

import com.customwrld.commonlib.util.MC;
import cc.minetale.hub.Hub;
import cc.minetale.hub.manager.HubManager;
import cc.minetale.hub.menu.ServerSelector;
import cc.minetale.hub.sidebar.HubSidebar;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.util.CyclicIterator;
import cc.minetale.hub.util.ItemUtil;
import cc.minetale.hub.util.Visibility;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.player.*;
import net.minestom.server.event.trait.EntityEvent;
import net.minestom.server.inventory.PlayerInventory;
import net.minestom.server.tag.Tag;

import java.time.Duration;

public class PlayerListener {

    public static EventNode<EntityEvent> events() {
        return EventNode.type("player-events", EventFilter.ENTITY)
                .addListener(InventoryPreClickEvent.class, event -> event.setCancelled(true))
                .addListener(ItemDropEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerSwapItemEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerBlockBreakEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerBlockPlaceEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerUseItemEvent.class, event -> {
                    var player = event.getPlayer();

                    event.setCancelled(true);

                    if(event.getHand() == Player.Hand.MAIN) {
                        String type = event.getItemStack().getTag(Tag.String("type"));

                        switch (type) {
                            case "SERVER_SELECTOR": {
                                player.openInventory(new ServerSelector());
                                break;
                            }
                            case "SHOP":
                            case "PROFILE":
                            case "COSMETIC_SELECTOR":
                                player.sendMessage(Component.text("This item is currently under development.", MC.CC.RED.getTextColor()));
                                player.playSound(Sound.sound(Key.key("block.note_block.bass"), Sound.Source.MASTER, 1F, 0.5F));
                                break;
                            case "VISIBILITY_SELECTOR":
                                int index = 1; // TODO: Get index

                                Visibility visibility = new CyclicIterator<>(Visibility.values(), index).next();

                                player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 1F, 2F));
                                break;
                        }
                    }
                })
                .addListener(PlayerLoginEvent.class, event -> {
                    final Player player = event.getPlayer();

                    event.setSpawningInstance(HubManager.getRandomInstance());
                    player.setRespawnPoint(Hub.spawn);
                })
                .addListener(PlayerDisconnectEvent.class, event -> HubSidebar.getSidebars().get(event.getPlayer().getUuid()).remove())
                .addListener(PlayerSpawnEvent.class, event -> {
                    final Player player = event.getPlayer();

                    Integer playerVisibility = Visibility.ALL.ordinal();
//                    HubExtension.visibilityMap.put(player.getUuid(), playerVisibility);

                    new HubSidebar(player);

                    player.sendMessage(MC.Style.SEPARATOR);
                    player.sendMessage(Component.empty());
                    player.sendMessage(Component.text()
                            .append(Component.text("Welcome to the ", MC.CC.GRAY.getTextColor()))
                            .append(Component.text("MineTale Network", MC.CC.GOLD.getTextColor(), TextDecoration.BOLD)));
                    player.sendMessage(Component.empty());
                    player.sendMessage(Component.text()
                            .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                            .append(Component.text("Website: ", MC.CC.GOLD.getTextColor()))
                            .append(Component.text("https://minetale.cc", MC.CC.GRAY.getTextColor())));
                    player.sendMessage(Component.text()
                            .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                            .append(Component.text("Store: ", MC.CC.GOLD.getTextColor()))
                            .append(Component.text("https://store.minetale.cc", MC.CC.GRAY.getTextColor())));
                    player.sendMessage(Component.text()
                            .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                            .append(Component.text("Discord: ", MC.CC.GOLD.getTextColor()))
                            .append(Component.text("https://discord.minetale.cc", MC.CC.GRAY.getTextColor())));
                    player.sendMessage(Component.text()
                            .append(Component.text("\u00BB ", MC.CC.DARK_GRAY.getTextColor(), TextDecoration.BOLD))
                            .append(Component.text("Twitter: ", MC.CC.GOLD.getTextColor()))
                            .append(Component.text("https://twitter.com/minetalecc", MC.CC.GRAY.getTextColor())));
                    player.sendMessage(Component.empty());
                    player.sendMessage(MC.Style.SEPARATOR);

                    player.setGameMode(GameMode.ADVENTURE);

                    PlayerInventory inventory = player.getInventory();

                    inventory.setItemStack(0, ItemUtil.SERVER_SELECTOR);
                    inventory.setItemStack(1, ItemUtil.SHOP);
                    inventory.setItemStack(4, ItemUtil.PROFILE(player));
                    inventory.setItemStack(7, ItemUtil.COSMETIC_SELECTOR);
                    inventory.setItemStack(8, ItemUtil.VISIBILITY_ITEM(player.getUuid()));

                    player.setAllowFlying(true);

                    player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));

                    player.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.MASTER, 1F, 2F));
                    player.sendMessage(MC.Style.component("Current Instance: " + HubManager.getName(player.getInstance()), MC.CC.GOLD));

                    player.showTitle(Title.title(Component.text("Welcome", MC.CC.GOLD.getTextColor()), Component.text("Welcome to MineTale", MC.CC.GRAY.getTextColor()),
                            Title.Times.of(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1))));
                });
    }
}
