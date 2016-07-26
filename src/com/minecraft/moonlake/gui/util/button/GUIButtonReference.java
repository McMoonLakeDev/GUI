package com.minecraft.moonlake.gui.util.button;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class GUIButtonReference implements GUIButton {

    private GUI gui;
    private GUIButtonExecute execute;
    private ItemStack icon;
    private int slot;

    public GUIButtonReference(GUI gui, GUIButtonExecute execute, ItemStack icon, int slot) {

        this.gui = gui;
        this.execute = execute;
        this.icon = icon;
        this.slot = slot;
    }

    /**
     * 获取此按钮的 GUI 对象
     *
     * @return GUI 对象
     */
    @Override
    public GUI getGUI() {

        return gui;
    }

    /**
     * 获取此按钮的执行对象
     */
    @Override
    public GUIButtonExecute getExecute() {

        return execute;
    }

    /**
     * 获取此按钮的图标对象
     *
     * @return 图标
     */
    @Override
    public ItemStack getIcon() {

        return icon;
    }

    /**
     * 获取此按钮的索引
     *
     * @return 索引
     */
    @Override
    public int getSlot() {

        return slot;
    }

    /**
     * 设置此按钮的图标对象
     *
     * @param icon 图标
     */
    @Override
    public void setIcon(ItemStack icon) {

        this.icon = icon;
    }

    /**
     * 设置此按钮的图标对象
     *
     * @param icon 图标
     */
    @Override
    public void updateIcon(ItemStack icon) {

        gui.setButtonIcon(slot, icon);
    }
}
