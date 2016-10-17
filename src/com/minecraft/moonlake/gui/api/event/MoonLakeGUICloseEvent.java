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
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class MoonLakeGUICloseEvent extends MoonLakeGUIEvent {

    private final static HandlerList handlers = new HandlerList();
    private Player player;

    public MoonLakeGUICloseEvent(GUI gui, Player player) {

        super(gui);

        this.player = player;
    }

    /**
     * 获取关闭此 GUI 对象的玩家
     *
     * @return 玩家
     */
    public Player getPlayer() {

        return player;
    }

    @Override
    public HandlerList getHandlers() {

        return handlers;
    }

    public static HandlerList getHandlerList() {

        return handlers;
    }
}
