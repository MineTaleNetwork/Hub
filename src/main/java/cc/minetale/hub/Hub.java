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

    @Getter private static Hub instance;
    private InstanceContainer container;

    public static Pos SPAWN = new Pos(0.5, 72, 0.5);

    @Override
    public void initialize() {
        instance = this;

        MinecraftServer.getConnectionManager().setPlayerProvider(HubPlayer::new);

        this.container = MinecraftServer.getInstanceManager().createInstanceContainer(new AnvilLoader("hub"));

        HubManager.createHubs(50);

        for(var instance : this.container.getSharedInstances()) {
            var border = instance.getWorldBorder();
            border.setCenterX(0.0F);
            border.setCenterZ(0.0F);
            border.setDiameter(768F);

            instance.setTimeRate(0);
        }

        MinecraftServer.getSchedulerManager().buildTask(() -> {
            for(var player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                HubSidebar.update(player);
                player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));
            }
        }).repeat(Duration.of(5, ChronoUnit.SECONDS)).schedule();

        MinecraftServer.getGlobalEventHandler().addChild(PlayerListener.events());
    }

    @Override
    public void terminate() {}

}
