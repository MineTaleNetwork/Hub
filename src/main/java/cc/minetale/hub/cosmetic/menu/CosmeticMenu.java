package cc.minetale.hub.cosmetic.menu;

import cc.minetale.hub.cosmetic.CosmeticType;
import cc.minetale.mlib.canvas.CanvasType;
import cc.minetale.mlib.canvas.Filler;
import cc.minetale.mlib.canvas.Fragment;
import cc.minetale.mlib.canvas.template.Menu;
import cc.minetale.sodium.util.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.item.ItemHideFlag;
import net.minestom.server.item.ItemStack;

import java.util.Arrays;

public class CosmeticMenu extends Menu {

    public CosmeticMenu(Player player) {
        super(player, Component.text("Cosmetic Selector"), CanvasType.THREE_ROW);

        setFiller(Filler.EMPTY_SLOTS);

        setButton(10, cosmeticFragment(CosmeticType.ARMOR));
        setButton(12, cosmeticFragment(CosmeticType.PARTICLE));
        setButton(14, cosmeticFragment(CosmeticType.GADGET));
        setButton(16, cosmeticFragment(CosmeticType.PET));
    }

    private Fragment cosmeticFragment(CosmeticType type) {
        return Fragment.of(ItemStack.of(type.getIcon())
                        .withDisplayName(type.getDisplay())
                        .withMeta(meta -> meta.hideFlag(ItemHideFlag.HIDE_ATTRIBUTES))
                        .withLore(Arrays.asList(
                                Message.parse("<c_dark_red>Test</c_dark_red> Test"),
                                Component.text().append(
                                        Component.text("Unlocked: ", Message.style(NamedTextColor.GRAY)),
                                        Component.text("0/0 ", Message.style(NamedTextColor.RED)),
                                        Component.text("(0%)", Message.style(NamedTextColor.DARK_GRAY))
                                ).build(),
                                Component.empty(),
                                Component.text("Click to browse!", Message.style(NamedTextColor.YELLOW))
                        )),
                event -> Menu.openMenu(new CosmeticTypeMenu(getPlayer(), type)));
    }

}
