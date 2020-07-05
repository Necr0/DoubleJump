package dev.necro.doublejump.common.sounds;

import dev.necro.doublejump.DoubleJump;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = DoubleJump.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSounds {
    public static final SoundEvent JUMP = createSoundEvent(new ResourceLocation(DoubleJump.MODID, "entity.player.jump"));

    @SubscribeEvent
    public static void registerSounds(RegistryEvent.Register<SoundEvent> event) {
        IForgeRegistry<SoundEvent> registry = event.getRegistry();
        registry.register(JUMP);
    }

    private static SoundEvent createSoundEvent(ResourceLocation name){
        return new SoundEvent(name).setRegistryName(name);
    }
}
