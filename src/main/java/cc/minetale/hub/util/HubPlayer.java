package cc.minetale.hub.util;

import cc.minetale.flame.util.FlamePlayer;
import cc.minetale.hub.Hub;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import net.minestom.server.utils.time.Tick;
import net.minestom.server.utils.time.TimeUnit;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.UUID;

@Getter @Setter
public class HubPlayer extends FlamePlayer {

    private Cooldown visibilityCooldown;

    public HubPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);

        this.visibilityCooldown = new Cooldown(Duration.of(40, TimeUnit.SERVER_TICK));
    }

    @Override
    public void handleVoid() {
        var instance = this.getInstance();

        if (instance != null && instance.isInVoid(this.position)) {
            this.teleport(Hub.getHub().getSpawn());
        }
    }

    public static HubPlayer fromPlayer(Player player) {
        return (HubPlayer) FlamePlayer.fromPlayer(player);
    }

}
