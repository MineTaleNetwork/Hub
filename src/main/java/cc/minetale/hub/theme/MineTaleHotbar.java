package cc.minetale.hub.theme;

import cc.minetale.hub.Hub;
import cc.minetale.hub.cosmetic.CosmeticType;
import cc.minetale.hub.cosmetic.impl.Gadget;
import cc.minetale.hub.cosmetic.menu.CosmeticMenu;
import cc.minetale.hub.hotbar.Hotbar;
import cc.minetale.hub.menu.HubSelectorMenu;
import cc.minetale.hub.menu.ServerSelectorMenu;
import cc.minetale.hub.util.HubPlayer;
import cc.minetale.hub.util.ItemUtil;
import cc.minetale.mlib.canvas.template.Menu;
import cc.minetale.mlib.util.SoundsUtil;
import cc.minetale.sodium.data.HubVisibility;
import cc.minetale.sodium.profile.Profile;
import cc.minetale.sodium.profile.punishment.Punishment;
import cc.minetale.sodium.profile.punishment.PunishmentType;
import cc.minetale.sodium.util.Duration;
import cc.minetale.sodium.util.MongoUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.JoinConfiguration;
import net.kyori.adventure.text.format.NamedTextColor;

import java.util.ArrayList;

public class MineTaleHotbar {

    public static void initializeDefaults() {
        Hotbar.addItem(0, interaction -> { // Server Selector
            Menu.openMenu(new ServerSelectorMenu(interaction.getPlayer()));
        });

        Hotbar.addItem(1, interaction -> { // Shop
            var player = interaction.getPlayer();

            player.sendActionBar(Component.text("This item is currently under development.", NamedTextColor.RED));
            SoundsUtil.playErrorSound(player);
        });

        Hotbar.addItem(3, interaction -> { // Profile
            var player = interaction.getPlayer();

//            player.sendActionBar(Component.text("This item is currently under development.", NamedTextColor.RED));
//            SoundsUtil.playErrorSound(player);

            var punishment = new Punishment(
                    "TEST",
                    player.getUuid(),
                    null,
                    System.currentTimeMillis(),
                    "Test Punishment",
                    Duration.fromString("1d").value(),
                    PunishmentType.MUTE
            );

            var message = new ArrayList<>(punishment.getPunishmentMessage());

            player.sendMessage(Component.join(JoinConfiguration.newlines(), message));
        });

        Hotbar.addItem(4, interaction -> { // Cosmetic Selector
            Menu.openMenu(new CosmeticMenu(interaction.getPlayer()));
        });

        Hotbar.addItem(CosmeticType.GADGET_SLOT, interaction -> { // Gadget
            var player = HubPlayer.fromPlayer(interaction.getPlayer());
            var gadget = player.getActiveGadget();

            if(gadget != null) {
                gadget.interact(interaction);
            }
        });

        Hotbar.addItem(7, interaction -> { // Lobby Selector
            Menu.openMenu(new HubSelectorMenu(interaction.getPlayer()));
        });

        Hotbar.addItem(8, interaction -> { // Visibility Selector
            var player = HubPlayer.fromPlayer(interaction.getPlayer());
            var cooldown = player.getVisibilityCooldown();

            if(cooldown.hasCooldown()) {
                player.sendActionBar(Component.text("You are on cooldown for another " + cooldown.getSecondsRemaining() + " seconds.", NamedTextColor.RED));
                SoundsUtil.playErrorSound(player);
                return;
            } else {
                cooldown.refresh();
            }

            var profile = player.getProfile();
            var hubProfile = profile.getHubProfile();

            SoundsUtil.playClickSound(player);
            hubProfile.setVisibility(HubVisibility.values()[(hubProfile.getVisibility().ordinal() + 1) % HubVisibility.values().length]);
            player.getInventory().setItemStack(8, ItemUtil.VISIBILITY_ITEM(player));
            player.updateViewerRule();
            MongoUtil.saveDocument(Profile.getCollection(), profile.getUuid(), profile);
        });

    }

}
