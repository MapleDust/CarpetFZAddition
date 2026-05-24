package top.fcidd;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import top.fcidd.api.ComponentHelper;
import top.fcidd.commands.CameraModeCommand;

import java.util.Map;

public class CarpetFZAdditionServer implements CarpetExtension {
    private static final CarpetFZAdditionServer INSTANCE = new CarpetFZAdditionServer();

    @Override
    public String version() {
        return CarpetFZAdditionMod.MOD_ID;
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetFZAdditionSetting.class);
    }

    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return ComponentHelper.fetchLanguage(lang);
    }

    public static CarpetFZAdditionServer getInstance(){
        return INSTANCE;
    }

    @Override
    public void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
        CameraModeCommand.register(dispatcher);
    }


    public static void init() {
        // 加入carpet扩展管理
        CarpetServer.manageExtension(INSTANCE);
    }
}
