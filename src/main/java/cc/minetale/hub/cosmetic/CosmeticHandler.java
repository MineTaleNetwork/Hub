package cc.minetale.hub.cosmetic;

import cc.minetale.hub.cosmetic.cosmetics.gadget.EnderButtGadget;
import cc.minetale.hub.cosmetic.cosmetics.gadget.FleshHookGadget;
import cc.minetale.hub.cosmetic.cosmetics.gadget.PaintBallGadget;
import cc.minetale.hub.util.HubPlayer;
import lombok.Getter;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.trait.PlayerEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class CosmeticHandler {

    private final List<Cosmetic> cosmetics = new ArrayList<>();

    public void setup() {
        cosmetics.addAll(Arrays.asList(
                new FleshHookGadget(),
                new EnderButtGadget(),
                new PaintBallGadget()
        ));

        MinecraftServer.getGlobalEventHandler()
                .addChild(events());
    }

    public EventNode<PlayerEvent> events() {
        return EventNode.type("cosmetic", EventFilter.PLAYER)
                .addListener(PlayerDisconnectEvent.class, event -> {
                    var player = HubPlayer.fromPlayer(event.getPlayer());

                    var gadget = player.getActiveGadget();

                    if(gadget != null) {
                        gadget.remove(player);
                    }
                });
    }

}