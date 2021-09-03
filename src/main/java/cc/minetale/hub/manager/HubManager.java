package cc.minetale.hub.manager;

import cc.minetale.hub.Hub;
import cc.minetale.hub.util.LobbyInstance;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.SharedInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HubManager {

    public static void createHubs(int amount) {
        for(int i = 1; i <= amount; i++) {
            SharedInstance instance = MinecraftServer.getInstanceManager().createSharedInstance(Hub.getHub().getInstanceContainer());

            LobbyInstance.getHubMap().put(instance.getUniqueId(), new LobbyInstance(instance, i));
        }
    }

    public static LobbyInstance getRandomInstance() {
        List<LobbyInstance> instances = new ArrayList<>(LobbyInstance.getHubMap().values());

        int size = instances.size();

        return instances.get(ThreadLocalRandom.current().nextInt(size) % size);
    }

    public static void lockAllChunks(Instance instance) {
        for(Chunk chunk : instance.getChunks()) {
            chunk.setReadOnly(true);
        }
    }

}
