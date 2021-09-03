package cc.minetale.hub.util.cooldown;

import lombok.Getter;

import java.text.DecimalFormat;

@Getter
public class Cooldown {

    private static DecimalFormat format = new DecimalFormat("#.##");
    private final CooldownType type;
    private final long started;
    private final long duration;

    public Cooldown(CooldownType type, long started, long duration) {
        this.type = type;
        this.started = started;
        this.duration = duration;
    }

    public boolean hasExpired() {
        return (this.started + this.duration) - System.currentTimeMillis() <= 0;
    }

    public double getSecondsRemaining() {
        return Double.parseDouble(format.format((double) ((this.started + this.duration) - System.currentTimeMillis()) / 1000));
    }


}
