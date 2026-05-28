package top.fcidd.carpet.fz.addition;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import top.fcidd.carpet.fz.addition.api.ComponentHelper;
import top.fcidd.carpet.fz.addition.commands.CameraModeCommand;

import java.util.Map;

public class CarpetFZAdditionServer implements CarpetExtension {
    private static final CarpetFZAdditionServer INSTANCE = new CarpetFZAdditionServer();

    /**
     * @return 返回扩展ID
     */
    @Override
    public String version() {
        return CarpetFZAdditionMod.MOD_ID;
    }

    /**
     * 在读取世界前执行
     */
    @Override
    public void onGameStarted() {
        // 加入carpet规则管理
        CarpetServer.settingsManager.parseSettingsClass(CarpetFZAdditionSetting.class);
    }

    /**
     * 语言管理
     */
    @Override
    public Map<String, String> canHasTranslations(String lang) {
        return ComponentHelper.fetchLanguage(lang);
    }

    public static CarpetFZAdditionServer getInstance(){
        return INSTANCE;
    }

    /**
     * 注册指令
     */
    @Override
    public void registerCommands(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext commandBuildContext) {
        // 注册摄像机模式指令
        CameraModeCommand.register(dispatcher);
    }

    /**
     * 初始化地毯扩展
     */
    public static void init() {
        // 加入carpet扩展管理
        CarpetServer.manageExtension(INSTANCE);
    }
}
