//package cc.minetale.hub.cosmetic.cosmetics.armor;
//
//import cc.minetale.hub.cosmetic.Cosmetic;
//import cc.minetale.hub.cosmetic.CosmeticType;
//import cc.minetale.hub.cosmetic.modifier.Tickable;
//import net.kyori.adventure.text.Component;
//import net.kyori.adventure.text.format.Style;
//import net.kyori.adventure.text.format.TextColor;
//import net.kyori.adventure.text.format.TextDecoration;
//import net.minestom.server.MinecraftServer;
//import net.minestom.server.color.Color;
//import net.minestom.server.entity.Player;
//import net.minestom.server.item.ItemHideFlag;
//import net.minestom.server.item.ItemStack;
//import net.minestom.server.item.Material;
//import net.minestom.server.item.metadata.LeatherArmorMeta;
//import net.minestom.server.utils.time.Tick;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class RainbowArmor extends Cosmetic implements Tickable {
//
//    private static final List<Color> colorList = Arrays.asList(
//            new Color(255, 0, 0),
//            new Color(255, 68, 0),
//            new Color(255, 111, 0),
//            new Color(255, 171, 0),
//            new Color(255, 255, 0),
//            new Color(188, 255, 0),
//            new Color(128, 255, 0),
//            new Color(43, 255, 0),
//            new Color(0, 255, 9),
//            new Color(0, 255, 51),
//            new Color(0, 255, 111),
//            new Color(0, 255, 162),
//            new Color(0, 255, 230),
//            new Color(0, 239, 255),
//            new Color(0, 196, 255),
//            new Color(0, 173, 255),
//            new Color(0, 162, 255),
//            new Color(0, 137, 255),
//            new Color(0, 100, 255),
//            new Color(0, 77, 255),
//            new Color(0, 34, 255),
//            new Color(17, 0, 255),
//            new Color(37, 0, 255),
//            new Color(68, 0, 255),
//            new Color(89, 0, 255),
//            new Color(102, 0, 255),
//            new Color(124, 0, 255),
//            new Color(154, 0, 255),
//            new Color(222, 0, 255),
//            new Color(255, 0, 247),
//            new Color(255, 0, 247),
//            new Color(255, 0, 179),
//            new Color(255, 0, 128));
//
//    static int lastSelected = 1;
//
//    public RainbowArmor() {
//        MinecraftServer.getSchedulerManager()
//                .buildTask(() -> {
//                    lastSelected = (lastSelected + 1) % colorList.size();
//                })
//                .repeat(1, Tick.SERVER_TICKS)
//                .schedule();
//    }
//
//    public String getName() {
//        return "Rainbow Armor";
//    }
//
//    public Component getDisplayName() {
//        return Component.text("Rainbow Armor",
//                Style.style(TextColor.color(getColor().asRGB()), TextDecoration.ITALIC.withState(false)));
//    }
//
//    public CosmeticType getCosmeticType() {
//        return CosmeticType.ARMOR;
//    }
//
//    public boolean hasPermission(Player player) {
//        return player.hasPermission("hub.armor.rainbow");
//    }
//
//    public List<Component> getDescription() {
//        return null;
//    }
//
//    public ItemStack getIcon() {
//        return getColorArmor(Material.LEATHER_CHESTPLATE);
//    }
//
//    public void apply(Player player) {}
//
//    public void remove(Player player) {
//        unselectCosmetic(player);
//
//        player.getInventory().setHelmet(ItemStack.AIR);
//        player.getInventory().setChestplate(ItemStack.AIR);
//        player.getInventory().setLeggings(ItemStack.AIR);
//        player.getInventory().setBoots(ItemStack.AIR);
//    }
//
//    public static Color getColor() {
//        return colorList.get(lastSelected);
//    }
//
//    public ItemStack getColorArmor(Material material) {
//        return ItemStack.of(material)
//                .withDisplayName(getDisplayName())
//                .withMeta(LeatherArmorMeta.class, meta -> {
//                    meta.color(getColor());
//                    meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES, ItemHideFlag.HIDE_DYE);
//                });
//    }
//
//    @Override
//    public void tick(Player player) {
//        if (player == null || !player.isOnline())
//            return;
//
//        var helmet = getColorArmor(Material.LEATHER_HELMET);
//        var chestPlate = getColorArmor(Material.LEATHER_CHESTPLATE);
//        var leggings = getColorArmor(Material.LEATHER_LEGGINGS);
//        var boots = getColorArmor(Material.LEATHER_BOOTS);
//
//        player.getInventory().setHelmet(helmet);
//        player.getInventory().setChestplate(chestPlate);
//        player.getInventory().setLeggings(leggings);
//        player.getInventory().setBoots(boots);
//    }
//
//}
