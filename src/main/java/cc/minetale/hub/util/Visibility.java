package cc.minetale.hub.util;

import com.customwrld.commonlib.util.MC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.minestom.server.color.Color;
import net.minestom.server.item.ItemHideFlag;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import net.minestom.server.item.firework.FireworkEffect;
import net.minestom.server.item.firework.FireworkEffectType;
import net.minestom.server.item.metadata.FireworkEffectMeta;
import net.minestom.server.tag.Tag;

import java.util.Collections;

public enum Visibility {
    ALL(ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(MC.Style.component("All Players Visible ", MC.CC.GREEN, TextDecoration.BOLD))
                    .append(MC.Style.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Collections.singletonList(MC.Style.component("Right Click to cycle through visibility!", MC.CC.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                java.awt.Color color = MC.CC.GREEN.getColor();
                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")),
    STAFF_AND_FRIENDS(ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(MC.Style.component("Staff and VIPs Visible ", MC.CC.GREEN, TextDecoration.BOLD))
                    .append(MC.Style.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Collections.singletonList(MC.Style.component("Right Click to cycle through visibility!", MC.CC.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                java.awt.Color color = MC.CC.DARK_PURPLE.getColor();
                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")),
    NONE(ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(MC.Style.component("No Players Visible ", MC.CC.GREEN, TextDecoration.BOLD))
                    .append(MC.Style.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Collections.singletonList(MC.Style.component("Right Click to cycle through visibility!", MC.CC.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                java.awt.Color color = MC.CC.RED.getColor();
                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR"));

    public final ItemStack itemStack;

    Visibility(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

}
