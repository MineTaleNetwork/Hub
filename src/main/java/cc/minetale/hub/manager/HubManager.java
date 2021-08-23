package cc.minetale.hub.manager;

import cc.minetale.hub.Hub;
import net.minestom.server.MinecraftServer;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.SharedInstance;
import net.minestom.server.tag.Tag;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class HubManager {

    public static void createHubs(int amount) {
        for(int i = 0; i < amount; i++) {
            SharedInstance instance = MinecraftServer.getInstanceManager().createSharedInstance(Hub.instance);

            instance.setTag(Tag.String("name"), "Hub-" + i);
        }
    }

    public static Instance getRandomInstance() {
        List<SharedInstance> instances = Hub.instance.getSharedInstances();
        int size = instances.size();

        return instances.get(ThreadLocalRandom.current().nextInt(size) % size);

    }

    public static String getName(Instance instance) {
        return instance.getTag(Tag.String("name"));
    }

}
