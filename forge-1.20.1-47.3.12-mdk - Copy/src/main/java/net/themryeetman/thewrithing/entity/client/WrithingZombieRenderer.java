package net.themryeetman.thewrithing.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.themryeetman.thewrithing.TheWrithig;
import net.themryeetman.thewrithing.entity.costom.WrithigZombieEntity;

public class WrithingZombieRenderer extends MobRenderer<WrithigZombieEntity, Writhezombie<WrithigZombieEntity>> {
    public WrithingZombieRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Writhezombie<>(pContext.bakeLayer(ModModelLayers.WRITHING_ZOMBIE_LAYER)), 2f);
    }

    @Override
    public ResourceLocation getTextureLocation(WrithigZombieEntity writhigZombieEntity) {
        return new ResourceLocation(TheWrithig.MOD_ID, "textures/entity/writhingzombie.png");
    }

    @Override
    public void render(WrithigZombieEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pPoseStack,
                       MultiBufferSource pBuffer, int pPackedLight) {


        super.render(pEntity, pEntityYaw, pPartialTicks, pPoseStack, pBuffer, pPackedLight);
    }
}
