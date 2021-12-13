package cc.minetale.hub.util;

import cc.minetale.flame.util.FlamePlayer;
import cc.minetale.hub.Hub;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.entity.damage.DamageType;
import net.minestom.server.network.player.PlayerConnection;
import net.minestom.server.utils.time.Cooldown;
import net.minestom.server.utils.time.TimeUnit;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.UUID;

@Getter @Setter
public class HubPlayer extends FlamePlayer {

    private Cooldown instanceSwitchCooldown;
    private Cooldown visibilitySwitchCooldown;

    public HubPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);

        this.visibilitySwitchCooldown = new Cooldown(Duration.of(1, TimeUnit.SECOND));
        this.instanceSwitchCooldown = new Cooldown(Duration.of(3, TimeUnit.SECOND));
    }

    @Override
    public void handleVoid() {
        var instance = this.getInstance();

        if (instance != null && instance.isInVoid(this.position)) {
            this.teleport(Hub.getHub().getSpawn());
        }
    }

}
