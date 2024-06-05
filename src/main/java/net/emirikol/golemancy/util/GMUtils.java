package net.emirikol.golemancy.util;

import net.minecraft.block.CocoaBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.block.StemBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;

public class GMUtils {
    //Check whether a given ItemStack is a "seed", i.e. an AliasedBlockItem that places something which extends CropBlock, StemBlock, CocoaBlock, or NetherWartBlock.
    public static boolean isSeed(ItemStack stack) {
        if (stack.getItem() instanceof BlockItem item) {
            boolean coco = item.getBlock() instanceof CocoaBlock;
            boolean crop = item.getBlock() instanceof CropBlock;
            boolean stem = item.getBlock() instanceof StemBlock;
            boolean wart = item.getBlock() instanceof NetherWartBlock;
            return (coco || crop || stem || wart);
        }

        return false;
    }
}
