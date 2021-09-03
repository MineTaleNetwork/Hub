package cc.minetale.hub.menu;

import cc.minetale.mlib.fabric.ClickableItem;
import cc.minetale.mlib.fabric.FabricInventory;
import cc.minetale.mlib.fabric.content.FabricContents;
import cc.minetale.mlib.fabric.content.FabricProvider;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;
import net.minestom.server.inventory.InventoryType;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;

public class ProfileMenu implements FabricProvider {

    private final FabricInventory inventory;

    public ProfileMenu(Player player) {
        this.inventory = FabricInventory.builder()
                .provider(this)
                .type(InventoryType.CHEST_5_ROW)
                .build();

        this.inventory.open(player);
    }

    @Override
    public void init(Player player, FabricContents contents) {
        contents.fill(ClickableItem.empty(ItemStack.of(Material.GRAY_STAINED_GLASS_PANE).withDisplayName(Component.empty())));
    }

}
