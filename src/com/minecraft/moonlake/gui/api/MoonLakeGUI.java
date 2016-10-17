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
 
 
package com.minecraft.moonlake.gui.api;

import com.minecraft.moonlake.gui.GUIPlugin;
import com.minecraft.moonlake.logger.MLogger;

/**
 * Created by MoonLake on 2016/7/24.
 */
public interface MoonLakeGUI {

    /**
     * 获取插件的主类对象
     *
     * @return 主类
     */
    GUIPlugin getMain();

    /**
     * 获取月色之湖 GUI 实例对象
     *
     * @return 实例对象
     */
    MoonLakeGUI getInstance();

    /**
     * 获取月色之湖控制台日志对象
     *
     * @return 日志对象
     */
    MLogger getMLogger();

    /**
     * 获取月色之湖 GUI 管理实例对象
     *
     * @return 管理实例对象
     */
    MoonLakeGUIManager getManager();
}
