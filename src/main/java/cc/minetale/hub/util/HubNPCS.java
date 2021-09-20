package cc.minetale.hub.util;

import cc.minetale.mlib.hologram.component.HologramComponent;
import cc.minetale.mlib.hologram.component.HologramLine;
import cc.minetale.mlib.npc.NPC;
import cc.minetale.mlib.npc.NPCInteraction;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.instance.Instance;

import java.util.function.Consumer;

@Getter
public enum HubNPCS {
    TEST_NPC(
            new Pos(0.5, 61.0, 0.5),
            new PlayerSkin("", ""),
            (interaction) -> {
                Player player = interaction.getPlayer();
                NPC npc = interaction.getNpc();

                player.sendMessage(Component.text("You interacted with the " + npc.getUsername() + " NPC"));
            },
            HologramLine.of(Component.text("Test"))
    );

    private final Pos position;
    private final PlayerSkin skin;
    private final Consumer<NPCInteraction> interaction;
    private final HologramComponent[] components;

    HubNPCS(
            Pos position,
            PlayerSkin skin,
            Consumer<NPCInteraction> interaction,
            HologramComponent... components
    ) {
        this.position = position;
        this.skin = skin;
        this.interaction = interaction;
        this.components = components;
    }

    public NPC spawn(Instance instance) {
        return new NPC(instance, this.position, this.skin, this.interaction, this.components);
    }

}
