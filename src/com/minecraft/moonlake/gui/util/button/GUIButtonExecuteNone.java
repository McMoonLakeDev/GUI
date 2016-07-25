package com.minecraft.moonlake.gui.util.button;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import org.bukkit.entity.Player;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class GUIButtonExecuteNone implements GUIButtonExecute {

    public GUIButtonExecuteNone() {

    }

    /**
     * 设置此按钮的执行对象
     *
     * @param gui     点击的 GUI 对象
     * @param clicked 点击的玩家对象
     */
    @Override
    public void run(GUI gui, Player clicked) {

    }
}
