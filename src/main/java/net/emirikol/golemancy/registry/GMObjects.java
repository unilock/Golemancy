package net.emirikol.golemancy.registry;

import net.emirikol.golemancy.GMIdentifier;
import net.emirikol.golemancy.block.ClayEffigyBlock;
import net.emirikol.golemancy.block.ObsidianEffigyBlock;
import net.emirikol.golemancy.block.SoulGrafterBlock;
import net.emirikol.golemancy.block.TerracottaEffigyBlock;
import net.emirikol.golemancy.item.GolemWand;
import net.emirikol.golemancy.item.SoulMirror;
import net.emirikol.golemancy.item.SoulstoneEmpty;
import net.emirikol.golemancy.item.SoulstoneFilled;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;

import java.util.LinkedHashMap;
import java.util.Map;

public class GMObjects {

    private static final Map<Identifier, Item> ITEMS = new LinkedHashMap<>();
    private static final Map<Identifier, Block> BLOCKS = new LinkedHashMap<>();

    public static final Item SOULSTONE_EMPTY = create("soulstone_empty", new SoulstoneEmpty(build()));
    public static final Item SOULSTONE_FILLED = create("soulstone_filled", new SoulstoneFilled(build()));
    public static final Item SOUL_MIRROR = create("soul_mirror", new SoulMirror(build().maxCount(1).maxDamage(256)));
    public static final Item GOLEM_WAND = create("golem_wand", new GolemWand(build().maxCount(1)));

    public static final Block CLAY_EFFIGY = create("clay_effigy", new ClayEffigyBlock(buildEffigy()));
    public static final Block TERRACOTTA_EFFIGY = create("terracotta_effigy", new TerracottaEffigyBlock(buildEffigy()));
    public static final Block OBSIDIAN_EFFIGY = create("obsidian_effigy", new ObsidianEffigyBlock(buildEffigy()));
    public static final Block SOUL_GRAFTER = create("soul_grafter", new SoulGrafterBlock(QuiltBlockSettings.create().mapColor(MapColor.STONE).hardness(4.0F).strength(5.0F, 1200.0F).requiresTool()));

    private static <T extends Item> T create(String path, T item) {
        ITEMS.put(new GMIdentifier(path), item);
        return item;
    }
    private static <T extends Block> T create(String path, T block) {
        final Identifier id = new GMIdentifier(path);
        ITEMS.put(id, new BlockItem(block, build()));
        BLOCKS.put(id, block);
        return block;
    }
    private static QuiltItemSettings build() {
        return new QuiltItemSettings();
    }
    private static AbstractBlock.Settings buildEffigy() {
        return QuiltBlockSettings.create().mapColor(MapColor.NONE).collidable(false).nonSolid(true).opaque(false).pistonBehavior(PistonBehavior.DESTROY).strength(0.6F);
    }
    public static void register() {
        ITEMS.keySet().forEach(entry -> Registry.register(Registries.ITEM, entry, ITEMS.get(entry)));
        BLOCKS.keySet().forEach(entry -> Registry.register(Registries.BLOCK, entry, BLOCKS.get(entry)));
    }
}
