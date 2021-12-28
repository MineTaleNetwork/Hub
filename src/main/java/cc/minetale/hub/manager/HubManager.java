package cc.minetale.hub.manager;

import cc.minetale.hub.Hub;
import cc.minetale.hub.util.HubNPCS;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.SharedInstance;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class HubManager {

    private static final Map<UUID, Integer> lobbyIds = new HashMap<>();

    public static int getLobbyId(UUID uuid) {
        return lobbyIds.getOrDefault(uuid, 0);
    }

    public static void createHubs(int amount) {
        var container = Hub.getInstance().getContainer();

        for(int i = 1; i <= amount; i++) {
            var instance = MinecraftServer.getInstanceManager().createSharedInstance(container);

            lobbyIds.put(instance.getUniqueId(), i);

            for (var npc : HubNPCS.values()) {
                npc.spawn(instance);
            }
        }
    }

    public static SharedInstance getRandomInstance() {
        var instances = Hub.getInstance().getContainer().getSharedInstances();
        int size = instances.size();

        return instances.get(ThreadLocalRandom.current().nextInt(size) % size);
    }

    public static void lockAllChunks(Instance instance) {
        for(var chunk : instance.getChunks()) {
            chunk.setReadOnly(true);
        }
    }

}
