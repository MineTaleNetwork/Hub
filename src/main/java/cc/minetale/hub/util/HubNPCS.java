package cc.minetale.hub.util;

import cc.minetale.mlib.hologram.component.HologramComponent;
import cc.minetale.mlib.hologram.component.HologramLine;
import cc.minetale.mlib.npc.NPC;
import cc.minetale.mlib.npc.NPCInteraction;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.util.TriState;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.instance.Instance;

import java.util.function.Consumer;

@Getter
public enum HubNPCS {
    TEST_NPC(
            new Pos(0.5, 70.0, 0.5),
            new PlayerSkin(
                    "ewogICJ0aW1lc3RhbXAiIDogMTYyNTA1MjQyOTE0OSwKICAicHJvZmlsZUlkIiA6ICI4MWU4ZWU1MTE1ZmE0NzU5OTU1YTAzMThkNTNhNjViYyIsCiAgInByb2ZpbGVOYW1lIiA6ICJSZWxpYWJsZVJlZHN0b25lIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2MwNWNjNmE2MzM5NGIwOTBjNmQ0Y2FkNzQyMDE5YzRhMjA2NzdjM2U4ZjlhNTZhMDZmZmM3ZTZiMGJhNGRlMTkiCiAgICB9CiAgfQp9",
                    "urn23BTb4xEuC6mNp749/PK4wFexlpl/F9X9JRg/wSSKmjefmQLeygCP7PFQPC7k6iewYlDAHtIAA4n++mDaColJWtH+eaFHbK1an6wEF/aFz3mtzacu5iAObLYtWOXceJVJTJSt94bSglyCA+odBEC9aHDYyXW7/KoGA2WPrBFa0SHmHZ5+WymlI8dsxDglu/dp4d9hg5OgSCK6OwkIlKOkxVkXxpBoZrMEUmJi6Ps80PO0f6w0buyhIJ3Sh98Zgv+zITLTW3Qz2+qy2QvttvkZDxxT/1oeNPKCpeZ/WFpzFZkAfoCnCjs5uTWaXUP3+MQA1zXZwyr3oU0Wj370DYrTzoZAZKwHayDQtllYN1rBcNJ7iFv3aEh2GVEmbds3kPAh1jPgbx7pRlfGzTw+GPgYbUALdD0Fcgz0jmO58NmJZn3XajlSD3FRozZfNrpGQG4JTQcV9gfc0dUUrqUhhmcLxuLB6uwJG9m0zD0/JNSwmO8bVRHYx7u/lxfS6aPLMuSU+Zz0d+sMp8yiF2bRJPsprjfQ+VH3/qT8k9bZsXg0NTVkviuI7KSQ1MQF9JMhqmyeaOs4SCr1AQOdo0GUhiFg2MZU6q8fwIXLFz4gZN6LU9eRgOWfKiZ0yRvAy7E6papz5nBIbacgC0Gl/WNZjnxfaM0aaQA15U5CJHFb8Oo="
            ),
            (interaction) -> {
                var player = interaction.getPlayer();
                var npc = interaction.getNpc();

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
        return new NPC(instance, this.position, this.skin, TriState.TRUE, this.interaction, this.components);
    }

}
