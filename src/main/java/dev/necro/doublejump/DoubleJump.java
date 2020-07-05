package dev.necro.doublejump;

import dev.necro.doublejump.common.config.ConfigHandler;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(DoubleJump.MODID)
public class DoubleJump
{
    public static final String MODID = "doublejump";

    public DoubleJump() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigHandler.SERVER_SPEC);
    }
}
