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

import com.minecraft.moonlake.property.ReadOnlyObjectProperty;
import com.minecraft.moonlake.property.SimpleObjectProperty;
import org.bukkit.entity.Player;

public class GUIEventHandler {

    private ReadOnlyObjectProperty<GUI> handler;
    private ReadOnlyObjectProperty<Player> target;

    public GUIEventHandler(GUI handler, Player target) {

        this.handler = new SimpleObjectProperty<>(handler);
        this.target = new SimpleObjectProperty<>(target);
    }

    /**
     * 获取触发事件的 GUI 句柄
     *
     * @return GUI
     */
    public GUI getHandler() {

         return handler.get();
    }

    /**
     * 获取触发事件的 Player 对象
     *
     * @return 玩家
     */
    public Player getTarget() {

        return target.get();
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null) {

            return false;
        }
        if(obj == this) {

            return true;
        }
        if(obj instanceof GUIEventHandler) {

            GUIEventHandler target = (GUIEventHandler) obj;
            return target.getHandler().equals(getHandler()) &&
                    target.getTarget().getName().equals(getTarget().getName());
        }
        return false;
    }
}
