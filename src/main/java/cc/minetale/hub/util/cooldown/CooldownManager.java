package cc.minetale.hub.util.cooldown;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CooldownManager {


    private final Map<UUID, Set<Cooldown>> cooldownMap;

    public CooldownManager() {
        this.cooldownMap = new ConcurrentHashMap<>();

        new Thread(() -> {
            while(true) {
                try {
                    this.cooldownMap.entrySet().removeIf(entry -> {
                        Set<Cooldown> cooldowns = entry.getValue();

                        cooldowns.removeIf(Cooldown::hasExpired);

                        return cooldowns.isEmpty();
                    });
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void putCooldown(UUID uuid, CooldownType type) {
        Set<Cooldown> cooldowns = this.cooldownMap.get(uuid);

        if(cooldowns == null) {
            cooldowns = new HashSet<>();
        } else {
            Cooldown cooldown = this.getCooldownByType(uuid, type);

            if(cooldown != null)
                cooldowns.remove(cooldown);
        }

        cooldowns.add(new Cooldown(type, System.currentTimeMillis(), type.getDuration()));

        this.cooldownMap.put(uuid, cooldowns);
    }

    public Cooldown getCooldownByType(UUID uuid, CooldownType type) {
        Set<Cooldown> cooldowns = this.cooldownMap.get(uuid);

        if(cooldowns == null || cooldowns.isEmpty())
            return null;

        for (final var it = cooldowns.iterator(); it.hasNext();) {
            Cooldown cooldown = it.next();

            if(cooldown.getType().equals(type)) {
                if(cooldown.hasExpired()) {
                    it.remove();

                    return null;
                }

                return cooldown;
            }
        }

        return null;
    }

}
