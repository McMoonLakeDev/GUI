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
 
 
package com.minecraft.moonlake.gui.api.event;

import com.minecraft.moonlake.gui.api.GUI;
import org.bukkit.event.Event;

/**
 * Created by MoonLake on 2016/7/24.
 */
public abstract class MoonLakeGUIEvent extends Event {

    private final GUI gui;

    public MoonLakeGUIEvent(GUI gui) {

        this.gui = gui;
    }

    /**
     * 获取此事件的 GUI 对象
     *
     * @return GUI 对象
     */
    public final GUI getGUI() {

        return gui;
    }
}
