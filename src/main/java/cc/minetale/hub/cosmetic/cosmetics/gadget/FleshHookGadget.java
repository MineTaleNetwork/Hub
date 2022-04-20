package cc.minetale.hub.cosmetic.cosmetics.gadget;

import cc.minetale.hub.cosmetic.impl.Gadget;
import cc.minetale.hub.cosmetic.objects.FleshHookProjectile;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.Material;

import java.util.Arrays;
import java.util.List;

public class FleshHookGadget extends Gadget {

    @Override
    public String getName() {
        return "Flesh Hook";
    }

    @Override
    public Material getIcon() {
        return Material.TRIPWIRE_HOOK;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(
                "Have you ever wished the worst to your enemies?",
                "Just toss them around!",
                "This will make sure they'll never forget you."
        );
    }

    @Override
    public int getCost() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void interact(PlayerUseItemEvent event) {
        var player = event.getPlayer();
        var projectile = new FleshHookProjectile(player);

        var position = player.getPosition().add(0D, player.getEyeHeight(), 0D);
        projectile.setInstance(player.getInstance(), position);

        var direction = position.direction();
        position = position.add(direction).sub(0, 0.2, 0);

        projectile.shoot(position, 2.0, 1.0);
    }

}
