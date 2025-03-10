package net.emilsg.molly_the_warrior.mixin;

import net.emilsg.molly_the_warrior.MollyTheWarrior;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Cat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CatRenderer.class)
public class CatEntityRendererMixin {
    @Unique
    private static final ResourceLocation MOLLY_TEXTURE = new ResourceLocation(MollyTheWarrior.MODID, "textures/entity/cat/molly.png");

    @Inject(
            method = "getTextureLocation(Lnet/minecraft/world/entity/animal/Cat;)Lnet/minecraft/resources/ResourceLocation;",
            at = @At("RETURN"),
            cancellable = true)
    private void getMollyTexture(Cat pEntity, CallbackInfoReturnable<ResourceLocation> cir) {
        String catName = pEntity.getName().getString();

        if (catName.equals("Molly") || catName.equals("molly")) cir.setReturnValue(MOLLY_TEXTURE);
    }

}
