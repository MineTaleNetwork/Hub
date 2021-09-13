package cc.minetale.hub.util;

import cc.minetale.commonlib.modules.rank.Rank;
import cc.minetale.commonlib.util.MC;
import cc.minetale.mlib.util.ProfileUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.item.ItemHideFlag;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.metadata.PlayerHeadMeta;
import net.minestom.server.tag.Tag;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.function.Consumer;

public class ItemUtil {

    public static final ItemStack SERVER_SELECTOR = ItemStack.of(Material.COMPASS)
            .withDisplayName(Component.text()
                    .append(MC.component("Server Selector ", MC.CC.BLUE, TextDecoration.BOLD))
                    .append(MC.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Collections.singletonList(MC.component("Right Click to select a server!", MC.CC.GRAY)))
            .withTag(Tag.String("type"), "SERVER_SELECTOR");

    public static final ItemStack SHOP = ItemStack.of(Material.EMERALD)
            .withDisplayName(Component.text()
                    .append(MC.component("Shop ", MC.CC.GREEN, TextDecoration.BOLD))
                    .append(MC.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Arrays.asList(MC.component("Right Click to open the shop!", MC.CC.GRAY)))
            .withTag(Tag.String("type"), "SHOP");

    public static ItemStack PROFILE(Player player) {
        UUID uuid = player.getUuid();

        return ItemStack.of(Material.PLAYER_HEAD)
                .withDisplayName(Component.text()
                        .append(MC.component("Profile ", MC.CC.LIGHT_PURPLE, TextDecoration.BOLD))
                        .append(MC.component("(Right Click)", MC.CC.GRAY))
                        .build())
                .withLore(Arrays.asList(MC.component("Right Click to open your profile!", MC.CC.GRAY)))
                .withMeta(PlayerHeadMeta.class, meta -> meta.skullOwner(uuid).playerSkin(PlayerSkin.fromUuid(uuid.toString())))
                .withTag(Tag.String("type"), "PROFILE");
    }

    public static ItemStack LOBBY_SELECTOR = ItemStack.of(Material.NETHER_STAR)
            .withDisplayName(Component.text()
                    .append(MC.component("Lobby Selector ", MC.CC.AQUA, TextDecoration.BOLD))
                    .append(MC.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Arrays.asList(MC.component("Right Click to select a lobby!", MC.CC.GRAY)))
            .withTag(Tag.String("type"), "LOBBY_SELECTOR");

    public static void VISIBILITY_ITEM(Player player, Consumer<ItemStack> item) {
        ProfileUtil.getAssociatedProfile(player).thenAccept(profile -> {
            item.accept(Visibility.values()[profile.getOptionsProfile().getVisibilityIndex()].getItemStack());
        });
    }

    public static ItemStack getParkourItem() {
        return ItemStack.of(Material.GOLDEN_BOOTS)
                .withDisplayName(MC.component("PARKOUR", MC.CC.GOLD, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.component("Fun, Casual, Competitive", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.component("Parkour is where you maneuver", MC.CC.GRAY),
                        MC.component("through various obstacles without", MC.CC.GRAY),
                        MC.component("falling or failing!", MC.CC.GRAY),
                        Component.empty(),
                        MC.component("▶ Click to Connect", MC.CC.GOLD),
                        Component.text()
                                .append(MC.component("0 ", MC.CC.GOLD))
                                .append(MC.component("Players are Online!", MC.CC.GRAY))
                                .build()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }

    public static ItemStack getPracticeItem() {
        return ItemStack.of(Material.IRON_SWORD)
                .withDisplayName(MC.component("PRACTICE", MC.CC.DARK_AQUA, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.component("PvP, Competitive, Fun", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.component("Practice your pvp skills and", MC.CC.GRAY),
                        MC.component("become the best of the best!", MC.CC.GRAY),
                        Component.empty(),
                        MC.component("▶ Click to Connect", MC.CC.DARK_AQUA),
                        Component.text()
                                .append(MC.component("0 ", MC.CC.DARK_AQUA))
                                .append(MC.component("Players are Online!", MC.CC.GRAY))
                                .build()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }
    public static ItemStack getSkyBlockItem() {
        return ItemStack.of(Material.OAK_SAPLING)
                .withDisplayName(MC.component("SKYBLOCK", MC.CC.AQUA, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.component("Survival, Challenge, Island", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.component("Spawn on a floating island with", MC.CC.GRAY),
                        MC.component("very limited resources, expand", MC.CC.GRAY),
                        MC.component("and grow your island!", MC.CC.GRAY),
                        Component.empty(),
                        MC.component("Coming Soon", MC.CC.RED)
//                        MC.component("▶ Click to Connect", MC.CC.AQUA),
//                        Component.text()
//                                .append(MC.component("0 ", MC.CC.AQUA))
//                                .append(MC.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }

    public static ItemStack getSkyWarsItem() {
        return ItemStack.of(Material.ENDER_EYE)
                .withDisplayName(MC.component("SKYWARS", MC.CC.GREEN, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.component("Competitive, Challenge, Island", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.component("Spawn on a floating island and", MC.CC.GRAY),
                        MC.component("fight against other players,", MC.CC.GRAY),
                        MC.component("last player remaining wins!", MC.CC.GRAY),
                        Component.empty(),
                        MC.component("Coming Soon", MC.CC.RED)
//                        MC.component("▶ Click to Connect", MC.CC.GREEN),
//                        Component.text()
//                                .append(MC.component("0 ", MC.CC.GREEN))
//                                .append(MC.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }

    public static ItemStack getWoolWarsItem() {
        return ItemStack.of(Material.YELLOW_WOOL)
                .withDisplayName(MC.component("WOOLWARS", MC.CC.YELLOW, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.component("Competitive, Fast, Arena", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.component("Quickly make your way to the", MC.CC.GRAY),
                        MC.component("top without touching the floor,", MC.CC.GRAY),
                        MC.component("and knock players in the process!", MC.CC.GRAY),
                        Component.empty(),
                        MC.component("▶ Click to Connect", MC.CC.YELLOW),
                        Component.text()
                                .append(MC.component("0 ", MC.CC.YELLOW))
                                .append(MC.component("Players are Online!", MC.CC.GRAY))
                                .build()
                ));
    }

    public static ItemStack getBedWarsItem() {
        return ItemStack.of(Material.RED_BED)
                .withDisplayName(MC.component("BEDWARS", MC.CC.RED, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.component("Competitive, Slow, Island", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.component("Protect your bed and seek out", MC.CC.GRAY),
                        MC.component("enemy players beds, and destroy", MC.CC.GRAY),
                        MC.component("them to become victorious!", MC.CC.GRAY),
                        Component.empty(),
                        MC.component("Coming Soon", MC.CC.RED)
//                        MC.component("▶ Click to Connect", MC.CC.RED),
//                        Component.text()
//                                .append(MC.component("0 ", MC.CC.RED))
//                                .append(MC.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }

    public static ItemStack getHousingItem() {
        return ItemStack.of(Material.OAK_DOOR)
                .withDisplayName(MC.component("HOUSING", MC.CC.BLUE, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.component("Creative, Building, Share", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.component("Customize and build on your own", MC.CC.GRAY),
                        MC.component("personal plot, hang out with your", MC.CC.GRAY),
                        MC.component("friends, visit other people's", MC.CC.GRAY),
                        MC.component("houses, socialize, and more!", MC.CC.GRAY),
                        Component.empty(),
                        MC.component("Coming Soon", MC.CC.RED)
//                        MC.component("▶ Click to Connect", MC.CC.BLUE),
//                        Component.text()
//                                .append(MC.component("0 ", MC.CC.BLUE))
//                                .append(MC.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }

    public static ItemStack getArcadeGamesItem() {
        return ItemStack.of(Material.NOTE_BLOCK)
                .withDisplayName(MC.component("ARCADE GAMES", MC.CC.LIGHT_PURPLE, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.component("Arcade, Games, Fun", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.component("Switch between multiple different", MC.CC.GRAY),
                        MC.component("gamemodes and play all of our", MC.CC.GRAY),
                        MC.component("different arcade games!", MC.CC.GRAY),
                        Component.empty(),
                        MC.component("Coming Soon", MC.CC.RED)
//                        MC.component("▶ Click to Connect", MC.CC.LIGHT_PURPLE),
//                        Component.text()
//                                .append(MC.component("0 ", MC.CC.LIGHT_PURPLE))
//                                .append(MC.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }


}
