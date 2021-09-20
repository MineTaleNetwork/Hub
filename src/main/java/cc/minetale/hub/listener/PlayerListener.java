package cc.minetale.hub.listener;

import cc.minetale.commonlib.util.MC;
import cc.minetale.hub.Hub;
import cc.minetale.hub.manager.HubManager;
import cc.minetale.hub.menu.HubSelectorMenu;
import cc.minetale.hub.menu.ServerSelectorMenu;
import cc.minetale.hub.sidebar.HubSidebar;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.util.*;
import cc.minetale.hub.util.cooldown.Cooldown;
import cc.minetale.hub.util.cooldown.CooldownManager;
import cc.minetale.hub.util.cooldown.CooldownType;
import cc.minetale.mlib.util.ProfileUtil;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.title.Title;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.player.*;
import net.minestom.server.event.trait.EntityEvent;
import net.minestom.server.inventory.PlayerInventory;
import net.minestom.server.item.ItemStack;
import net.minestom.server.tag.Tag;
import net.minestom.server.utils.time.Tick;

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
                        ItemStack itemStack = event.getItemStack();
                        String type = itemStack.getTag(Tag.String("type"));

                        switch (type) {
                            case "SERVER_SELECTOR": {
                                player.openInventory(new ServerSelectorMenu());
                                break;
                            }
                            case "SHOP":
                            case "PROFILE":
                                player.sendActionBar(Component.text("This item is currently under development.", MC.CC.RED.getTextColor()));
                                player.playSound(Sound.sound(Key.key("block.note_block.bass"), Sound.Source.MASTER, 1F, 0.5F));
                                break;
                            case "LOBBY_SELECTOR":
                                Hub.getHub().getHubSelectorMenu().getInventory().open(player);
                                break;
                            case "VISIBILITY_SELECTOR":
                                CooldownManager manager = Hub.getHub().getCooldownManager();

                                Cooldown cooldown = manager.getCooldownByType(player.getUuid(), CooldownType.VISIBILITY);

                                if(cooldown != null && !cooldown.hasExpired()) {
                                    player.sendActionBar(MC.component("You are on cooldown for another " + cooldown.getSecondsRemaining() + " seconds.", MC.CC.RED));
                                    player.playSound(Sound.sound(Key.key("block.note_block.bass"), Sound.Source.MASTER, 1F, 0.5F));
                                } else {
                                    manager.putCooldown(player.getUuid(), CooldownType.VISIBILITY);
                                    int index = itemStack.getTag(Tag.Integer("index"));

                                    Visibility visibility = new CyclicIterator<>(Visibility.values(), index).next();

                                    player.getInventory().setItemStack(8, visibility.getItemStack());
                                    player.playSound(Sound.sound(Key.key("ui.button.click"), Sound.Source.MASTER, 1F, 2F));

                                    ProfileUtil.getAssociatedProfile(player).thenAccept(profile -> profile.getOptionsProfile().setVisibilityIndex(index));
                                }
                                break;
                        }
                    }
                })
                .addListener(PlayerLoginEvent.class, event -> {
                    final Player player = event.getPlayer();

                    event.setSpawningInstance(HubManager.getRandomInstance().getInstance());
                    player.setRespawnPoint(Hub.getHub().getSpawn());
                })
                .addListener(PlayerDisconnectEvent.class, event -> {
                    final var player = event.getPlayer();

//                    RankUtil.hasMinimumRank(player, Rank.getRank("Owner"), rankCallback -> {
//                        if (rankCallback.isMinimum()) {
//                            Profile profile = rankCallback.getProfile();
//
//                            Instance instance = player.getInstance();
//
//                            if(instance != null)
//                                instance.sendMessage(MC.Chat.notificationMessage("Lobby",
//                                        Component.text()
//                                                .append(
//                                                        profile.api().getChatFormat(),
//                                                        Component.text(" has left the lobby.", profile.getGrant().api().getRank().api().getRankColor().getTextColor())
//                                                ).build()
//                                ));
//                        }
//                    });
                    HubSidebar sidebar = HubSidebar.getSidebars().get(event.getPlayer().getUuid());

                    if(sidebar != null)
                        sidebar.remove();
                })
                .addListener(PlayerSpawnEvent.class, event -> {
                    final var player = event.getPlayer();

                    new HubSidebar(player);

                    if(event.isFirstSpawn()) {
                        player.sendMessage(MC.Style.SEPARATOR_80);
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
                        player.sendMessage(MC.Style.SEPARATOR_80);
                    }

                    player.setGameMode(GameMode.ADVENTURE);

                    PlayerInventory inventory = player.getInventory();

                    inventory.setItemStack(0, ItemUtil.SERVER_SELECTOR);
                    inventory.setItemStack(1, ItemUtil.SHOP);
                    inventory.setItemStack(4, ItemUtil.PROFILE(player));
                    inventory.setItemStack(7, ItemUtil.LOBBY_SELECTOR);
                    ItemUtil.VISIBILITY_ITEM(player, item -> inventory.setItemStack(8, item));

                    player.setAllowFlying(true);

                    player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));

                    player.showTitle(Title.title(Component.text("Welcome", MC.CC.GOLD.getTextColor()), Component.text("Welcome to MineTale", MC.CC.GRAY.getTextColor()),
                            Title.Times.of(Duration.ofSeconds(1), Duration.ofSeconds(3), Duration.ofSeconds(1))));

                    MinecraftServer.getSchedulerManager().buildTask(() -> {
                        player.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.MASTER, 1F, 2F));
                    }).delay(Tick.server(5)).schedule();

//                    RankUtil.hasMinimumRank(player, Rank.getRank("Owner"), rankCallback -> {
//                        if (rankCallback.isMinimum()) {
//                            Profile profile = rankCallback.getProfile();
//
//                            Instance instance = player.getInstance();
//
//                            if (instance != null) {
//                                instance.sendMessage(MC.Chat.notificationMessage("Lobby",
//                                        MC.component(
//                                                profile.api().getChatFormat(),
//                                                MC.component(" has joined the lobby.", profile.getGrant().api().getRank().api().getRankColor())
//                                        )
//                                ));
//                            }
//                        }
//                    });
                });
    }
}
