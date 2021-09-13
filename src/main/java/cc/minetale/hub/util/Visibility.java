package cc.minetale.hub.util;

import cc.minetale.commonlib.util.MC;
import lombok.AllArgsConstructor;
import lombok.Getter;
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

@Getter
@AllArgsConstructor
public enum Visibility {
    ALL(ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(MC.component("All Players Visible ", MC.CC.GREEN, TextDecoration.BOLD))
                    .append(MC.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Collections.singletonList(MC.component("Right Click to cycle through visibility!", MC.CC.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                java.awt.Color color = MC.CC.GREEN.getColor();
                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 0)),
    STAFF_AND_FRIENDS(ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(MC.component("Staff and VIPs Visible ", MC.CC.DARK_PURPLE, TextDecoration.BOLD))
                    .append(MC.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Collections.singletonList(MC.component("Right Click to cycle through visibility!", MC.CC.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                java.awt.Color color = MC.CC.DARK_PURPLE.getColor();
                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 1)),
    NONE(ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(MC.component("No Players Visible ", MC.CC.RED, TextDecoration.BOLD))
                    .append(MC.component("(Right Click)", MC.CC.GRAY))
                    .build())
            .withLore(Collections.singletonList(MC.component("Right Click to cycle through visibility!", MC.CC.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                java.awt.Color color = MC.CC.RED.getColor();
                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 2));

    private final ItemStack itemStack;

}
