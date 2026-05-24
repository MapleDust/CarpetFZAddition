package top.fcidd.api;

import carpet.utils.Translations;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import org.jetbrains.annotations.Nullable;
import top.fcidd.CarpetFZAdditionMod;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class ComponentHelper {
    private static String lang = "";
    private static final Map<String, String> language = new HashMap<>();
    private static final Map<String, String> zh_cn = new HashMap<>();

    public static MutableComponent tr(String key, Object... args) {
        return tr(key, null, Style.EMPTY, args);
    }

    public static MutableComponent tr(String key, @Nullable TextColor color, Object... args) {
        return tr(key, color, Style.EMPTY, args);
    }

    public static MutableComponent tr(String key, @Nullable TextColor color, Style style, Object... args) {
        if (color != null) style = style.withColor(color);
        String text = language.get(key);
        return Component.translatableWithFallback(key, text, args).setStyle(style);
    }

    @SuppressWarnings("NoTranslation")
    public static MutableComponent fmt(String text, Object... args) {
        return Component.translatableWithFallback("fz.format.empty", text, args);
    }

    public static Component highlight(Object value) {
        MutableComponent component;
        if (value instanceof MutableComponent cpt) component = cpt;
        else if (value instanceof Component cpt) component = Component.literal("").append(cpt);
        else if (value instanceof String str && str.startsWith("msg.fz.")) component = tr(str);
        else component = Component.literal(String.valueOf(value));

        return component.withStyle(ChatFormatting.GOLD);
    }

    public static Component fmtTr(String key, Object... args) {
        Object[] highlights = Arrays.stream(args).map(ComponentHelper::highlight).toArray();
        return tr(key, highlights);
    }

    public static Component prefix(Component content) {
        return Component.literal("")
                .append(Component.literal("[FZ]").withStyle(ChatFormatting.DARK_AQUA))
                .append(" ")
                .append(content);
    }

    public static Component intro(Component content) {
        return fmt("======== %s ========", content).withStyle(ChatFormatting.GRAY);
    }

    public static void updateLanguage(String lang) {
        if (zh_cn.isEmpty()) {
            String path = String.format("assets/%s/lang/%s.json", CarpetFZAdditionMod.MOD_ID, "zh_cn");
            Map<String, String> translations = Translations.getTranslationFromResourcePath(path);
            zh_cn.putAll(translations);
        }
        language.clear();
        language.putAll(zh_cn);
        ComponentHelper.lang = lang;
        if (!"zh_cn".equals(lang)) {
            String path = String.format("assets/%s/lang/%s.json", CarpetFZAdditionMod.MOD_ID, lang);
            language.putAll(Translations.getTranslationFromResourcePath(path));
        }
    }

    public static Map<String, String> fetchLanguage(String lang) {
        if (!ComponentHelper.lang.equals(lang)) updateLanguage(lang);
        return language;
    }
}
