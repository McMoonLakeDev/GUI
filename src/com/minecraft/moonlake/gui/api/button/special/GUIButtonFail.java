package com.minecraft.moonlake.gui.api.button.special;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import org.bukkit.entity.Player;

/**
 * Created by MoonLake on 2016/9/12.
 */
public interface GUIButtonFail {


    /**
     * 回调: 如果点击此按钮后失败的执行对象
     *
     * @param gui           点击的 GUI 对象
     * @param clicked       点击的玩家对象
     * @param currentButton 点击的按钮对象
     */
     void failed(GUI gui, Player clicked, GUIButton currentButton);
}
