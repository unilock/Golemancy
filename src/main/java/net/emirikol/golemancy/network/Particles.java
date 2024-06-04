package net.emirikol.golemancy.network;

import net.emirikol.golemancy.GMIdentifier;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.random.RandomGenerator;
import org.quiltmc.loader.api.minecraft.ClientOnly;
import org.quiltmc.qsl.networking.api.PacketByteBufs;
import org.quiltmc.qsl.networking.api.PlayerLookup;
import org.quiltmc.qsl.networking.api.ServerPlayNetworking;

public class Particles {
    public static final Identifier HEAL_PARTICLE_ID = new GMIdentifier("heal_particle");
    public static final Identifier SMOKE_PARTICLE_ID = new GMIdentifier("smoke_particle");
    public static final Identifier FOOD_PARTICLE_ID = new GMIdentifier("food_particle");

    public static void healParticle(LivingEntity target) {
        if (target == null) {
            return;
        }

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(target.getBlockPos());

        for (ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) target.getWorld(), target.getBlockPos())) {
            ServerPlayNetworking.send(user, HEAL_PARTICLE_ID, buf);
        }
    }

    public static void smokeParticle(LivingEntity target) {
        if (target == null) {
            return;
        }

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(target.getBlockPos());

        for (ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) target.getWorld(), target.getBlockPos())) {
            ServerPlayNetworking.send(user, SMOKE_PARTICLE_ID, buf);
        }
    }

    public static void foodParticle(LivingEntity target) {
        if (target == null) {
            return;
        }

        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBlockPos(target.getBlockPos());
        buf.writeVarInt(target.getId());

        for (ServerPlayerEntity user : PlayerLookup.tracking((ServerWorld) target.getWorld(), target.getBlockPos())) {
            ServerPlayNetworking.send(user, FOOD_PARTICLE_ID, buf);
        }
    }

    @ClientOnly
    public static void spawnHealParticle(BlockPos pos) {
        assert MinecraftClient.getInstance().world != null;
        RandomGenerator rand = MinecraftClient.getInstance().world.getRandom();
        for (int i = 0; i < 15; i++) {
            double d = 0.5D;
            double m = (double) pos.getX() + rand.nextDouble() * d;
            double n = (double) pos.getY() + rand.nextDouble() * d + 0.5D;
            double o = (double) pos.getZ() + rand.nextDouble() * d;
            double h = rand.nextGaussian() * 0.02D;
            double j = rand.nextGaussian() * 0.02D;
            double k = rand.nextGaussian() * 0.02D;
            MinecraftClient.getInstance().world.addParticle(ParticleTypes.HAPPY_VILLAGER, m, n, o, h, j, k);
        }
    }

    @ClientOnly
    public static void spawnSmokeParticle(BlockPos pos) {
        assert MinecraftClient.getInstance().world != null;
        RandomGenerator rand = MinecraftClient.getInstance().world.getRandom();
        for (int i = 0; i < 15; i++) {
            double d = 0.5D;
            double m = (double) pos.getX() + rand.nextDouble() * d;
            double n = (double) pos.getY() + rand.nextDouble() * d + 0.5D;
            double o = (double) pos.getZ() + rand.nextDouble() * d;
            double h = rand.nextGaussian() * 0.02D;
            double j = rand.nextGaussian() * 0.02D;
            double k = rand.nextGaussian() * 0.02D;
            MinecraftClient.getInstance().world.addParticle(ParticleTypes.SMOKE, m, n, o, h, j, k);
        }
    }

    @ClientOnly
    public static void spawnFoodParticle(BlockPos pos, Entity entity) {
        assert MinecraftClient.getInstance().world != null;
        RandomGenerator rand =  MinecraftClient.getInstance().world.getRandom();
        if (entity instanceof LivingEntity) {
            ItemStack stack = ((LivingEntity) entity).getEquippedStack(EquipmentSlot.MAINHAND);
            for (int i = 0; i < 15; i++) {
                double d = 0.5D;
                double m = (double) pos.getX() + rand.nextDouble() * d;
                double n = (double) pos.getY() + rand.nextDouble() * d + 0.7D;
                double o = (double) pos.getZ() + rand.nextDouble() * d + 0.5D;
                double h = rand.nextGaussian() * 0.02D;
                double j = rand.nextGaussian() * 0.02D;
                double k = rand.nextGaussian() * 0.02D;
                MinecraftClient.getInstance().world.addParticle(new ItemStackParticleEffect(ParticleTypes.ITEM, stack), m, n, o, h, j, k);
            }
        }
    }
}