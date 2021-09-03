package cc.minetale.hub.util.cooldown;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CooldownType {
    VISIBILITY(1000),
    LOBBY_SWITCH(3000);

    private final long duration;
}
