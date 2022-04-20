package cc.minetale.hub.cosmetic.menu;

import cc.minetale.hub.Hub;
import cc.minetale.hub.cosmetic.CosmeticType;
import cc.minetale.hub.util.HubPlayer;
import cc.minetale.mlib.canvas.CanvasType;
import cc.minetale.mlib.canvas.Fragment;
import cc.minetale.mlib.canvas.template.PaginatedMenu;
import cc.minetale.mlib.util.SoundsUtil;
import cc.minetale.sodium.util.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.item.Enchantment;
import net.minestom.server.item.ItemHideFlag;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

import java.util.ArrayList;

public class CosmeticTypeMenu extends PaginatedMenu {

    private final CosmeticType type;

    public CosmeticTypeMenu(Player player, CosmeticType type) {
        super(player, Component.text(type.getName() + " Cosmetics"), CanvasType.FIVE_ROW);
        this.type = type;

        super.initiliaze();
    }

    @Override
    public Fragment[] getPaginatedFragments(Player player) {
        var fragments = new Fragment[type.getCosmetics().size()];
        var hubPlayer = HubPlayer.fromPlayer(player);

        var i = 0;
        for (var cosmetic : type.getCosmetics()) {
            var selected = hubPlayer.isSelected(cosmetic);
            var permission = cosmetic.hasPermission(player);

            var lore = new ArrayList<Component>();

            lore.add(Component.empty());
            lore.addAll(cosmetic.getDescriptionComponents());
            lore.add(Component.empty());
            lore.add(selected ?
                    Component.text("Left-Click to Disable", Message.style(NamedTextColor.RED)) :
                    permission ?
                            Component.text("Left-Click to Enable", Message.style(NamedTextColor.GREEN)) :
                            Component.text("Cost: " + cosmetic.getCost() + " Gold", Message.style(NamedTextColor.YELLOW)));

            fragments[i] = Fragment.of(
                    ItemStack.of(permission ?
                            cosmetic.getIcon() :
                            Material.GRAY_DYE
                    ).withDisplayName(
                            permission ?
                                    Component.text(cosmetic.getName(), Message.style(NamedTextColor.GREEN, TextDecoration.BOLD)) :
                                    Component.text(cosmetic.getName(), Message.style(NamedTextColor.RED, TextDecoration.BOLD))
                    ).withMeta(meta -> {
                        meta.hideFlag(
                                ItemHideFlag.HIDE_ATTRIBUTES,
                                ItemHideFlag.HIDE_ENCHANTS,
                                ItemHideFlag.HIDE_POTION_EFFECTS,
                                ItemHideFlag.HIDE_DYE
                        );

                        if (selected) {
                            meta.enchantment(Enchantment.UNBREAKING, (short) 0);
                        }

                        return meta;
                    }).withLore(lore), event -> {
                        if (!permission) return;

                        if (hubPlayer.isSelected(cosmetic)) {
                            hubPlayer.deselectType(type);
                        } else {
                            hubPlayer.deselectType(type);
                            hubPlayer.selectCosmetic(cosmetic);
                        }

                        SoundsUtil.playClickSound(player);
                        handleClose(player);
                    });

            i++;
        }

        return fragments;
    }

    @Override
    protected void initiliaze() {}

}
