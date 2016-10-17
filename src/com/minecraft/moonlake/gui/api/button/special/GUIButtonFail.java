/*
 * Copyright (C) 2016 The MoonLake Authors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 
 
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
