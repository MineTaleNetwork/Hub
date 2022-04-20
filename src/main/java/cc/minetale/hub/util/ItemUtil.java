package cc.minetale.hub.util;

import cc.minetale.sodium.util.Colors;
import cc.minetale.sodium.util.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.color.Color;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.item.ItemHideFlag;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.firework.FireworkEffect;
import net.minestom.server.item.firework.FireworkEffectType;
import net.minestom.server.item.metadata.FireworkEffectMeta;
import net.minestom.server.item.metadata.PlayerHeadMeta;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

public class ItemUtil {

    public static final ItemStack VISIBILITY_ALL = ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text().append(
                    Component.text("All Players Visible ", Style.style(NamedTextColor.GREEN, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                    Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
            ).build())
            .withLore(Collections.singletonList(Component.text("Right Click to cycle through visibility!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
            .withMeta(FireworkEffectMeta.class, meta -> {
                var colors = Collections.singletonList(new Color(NamedTextColor.GREEN));

                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, colors, colors));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            });

    public static final ItemStack VISIBILITY_VIP = ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(Component.text("VIPs Visible ", Style.style(NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                    .append(Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))))
                    .build())
            .withLore(Collections.singletonList(Component.text("Right Click to cycle through visibility!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
            .withMeta(FireworkEffectMeta.class, meta -> {
                var colors = Collections.singletonList(new Color(NamedTextColor.DARK_PURPLE));

                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, colors, colors));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            });

    public static final ItemStack VISIBILITY_NONE = ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(Component.text("No Players Visible ", Style.style(Colors.RED, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                    .append(Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))))
                    .build())
            .withLore(Collections.singletonList(Component.text("Right Click to cycle through visibility!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
            .withMeta(FireworkEffectMeta.class, meta -> {
                var colors = Collections.singletonList(new Color(Colors.RED));

                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, colors, colors));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            });

    public static final ItemStack SERVER_SELECTOR = ItemStack.of(Material.COMPASS)
            .withDisplayName(Component.text().append(
                    Component.text("Server Selector ", Style.style(NamedTextColor.BLUE, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                    Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
            ).build())
            .withLore(Collections.singletonList(Component.text("Right Click to select a server!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))));

    public static final ItemStack SHOP = ItemStack.of(Material.EMERALD)
            .withDisplayName(Component.text().append(
                    Component.text("Shop ", Style.style(NamedTextColor.GREEN, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                    Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
            ).build())
            .withLore(List.of(Component.text("Right Click to open the shop!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))));

    public static ItemStack PROFILE(Player player) {
        UUID uuid = player.getUuid();

        return ItemStack.of(Material.PLAYER_HEAD)
                .withDisplayName(Component.text().append(
                        Component.text("Profile ", Style.style(NamedTextColor.LIGHT_PURPLE, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                        Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
                ).build())
                .withLore(List.of(Component.text("Right Click to open your profile!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
                .withMeta(PlayerHeadMeta.class, meta -> meta.skullOwner(uuid).playerSkin(PlayerSkin.fromUuid(uuid.toString())));
    }

    public static final ItemStack COSMETIC_SELECTOR = ItemStack.of(Material.BEACON)
            .withDisplayName(Component.text().append(
                    Component.text("Cosmetic Selector ", Style.style(NamedTextColor.YELLOW, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                    Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
            ).build())
            .withLore(Collections.singletonList(Component.text("Right Click to select a cosmetic!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))));

    public static ItemStack LOBBY_SELECTOR = ItemStack.of(Material.NETHER_STAR)
            .withDisplayName(Component.text().append(
                    Component.text("Lobby Selector ", Style.style(NamedTextColor.AQUA, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                    Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
            ).build())
            .withLore(List.of(Component.text("Right Click to select a lobby!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))));

    public static ItemStack VISIBILITY_ITEM(Player player) {
        var profile = HubPlayer.fromPlayer(player).getProfile();

        return switch (profile.getHubProfile().getVisibility()) {
            case ALL -> VISIBILITY_ALL;
            case VIP -> VISIBILITY_VIP;
            case NONE -> VISIBILITY_NONE;
        };
    }

    public static ItemStack getSurvivalItem() {
        return ItemStack.of(Material.GRASS_BLOCK)
                .withDisplayName(Component.text("Survival", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Play classic survival", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("with your friends!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }

    public static ItemStack getPracticeItem() {
        return ItemStack.of(Material.IRON_SWORD)
                .withDisplayName(Component.text("Practice", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Practice your pvp skills and", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("become the best of the best!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }

    public static ItemStack getParkourItem() {
        return ItemStack.of(Material.GOLDEN_BOOTS)
                .withDisplayName(Component.text("Parkour", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Maneuver through various obstacle", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("courses without falling or failing!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }

    public static ItemStack getSkyWarsItem() {
        return ItemStack.of(Material.ENDER_EYE)
                .withDisplayName(Component.text("Sky Wars", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Fight against other players in", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("the sky, last player remaining wins!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }

    public static ItemStack getWoolWarsItem() {
        return ItemStack.of(Material.WHITE_WOOL)
                .withDisplayName(Component.text("Wool Wars", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Quickly make your way to the", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("top without touching the floor,", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("and knock players off in the process!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }

    public static ItemStack getBedWarsItem() {
        return ItemStack.of(Material.RED_BED)
                .withDisplayName(Component.text("Bed Wars", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Protect your bed and seek out", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("enemy players beds, and destroy", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("them to become victorious!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }

    public static ItemStack getHousingItem() {
        return ItemStack.of(Material.OAK_DOOR)
                .withDisplayName(Component.text("Housing", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Customize and build on your own", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("personal plot, hang out with your", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("friends, visit other peoples", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("houses, socialize, and more!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }

    public static ItemStack getArcadeGamesItem() {
        return ItemStack.of(Material.NOTE_BLOCK)
                .withDisplayName(Component.text("Arcade", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Switch between multiple different", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("gamemodes and play all of our", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("different arcade games!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }

    public static ItemStack getBlockSumoItem() {
        return ItemStack.of(Material.SLIME_BALL)
                .withDisplayName(Component.text("Block Sumo", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Sumo your way to the center", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("and capture the zone for 20", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("seconds to become victorious!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }

    public static ItemStack getNaturalDisastersItem() {
        return ItemStack.of(Material.BLAZE_POWDER)
                .withDisplayName(Component.text("Natural Disasters", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Survive through waves of", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("natural disasters and be", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("the last man standing!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }

    public static ItemStack getSpeedBuildersItem() {
        return ItemStack.of(Material.BRICKS)
                .withDisplayName(Component.text("Speed Builders", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Replicate the build the fastest", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("and most accurately, but watch", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("for the Elder Guardian!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }

    public static ItemStack getTheBackroomsItem() {
        return ItemStack.of(Material.END_STONE)
                .withDisplayName(Component.text("The Backrooms", Style.style(Colors.BLUE, TextDecoration.ITALIC.withState(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Message.menuSeparator(),
                        Component.text("Fight through waves of entities", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.text("while surviving in The Backrooms!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(Colors.RED, TextDecoration.ITALIC.withState(false))),
                        Message.menuSeparator()
                ));
    }


}
