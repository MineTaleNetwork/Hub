package cc.minetale.hub.cosmetic.objects;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Vec;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.metadata.item.SnowballMeta;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.particle.Particle;
import net.minestom.server.particle.ParticleCreator;
import org.jetbrains.annotations.Nullable;

public class FleshHookProjectile extends CustomEntityProjectile {

    public FleshHookProjectile(@Nullable Entity shooter) {
        super(shooter, EntityType.SNOWBALL, true);
        ((SnowballMeta) getEntityMeta()).setItem(ItemStack.of(Material.TRIPWIRE_HOOK));
        setBoundingBox(getBoundingBox().expand(3, 0, 3));
    }

    public void onStuck() {
        remove();
    }

    @Override
    public void onHit(@Nullable Entity entity) {
        if(entity == null || entity.getVehicle() != null || getShooter() == null || getShooter() == entity) return;
        if(!(entity instanceof Player)) return;

        remove();

        var target = (Player) entity;
        var shooter = (Player) getShooter();
        var shooterPos = shooter.getPosition();
        var pos = entity.getPosition();

        target.playSound(Sound.sound(Key.key("entity.iron_golem.attack"), Sound.Source.MASTER, 10.0F, 1.0F));
        shooter.playSound(Sound.sound(Key.key("entity.experience_orb.pickup"), Sound.Source.MASTER, 1.0F, 1.25F));

        var velocity = new Vec(shooterPos.x() - pos.x(), shooterPos.y() - pos.y(),
                shooterPos.z() - pos.z()).normalize();

        entity.setVelocity(entity.getVelocity().add(velocity.mul(50).withY(20)));
    }

    @Override
    public void tick(long time) {
        super.tick(time);

        if(getShooter() != null) {
            getInstance().playSound(Sound.sound(
                    Key.key("item.flintandsteel.use"),
                    Sound.Source.MASTER,
                    1.4F,
                    0.8F
            ), getPosition().x(), getPosition().y(), getPosition().z());

            var particle = ParticleCreator.createParticlePacket(
                    Particle.CRIT,
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
