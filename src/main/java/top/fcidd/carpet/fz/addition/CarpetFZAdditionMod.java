package top.fcidd.carpet.fz.addition;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class CarpetFZAdditionMod implements ModInitializer {
    // fz扩展id
    public static final String MOD_ID = "carpet_fz_addition";
    // fz扩展版本
    public static String version;

    @Override
    public void onInitialize() {
        // fz扩展版本
        version = FabricLoader.getInstance().getModContainer(MOD_ID).orElseThrow(RuntimeException::new).getMetadata().getVersion().getFriendlyString();
        // fz扩展初始化
        CarpetFZAdditionServer.init();
    }
}