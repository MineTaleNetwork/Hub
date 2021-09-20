package cc.minetale.hub.util;

import cc.minetale.mlib.npc.NPCInteraction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.player.PlayerEntityInteractEvent;

import java.util.function.Consumer;

@Getter @AllArgsConstructor
public enum HubNPCS {
    TEST_NPC(Pos.ZERO, new PlayerSkin("", ""), (interaction) -> {});

    private final Pos spawnPosition;
    private final PlayerSkin skin;
    private final Consumer<NPCInteraction> interaction;
    // TODO private final Hologram hologram;
}
