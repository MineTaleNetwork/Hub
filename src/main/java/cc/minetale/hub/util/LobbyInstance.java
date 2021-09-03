package cc.minetale.hub.util;

import lombok.Getter;
import net.minestom.server.instance.SharedInstance;

import java.util.*;

@Getter
public class LobbyInstance {

    @Getter private static final Map<UUID, LobbyInstance> hubMap = new HashMap<>();

    private final SharedInstance instance;
    private final Integer lobbyId;

    public LobbyInstance(SharedInstance instance, Integer lobbyId) {
        this.instance = instance;
        this.lobbyId = lobbyId;
    }
}
