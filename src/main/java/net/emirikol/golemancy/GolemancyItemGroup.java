package net.emirikol.golemancy;

import net.emirikol.golemancy.genetics.Genome;
import net.emirikol.golemancy.genetics.Genomes;
import net.emirikol.golemancy.genetics.SoulTypes;
import net.emirikol.golemancy.registry.GMObjects;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.List;

public class GolemancyItemGroup {
    public static final ItemGroup GOLEMANCY_ITEM_GROUP = FabricItemGroup.builder()
                .name(Text.translatable("itemGroup.golemancy.golemancy_items"))
                .icon(() -> new ItemStack(GMObjects.CLAY_EFFIGY))
                .entries((parameters, stacks) -> {
                    stacks.addStack(new ItemStack(GMObjects.SOUL_MIRROR));
                    stacks.addStack(new ItemStack(GMObjects.SOUL_GRAFTER));
                    stacks.addStack(new ItemStack(GMObjects.GOLEM_WAND));
                    stacks.addStack(new ItemStack(GMObjects.CLAY_EFFIGY));
                    stacks.addStack(new ItemStack(GMObjects.TERRACOTTA_EFFIGY));
                    stacks.addStack(new ItemStack(GMObjects.OBSIDIAN_EFFIGY));
                    stacks.addStack(new ItemStack(GMObjects.SOULSTONE_EMPTY));
                    List<Genome> genomes = Arrays.asList(
                            //Natural genomes
                            Genomes.creativeGenome(SoulTypes.COVETOUS),
                            Genomes.creativeGenome(SoulTypes.CURIOUS),
                            Genomes.creativeGenome(SoulTypes.ENTROPIC),
                            Genomes.creativeGenome(SoulTypes.HUNGRY),
                            Genomes.creativeGenome(SoulTypes.INTREPID),
                            Genomes.creativeGenome(SoulTypes.MARSHY),
                            Genomes.creativeGenome(SoulTypes.PARCHED),
                            Genomes.creativeGenome(SoulTypes.RESTLESS),
                            Genomes.creativeGenome(SoulTypes.TACTILE),
                            Genomes.creativeGenome(SoulTypes.VALIANT),
                            Genomes.creativeGenome(SoulTypes.WEEPING),
                            //Mutated genomes
                            Genomes.creativeGenome(SoulTypes.CAREFUL),
                            Genomes.creativeGenome(SoulTypes.PIOUS),
                            Genomes.creativeGenome(SoulTypes.RUSTIC),
                            Genomes.creativeGenome(SoulTypes.VERDANT)
                    );
                    for (Genome genome : genomes) {
                        ItemStack stack = new ItemStack(GMObjects.SOULSTONE_FILLED);
                        genome.toItemStack(stack);
                        stacks.addStack(stack);
                    }
                }).build();

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, new GMIdentifier("golemancy_items"), GOLEMANCY_ITEM_GROUP);
    }
}