package cc.minetale.hub.cosmetic.cosmetics.gadget;

import cc.minetale.hub.Hub;
import cc.minetale.hub.cosmetic.impl.Gadget;
import cc.minetale.hub.cosmetic.objects.PaintBallProjectile;
import cc.minetale.hub.cosmetic.objects.SplatterLocation;
import lombok.Getter;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.sound.Sound;
import net.minestom.server.MinecraftServer;
import net.minestom.server.event.player.PlayerUseItemEvent;
import net.minestom.server.item.Material;
import net.minestom.server.timer.ExecutionType;
import net.minestom.server.timer.TaskSchedule;
import net.minestom.server.utils.time.Tick;

import java.util.*;

public class PaintBallGadget extends Gadget {

    @Getter private static final List<SplatterLocation> splatterList = Collections.synchronizedList(new LinkedList<>());

    public PaintBallGadget() {
        MinecraftServer.getSchedulerManager()
                .buildTask(() -> {
                    if(splatterList.size() > 0) {
                        var splatterLocation = splatterList.get(0);
                        var time = splatterLocation.time();

                        if (time - System.currentTimeMillis() <= 0) {
                            splatterList.remove(0);

                            var container = Hub.getInstance().getContainer();

                            var it = splatterLocation.blocks().iterator();

                            MinecraftServer.getSchedulerManager()
                                    .submitTask(() -> {
                                        if(it.hasNext()) {
                                            var blockPos = it.next();

                                            it.remove();

                                            container.setBlock(blockPos.pos(), blockPos.block());
                                        }

                                        return !it.hasNext() ? TaskSchedule.stop() : TaskSchedule.nextTick();
                                    }, ExecutionType.ASYNC);
                        }
                    }
                })
                .executionType(ExecutionType.ASYNC)
                .repeat(1L, Tick.SERVER_TICKS)
                .schedule();
    }

    @Override
    public String getName() {
        return "Paint Ball Gun";
    }

    @Override
    public Material getIcon() {
        return Material.IRON_HORSE_ARMOR;
    }

    @Override
    public List<String> getDescription() {
        return Arrays.asList(
                "Once I shoot someone in the eye during a paintball match.",
                "It counts as a headshot, right?"
        );
    }

    @Override
    public int getCost() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void interact(PlayerUseItemEvent event) {
        var player = event.getPlayer();
        var projectile = new PaintBallProjectile(player);

        var position = player.getPosition().add(0D, player.getEyeHeight(), 0D);
        projectile.setInstance(player.getInstance(), position);

        var direction = position.direction();
        position = position.add(direction).sub(0, 0.2, 0);

        projectile.shoot(position, 1.0, 1.0);

        player.playSound(Sound.sound(Key.key("entity.chicken.egg"), Sound.Source.MASTER, 1.0F, 1.5F));
    }

}
