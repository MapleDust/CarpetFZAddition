package top.fcidd.carpet.fz.addition.commands;

import carpet.utils.CommandHelper;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import lombok.SneakyThrows;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import org.jetbrains.annotations.NotNull;
import top.fcidd.carpet.fz.addition.CarpetFZAdditionSetting;

public class CameraModeCommand {
    /**
     * 注册/c和/s指令
     * /c:旁观
     * /s:生存
     * @param dispatcher
     */
    public static void register(@NotNull CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
                Commands.literal("c")
                        .requires(stack -> CommandHelper.canUseCommand(stack, CarpetFZAdditionSetting.commandCameraMode))
                        .executes(CameraModeCommand::executeCameraMode)
        );
        dispatcher.register(
                Commands.literal("s")
                        .requires(stack -> CommandHelper.canUseCommand(stack, CarpetFZAdditionSetting.commandCameraMode))
                        .executes(CameraModeCommand::executeSurvivalMode)
        );
    }

    /**
     * 执行摄像机模式
     * @return 执行成功后返回1
     */
    @SneakyThrows(CommandSyntaxException.class)
    private static int executeCameraMode(CommandContext<CommandSourceStack> context) {
        // 获取玩家属性
        ServerPlayer player = context.getSource().getPlayerOrException();
        // 玩家不是旁观模式
        if (player.gameMode() != GameType.SPECTATOR) {
            // 将玩家设置为旁观模式
            player.setGameMode(GameType.SPECTATOR);
        }
        return 1;
    }

    /**
     * 执行生存模式
     * @return 执行成功后返回1
     */
    @SneakyThrows(CommandSyntaxException.class)
    private static int executeSurvivalMode(CommandContext<CommandSourceStack> context) {
        // 获取玩家属性
        ServerPlayer player = context.getSource().getPlayerOrException();
        // 玩家不是生存模式
        if (player.gameMode()!= GameType.SURVIVAL) {
            // 将玩家设置为生存模式
            player.setGameMode(GameType.SURVIVAL);
        }
        return 1;
    }

}
