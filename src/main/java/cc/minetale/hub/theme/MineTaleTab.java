package cc.minetale.hub.theme;

import cc.minetale.hub.tab.Tab;
import cc.minetale.sodium.util.Colors;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;

public class MineTaleTab extends Tab {

    public MineTaleTab() {

        //
        // Row #1
        //

        update(0, 1, Component.text("Website", Colors.BLUE, TextDecoration.BOLD));
        update(0, 2, Component.text("minetale.cc", NamedTextColor.GRAY));

        update(0, 4, Component.text("???", Colors.BLUE));
        update(0, 5, Component.text()
                .append(
                        Component.text("Playing: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());
        update(0, 6, Component.text()
                .append(
                        Component.text("Queued: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());

        update(0, 8, Component.text("???", Colors.BLUE));
        update(0, 9, Component.text()
                .append(
                        Component.text("Playing: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());
        update(0, 10, Component.text()
                .append(
                        Component.text("Queued: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());

        update(0, 12, Component.text("???", Colors.BLUE));
        update(0, 13, Component.text()
                .append(
                        Component.text("Playing: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());
        update(0, 14, Component.text()
                .append(
                        Component.text("Queued: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());

        update(0, 16, Component.text("???", Colors.BLUE));
        update(0, 17, Component.text()
                .append(
                        Component.text("Playing: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());
        update(0, 18, Component.text()
                .append(
                        Component.text("Queued: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());

        //
        // Row #2
        //

        update(1, 1, Component.text("Store", Colors.BLUE, TextDecoration.BOLD));
        update(1, 2, Component.text("store.minetale.cc", NamedTextColor.GRAY));

        update(1, 4, Component.text("???", Colors.BLUE));
        update(1, 5, Component.text()
                .append(
                        Component.text("Playing: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());
        update(1, 6, Component.text()
                .append(
                        Component.text("Queued: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());

        update(1, 8, Component.text("???", Colors.BLUE));
        update(1, 9, Component.text()
                .append(
                        Component.text("Playing: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());
        update(1, 10, Component.text()
                .append(
                        Component.text("Queued: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());

        update(1, 12, Component.text("???", Colors.BLUE));
        update(1, 13, Component.text()
                .append(
                        Component.text("Playing: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());
        update(1, 14, Component.text()
                .append(
                        Component.text("Queued: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());

        update(1, 16, Component.text("???", Colors.BLUE));
        update(1, 17, Component.text()
                .append(
                        Component.text("Playing: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());
        update(1, 18, Component.text()
                .append(
                        Component.text("Queued: ", NamedTextColor.GRAY),
                        Component.text("0", NamedTextColor.WHITE)
                ).build());

        //
        // Row #3
        //

        update(2, 1, Component.text("Discord", Colors.BLUE, TextDecoration.BOLD));
        update(2, 2, Component.text(".gg/MineTale", NamedTextColor.GRAY));

        update(2, 4, Component.text("    Survival Red    ", NamedTextColor.RED, TextDecoration.BOLD));

        update(2, 6, Component.text("Server Information", Colors.DARK_BLUE, TextDecoration.BOLD));
        update(2, 7, Component.text().append(
                Component.text(" Online: "),
                Component.text("0/200", NamedTextColor.YELLOW)
        ).build());
        update(2, 8, Component.text().append(
                Component.text(" Queued: "),
                Component.text("0", NamedTextColor.DARK_AQUA)
        ).build());

        update(2, 10, Component.text("Player Information", Colors.DARK_BLUE, TextDecoration.BOLD));
        update(2, 11, Component.text().append(
                Component.text(" Kills: "),
                Component.text("0", NamedTextColor.GREEN)
        ).build());
        update(2, 12, Component.text().append(
                Component.text(" Deaths: "),
                Component.text("0", NamedTextColor.RED)
        ).build());
        update(2, 13, Component.text().append(
                Component.text(" Playtime: "),
                Component.text("0 seconds", NamedTextColor.AQUA)
        ).build());

        update(2, 15, Component.text("Team Information", Colors.DARK_BLUE, TextDecoration.BOLD));
        update(2, 16, Component.text().append(
                Component.text(" Role: "),
                Component.text("Member", NamedTextColor.DARK_GRAY)
        ).build());
        update(2, 17, Component.text().append(
                Component.text(" Members: "),
                Component.text("0/0", NamedTextColor.GRAY)
        ).build());
        update(2, 18, Component.text().append(
                Component.text(" Balance: "),
                Component.text("$", NamedTextColor.DARK_GREEN),
                Component.text("0", NamedTextColor.GREEN)
        ).build());

        //
        // Row #4
        //

        update(3, 1, Component.text("Twitter", Colors.BLUE, TextDecoration.BOLD));
        update(3, 2, Component.text("@MineTaleCC", NamedTextColor.GRAY));

        update(3, 4, Component.text("    Survival Blue    ", NamedTextColor.BLUE, TextDecoration.BOLD));

        update(3, 6, Component.text("Server Information", Colors.DARK_BLUE, TextDecoration.BOLD));
        update(3, 7, Component.text().append(
                Component.text(" Online: "),
                Component.text("0/200", NamedTextColor.YELLOW)
        ).build());
        update(3, 8, Component.text().append(
                Component.text(" Queued: "),
                Component.text("0", NamedTextColor.DARK_AQUA)
        ).build());

        update(3, 10, Component.text("Player Information", Colors.DARK_BLUE, TextDecoration.BOLD));
        update(3, 11, Component.text().append(
                Component.text(" Kills: "),
                Component.text("0", NamedTextColor.GREEN)
        ).build());
        update(3, 12, Component.text().append(
                Component.text(" Deaths: "),
                Component.text("0", NamedTextColor.RED)
        ).build());
        update(3, 13, Component.text().append(
                Component.text(" Playtime: "),
                Component.text("0 seconds", NamedTextColor.AQUA)
        ).build());

        update(3, 15, Component.text("Team Information", Colors.DARK_BLUE, TextDecoration.BOLD));
        update(3, 16, Component.text().append(
                Component.text(" Role: "),
                Component.text("Member", NamedTextColor.DARK_GRAY)
        ).build());
        update(3, 17, Component.text().append(
                Component.text(" Members: "),
                Component.text("0/0", NamedTextColor.GRAY)
        ).build());
        update(3, 18, Component.text().append(
                Component.text(" Balance: "),
                Component.text("$", NamedTextColor.DARK_GREEN),
                Component.text("0", NamedTextColor.GREEN)
        ).build());

    }

}
