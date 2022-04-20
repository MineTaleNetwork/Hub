package cc.minetale.hub.cosmetic.objects;

import cc.minetale.hub.Hub;
import cc.minetale.hub.cosmetic.cosmetics.gadget.PaintBallGadget;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.metadata.item.SnowballMeta;
import net.minestom.server.instance.batch.AbsoluteBlockBatch;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.particle.Particle;
import net.minestom.server.particle.ParticleCreator;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class PaintBallProjectile extends CustomEntityProjectile {

    public static List<Material> CONCRETE = List.of(
            Material.RED_CONCRETE,
            Material.PINK_CONCRETE,
            Material.LIME_CONCRETE,
            Material.YELLOW_CONCRETE,
            Material.BLUE_CONCRETE,
            Material.LIGHT_BLUE_CONCRETE,
            Material.PURPLE_CONCRETE,
            Material.MAGENTA_CONCRETE,
            Material.ORANGE_CONCRETE,
            Material.CYAN_CONCRETE
    );

    public PaintBallProjectile(@Nullable Entity shooter) {
        super(shooter, EntityType.SNOWBALL, true);
        ((SnowballMeta) getEntityMeta()).setItem(ItemStack.of(Material.SLIME_BALL));
    }

    @Override
    public void onStuck() {
        remove();

        var splatterBlock = CONCRETE.get(ThreadLocalRandom.current().nextInt(CONCRETE.size())).block();

        var radiusPositions = getNearbyBlocks(getPosition(), 3);
        var locations = new ArrayList<SplatterLocation.BlockPos>();

        var container = Hub.getInstance().getContainer();
        var batch = new AbsoluteBlockBatch();

        for(var pos : radiusPositions) {
            var block = container.getBlock(pos);

            if(!block.isAir() && !CONCRETE.contains(block.registry().material())) {
                locations.add(new SplatterLocation.BlockPos(block, pos));

                batch.setBlock(pos, splatterBlock);
            }
        }

        batch.apply(container, null);

        getInstance().playSound(Sound.sound(
                Key.key("entity.slime.squish"),
                Sound.Source.MASTER,
                2.0F,
                1.0F
        ), position.x(), position.y(), position.z());

        PaintBallGadget.getSplatterList().add(new SplatterLocation(System.currentTimeMillis() + 3000, locations));
    }

    public static List<Pos> getNearbyBlocks(Pos pos, int radius) {
        var blocks = new ArrayList<Pos>();

        for(int x = pos.blockX() - radius; x <= pos.blockX() + radius; x++) {
            for(int y = pos.blockY() - radius; y <= pos.blockY() + radius; y++) {
                for(int z = pos.blockZ() - radius; z <= pos.blockZ() + radius; z++) {
                    var xDist = x - pos.x();
                    var yDist = y - pos.y();
                    var zDist = z - pos.z();

                    var distance = (xDist*xDist) + (yDist*yDist) + (zDist*zDist);
                    var newPos = new Pos(x, y, z);

                    if(distance > radius * radius || Hub.getInstance().getContainer().getBlock(newPos).isAir()) { continue; }

                    blocks.add(newPos);
                }
            }
        }

        Collections.shuffle(blocks);

        return blocks;
    }

    @Override
    public void tick(long time) {
        super.tick(time);

        if(getShooter() != null) {
            var particle = ParticleCreator.createParticlePacket(
                    Particle.ITEM_SLIME,
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
