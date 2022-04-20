package cc.minetale.hub.cosmetic.impl;

import cc.minetale.hub.cosmetic.Cosmetic;
import cc.minetale.hub.cosmetic.CosmeticType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.ItemStack;

public abstract class Gadget extends Cosmetic {

    @Override
    public CosmeticType getCosmeticType() {
        return CosmeticType.GADGET;
    }

    @Override
    public void apply(Player player) {
        giveGadget(player);
    }

    private void giveGadget(Player player) {
        player.getInventory().setItemStack(CosmeticType.GADGET_SLOT, buildGadgetItem());
    }

    @Override
    public void remove(Player player) {
        removeGadget(player);
    }

    private void removeGadget(Player player) {
        player.getInventory().setItemStack(CosmeticType.GADGET_SLOT, ItemStack.AIR);
    }

    private ItemStack buildGadgetItem() {
        return ItemStack.of(getIcon())
                .withDisplayName(Component.text().append(
                        Component.text(getName(), Style.style(NamedTextColor.GREEN, TextDecoration.ITALIC.withState(false))),
                        Component.text(" (Right Click)", Style.style(NamedTextColor.GRAY, TextDecoration.ITALIC.withState(false)))
                ).build());
    }

    public abstract void interact(PlayerUseItemEvent event);

}
