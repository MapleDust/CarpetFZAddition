package top.fcidd.commands;

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
import top.fcidd.CarpetFZAdditionSetting;

public class CameraModeCommand {
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

    @SneakyThrows(CommandSyntaxException.class)
    private static int executeCameraMode(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayerOrException();
        if (player.gameMode() != GameType.SPECTATOR) {
            player.setGameMode(GameType.SPECTATOR);
        }
        return 1;
    }

    @SneakyThrows(CommandSyntaxException.class)
    private static int executeSurvivalMode(CommandContext<CommandSourceStack> context) {
        ServerPlayer player = context.getSource().getPlayerOrException();
        if (player.gameMode()!= GameType.SURVIVAL) {
            player.setGameMode(GameType.SURVIVAL);
        }
        return 1;
    }

}
