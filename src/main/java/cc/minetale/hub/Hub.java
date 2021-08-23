package cc.minetale.hub;

import cc.minetale.hub.listener.PlayerListener;
import cc.minetale.hub.manager.HubManager;
import cc.minetale.hub.sidebar.HubSidebar;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.util.HubUtil;
import cc.minetale.quartz.generator.VoidGenerator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.trait.EntityEvent;
import net.minestom.server.extensions.Extension;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.SharedInstance;
import net.minestom.server.instance.WorldBorder;
import net.minestom.server.instance.block.Block;
import net.minestom.server.utils.chunk.ChunkUtils;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Hub extends Extension {

    public static InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer();
    public static Pos spawn = new Pos(0, 64, 0);

    @Override
    public void initialize() {
        instance.setChunkGenerator(new VoidGenerator());

        HubManager.createHubs(1);

        var positions = new Pos[]{new Pos(0.5, 60, 0.5), new Pos(-0.5, 60, -0.5), new Pos(-0.5, 60, 0.5), new Pos(0.5, 60, -0.5)};

        // TODO: Improve this

        ChunkUtils.optionalLoadAll(instance, ChunkUtils.getChunksInRange(new Pos(0.0, 0, 0.0), 2), (chunk) -> {}).thenAccept((Void) -> {
            for(SharedInstance sharedInstance : instance.getSharedInstances()) {
                WorldBorder border = sharedInstance.getWorldBorder();
                border.setCenterX(0.0F);
                border.setCenterZ(0.0F);
                border.setDiameter(64F);
            }

            instance.enableAutoChunkLoad(false);

            for(Pos pos : positions) {
                instance.setBlock(pos, Block.BEDROCK);
            }

            HubUtil.lockAllChunks(instance);
        });

        MinecraftServer.getSchedulerManager().buildTask(() -> {
            for(HubSidebar hubSidebar : HubSidebar.getSidebars().values()) {
                hubSidebar.update();
            }
        }).repeat(Duration.of(10, ChronoUnit.SECONDS)).schedule();

        MinecraftServer.getSchedulerManager().buildTask(() -> {
            for(Player player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));
            }
        }).repeat(Duration.of(5, ChronoUnit.SECONDS)).schedule();

        MinecraftServer.getGlobalEventHandler().addChild(events());
    }

    @Override
    public void terminate() {}

    public static EventNode<EntityEvent> events() {
        EventNode<EntityEvent> node = EventNode.type("hub-events", EventFilter.ENTITY);

        node.addChild(playerEvents());

        return node;
    }

    public static EventNode<EntityEvent> playerEvents() {
        return PlayerListener.events();
    }
}
