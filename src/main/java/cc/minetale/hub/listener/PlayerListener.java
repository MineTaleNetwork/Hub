package cc.minetale.hub.listener;

import cc.minetale.hub.Hub;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.theme.MineTaleSidebar;
import cc.minetale.hub.util.ItemUtil;
import cc.minetale.sodium.util.Colors;
import cc.minetale.sodium.util.Message;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.GameMode;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.inventory.InventoryPreClickEvent;
import net.minestom.server.event.item.ItemDropEvent;
import net.minestom.server.event.player.*;
import net.minestom.server.event.trait.PlayerEvent;
import net.minestom.server.instance.block.BlockFace;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.network.packet.client.play.ClientSteerVehiclePacket;

public class PlayerListener {

    public static EventNode<PlayerEvent> events() {
        return EventNode.type("hub", EventFilter.PLAYER)
                .addListener(PlayerPacketEvent.class, event -> {
                    var player = event.getPlayer();

                    if (event.getPacket() instanceof ClientSteerVehiclePacket packet) {
                        if (packet.flags() == 2) {
                            var vehicle = player.getVehicle();

                            if(vehicle != null) {
                                vehicle.remove();
                            }
                        }
                    }
                })
                .addListener(PlayerMoveEvent.class, event -> {
                    var player = event.getPlayer();

                    if (player.getGameMode() != GameMode.CREATIVE && !player.getInstance()
                            .getBlock(player.getPosition()
                                    .relative(BlockFace.BOTTOM))
                            .isAir()) {
                        player.setAllowFlying(true);
                    }
                })
                .addListener(PlayerBlockInteractEvent.class, event -> event.setCancelled(true))
                .addListener(InventoryPreClickEvent.class, event -> event.setCancelled(true))
                .addListener(ItemDropEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerSwapItemEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerBlockPlaceEvent.class, event -> event.setCancelled(true))
                .addListener(PlayerLoginEvent.class, event -> event.setSpawningInstance(Hub.getInstance().getContainer()))
                .addListener(PlayerDisconnectEvent.class, event -> {
                    var player = event.getPlayer();

                    if(player.getVehicle() != null) {
                        player.getVehicle().remove();
                    }

                    MineTaleSidebar.remove(player);
                })
                .addListener(PlayerStartFlyingEvent.class, event -> {
                    var player = event.getPlayer();

                    if (player.getGameMode() != GameMode.CREATIVE) {
                        player.setAllowFlying(false);
                        player.setFlying(false);
                        player.setVelocity(player.getPosition().direction().mul(50).withY(20));
                        player.playSound(Sound.sound(Key.key("entity.blaze.hurt"), Sound.Source.MASTER, 1.0F, 0.8F));
                    }
                })
                .addListener(PlayerSpawnEvent.class, event -> {
                    var player = event.getPlayer();

                    if(event.isFirstSpawn()) {
                        player.setFieldViewModifier(0);
                        MineTaleSidebar.createSidebar(player);
                        Hub.getInstance().getTab().show(player);

                        player.sendMessage(Message.chatSeparator());
                        player.sendMessage(Component.empty());
                        player.sendMessage(Component.text()
                                .append(Component.text("Welcome to the ", NamedTextColor.GRAY))
                                .append(Component.text("MineTale Network", Colors.BLUE, TextDecoration.BOLD)));
                        player.sendMessage(Component.empty());
                        player.sendMessage(Component.text()
                                .append(Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
                                .append(Component.text("Website: ", Colors.BLUE))
                                .append(Component.text("https://minetale.cc", NamedTextColor.GRAY)));
                        player.sendMessage(Component.text()
                                .append(Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
                                .append(Component.text("Store: ", Colors.BLUE))
                                .append(Component.text("https://store.minetale.cc", NamedTextColor.GRAY)));
                        player.sendMessage(Component.text()
                                .append(Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
                                .append(Component.text("Discord: ", Colors.BLUE))
                                .append(Component.text("https://discord.minetale.cc", NamedTextColor.GRAY)));
                        player.sendMessage(Component.text()
                                .append(Component.text("» ", NamedTextColor.DARK_GRAY, TextDecoration.BOLD))
                                .append(Component.text("Twitter: ", Colors.BLUE))
                                .append(Component.text("https://twitter.com/minetalecc", NamedTextColor.GRAY)));
                        player.sendMessage(Component.empty());
                        player.sendMessage(Message.chatSeparator());

                        player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));
                        player.setGameMode(GameMode.ADVENTURE);

                        var inventory = player.getInventory();

                        inventory.setItemStack(0, ItemUtil.SERVER_SELECTOR);
                        inventory.setItemStack(1, ItemUtil.SHOP);
                        inventory.setItemStack(3, ItemUtil.PROFILE(player));
                        inventory.setItemStack(4, ItemUtil.COSMETIC_SELECTOR);
                        inventory.setItemStack(7, ItemUtil.LOBBY_SELECTOR);
                        inventory.setItemStack(8, ItemUtil.VISIBILITY_ITEM(player));

                        player.setAllowFlying(true);
                    }

                    player.playSound(Sound.sound(Key.key("entity.player.levelup"), Sound.Source.MASTER, 1F, 2F));
                });
    }

}
