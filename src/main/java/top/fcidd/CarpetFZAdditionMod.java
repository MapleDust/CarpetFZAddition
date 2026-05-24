package top.fcidd;

import net.fabricmc.api.ModInitializer;

public class CarpetFZAdditionMod implements ModInitializer {
    public static final String MOD_ID = "carpet_fz_addition";
    public static final String MOD_NAME = "Carpet FZ Addition";

    @Override
    public void onInitialize() {
        CarpetFZAdditionServer.init();
    }
}