package top.fcidd.mixin;

import carpet.commands.PlayerCommand;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.fcidd.CarpetFZAdditionSetting;

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
        if (Boolean.parseBoolean(CarpetFZAdditionSetting.spawnWhitelistedFakePlayer)) {
            cir.setReturnValue(false);
        }
    }
}