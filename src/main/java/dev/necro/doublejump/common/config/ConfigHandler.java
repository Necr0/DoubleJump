package dev.necro.doublejump.common.config;

import dev.necro.doublejump.DoubleJump;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

@Mod.EventBusSubscriber(modid = DoubleJump.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigHandler {

    public enum CalculationMode {
        ADD,
        SET,
        MAX;
    }

    public static class Server {
        public final ForgeConfigSpec.IntValue jumps_count;
        public final ForgeConfigSpec.EnumValue<CalculationMode> jumps_calculation_mode;
        public final ForgeConfigSpec.IntValue coyote_time_ticks;
        public final ForgeConfigSpec.EnumValue<CalculationMode> coyote_time_calculation_mode;

        public final ForgeConfigSpec.BooleanValue jumps_play_sound;
        public final ForgeConfigSpec.BooleanValue jumps_spawn_particle;

        public Server(ForgeConfigSpec.Builder builder) {
            jumps_count = builder
                    .comment("The number of mid air jumps any player can do. 1 is double jump, 2 is a triple jump, 3 is a quadruple jump, and so on.")
                    .defineInRange("jumps.count",1,0,Integer.MAX_VALUE);
            jumps_calculation_mode = builder
                    .comment("ADD: always increase the jumps count (recommended), SET: set the number of jumps (depends on event firing order if other mods are present), MAX: set the number of jumps to the given count if it's lower otherwise leave unchanged (depends on event firing order if other mods are present)")
                    .defineEnum("jumps.calculation_mode", CalculationMode.ADD);
            jumps_play_sound = builder
                    .comment("Wether or not to play a sound when a midair jump is performed.")
                    .define("jumps.play_sound", true);
            jumps_spawn_particle = builder
                    .comment("Wether or not to spawn particles when a midair jump is performed.")
                    .define("jumps.spawn_particle", true);
            coyote_time_ticks = builder
                    .comment("The number of ticks after walking of a platform in which the player can still jump.")
                    .defineInRange("coyote_time.ticks",3,0,Integer.MAX_VALUE);
            coyote_time_calculation_mode = builder
                    .comment("ADD: always increase the jumps count (may cause ridiculously long coyote time with other mods), SET: set the number of jumps (depends on event firing order if other mods are present), MAX: set the number of jumps to the given count if it's lower otherwise leave unchanged (recommended)")
                    .defineEnum("coyote_time.calculation_mode", CalculationMode.MAX);
        }

    }

    public static final Server SERVER;
    public static final ForgeConfigSpec SERVER_SPEC;
    static {
        final Pair<Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Server::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }
}
