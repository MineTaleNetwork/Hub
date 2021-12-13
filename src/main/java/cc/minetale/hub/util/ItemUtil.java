package cc.minetale.hub.util;

import cc.minetale.commonlib.util.MC;
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
import net.minestom.server.tag.Tag;

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
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 0);

    public static final ItemStack VISIBILITY_STAFF_AND_FRIENDS = ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(Component.text("Staff and VIPs Visible ", Style.style(NamedTextColor.DARK_PURPLE, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                    .append(Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))))
                    .build())
            .withLore(Collections.singletonList(Component.text("Right Click to cycle through visibility!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
            .withMeta(FireworkEffectMeta.class, meta -> {
                var colors = Collections.singletonList(new Color(NamedTextColor.DARK_PURPLE));

                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, colors, colors));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 1);

    public static final ItemStack VISIBILITY_NONE = ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(Component.text("No Players Visible ", Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                    .append(Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))))
                    .build())
            .withLore(Collections.singletonList(Component.text("Right Click to cycle through visibility!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
            .withMeta(FireworkEffectMeta.class, meta -> {
                var colors = Collections.singletonList(new Color(NamedTextColor.RED));

                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, colors, colors));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 2);

    public static final ItemStack SERVER_SELECTOR = ItemStack.of(Material.COMPASS)
            .withDisplayName(Component.text().append(
                    Component.text("Server Selector ", Style.style(NamedTextColor.BLUE, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                    Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
            ).build())
            .withLore(Collections.singletonList(Component.text("Right Click to select a server!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
            .withTag(Tag.String("type"), "SERVER_SELECTOR");

    public static final ItemStack SHOP = ItemStack.of(Material.EMERALD)
            .withDisplayName(Component.text().append(
                    Component.text("Shop ", Style.style(NamedTextColor.GREEN, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                    Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
            ).build())
            .withLore(List.of(Component.text("Right Click to open the shop!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
            .withTag(Tag.String("type"), "SHOP");

    public static ItemStack PROFILE(Player player) {
        UUID uuid = player.getUuid();

        return ItemStack.of(Material.PLAYER_HEAD)
                .withDisplayName(Component.text().append(
                        Component.text("Profile ", Style.style(NamedTextColor.LIGHT_PURPLE, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                        Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
                ).build())
                .withLore(List.of(Component.text("Right Click to open your profile!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
                .withMeta(PlayerHeadMeta.class, meta -> meta.skullOwner(uuid).playerSkin(PlayerSkin.fromUuid(uuid.toString())))
                .withTag(Tag.String("type"), "PROFILE");
    }

    public static ItemStack LOBBY_SELECTOR = ItemStack.of(Material.NETHER_STAR)
            .withDisplayName(Component.text().append(
                    Component.text("Lobby Selector ", Style.style(NamedTextColor.AQUA, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)),
                    Component.text("(Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
            ).build())
            .withLore(List.of(Component.text("Right Click to select a lobby!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))))
            .withTag(Tag.String("type"), "LOBBY_SELECTOR");

    // TODO -> Fix
    public static void VISIBILITY_ITEM(Player player, Consumer<ItemStack> item) {
        item.accept(Visibility.values()[0].getItemStack());

//        ProfileUtil.getAssociatedProfile(player).thenAccept(profile -> {
//            item.accept(Visibility.values()[profile.getOptionsProfile().getVisibilityIndex()].getItemStack());
//        });
    }

    public static ItemStack getParkourItem() {
        return ItemStack.of(Material.GOLDEN_BOOTS)
                .withDisplayName(Component.text("PARKOUR", Style.style(NamedTextColor.GOLD, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Component.text("Fun, Casual, Competitive", Style.style(NamedTextColor.DARK_GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Parkour is where you maneuver", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("through various obstacles without", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("falling or failing!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("▶ Click to Connect", Style.style(NamedTextColor.GOLD, TextDecoration.ITALIC.as(false))),
                        Component.text().append(
                                Component.text("0 ", Style.style(NamedTextColor.GOLD, TextDecoration.ITALIC.as(false))),
                                Component.text("Players are Online!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
                        ).build()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }

    public static ItemStack getPracticeItem() {
        return ItemStack.of(Material.IRON_SWORD)
                .withDisplayName(Component.text("PRACTICE", Style.style(NamedTextColor.DARK_AQUA, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Component.text("PvP, Competitive, Fun", Style.style(NamedTextColor.DARK_GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Practice your pvp skills and", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("become the best of the best!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("▶ Click to Connect", Style.style(NamedTextColor.DARK_AQUA, TextDecoration.ITALIC.as(false))),
                        Component.text().append(
                                Component.text("0 ", Style.style(NamedTextColor.DARK_AQUA, TextDecoration.ITALIC.as(false))),
                                Component.text("Players are Online!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
                        ).build()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }
    public static ItemStack getSkyBlockItem() {
        return ItemStack.of(Material.OAK_SAPLING)
                .withDisplayName(Component.text("SKYBLOCK", Style.style(NamedTextColor.AQUA, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Component.text("Survival, Challenge, Island", Style.style(NamedTextColor.DARK_GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Spawn on a floating island with", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("very limited resources, expand", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("and grow your island!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false)))
                ));
    }

    public static ItemStack getSkyWarsItem() {
        return ItemStack.of(Material.ENDER_EYE)
                .withDisplayName(Component.text("SKYWARS", Style.style(NamedTextColor.GREEN, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Component.text("Competitive, Challenge, Island", Style.style(NamedTextColor.DARK_GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Spawn on a floating island and", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("fight against other players,", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("last player remaining wins!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false)))
                ));
    }

    public static ItemStack getWoolWarsItem() {
        return ItemStack.of(Material.YELLOW_WOOL)
                .withDisplayName(Component.text("WOOLWARS", Style.style(NamedTextColor.YELLOW, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Component.text("Competitive, Fast, Arena", Style.style(NamedTextColor.DARK_GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Quickly make your way to the", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("top without touching the floor,", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("and knock players in the process!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("▶ Click to Connect", Style.style(NamedTextColor.YELLOW, TextDecoration.ITALIC.as(false))),
                        Component.text().append(
                                Component.text("0 ", Style.style(NamedTextColor.YELLOW, TextDecoration.ITALIC.as(false))),
                                Component.text("Players are Online!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false)))
                        ).build()
                ));
    }

    public static ItemStack getBedWarsItem() {
        return ItemStack.of(Material.RED_BED)
                .withDisplayName(Component.text("BEDWARS", Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Component.text("Competitive, Slow, Island", Style.style(NamedTextColor.DARK_GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Protect your bed and seek out", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("enemy players beds, and destroy", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("them to become victorious!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false)))
                ));
    }

    public static ItemStack getHousingItem() {
        return ItemStack.of(Material.OAK_DOOR)
                .withDisplayName(Component.text("HOUSING", Style.style(NamedTextColor.BLUE, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Component.text("Creative, Building, Share", Style.style(NamedTextColor.DARK_GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Customize and build on your own", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("personal plot, hang out with your", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("friends, visit other people's", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("houses, socialize, and more!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false)))
                ));
    }

    public static ItemStack getArcadeGamesItem() {
        return ItemStack.of(Material.NOTE_BLOCK)
                .withDisplayName(Component.text("ARCADE GAMES", Style.style(NamedTextColor.LIGHT_PURPLE, TextDecoration.ITALIC.as(false), TextDecoration.BOLD)))
                .withLore(Arrays.asList(
                        Component.text("Arcade, Games, Fun", Style.style(NamedTextColor.DARK_GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Switch between multiple different", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("gamemodes and play all of our", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.text("different arcade games!", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.as(false))),
                        Component.empty(),
                        Component.text("Coming Soon", Style.style(NamedTextColor.RED, TextDecoration.ITALIC.as(false)))
                ));
    }


}
