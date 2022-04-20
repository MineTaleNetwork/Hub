package cc.minetale.hub.hotbar;

import cc.minetale.hub.util.HubPlayer;
import net.minestom.server.entity.Player;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerBlockInteractEvent;
import net.minestom.server.event.player.PlayerEntityInteractEvent;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.event.trait.PlayerEvent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Hotbar {

    private static final List<Consumer<PlayerUseItemEvent>> itemActions = new ArrayList<>(Collections.nCopies(9, null));

    public static void addItem(int slot, Consumer<PlayerUseItemEvent> action) {
        itemActions.set(slot, action);
    }

    public static void removeItem(int slot) {
        itemActions.remove(slot);
    }

    public static EventNode<PlayerEvent> events() {
        return EventNode.type("hotbar", EventFilter.PLAYER)
                .addListener(PlayerUseItemEvent.class, event -> {
                    var player = HubPlayer.fromPlayer(event.getPlayer());
                    var cooldown = player.getHotbarInteractionCooldown();

                    event.setCancelled(true);

//                    if(cooldown.hasCooldown()) {
//                        return;
//                    } else {
//                        cooldown.refresh();
//                    }

                    if (event.getHand() == Player.Hand.MAIN) {
                        var action = itemActions.get(player.getHeldSlot());

                        if(action == null) { return; }

                        action.accept(event);
                    }
                });
    }

}
