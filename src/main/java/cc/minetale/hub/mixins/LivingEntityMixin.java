package cc.minetale.hub.mixins;

import cc.minetale.hub.Hub;
import net.minestom.server.entity.Entity;
import net.minestom.server.entity.EntityType;
import net.minestom.server.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.UUID;

@Mixin(value = LivingEntity.class, remap = false)
public class LivingEntityMixin extends Entity {

    public LivingEntityMixin(@NotNull EntityType entityType, @NotNull UUID uuid) {
        super(entityType, uuid);
    }

    /**
     * @author oHate
     */
    @Overwrite
    protected void handleVoid() {
        // Teleport if in void
        if (getInstance().isInVoid(this.position)) {
            teleport(Hub.getHub().getSpawn());
        }
    }

}
