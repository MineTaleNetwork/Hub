package cc.minetale.hub.menu;

import cc.minetale.hub.util.ItemUtil;
import cc.minetale.mlib.canvas.CanvasType;
import cc.minetale.mlib.canvas.FillingType;
import cc.minetale.mlib.canvas.Fragment;
import cc.minetale.mlib.canvas.Menu;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;

public class ServerSelectorMenu extends Menu {

    public ServerSelectorMenu(Player player) {
        super(player, Component.text("Server Selector"), CanvasType.FIVE_ROW);

        setFiller(FillingType.EMPTY_SLOTS);

        setFragment(10, Fragment.empty(ItemUtil.getPracticeItem()));
        setFragment(12, Fragment.empty(ItemUtil.getParkourItem()));
        setFragment(14, Fragment.empty(ItemUtil.getWoolWarsItem()));
        setFragment(16, Fragment.empty(ItemUtil.getBedWarsItem()));
        setFragment(28, Fragment.empty(ItemUtil.getHousingItem()));
        setFragment(30, Fragment.empty(ItemUtil.getArcadeGamesItem()));
        setFragment(32, Fragment.empty(ItemUtil.getSkyBlockItem()));
        setFragment(34, Fragment.empty(ItemUtil.getSkyWarsItem()));

        openMenu();
    }

    @Override
    public void close() {}
}
