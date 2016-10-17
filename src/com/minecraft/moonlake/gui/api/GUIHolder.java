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

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

/**
 * Created by MoonLake on 2016/9/4.
 */
public class GUIHolder implements InventoryHolder {

    private final GUI object;

    public GUIHolder(GUI object) {

        this.object = object;
    }

    /**
     * 获取此 GUI 拥有者的 GUI 对象
     *
     * @return
     */
    public GUI getObject() {

        return object;
    }

    @Override
    @Deprecated
    public Inventory getInventory() {

        return null;
    }
}
