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
