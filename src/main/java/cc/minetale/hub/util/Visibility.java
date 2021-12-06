package cc.minetale.hub.util;

import cc.minetale.commonlib.util.MC;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
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
            .withDisplayName(Component.text().append(
                    Component.text("All Players Visible ", NamedTextColor.GREEN, TextDecoration.BOLD),
                    Component.text("(Right Click)", NamedTextColor.GRAY)
            ).build())
            .withLore(Collections.singletonList(Component.text("Right Click to cycle through visibility!", NamedTextColor.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                var color = MC.fromNamedTextColor(NamedTextColor.GREEN);

                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 0)),
    STAFF_AND_FRIENDS(ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(Component.text("Staff and VIPs Visible ", NamedTextColor.DARK_PURPLE, TextDecoration.BOLD))
                    .append(Component.text("(Right Click)", NamedTextColor.GRAY))
                    .build())
            .withLore(Collections.singletonList(Component.text("Right Click to cycle through visibility!", NamedTextColor.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                var color = MC.fromNamedTextColor(NamedTextColor.DARK_PURPLE);

                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 1)),
    NONE(ItemStack.of(Material.FIREWORK_STAR)
            .withDisplayName(Component.text()
                    .append(Component.text("No Players Visible ", NamedTextColor.RED, TextDecoration.BOLD))
                    .append(Component.text("(Right Click)", NamedTextColor.GRAY))
                    .build())
            .withLore(Collections.singletonList(Component.text("Right Click to cycle through visibility!", NamedTextColor.GRAY)))
            .withMeta(FireworkEffectMeta.class, meta -> {
                var color = MC.fromNamedTextColor(NamedTextColor.RED);

                meta.effect(new FireworkEffect(false ,false, FireworkEffectType.SMALL_BALL, Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue())), Collections.singletonList(new Color(color.getRed(), color.getGreen(), color.getBlue()))));
                meta.hideFlag(ItemHideFlag.HIDE_POTION_EFFECTS);
            })
            .withTag(Tag.String("type"), "VISIBILITY_SELECTOR")
            .withTag(Tag.Integer("index"), 2));

    private final ItemStack itemStack;

}
