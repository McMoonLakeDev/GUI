package com.minecraft.moonlake.gui.api.button;

import com.minecraft.moonlake.gui.api.GUI;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/24.
 */
public interface GUIButton {

    /**
     * 获取此按钮的 GUI 对象
     *
     * @return GUI 对象
     */
    GUI getGUI();

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

    /**
     * 获取此按钮的索引
     *
     * @return 索引
     */
    int getSlot();

    /**
     * 设置此按钮的图标对象
     *
     * @param icon 图标
     */
    void setIcon(ItemStack icon);

    /**
     * 更新此按钮的图标对象
     *
     * @param icon 图标
     */
    void updateIcon(ItemStack icon);
}
