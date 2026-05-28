package top.fcidd.carpet.fz.addition.mixin;

import carpet.commands.PlayerCommand;
import carpet.utils.Messenger;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.fcidd.carpet.fz.addition.CarpetFZAdditionSetting;

@Mixin(PlayerCommand.class)
public abstract class PlayerCommandMixin {
    @Inject(
            method = "Lcarpet/commands/PlayerCommand;cantSpawn(Lcom/mojang/brigadier/context/CommandContext;)Z",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/players/PlayerList;isUsingWhitelist()Z"
            ),
            cancellable = true
    )
    private static void shouldCheckWhitelist(CommandContext<CommandSourceStack> context, CallbackInfoReturnable<Boolean> cir) {
        // 启用召唤白名单中的假人
        if (Boolean.parseBoolean(CarpetFZAdditionSetting.spawnWhitelistedFakePlayer)) {
            // 服务器是否开启白名单的值强制修改成false
            cir.setReturnValue(false);
        }
        // 未启用召唤白名单中的假人
        if (!Boolean.parseBoolean(CarpetFZAdditionSetting.spawnWhitelistedFakePlayer)) {
            // 服务器是否开启白名单改为真实的值
            cir.setReturnValue(context.getSource().getServer().isUsingWhitelist());
            // 发送提示
            Messenger.m(context.getSource(), "r Whitelisted players can only be spawned by operators");
        }
    }
}