/*
 * Decompiled with CFR 0.150.
 */
package me.wintware.client.module.visual;

import me.wintware.client.event.EventTarget;
import me.wintware.client.event.impl.EventUpdate;
import me.wintware.client.module.Category;
import me.wintware.client.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class ShadowESP
extends Module {
    public ShadowESP() {
        super("ShadowEsp", Category.Visuals);
    }

    @Override
    public void onDisable() {
        for (EntityPlayer player : ShadowESP.mc.world.playerEntities) {
            if (!player.isGlowing()) continue;
            player.setGlowing(false);
        }
        super.onDisable();
    }

    @EventTarget
    public void onUpdate(EventUpdate event) {
        for (Entity player : ShadowESP.mc.world.loadedEntityList) {
            if (!(player instanceof EntityPlayer)) continue;
            player.setGlowing(true);
        }
    }
}

