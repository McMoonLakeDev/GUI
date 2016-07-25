package com.minecraft.moonlake.gui.api.button;

import com.minecraft.moonlake.gui.api.GUI;
import org.bukkit.entity.Player;

/**
 * Created by MoonLake on 2016/7/24.
 */
public interface GUIButtonExecute {

    /**
     * 设置此按钮的执行对象
     *
     * @param gui 点击的 GUI 对象
     * @param clicked 点击的玩家对象
     */
    void run(GUI gui, Player clicked);
}
