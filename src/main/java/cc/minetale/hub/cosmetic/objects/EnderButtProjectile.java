package cc.minetale.hub.cosmetic.objects;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.particle.Particle;
import net.minestom.server.particle.ParticleCreator;
import org.jetbrains.annotations.Nullable;

public class EnderButtProjectile extends CustomEntityProjectile {

    public EnderButtProjectile(@Nullable Entity shooter) {
        super(shooter, EntityType.ENDER_PEARL, true);
    }

    @Override
    public void onStuck() {
        remove();

        if(getShooter() instanceof Player player) {
            removePassenger(player);
            player.teleport(player.getPosition().add(0, 1, 0));
            player.playSound(Sound.sound(Key.key("entity.enderman.teleport"), Sound.Source.MASTER, 2.0F, 1.5F));
        }
    }

    @Override
    public void tick(long time) {
        super.tick(time);

        if(getShooter() != null) {
            var particle = ParticleCreator.createParticlePacket(
                    Particle.WITCH,
                    getPosition().x(),
                    getPosition().y(),
                    getPosition().z(),
                    0f,
                    0f,
                    0f,
                    1
            );

            getInstance().sendGroupedPacket(particle);
        }
    }

}
