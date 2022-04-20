package cc.minetale.hub.cosmetic.objects;

import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.block.Block;

import java.util.List;

public record SplatterLocation(Long time, List<BlockPos> blocks) {

    public record BlockPos(Block block, Pos pos) {}

}
