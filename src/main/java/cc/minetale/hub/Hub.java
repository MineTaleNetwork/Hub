package cc.minetale.hub;

import cc.minetale.hub.listener.PlayerListener;
import cc.minetale.hub.sidebar.HubSidebar;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.util.HubNPCS;
import cc.minetale.hub.util.HubPlayer;
import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.extensions.Extension;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.timer.ExecutionType;
import net.minestom.server.utils.time.Tick;

@Getter
public class Hub extends Extension {

    @Getter
    private static Hub instance;
    private InstanceContainer container;

    public static Pos SPAWN = new Pos(0.5, 72, 0.5);

    @Override
    public void initialize() {
        instance = this;

        MinecraftServer.getConnectionManager().setPlayerProvider(HubPlayer::new);

        var container = MinecraftServer.getInstanceManager().createInstanceContainer(new AnvilLoader("hub"));
        container.setTimeRate(0);

        var border = container.getWorldBorder();

        border.setCenterX(0.0F);
        border.setCenterZ(0.0F);
        border.setDiameter(256F);

        for(var npc : HubNPCS.values()) {
            npc.spawn(container);
        }

        this.container = container;

        MinecraftServer.getSchedulerManager()
                .buildTask(() -> {
                    for (var player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                        HubSidebar.update(player);
                        player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));
                    }
                })
                .executionType(ExecutionType.ASYNC)
                .repeat(20, Tick.SERVER_TICKS)
                .schedule();

        MinecraftServer.getGlobalEventHandler().addChild(PlayerListener.events());
    }

    @Override
    public void terminate() {}

}
