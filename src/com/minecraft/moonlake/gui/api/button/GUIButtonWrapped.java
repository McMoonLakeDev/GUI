package com.minecraft.moonlake.gui.api.button;

/**
 * Created by MoonLake on 2016/9/4.
 */
public interface GUIButtonWrapped {

    /**
     * 获取此按钮包装对象的点击类型
     *
     * @return 点击类型
     */
    GUIButtonClick getClick();

    /**
     * 获取此按钮包装对象的执行对象
     *
     * @return 执行对象
     */
    GUIButtonExecute getExecute();
}
