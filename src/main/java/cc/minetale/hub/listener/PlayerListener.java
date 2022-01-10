package cc.minetale.hub.listener;

import cc.minetale.commonlib.util.MC;
import cc.minetale.hub.Hub;
import cc.minetale.hub.menu.ServerSelectorMenu;
import cc.minetale.hub.sidebar.HubSidebar;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.util.CyclicIterator;
import cc.minetale.hub.util.HubPlayer;
import cc.minetale.hub.util.ItemUtil;
import cc.minetale.hub.util.Visibility;
import cc.minetale.mlib.util.SoundsUtil;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.player.*;
import net.minestom.server.event.trait.PlayerEvent;
import net.minestom.server.item.Material;

import java.time.Duration;

public class PlayerListener {

    public static EventNode<PlayerEvent> events() {
        return EventNode.type("hub", EventFilter.PLAYER)
                .addListener(PlayerBlockInteractEvent.class, event -> event.setCancelled(true))
                .addListener(InventoryPreClickEvent.class, event -> event.setCancelled(true))
                .addListener(ItemDropEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerSwapItemEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerBlockPlaceEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerUseItemEvent.class, event -> {
                    var player = event.getPlayer();

                    event.setCancelled(true);

                    if(event.getHand() == Player.Hand.MAIN) {
                        var itemStack = event.getItemStack();
                        var material = itemStack.getMaterial();

                        if(material == Material.COMPASS) {
                            new ServerSelectorMenu(player);
                        } else if(material == Material.EMERALD || material == Material.PLAYER_HEAD || material == Material.NETHER_STAR) {
                            player.sendActionBar(Component.text("This item is currently under development.", NamedTextColor.RED));
                            SoundsUtil.playErrorSound(player);
                        } else if(material == Material.FIREWORK_STAR) {
                            var cooldown = HubPlayer.fromPlayer(player).getVisibilityCooldown();
                            if (cooldown.isReady()) {
                                int index = 0;

                                // TODO -> Find a better way (store the index somewhere else and extract the logic from the CyclicIterator)

                                for(var value : Visibility.values()) {
                                    if(value.getItemStack().equals(itemStack)) {
                                        index = value.ordinal();
                                    }
                                }

                                var visibility = new CyclicIterator<>(Visibility.values(), index).next();

                                player.getInventory().setItemStack(8, visibility.getItemStack());
                                SoundsUtil.playClickSound(player);

                                cooldown.refresh();
                            } else {
                                player.sendActionBar(Component.text("You are on cooldown for " + cooldown.getSecondsRemaining() + " seconds.", NamedTextColor.RED));
                                SoundsUtil.playErrorSound(player);
                            }
                        } else {
                            player.sendActionBar(Component.text("Unknown hotbar item.", NamedTextColor.RED));
                            SoundsUtil.playErrorSound(player);
                        }
                    }
                })
                .addListener(PlayerLoginEvent.class, event -> {
                    event.setSpawningInstance(Hub.getInstance().getContainer());
                })
                .addListener(PlayerDisconnectEvent.class, event -> {
                    HubSidebar.remove(event.getPlayer());
                })
                .addListener(PlayerSpawnEvent.class, event -> {
                    var player = event.getPlayer();

                    HubSidebar.createSidebar(player);

                    if(event.isFirstSpawn()) {
                        player.sendMessage(MC.SEPARATOR_80);
                        player.sendMessage(Component.empty());
                        player.sendMessage(Component.text()
                                .append(Component.text("Welcome to the ", NamedTextColor.GRAY))
                                .append(Component.text("MineTale Network", NamedTextColor.GOLD, TextDecoration.BOLD)));
                        player.sendMessage(Component.empty());
                        player.sendMessage(Component.text()
                                .append(Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
                                .append(Component.text("Website: ", NamedTextColor.GOLD))
                                .append(Component.text("https://minetale.cc", NamedTextColor.GRAY)));
                        player.sendMessage(Component.text()
                                .append(Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
                                .append(Component.text("Store: ", NamedTextColor.GOLD))
                                .append(Component.text("https://store.minetale.cc", NamedTextColor.GRAY)));
                        player.sendMessage(Component.text()
                                .append(Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
                                .append(Component.text("Discord: ", NamedTextColor.GOLD))
                                .append(Component.text("https://discord.minetale.cc", NamedTextColor.GRAY)));
                        player.sendMessage(Component.text()
                                .append(Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
                                .append(Component.text("Twitter: ", NamedTextColor.GOLD))
                                .append(Component.text("https://twitter.com/minetalecc", NamedTextColor.GRAY)));
                        player.sendMessage(Component.empty());
                        player.sendMessage(MC.SEPARATOR_80);

                        player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));

                        player.setGameMode(GameMode.ADVENTURE);

                        var inventory = player.getInventory();

                        inventory.setItemStack(0, ItemUtil.SERVER_SELECTOR);
                        inventory.setItemStack(1, ItemUtil.SHOP);
                        inventory.setItemStack(4, ItemUtil.PROFILE(player));
                        inventory.setItemStack(7, ItemUtil.LOBBY_SELECTOR);
                        ItemUtil.VISIBILITY_ITEM(player, item -> inventory.setItemStack(8, item));

                        player.setAllowFlying(true);
                    }

                    player.showTitle(Title.title(
                            Component.text("Welcome", NamedTextColor.GOLD),
                            Component.text("Welcome to MineTale", NamedTextColor.GRAY),
                            Title.Times.of(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1))
                    ));

                    player.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.MASTER, 1F, 2F));
                });
    }
}
