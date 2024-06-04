package net.emirikol.golemancy.entity.projectile;

import net.emirikol.golemancy.entity.AbstractGolemEntity;
import net.emirikol.golemancy.network.SpawnPacket;
import net.emirikol.golemancy.registry.GMEntityTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.quiltmc.loader.api.minecraft.ClientOnly;

public class ClayballEntity extends ThrownItemEntity {
    private double damage;

    public ClayballEntity(EntityType<? extends ClayballEntity> entityType, World world) {
        super(entityType, world);
    }

    public ClayballEntity(World world, LivingEntity owner) {
        super(GMEntityTypes.CLAYBALL, owner, world);
    }

    public ClayballEntity(World world, double x, double y, double z) {
        super(GMEntityTypes.CLAYBALL, x, y, z, world);
    }

    protected Item getDefaultItem() {
        return Items.CLAY_BALL;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    @ClientOnly
    private ParticleEffect getParticleParameters() {
        return new ItemStackParticleEffect(ParticleTypes.ITEM, new ItemStack(this.getDefaultItem()));
    }

    @ClientOnly
    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        SpawnPacket.sendSpawnPacket(this);
        return super.createSpawnPacket();
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        if (entity instanceof AbstractGolemEntity) {
            return;
        }
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), (float) this.damage);
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, (byte) 3);
            this.discard();
        }
    }
}