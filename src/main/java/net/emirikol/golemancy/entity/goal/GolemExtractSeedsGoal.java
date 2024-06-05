package net.emirikol.golemancy.entity.goal;

import net.emirikol.golemancy.entity.AbstractGolemEntity;
import net.emirikol.golemancy.util.GMUtils;
import net.minecraft.item.ItemStack;

public class GolemExtractSeedsGoal extends GolemExtractItemGoal {
    public GolemExtractSeedsGoal(AbstractGolemEntity entity) {
        super(entity);
    }

    @Override
    protected boolean canTake(ItemStack stack) {
        return GMUtils.isSeed(stack) && super.canTake(stack);
    }
}
