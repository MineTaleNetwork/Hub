package cc.minetale.hub.util;

import cc.minetale.flame.util.FlamePlayer;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

@Getter @Setter
public class HubPlayer extends FlamePlayer {

    public HubPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);
    }

}
