package cc.minetale.hub;

import cc.minetale.hub.listener.PlayerListener;
import cc.minetale.hub.manager.HubManager;
import cc.minetale.hub.sidebar.HubSidebar;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.util.HubPlayer;
import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.extensions.Extension;
import net.minestom.server.instance.AnvilLoader;
import net.minestom.server.instance.InstanceContainer;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

@Getter
public class Hub extends Extension {

    @Getter private static Hub hub;
    private InstanceContainer instanceContainer;
    private Pos spawn;

    @Override
    public void initialize() {
        hub = this;

        MinecraftServer.getConnectionManager().setPlayerProvider(HubPlayer::new);

        this.instanceContainer = MinecraftServer.getInstanceManager().createInstanceContainer(new AnvilLoader("hub")); // new AnvilLoader("hub")
//        this.instanceContainer.setChunkGenerator(new VoidGenerator());

        this.spawn = new Pos(0.5, 72, 0.5);

        HubManager.createHubs(50);

        // TODO Prevent player from moving past world border

        for(var instance : this.instanceContainer.getSharedInstances()) {
            var border = instance.getWorldBorder();
            border.setCenterX(0.0F);
            border.setCenterZ(0.0F);
            border.setDiameter(768F);

            instance.setTimeRate(0);
        }

        MinecraftServer.getSchedulerManager().buildTask(() -> {
            for(var player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                var sidebar = HubSidebar.getSidebars().get(player.getUuid());

                if(sidebar != null) {
                    sidebar.update();
                }

                player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));
            }
        }).repeat(Duration.of(5, ChronoUnit.SECONDS)).schedule();

        MinecraftServer.getGlobalEventHandler().addChild(PlayerListener.events());
    }

    @Override
    public void terminate() {}

}
