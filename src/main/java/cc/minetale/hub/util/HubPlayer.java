package cc.minetale.hub.util;

import cc.minetale.commonlib.modules.profile.Profile;
import lombok.Getter;
import lombok.Setter;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

// TODO Extend Flame Player

@Getter @Setter
public class HubPlayer extends Player {

    private Profile profile;

    public HubPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);
    }

}
