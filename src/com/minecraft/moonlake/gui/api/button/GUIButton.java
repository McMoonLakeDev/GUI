package com.minecraft.moonlake.gui.api.button;

import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/24.
 */
public interface GUIButton {

    /**
     * 获取此按钮的执行对象
     */
    GUIButtonExecute getExecute();

    /**
     * 获取此按钮的图标对象
     *
     * @return 图标
     */
    ItemStack getIcon();
}
