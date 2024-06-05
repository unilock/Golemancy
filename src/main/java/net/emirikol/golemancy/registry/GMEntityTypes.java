package net.emirikol.golemancy.registry;

import net.emirikol.golemancy.GMIdentifier;
import net.emirikol.golemancy.entity.AbstractGolemEntity;
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
    public static final EntityType<CarefulGolemEntity> CAREFUL_GOLEM_ENTITY = createGolem("golem_careful", CarefulGolemEntity::new);
    public static final EntityType<CovetousGolemEntity> COVETOUS_GOLEM_ENTITY = createGolem("golem_covetous", CovetousGolemEntity::new);
    public static final EntityType<CuriousGolemEntity> CURIOUS_GOLEM_ENTITY = createGolem("golem_curious", CuriousGolemEntity::new);
    public static final EntityType<EntropicGolemEntity> ENTROPIC_GOLEM_ENTITY = createGolem("golem_entropic", EntropicGolemEntity::new);
    public static final EntityType<HungryGolemEntity> HUNGRY_GOLEM_ENTITY = createGolem("golem_hungry", HungryGolemEntity::new);
    public static final EntityType<IntrepidGolemEntity> INTREPID_GOLEM_ENTITY = createGolem("golem_intrepid", IntrepidGolemEntity::new);
    public static final EntityType<MarshyGolemEntity> MARSHY_GOLEM_ENTITY = createGolem("golem_marshy", MarshyGolemEntity::new);
    public static final EntityType<ParchedGolemEntity> PARCHED_GOLEM_ENTITY = createGolem("golem_parched", ParchedGolemEntity::new);
    public static final EntityType<PiousGolemEntity> PIOUS_GOLEM_ENTITY = createGolem("golem_pious",  PiousGolemEntity::new);
    public static final EntityType<RestlessGolemEntity> RESTLESS_GOLEM_ENTITY = createGolem("golem_restless", RestlessGolemEntity::new);
    public static final EntityType<RusticGolemEntity> RUSTIC_GOLEM_ENTITY = createGolem("golem_rustic", RusticGolemEntity::new);
    public static final EntityType<TactileGolemEntity> TACTILE_GOLEM_ENTITY = createGolem("golem_tactile", TactileGolemEntity::new);
    public static final EntityType<ValiantGolemEntity> VALIANT_GOLEM_ENTITY = createGolem("golem_valiant", ValiantGolemEntity::new);
    public static final EntityType<VerdantGolemEntity> VERDANT_GOLEM_ENTITY = createGolem("golem_verdant", VerdantGolemEntity::new);
    public static final EntityType<WeepingGolemEntity> WEEPING_GOLEM_ENTITY = createGolem("golem_weeping", WeepingGolemEntity::new);
    public static final EntityType<ClayballEntity> CLAYBALL = create("clayball", QuiltEntityTypeBuilder.<ClayballEntity>create(SpawnGroup.MISC, ClayballEntity::new).setDimensions(EntityDimensions.fixed(0.25F, 0.25F)).maxBlockTrackingRange(4).trackingTickInterval(10).build());

    private static <T extends Entity> EntityType<T> create(String name, EntityType<T> type) {
        ENTITY_TYPES.put(new GMIdentifier(name), type);
        return type;
    }

    private static <T extends AbstractGolemEntity> EntityType<T> createGolem(String name, EntityType.EntityFactory<T> factory) {
        return create(name, QuiltEntityTypeBuilder.createMob().spawnGroup(SpawnGroup.CREATURE).entityFactory(factory).defaultAttributes(AbstractGolemEntity.createGolemAttributes()).setDimensions(GOLEM_DIMENSIONS).build());
    }

    public static void register() {
        ENTITY_TYPES.forEach((id, entry) -> Registry.register(Registries.ENTITY_TYPE, id, entry));
    }

}
