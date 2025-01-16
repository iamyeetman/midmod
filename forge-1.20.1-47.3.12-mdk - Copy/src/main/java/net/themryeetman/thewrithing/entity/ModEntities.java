package net.themryeetman.thewrithing.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.themryeetman.thewrithing.TheWrithig;
import net.themryeetman.thewrithing.entity.costom.WrithigZombieEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheWrithig.MOD_ID);

    public static final RegistryObject<EntityType<WrithigZombieEntity>> WRITHINGZOMBIE =
            ENTITY_TYPES.register("writhingzombie", () -> EntityType.Builder.of(WrithigZombieEntity::new, MobCategory.MONSTER)
                    .sized(1.2f ,2.2f).build("writhingzombie"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
