package cc.minetale.hub.menu;

import cc.minetale.hub.util.ItemUtil;
import net.kyori.adventure.text.Component;
import net.minestom.server.inventory.Inventory;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ServerSelector extends Inventory {

    public ServerSelector() {
        super(InventoryType.CHEST_5_ROW, "Server Selector");

        this.addInventoryCondition((player, slot, clickType, result) -> {
            result.setCancel(true);
        });

        for(int i = 0; i < this.getSize(); i++) {
            this.setItemStack(i, ItemStack.of(Material.GRAY_STAINED_GLASS_PANE).withDisplayName(Component.empty()));
        }

        this.setItemStack(10, ItemUtil.getPracticeItem());
        this.setItemStack(12, ItemUtil.getParkourItem());
        this.setItemStack(14, ItemUtil.getWoolWarsItem());
        this.setItemStack(16, ItemUtil.getBedWarsItem());
        this.setItemStack(28, ItemUtil.getHousingItem());
        this.setItemStack(30, ItemUtil.getArcadeGamesItem());
        this.setItemStack(32, ItemUtil.getSkyBlockItem());
        this.setItemStack(34, ItemUtil.getSkyWarsItem());
    }
}
