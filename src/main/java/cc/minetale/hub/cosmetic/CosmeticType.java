package cc.minetale.hub.cosmetic;

import cc.minetale.hub.Hub;
import cc.minetale.sodium.util.Message;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.item.Material;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum CosmeticType {
    ARMOR(
            "Armor",
            Material.DIAMOND_CHESTPLATE
    ),
    PARTICLE(
            "Particles",
            Material.BLAZE_POWDER
    ),
    GADGET(
            "Gadgets",
            Material.IRON_HORSE_ARMOR
    ),
    PET(
            "Pets",
            Material.BONE
    );

    public static final int GADGET_SLOT = 5;

    private final String name;
    private final Material icon;

    CosmeticType(String name, Material icon) {
        this.name = name;
        this.icon = icon;
    }

    public Component getDisplay() {
        return Component.text(getName(), Message.style(NamedTextColor.GREEN));
    }

    public List<Cosmetic> getCosmetics() {
        return Hub.getInstance()
                .getCosmeticHandler()
                .getCosmetics()
                .stream()
                .filter(iCosmetic -> (iCosmetic.getCosmeticType() == this))
                .collect(Collectors.toList());
    }

}
