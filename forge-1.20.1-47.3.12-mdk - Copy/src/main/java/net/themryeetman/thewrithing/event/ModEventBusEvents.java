package net.themryeetman.thewrithing.event;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.themryeetman.thewrithing.TheWrithig;
import net.themryeetman.thewrithing.entity.ModEntities;
import net.themryeetman.thewrithing.entity.costom.WrithigZombieEntity;

@Mod.EventBusSubscriber(modid = TheWrithig.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.WRITHINGZOMBIE.get(), WrithigZombieEntity.createAttributes().build());
    }
}
