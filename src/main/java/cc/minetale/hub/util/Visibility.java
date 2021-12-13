package cc.minetale.hub.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minestom.server.item.ItemStack;

@Getter @AllArgsConstructor
public enum Visibility {
    ALL(ItemUtil.VISIBILITY_ALL),
    STAFF_AND_FRIENDS(ItemUtil.VISIBILITY_STAFF_AND_FRIENDS),
    NONE(ItemUtil.VISIBILITY_NONE);

    private final ItemStack itemStack;

}
