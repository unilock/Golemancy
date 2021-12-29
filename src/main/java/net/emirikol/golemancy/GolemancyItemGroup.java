package net.emirikol.golemancy;

import net.emirikol.golemancy.genetics.*;

import net.fabricmc.fabric.api.client.itemgroup.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class GolemancyItemGroup {
	public static ItemGroup GOLEMANCY_ITEM_GROUP;
	
	public static void buildGolemancyItemGroup() {
		GOLEMANCY_ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("golemancy", "golemancy_items"))
		.icon(() -> new ItemStack(Golemancy.CLAY_EFFIGY))
		.appendItems(stacks -> {
			stacks.add(new ItemStack(Golemancy.SOUL_MIRROR));
			stacks.add(new ItemStack(Golemancy.SOUL_GRAFTER_ITEM));
			stacks.add(new ItemStack(Golemancy.GOLEM_WAND));
			stacks.add(new ItemStack(Golemancy.CLAY_EFFIGY));
			stacks.add(new ItemStack(Golemancy.SOULSTONE_EMPTY));
			List<Genome> genomes = Arrays.asList(
				Genomes.ENDERMAN,
				Genomes.SLIME,
				Genomes.CREEPER,
				Genomes.ZOMBIE,
				Genomes.SHULKER,
				Genomes.BLAZE,
				Genomes.SKELETON,
				Genomes.PHANTOM,
				Genomes.RAVAGER,
				Genomes.GHAST
			);
			for(Genome genome: genomes) {
				ItemStack stack = new ItemStack(Golemancy.SOULSTONE_FILLED);
				genome.toItemStack(stack);
				stacks.add(stack);
			}
		})
		.build();
	}
}