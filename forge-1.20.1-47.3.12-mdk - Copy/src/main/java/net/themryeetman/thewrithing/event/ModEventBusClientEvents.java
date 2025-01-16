package net.themryeetman.thewrithing.event;


import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.themryeetman.thewrithing.TheWrithig;
import net.themryeetman.thewrithing.entity.client.ModModelLayers;
import net.themryeetman.thewrithing.entity.client.Writhezombie;

@Mod.EventBusSubscriber(modid = TheWrithig.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.WRITHING_ZOMBIE_LAYER, Writhezombie::createBodyLayer);
    }
}
