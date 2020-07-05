package dev.necro.doublejump.common.movement;

import dev.necro.coyotelib.api.common.movement.midair_jump.MidairJumpEvent;
import dev.necro.doublejump.DoubleJump;
import dev.necro.doublejump.common.config.ConfigHandler.CalculationMode;
import dev.necro.doublejump.common.sounds.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static dev.necro.doublejump.common.config.ConfigHandler.SERVER;

@Mod.EventBusSubscriber(modid = DoubleJump.MODID)
public class DoubleJumpHandler {

    @SubscribeEvent
    public static void setCoyoteTime(MidairJumpEvent.SetCoyoteTime event) {
        CalculationMode calculationMode = SERVER.coyote_time_calculation_mode.get();
        int ticks = SERVER.coyote_time_ticks.get();
        if(calculationMode == CalculationMode.ADD)
            event.addCoyoteTime(ticks);
        else if (calculationMode== CalculationMode.SET)
            event.setCoyoteTime(ticks);
        else
            event.maximizeCoyoteTime(ticks);
    }

    @SubscribeEvent
    public static void setJumps(MidairJumpEvent.SetMultiJumpCount event) {
        CalculationMode calculationMode = SERVER.jumps_calculation_mode.get();
        int count = SERVER.jumps_count.get();
        if(calculationMode == CalculationMode.ADD)
            event.addJumps(count);
        else if (calculationMode== CalculationMode.SET)
            event.setJumps(count);
        else
            event.maximizeJumps(count);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void multiJump(MidairJumpEvent.MultiJump.Post event) {
        if(!event.isHandled()){
            PlayerEntity player = event.getPlayer();
            World world = event.getPlayer().getEntityWorld();
            if(!world.isRemote && world instanceof ServerWorld && player instanceof ServerPlayerEntity){
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
                ServerWorld serverWorld = (ServerWorld)world;
                if(SERVER.jumps_spawn_particle.get()) {
                    serverWorld.spawnParticle(serverPlayer, ParticleTypes.CLOUD, false, player.getPosX(), player.getPosY(), player.getPosZ(), 1, 0, 0, 0, 0);
                    event.setHandled();
                }
            }
            if(SERVER.jumps_play_sound.get()) {
                world.playSound(player, player.getPosX(), player.getPosY(), player.getPosZ(), ModSounds.JUMP, SoundCategory.PLAYERS,.5f,1.8f);
                event.setHandled();
            }
        }
    }


}
