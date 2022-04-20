package cc.minetale.hub;

import cc.minetale.hub.cosmetic.CosmeticHandler;
import cc.minetale.hub.hotbar.Hotbar;
import cc.minetale.hub.theme.MineTaleHotbar;
import cc.minetale.hub.listener.PlayerListener;
import cc.minetale.hub.theme.MineTaleSidebar;
import cc.minetale.hub.theme.MineTaleTab;
import cc.minetale.hub.tab.Tab;
import cc.minetale.hub.util.HubNPCS;
import cc.minetale.hub.util.HubPlayer;
import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.extensions.Extension;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.timer.ExecutionType;
import net.minestom.server.utils.NamespaceID;
import net.minestom.server.utils.time.Tick;
import net.minestom.server.world.DimensionType;

@Getter
public class Hub extends Extension {

    @Getter
    private static Hub instance;
    private InstanceContainer container;
    private MineTaleTab tab;
    private CosmeticHandler cosmeticHandler;

    public static Pos SPAWN = new Pos(0.5, 72, 0.5);

    @Override
    public void initialize() {
        instance = this;

        (cosmeticHandler = new CosmeticHandler()).setup();

        Tab.initializeTab();

        MinecraftServer.getConnectionManager()
                .setPlayerProvider(HubPlayer::new);

        var dimension = DimensionType
                .builder(NamespaceID.from("minestom:fullbright"))
                .ambientLight(2.0f)
                .build();

        var dimensionTypeManager = MinecraftServer.getDimensionTypeManager();
        dimensionTypeManager.addDimension(dimension);

        var container = MinecraftServer.getInstanceManager()
                .createInstanceContainer(dimension);

        container.setGenerator(unit -> unit.modifier().fillHeight(0, 40, Block.WHITE_CONCRETE));
        container.setTimeRate(0);

        var border = container.getWorldBorder();

        border.setCenterX(0.0F);
        border.setCenterZ(0.0F);
        border.setDiameter(256F);

        for(var npc : HubNPCS.values()) {
            npc.spawn(container);
        }

        this.container = container;

        tab = new MineTaleTab();

        MineTaleHotbar.initializeDefaults();

        MinecraftServer.getSchedulerManager()
                .buildTask(() -> {
                    for (var player : MinecraftServer.getConnectionManager().getOnlinePlayers()) {
                        MineTaleSidebar.update(player);
                        player.sendPlayerListHeaderAndFooter(Tab.header(), Tab.footer(player));
                    }
                })
                .executionType(ExecutionType.ASYNC)
                .repeat(20, Tick.SERVER_TICKS)
                .schedule();

        MinecraftServer.getGlobalEventHandler()
                .addChild(PlayerListener.events())
                .addChild(Hotbar.events());
    }

    @Override
    public void terminate() {}

}
