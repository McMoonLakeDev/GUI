package com.minecraft.moonlake.gui.util.button;

import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class GUIButtonReference implements GUIButton {

    private GUIButtonExecute execute;
    private ItemStack icon;

    public GUIButtonReference(GUIButtonExecute execute, ItemStack icon) {

        this.execute = execute;
        this.icon = icon;
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
}
