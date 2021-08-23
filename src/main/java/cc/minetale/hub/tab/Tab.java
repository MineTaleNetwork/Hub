package cc.minetale.hub.tab;

import cc.minetale.mlib.mLib;
import com.customwrld.commonlib.util.MC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Tab {

    public static Component header() {
        List<Component> header = new ArrayList<>();
        header.add(Component.empty());
        header.add(Component.text("MineTale Network", MC.CC.GOLD.getTextColor(), TextDecoration.BOLD));
        header.add(Component.text()
                .append(Component.text("    www.minetale.cc", MC.CC.GRAY.getTextColor()))
                .append(Component.text(" * ", MC.CC.DARK_GRAY.getTextColor()))
                .append(Component.text("store.minetale.cc    ", MC.CC.GRAY.getTextColor()))
                .build());
        header.add(Component.empty());

        return Component.join(Component.newline(), header);
    }

    public static Component footer(Player player) {
        List<Component> footer = new ArrayList<>();
        footer.add(Component.empty());
        footer.add(Component.text()
                .append(Component.text("    " + mLib.getMLib().getConfig().getName(), MC.CC.GOLD.getTextColor()))
                .append(Component.text(" (", MC.CC.GRAY.getTextColor()))
                .append(Component.text("Ping: " + player.getLatency() + "ms", MC.CC.GOLD.getTextColor()))
                .append(Component.text(")", MC.CC.GRAY.getTextColor()))
                .append(Component.text(" * ", MC.CC.DARK_GRAY.getTextColor()))
                .append(Component.text("minetale.cc/discord    ", MC.CC.GOLD.getTextColor()))
                .build());
        footer.add(Component.text()
                .append(Component.text("    There are currently ", MC.CC.GRAY.getTextColor()))
                .append(Component.text(player.getInstance().getPlayers().size(), MC.CC.GOLD.getTextColor()))
                .append(Component.text(" players connected    ", MC.CC.GRAY.getTextColor()))
                .build());
        footer.add(Component.empty());

        return Component.join(Component.newline(), footer);
    }

}
