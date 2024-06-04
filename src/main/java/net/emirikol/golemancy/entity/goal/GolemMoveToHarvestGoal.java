package net.emirikol.golemancy.entity.goal;

import net.emirikol.golemancy.entity.AbstractGolemEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.GourdBlock;
import net.minecraft.block.SugarCaneBlock;
import net.minecraft.util.math.BlockPos;

public class GolemMoveToHarvestGoal extends GolemMoveToBreakGoal {
    public GolemMoveToHarvestGoal(AbstractGolemEntity entity, float maxYDifference) {
        super(entity, maxYDifference);
    }

    @Override
    public boolean isTargetPos(BlockPos pos) {
        //Any mature crop block is a valid targetPos.
        BlockState state = this.entity.getWorld().getBlockState(pos);
        Block block = state.getBlock();
        boolean isCrop = block instanceof CropBlock && ((CropBlock) block).isMature(state);
        boolean isGourd = block instanceof GourdBlock;
        boolean isCane = block instanceof SugarCaneBlock && this.entity.getWorld().getBlockState(pos.down()).getBlock() instanceof SugarCaneBlock;
        return (isCrop || isGourd || isCane) && super.isTargetPos(pos);
    }

    protected int getMaxProgress() {
        return 40;
    }
}
