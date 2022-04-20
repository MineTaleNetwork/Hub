package cc.minetale.hub.cosmetic.cosmetics.gadget;

import cc.minetale.hub.cosmetic.impl.Gadget;
import cc.minetale.hub.cosmetic.objects.EnderButtProjectile;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.Material;

import java.util.Arrays;
import java.util.List;

public class EnderButtGadget extends Gadget {

    @Override
    public String getName() {
        return "Ender Butt";
    }

    @Override
    public Material getIcon() {
        return Material.ENDER_PEARL;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(
                "Riding on top of such a small",
                "endy pearly sure must be hard.",
                "Make sure to hold on strong!"
        );
    }

    @Override
    public int getCost() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void interact(PlayerUseItemEvent event) {
        var player = event.getPlayer();
        var vehicle = player.getVehicle();

        var projectile = new EnderButtProjectile(player);

        var position = player.getPosition().add(0D, player.getEyeHeight(), 0D);
        projectile.setInstance(player.getInstance(), position);

        var direction = position.direction();
        position = position.add(direction).sub(0, 0.2, 0);

        projectile.shoot(position, 2.0, 1.0);
        projectile.addPassenger(player);

        player.playSound(Sound.sound(Key.key("entity.enderman.teleport"), Sound.Source.MASTER, 2.0F, 0.9F));

        if(vehicle instanceof EnderButtProjectile other) {
            other.remove();
        }
    }

}
