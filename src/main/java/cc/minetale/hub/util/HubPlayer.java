package cc.minetale.hub.util;

import cc.minetale.flame.util.FlamePlayer;
import cc.minetale.hub.Hub;
import cc.minetale.hub.cosmetic.Cosmetic;
import cc.minetale.hub.cosmetic.CosmeticType;
import cc.minetale.hub.cosmetic.impl.Gadget;
import cc.minetale.sodium.util.Cooldown;
import lombok.Getter;
import net.minestom.server.entity.Player;
import net.minestom.server.network.player.PlayerConnection;
import net.minestom.server.utils.time.TimeUnit;
import org.jetbrains.annotations.NotNull;

import java.time.Duration;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
public class HubPlayer extends FlamePlayer {

    private Gadget activeGadget;

    private final Cooldown visibilityCooldown;
    private final Cooldown hotbarInteractionCooldown;

    public HubPlayer(@NotNull UUID uuid, @NotNull String username, @NotNull PlayerConnection playerConnection) {
        super(uuid, username, playerConnection);

        setRespawnPoint(Hub.SPAWN);

        activeGadget = null;

        visibilityCooldown = new Cooldown(Duration.of(40, TimeUnit.SERVER_TICK));
        hotbarInteractionCooldown = new Cooldown(Duration.of(10, TimeUnit.SERVER_TICK));

        updateViewerRule(target -> {
            var profile = getProfile();
            var visibility = profile.getHubProfile().getVisibility();

            return switch (visibility) {
                case ALL -> true;
                case VIP -> {
                    if(target instanceof FlamePlayer player) {
                        var targetProfile = player.getProfile();

                        yield targetProfile.isFriends(profile) || targetProfile.isStaff();
                    }

                    yield true;
                }
                case NONE -> !(target instanceof Player);
            };
        });
    }

    @Override
    public void handleVoid() {
        var instance = this.getInstance();

        if (instance != null && instance.isInVoid(this.position)) {
            this.teleport(Hub.SPAWN);
        }
    }

    public boolean isSelected(Cosmetic cosmetic) {
        return switch (cosmetic.getCosmeticType()) {
            case ARMOR -> false;
            case PARTICLE -> false;
            case GADGET -> activeGadget == cosmetic;
            case PET -> false;
        };
    }

    public void deselectType(CosmeticType cosmeticType) {
        switch (cosmeticType) {
            case ARMOR -> {}
            case PARTICLE -> {}
            case GADGET -> {
                if(activeGadget != null) {
                    activeGadget.remove(this);
                    activeGadget = null;
                }
            }
            case PET -> {}
        }
    }

    public void selectCosmetic(Cosmetic cosmetic) {
        cosmetic.apply(this);

        switch (cosmetic.getCosmeticType()) {
            case ARMOR -> {}
            case PARTICLE -> {}
            case GADGET -> activeGadget = (Gadget) cosmetic;
            case PET -> {}
        }
    }

    public List<Cosmetic> getAvailableCosmetics() {
        return Hub.getInstance()
                .getCosmeticHandler()
                .getCosmetics()
                .stream()
                .filter(cosmetic -> cosmetic.hasPermission(this))
                .collect(Collectors.toList());
    }

    public Cosmetic getSelectedCosmetic(CosmeticType cosmeticType) {
        return switch (cosmeticType) {
            case ARMOR -> null;
            case PARTICLE -> null;
            case GADGET -> activeGadget;
            case PET -> null;
        };
    }

    public static HubPlayer fromPlayer(Player player) {
        return (HubPlayer) FlamePlayer.fromPlayer(player);
    }

}
