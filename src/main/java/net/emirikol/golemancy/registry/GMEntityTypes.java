package net.emirikol.golemancy.registry;

import net.emirikol.golemancy.GMIdentifier;
import net.emirikol.golemancy.entity.CarefulGolemEntity;
import net.emirikol.golemancy.entity.CovetousGolemEntity;
import net.emirikol.golemancy.entity.CuriousGolemEntity;
import net.emirikol.golemancy.entity.EntropicGolemEntity;
import net.emirikol.golemancy.entity.HungryGolemEntity;
import net.emirikol.golemancy.entity.IntrepidGolemEntity;
import net.emirikol.golemancy.entity.MarshyGolemEntity;
import net.emirikol.golemancy.entity.ParchedGolemEntity;
import net.emirikol.golemancy.entity.PiousGolemEntity;
import net.emirikol.golemancy.entity.RestlessGolemEntity;
import net.emirikol.golemancy.entity.RusticGolemEntity;
import net.emirikol.golemancy.entity.TactileGolemEntity;
import net.emirikol.golemancy.entity.ValiantGolemEntity;
import net.emirikol.golemancy.entity.VerdantGolemEntity;
import net.emirikol.golemancy.entity.WeepingGolemEntity;
import net.emirikol.golemancy.entity.projectile.ClayballEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.qsl.entity.api.QuiltEntityTypeBuilder;

import java.util.LinkedHashMap;
import java.util.Map;

public class GMEntityTypes {

    private static final Map<Identifier, EntityType<?>> ENTITY_TYPES = new LinkedHashMap<>();

    private static final float GOLEM_WIDTH = 0.7f;
    private static final float GOLEM_HEIGHT = 1.30f;
    private static final EntityDimensions GOLEM_DIMENSIONS = EntityDimensions.fixed(GOLEM_WIDTH, GOLEM_HEIGHT);
    public static final EntityType<CarefulGolemEntity> CAREFUL_GOLEM_ENTITY = create("golem_careful", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, CarefulGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<CovetousGolemEntity> COVETOUS_GOLEM_ENTITY = create("golem_covetous", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, CovetousGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<CuriousGolemEntity> CURIOUS_GOLEM_ENTITY = create("golem_curious", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, CuriousGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<EntropicGolemEntity> ENTROPIC_GOLEM_ENTITY = create("golem_entropic", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, EntropicGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<HungryGolemEntity> HUNGRY_GOLEM_ENTITY = create("golem_hungry", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, HungryGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<IntrepidGolemEntity> INTREPID_GOLEM_ENTITY = create("golem_intrepid", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, IntrepidGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<MarshyGolemEntity> MARSHY_GOLEM_ENTITY = create("golem_marshy", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, MarshyGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<ParchedGolemEntity> PARCHED_GOLEM_ENTITY = create("golem_parched", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, ParchedGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<PiousGolemEntity> PIOUS_GOLEM_ENTITY = create("golem_pious",  QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, PiousGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<RestlessGolemEntity> RESTLESS_GOLEM_ENTITY = create("golem_restless", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, RestlessGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<RusticGolemEntity> RUSTIC_GOLEM_ENTITY = create("golem_rustic", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, RusticGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<TactileGolemEntity> TACTILE_GOLEM_ENTITY = create("golem_tactile", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, TactileGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<ValiantGolemEntity> VALIANT_GOLEM_ENTITY = create("golem_valiant", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, ValiantGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<VerdantGolemEntity> VERDANT_GOLEM_ENTITY = create("golem_verdant", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, VerdantGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<WeepingGolemEntity> WEEPING_GOLEM_ENTITY = create("golem_weeping", QuiltEntityTypeBuilder.create(SpawnGroup.CREATURE, WeepingGolemEntity::new).setDimensions(GOLEM_DIMENSIONS).build());
    public static final EntityType<ClayballEntity> CLAYBALL = create("clayball", QuiltEntityTypeBuilder.<ClayballEntity>create(SpawnGroup.MISC, net.emirikol.golemancy.entity.projectile.ClayballEntity::new).setDimensions(EntityDimensions.fixed(0.25F, 0.25F)).maxBlockTrackingRange(4).trackingTickInterval(10).build());

    private static <T extends Entity> EntityType<T> create(String name, EntityType<T> type) {
        ENTITY_TYPES.put(new GMIdentifier(name), type);
        return type;
    }

    public static void register() {
        ENTITY_TYPES.forEach((id, entry) -> Registry.register(Registries.ENTITY_TYPE, id, entry));
    }

}
