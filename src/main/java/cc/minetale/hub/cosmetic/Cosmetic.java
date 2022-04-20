package cc.minetale.hub.cosmetic;

import cc.minetale.sodium.util.Message;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.minestom.server.entity.Player;
import net.minestom.server.item.Material;

import java.util.ArrayList;
import java.util.List;

public abstract class Cosmetic {

    public boolean equals(Object obj) {
        return (obj instanceof Cosmetic cosmetic && cosmetic.getName().equals(getName()) && cosmetic.getCosmeticType() == getCosmeticType());
    }

    public String getName() {
        return "Unknown";
    }

    public abstract CosmeticType getCosmeticType();

    public boolean hasPermission(Player player) {
        return true; // TODO
    }

    public List<Component> getDescriptionComponents() {
        var components = new ArrayList<Component>();

        for(var description : getDescription()) {
            components.add(Component.text(description, Message.style(NamedTextColor.GRAY)));
        }

        return components;
    }

    public Material getIcon() {
        return Material.AIR;
    }

    public abstract List<String> getDescription();

    public abstract int getCost();

    public abstract void apply(Player player);

    public abstract void remove(Player player);

}