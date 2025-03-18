package net.emilsg.princess_the_darling.mixin;

import net.emilsg.princess_the_darling.PrincessTheDarling;
import net.minecraft.client.renderer.entity.WolfRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Wolf;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WolfRenderer.class)
public class WolfEntityRendererMixin {
    @Unique
    private static final ResourceLocation PRINCESS_TAME_TEXTURE = ResourceLocation.fromNamespaceAndPath(PrincessTheDarling.MODID, "textures/entity/wolf/princess_tame.png");
    @Unique
    private static final ResourceLocation PRINCESS_ANGRY_TEXTURE = ResourceLocation.fromNamespaceAndPath(PrincessTheDarling.MODID, "textures/entity/wolf/princess_angry.png");
    @Unique
    private static final ResourceLocation PRINCESS_TEXTURE = ResourceLocation.fromNamespaceAndPath(PrincessTheDarling.MODID, "textures/entity/wolf/princess.png");

    @Inject(
            method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Wolf;)Lnet/minecraft/resources/ResourceLocation;",
            at = @At("RETURN"),
            cancellable = true)
    private void getPrincessTexture(Wolf pEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        String wolfName = pEntity.getName().getString();

        if (wolfName.equalsIgnoreCase("princess")){
            cir.setReturnValue(pEntity.isTame() ? PRINCESS_TAME_TEXTURE : pEntity.isAngry() ? PRINCESS_ANGRY_TEXTURE : PRINCESS_TEXTURE);
        }
    }

}
