package cc.minetale.hub.tab;

import cc.minetale.mlib.mLib;
import cc.minetale.commonlib.util.MC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;

import java.util.Arrays;

public class Tab {

    // TODO: Make this an interface

    public static Component header() {
        return Component.join(Component.newline(), Arrays.asList(
                Component.empty(),
                MC.component("MineTale Network", MC.CC.GOLD, TextDecoration.BOLD),
                MC.component(
                        MC.component("    www.minetale.cc", MC.CC.GRAY),
                        MC.component(" * ", MC.CC.DARK_GRAY),
                        MC.component("store.minetale.cc    ", MC.CC.GRAY)
                ),
                Component.empty()
        ));
    }

    public static Component footer(Player player) {
        Instance instance = player.getInstance();

        if(instance != null) {
            return Component.join(Component.newline(), Arrays.asList(
                    Component.empty(),
                    MC.component(
                            MC.component("    " + mLib.getMLib().getConfig().getName(), MC.CC.GOLD),
                            MC.component(" (", MC.CC.GRAY),
                            MC.component("Ping: " + player.getLatency() + "ms", MC.CC.GOLD),
                            MC.component(")", MC.CC.GRAY),
                            MC.component(" * ", MC.CC.DARK_GRAY),
                            MC.component("minetale.cc/discord    ", MC.CC.GOLD)
                    ),
                    MC.component(
                            MC.component("    There are currently ", MC.CC.GRAY),
                            MC.component(String.valueOf(instance.getPlayers().size()), MC.CC.GOLD),
                            MC.component(" players connected    ", MC.CC.GRAY)
                    ),
                    Component.empty()
            ));
        } else {
            return Component.empty();
        }
    }

}
