package cc.minetale.hub.util;

import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.Instance;

public class HubUtil {

    public static void lockAllChunks(Instance instance) {
        for(Chunk chunk : instance.getChunks()) {
            chunk.setReadOnly(true);
        }
    }

}
