package top.fcidd;

import carpet.api.settings.Rule;
import carpet.api.settings.Validators;

public class CarpetFZAdditionSetting {
    private static final String FZ = "FZ";

    // 摄像机模式
    @Rule(
            options = {"true", "false", "ops", "1", "2", "3", "4"},
            categories = {FZ},
            validators = Validators.CommandLevel.class

    )
    public static String commandCameraMode = "false";

    // 召唤白名单假人
    @Rule(
            options = {"true", "false"},
            categories = {FZ},
            validators = Validators.CommandLevel.class

    )
    public static String spawnWhitelistedFakePlayer = "false";
}
