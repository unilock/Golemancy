package net.emirikol.golemancy;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.emirikol.golemancy.block.entity.SoulGrafterBlockEntity;
import net.emirikol.golemancy.event.CommandRegistrationHandler;
import net.emirikol.golemancy.event.ConfigurationHandler;
import net.emirikol.golemancy.event.SoulstoneFillHandler;
import net.emirikol.golemancy.registry.GMEntityTypes;
import net.emirikol.golemancy.registry.GMObjects;
import net.emirikol.golemancy.screen.SoulGrafterScreenHandler;
import net.emirikol.golemancy.screen.SoulMirrorScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Golemancy implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Golemancy");
    public static final Identifier CONFIG_PACKET_ID = new GMIdentifier("config_packet");

    public static final BlockEntityType<SoulGrafterBlockEntity> SOUL_GRAFTER_ENTITY = BlockEntityType.Builder.create(SoulGrafterBlockEntity::new, GMObjects.SOUL_GRAFTER).build(null);
    public static final ScreenHandlerType<SoulGrafterScreenHandler> SOUL_GRAFTER_SCREEN_HANDLER =  Registry.register(Registries.SCREEN_HANDLER, new GMIdentifier("soul_grafter"), new ScreenHandlerType<>(SoulGrafterScreenHandler::new, FeatureFlags.VANILLA_FEATURES));
    public static final ExtendedScreenHandlerType<SoulMirrorScreenHandler> SOUL_MIRROR_SCREEN_HANDLER = Registry.register(Registries.SCREEN_HANDLER, new GMIdentifier("soul_mirror"), new ExtendedScreenHandlerType<>(SoulMirrorScreenHandler::new));


    @Override
    public void onInitialize() {
        GMObjects.register();
        GMEntityTypes.register();
        Registry.register(Registries.BLOCK_ENTITY_TYPE, "golemancy:soul_grafter", SOUL_GRAFTER_ENTITY);
        CommandRegistrationHandler.commandRegistrationHook(); //add event hook for registering this mod's commands
        SoulstoneFillHandler.soulstoneFillHook(); //add event hook for replacing soulstones with mob soulstones when you kill mobs
        ConfigurationHandler.syncConfigHook(); //add event hook for syncing server and client configs when a player connects
        GolemancyItemGroup.register(); //add custom ItemGroup that contains all mod items including custom soulstones
        AutoConfig.register(ConfigurationHandler.class, GsonConfigSerializer::new); //register the AutoConfig handler - see GolemancyConfig for details
        LOGGER.info("Arise, my minions!");
    }
}
