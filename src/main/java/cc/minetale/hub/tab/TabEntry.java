package cc.minetale.hub.tab;

import lombok.Getter;
import lombok.Setter;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.PlayerSkin;

import java.util.UUID;

@Getter @Setter
public class TabEntry {

    public static final PlayerSkin DEFAULT_SKIN = new PlayerSkin("ewogICJ0aW1lc3RhbXAiIDogMTY0ODI4NzUzMTA4NywKICAicHJvZmlsZUlkIiA6ICJhYzM2YmVkZGQxNGQ0YjVmYmQyYzc5OThlMWMwOTg3ZCIsCiAgInByb2ZpbGVOYW1lIiA6ICJtYWlzYWthIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU3ZGRlMGExN2MwNDY4MzI0MGIyNzJlYjJkODk1NWY4NzRiM2ExMTIyNTkxNjMwMGZlM2U4ODhkZjI4YjI4ZDciCiAgICB9CiAgfQp9", "ScFX2rWxyhSyIn3YIke0ecNezt0bx+K8fRLQVPAzNli+X/dmod9ohujwwmDyog0exnlDAdEtXJY2XEUELpNLBHYZFyMsUChL4MObACzxGXExRBbyE8NzemmcRePKmglJfIHBxATlvG3VXeNn1dNA9g+GgLAB4KdqlDaYO5qAtdET7rckzLhSVeFz3yct3LuqZwzutdkJIbnR2Bu9kM4IpyowDbBEoASUp2ogNq4bQ+9O7cU7PtayknPGJaustHQR32jVcLYNqGweZKjZZUgER+6XrGAwyuWENQ00UpEWanHa2ahBugxzg1atcgc3spx3FxWLN0bVsUj4oXulPhxD4/44jALhHl7898qXwoRhqGaAuombJRH/bMoyoTZUDgcxmTbWFZcos9Ugg6eBlQo7ip35mW7fyd/8rk6RCGyf/wmqyDUnFNUHeQgJHHED+yr2oN/Y4jzCmG5Ikk1RExpi2Mbi7ouZx3bKzkTsEafGdnx8sMxenRCYFtcoQCcV4woQsk7xqqJyBVFiU4wXzHzMnbOhiRPJHlcqzJljFw2LuI97f8vlQGpW5KriFUWLSgKs1Zw4QOIdl5cv/oSZOvEAoi0s5EOB4rzVVE1XXLatYWOaRXy6Nbl8c9SH8UbkhcK5t+J54UIsvvuqq8AoDOX6yyw/wDORVlTaqy7pVXKZSQk=");

    private final UUID uuid;
    private final PlayerSkin skin;
    private Component display;

    public TabEntry(Component display) {
        this(DEFAULT_SKIN, display);
    }

    public TabEntry(PlayerSkin skin, Component display) {
        this.uuid = UUID.randomUUID();
        this.skin = skin;
        this.display = display;
    }

}
