package top.fcidd.carpet.fz.addition.mixin;

import carpet.CarpetServer;
import carpet.api.settings.SettingsManager;
import carpet.utils.Messenger;
import net.minecraft.commands.CommandSourceStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import top.fcidd.carpet.fz.addition.CarpetFZAdditionMod;
import top.fcidd.carpet.fz.addition.api.ComponentHelper;

@Mixin(SettingsManager.class)
public class SettingsManagerMixin {
    @Unique
    private static final ComponentHelper translator = new ComponentHelper();

    @SuppressWarnings("ConstantValue")
    @Inject(
            method = "listAllSettings",
            slice = @Slice(
                    from = @At(
                            value = "CONSTANT",
                            args = "stringValue=carpet.settings.command.version",
                            ordinal = 0
                    )
            ),
            at = @At(
                    value = "INVOKE",
                    target = "Lcarpet/api/settings/SettingsManager;getCategories()Ljava/lang/Iterable;",
                    ordinal = 0
            ),
            remap = false
    )

    /*
      打印版本
     */
    private void printVersion(CommandSourceStack source, CallbackInfoReturnable<Integer> cir) {
        if ((Object)this == CarpetServer.settingsManager) {
            Messenger.m(
                    source, Messenger.c(
                            String.format("g %s ", CarpetFZAdditionMod.MOD_NAME),
                            String.format("g %s: ", translator.tr("carpetfzaddition.version").getString()),
                            String.format("g %s ", CarpetFZAdditionMod.version)
                    )
            );
        }
    }
}
