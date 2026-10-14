package com.example.hitbox.mixin;

import com.example.hitbox.HitboxMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @Inject(method = "getTargetingMargin", at = @At("HEAD"), cancellable = true)
    private void hitbox$margin(CallbackInfoReturnable<Float> cir) {
        if (!HitboxMod.enabled) return;
        Entity self = (Entity) (Object) this;
        if (self instanceof PlayerEntity && self != MinecraftClient.getInstance().player) {
            cir.setReturnValue(HitboxMod.margin);
        }
    }
}
