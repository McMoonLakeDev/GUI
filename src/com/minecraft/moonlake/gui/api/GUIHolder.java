package com.minecraft.moonlake.gui.api;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by MoonLake on 2016/9/4.
 */
public class GUIHolder implements InventoryHolder {

    private final GUI object;

    public GUIHolder(GUI object) {

        this.object = object;
    }

    /**
     * 获取此 GUI 拥有者的 GUI 对象
     *
     * @return
     */
    public GUI getObject() {

        return object;
    }

    @Override
    @Deprecated
    public Inventory getInventory() {

        return null;
    }
}
