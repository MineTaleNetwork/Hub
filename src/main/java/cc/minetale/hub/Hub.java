package cc.minetale.hub;

import cc.minetale.hub.listener.PlayerListener;
import cc.minetale.hub.manager.HubManager;
import cc.minetale.hub.menu.HubSelectorMenu;
import cc.minetale.hub.menu.ServerSelectorMenu;
import cc.minetale.hub.sidebar.HubSidebar;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.util.cooldown.CooldownManager;
import cc.minetale.quartz.generator.VoidGenerator;
import lombok.Getter;
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

@Getter
public class Hub extends Extension {

    // TODO: Make ServerSelector Fabric & make each inventory update online players

    @Getter private static Hub hub;
    private InstanceContainer instanceContainer;
    private CooldownManager cooldownManager;
    private ServerSelectorMenu serverSelectorMenu;
    private HubSelectorMenu hubSelectorMenu;
    private Pos spawn;

    @Override
    public void initialize() {
        hub = this;

        this.instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer();
        this.instanceContainer.setChunkGenerator(new VoidGenerator());

        this.cooldownManager = new CooldownManager();

        this.serverSelectorMenu = new ServerSelectorMenu();
        this.hubSelectorMenu = new HubSelectorMenu();

        this.spawn = new Pos(0, 64, 0);

        HubManager.createHubs(50);

        var positions = new Pos[]{new Pos(0.5, 60, 0.5), new Pos(-0.5, 60, -0.5), new Pos(-0.5, 60, 0.5), new Pos(0.5, 60, -0.5)};

        ChunkUtils.optionalLoadAll(this.instanceContainer, ChunkUtils.getChunksInRange(new Pos(0.0, 0, 0.0), 2), (chunk) -> {}).thenAccept((Void) -> {
            for(SharedInstance sharedInstance : this.instanceContainer.getSharedInstances()) {
                WorldBorder border = sharedInstance.getWorldBorder();
                border.setCenterX(0.0F);
                border.setCenterZ(0.0F);
                border.setDiameter(64F);
            }

            this.instanceContainer.enableAutoChunkLoad(false);

            for(Pos pos : positions) {
                this.instanceContainer.setBlock(pos, Block.BEDROCK);
            }

            HubManager.lockAllChunks(this.instanceContainer);
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
