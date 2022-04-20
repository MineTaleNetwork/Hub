package cc.minetale.hub.menu;

import cc.minetale.hub.util.ItemUtil;
import cc.minetale.mlib.canvas.CanvasType;
import cc.minetale.mlib.canvas.Filler;
import cc.minetale.mlib.canvas.Fragment;
import cc.minetale.mlib.canvas.template.Menu;
import net.kyori.adventure.text.Component;
import net.minestom.server.entity.Player;

public class ServerSelectorMenu extends Menu {

    public ServerSelectorMenu(Player player) {
        super(player, Component.text("Server Selector"), CanvasType.FIVE_ROW);

        setFiller(Filler.EMPTY_SLOTS);

        setButton(11, Fragment.empty(ItemUtil.getSurvivalItem()));
        setButton(12, Fragment.empty(ItemUtil.getSkyWarsItem()));
        setButton(13, Fragment.empty(ItemUtil.getWoolWarsItem()));
        setButton(14, Fragment.empty(ItemUtil.getBedWarsItem()));
        setButton(15, Fragment.empty(ItemUtil.getHousingItem()));

        setButton(20, Fragment.empty(ItemUtil.getArcadeGamesItem()));
        setButton(21, Fragment.empty(ItemUtil.getPracticeItem()));
        setButton(22, Fragment.empty(ItemUtil.getParkourItem()));
        setButton(23, Fragment.empty(ItemUtil.getTheBackroomsItem()));
        setButton(24, Fragment.empty(ItemUtil.getBlockSumoItem()));

        setButton(30, Fragment.empty(ItemUtil.getSpeedBuildersItem()));
        setButton(32, Fragment.empty(ItemUtil.getNaturalDisastersItem()));

    }

}
