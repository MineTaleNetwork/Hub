package cc.minetale.hub.menu;

import cc.minetale.mlib.canvas.CanvasType;
import cc.minetale.mlib.canvas.FillingType;
import cc.minetale.mlib.canvas.Menu;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;

public class ProfileMenu extends Menu {

    public ProfileMenu(Player player) {
        super(player, Component.text("My Profile"), CanvasType.SIX_ROW);

        setFiller(FillingType.EMPTY_SLOTS);

        // TODO
    }

    @Override
    public void close() {}

}
