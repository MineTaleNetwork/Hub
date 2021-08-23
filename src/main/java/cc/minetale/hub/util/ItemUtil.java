package cc.minetale.hub.util;

import com.customwrld.commonlib.util.MC;
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

public class ItemUtil {

    public static final ItemStack SERVER_SELECTOR = ItemStack.of(Material.COMPASS)
            .withDisplayName(Component.text()
                    .append(MC.Style.component("Server Selector ", MC.CC.BLUE, TextDecoration.BOLD))
                    .append(MC.Style.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Collections.singletonList(MC.Style.component("Right Click to select a server!", MC.CC.GRAY)))
            .withTag(Tag.String("type"), "SERVER_SELECTOR");

    public static final ItemStack SHOP = ItemStack.of(Material.EMERALD)
            .withDisplayName(Component.text()
                    .append(MC.Style.component("Shop ", MC.CC.GREEN, TextDecoration.BOLD))
                    .append(MC.Style.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Arrays.asList(MC.Style.component("Right Click to open the shop!", MC.CC.GRAY)))
            .withTag(Tag.String("type"), "SHOP");

    public static ItemStack PROFILE(Player player) {
        UUID uuid = player.getUuid();

        return ItemStack.of(Material.PLAYER_HEAD)
                .withDisplayName(Component.text()
                        .append(MC.Style.component("Profile ", MC.CC.LIGHT_PURPLE, TextDecoration.BOLD))
                        .append(MC.Style.component("(Right Click)", MC.CC.GRAY))
                        .build())
                .withLore(Arrays.asList(MC.Style.component("Right Click to open your profile!", MC.CC.GRAY)))
                .withMeta(PlayerHeadMeta.class, meta -> meta.skullOwner(uuid).playerSkin(PlayerSkin.fromUuid(uuid.toString())))
                .withTag(Tag.String("type"), "PROFILE");
    }

    public static ItemStack COSMETIC_SELECTOR = ItemStack.of(Material.BEACON)
            .withDisplayName(Component.text()
                    .append(MC.Style.component("Cosmetic Selector ", MC.CC.AQUA, TextDecoration.BOLD))
                    .append(MC.Style.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Arrays.asList(MC.Style.component("Right Click to select a cosmetic!", MC.CC.GRAY)))
            .withTag(Tag.String("type"), "COSMETIC_SELECTOR");

    public static ItemStack VISIBILITY_ITEM(UUID uuid) {
        return ItemStack.AIR; // Visibility.values()[HubExtension.visibilityMap.get(uuid)].itemStack;
    }

    public static ItemStack getParkourItem() {
        return ItemStack.of(Material.GOLDEN_BOOTS)
                .withDisplayName(MC.Style.component("PARKOUR", MC.CC.GOLD, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.Style.component("Fun, Casual, Competitive", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.Style.component("Parkour is where you maneuver", MC.CC.GRAY),
                        MC.Style.component("through various obstacles without", MC.CC.GRAY),
                        MC.Style.component("falling or failing!", MC.CC.GRAY),
                        Component.empty(),
                        MC.Style.component("▶ Click to Connect", MC.CC.GOLD),
                        Component.text()
                                .append(MC.Style.component("0 ", MC.CC.GOLD))
                                .append(MC.Style.component("Players are Online!", MC.CC.GRAY))
                                .build()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }

    public static ItemStack getPracticeItem() {
        return ItemStack.of(Material.IRON_SWORD)
                .withDisplayName(MC.Style.component("PRACTICE", MC.CC.DARK_AQUA, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.Style.component("PvP, Competitive, Fun", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.Style.component("Practice your pvp skills and", MC.CC.GRAY),
                        MC.Style.component("become the best of the best!", MC.CC.GRAY),
                        Component.empty(),
                        MC.Style.component("▶ Click to Connect", MC.CC.DARK_AQUA),
                        Component.text()
                                .append(MC.Style.component("0 ", MC.CC.DARK_AQUA))
                                .append(MC.Style.component("Players are Online!", MC.CC.GRAY))
                                .build()
                ))
                .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES));
    }
    public static ItemStack getSkyBlockItem() {
        return ItemStack.of(Material.OAK_SAPLING)
                .withDisplayName(MC.Style.component("SKYBLOCK", MC.CC.AQUA, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.Style.component("Survival, Challenge, Island", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.Style.component("Spawn on a floating island with", MC.CC.GRAY),
                        MC.Style.component("very limited resources, expand", MC.CC.GRAY),
                        MC.Style.component("and grow your island!", MC.CC.GRAY),
                        Component.empty(),
                        MC.Style.component("Coming Soon", MC.CC.RED)
//                        MC.Style.component("▶ Click to Connect", MC.CC.AQUA),
//                        Component.text()
//                                .append(MC.Style.component("0 ", MC.CC.AQUA))
//                                .append(MC.Style.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }

    public static ItemStack getSkyWarsItem() {
        return ItemStack.of(Material.ENDER_EYE)
                .withDisplayName(MC.Style.component("SKYWARS", MC.CC.GREEN, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.Style.component("Competitive, Challenge, Island", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.Style.component("Spawn on a floating island and", MC.CC.GRAY),
                        MC.Style.component("fight against other players,", MC.CC.GRAY),
                        MC.Style.component("last player remaining wins!", MC.CC.GRAY),
                        Component.empty(),
                        MC.Style.component("Coming Soon", MC.CC.RED)
//                        MC.Style.component("▶ Click to Connect", MC.CC.GREEN),
//                        Component.text()
//                                .append(MC.Style.component("0 ", MC.CC.GREEN))
//                                .append(MC.Style.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }

    public static ItemStack getWoolWarsItem() {
        return ItemStack.of(Material.YELLOW_WOOL)
                .withDisplayName(MC.Style.component("WOOLWARS", MC.CC.YELLOW, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.Style.component("Competitive, Fast, Arena", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.Style.component("Quickly make your way to the", MC.CC.GRAY),
                        MC.Style.component("top without touching the floor,", MC.CC.GRAY),
                        MC.Style.component("and knock players in the process!", MC.CC.GRAY),
                        Component.empty(),
                        MC.Style.component("▶ Click to Connect", MC.CC.YELLOW),
                        Component.text()
                                .append(MC.Style.component("0 ", MC.CC.YELLOW))
                                .append(MC.Style.component("Players are Online!", MC.CC.GRAY))
                                .build()
                ));
    }

    public static ItemStack getBedWarsItem() {
        return ItemStack.of(Material.RED_BED)
                .withDisplayName(MC.Style.component("BEDWARS", MC.CC.RED, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.Style.component("Competitive, Slow, Island", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.Style.component("Protect your bed and seek out", MC.CC.GRAY),
                        MC.Style.component("enemy players beds, and destroy", MC.CC.GRAY),
                        MC.Style.component("them to become victorious!", MC.CC.GRAY),
                        Component.empty(),
                        MC.Style.component("Coming Soon", MC.CC.RED)
//                        MC.Style.component("▶ Click to Connect", MC.CC.RED),
//                        Component.text()
//                                .append(MC.Style.component("0 ", MC.CC.RED))
//                                .append(MC.Style.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }

    public static ItemStack getHousingItem() {
        return ItemStack.of(Material.OAK_DOOR)
                .withDisplayName(MC.Style.component("HOUSING", MC.CC.BLUE, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.Style.component("Creative, Building, Share", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.Style.component("Customize and build on your own", MC.CC.GRAY),
                        MC.Style.component("personal plot, hang out with your", MC.CC.GRAY),
                        MC.Style.component("friends, visit other people's", MC.CC.GRAY),
                        MC.Style.component("houses, socialize, and more!", MC.CC.GRAY),
                        Component.empty(),
                        MC.Style.component("Coming Soon", MC.CC.RED)
//                        MC.Style.component("▶ Click to Connect", MC.CC.BLUE),
//                        Component.text()
//                                .append(MC.Style.component("0 ", MC.CC.BLUE))
//                                .append(MC.Style.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }

    public static ItemStack getArcadeGamesItem() {
        return ItemStack.of(Material.NOTE_BLOCK)
                .withDisplayName(MC.Style.component("ARCADE GAMES", MC.CC.LIGHT_PURPLE, TextDecoration.BOLD))
                .withLore(Arrays.asList(
                        MC.Style.component("Arcade, Games, Fun", MC.CC.DARK_GRAY),
                        Component.empty(),
                        MC.Style.component("Switch between multiple different", MC.CC.GRAY),
                        MC.Style.component("gamemodes and play all of our", MC.CC.GRAY),
                        MC.Style.component("different arcade games!", MC.CC.GRAY),
                        Component.empty(),
                        MC.Style.component("Coming Soon", MC.CC.RED)
//                        MC.Style.component("▶ Click to Connect", MC.CC.LIGHT_PURPLE),
//                        Component.text()
//                                .append(MC.Style.component("0 ", MC.CC.LIGHT_PURPLE))
//                                .append(MC.Style.component("Players are Online!", MC.CC.GRAY))
//                                .build()
                ));
    }


}
