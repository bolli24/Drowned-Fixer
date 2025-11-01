package com.bolli24.drowned_fixer.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin {

    @Redirect(method = "damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieEntity;getType()Lnet/minecraft/entity/EntityType;"))
    private EntityType<? extends ZombieEntity> replaceEntityType(ZombieEntity instance) {
        EntityType<? extends ZombieEntity> actualType = instance.getType();
        if (actualType == EntityType.DROWNED) {
            return EntityType.ZOMBIE;
        }
        return actualType;
    }
}