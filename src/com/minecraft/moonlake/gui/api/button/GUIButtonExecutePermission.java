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

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.button.special.GUIButtonFail;
import com.minecraft.moonlake.property.ReadOnlyObjectProperty;
import com.minecraft.moonlake.property.SimpleObjectProperty;
import com.minecraft.moonlake.property.SimpleStringProperty;
import com.minecraft.moonlake.property.StringProperty;
import org.bukkit.entity.Player;

/**
 * Created by MoonLake on 2016/9/12.
 */
public abstract class GUIButtonExecutePermission implements GUIButtonExecute {

    private StringProperty permissionProperty;
    private ReadOnlyObjectProperty<GUIButtonFail> failedProperty;

    public GUIButtonExecutePermission(String permission) {

        this(permission, null);
    }

    public GUIButtonExecutePermission(String permission, GUIButtonFail failed) {

        this.permissionProperty = new SimpleStringProperty(permission);
        this.failedProperty = new SimpleObjectProperty<>(failed);
    }

    public StringProperty getPermission() {

        return permissionProperty;
    }

    public void setPermission(String permission) {

        this.permissionProperty.set(permission);
    }

    /**
     * 设置此按钮的执行对象
     *
     * @param gui 点击的 GUI 对象
     * @param clicked 点击的玩家对象
     * @param currentButton 点击的按钮对象
     */
    public void execute(GUI gui, Player clicked, GUIButton currentButton) {

        if(!clicked.hasPermission(permissionProperty.get())) {

            GUIButtonFail failed = failedProperty.get();

            if(failed != null) {

                failed.failed(gui, clicked, currentButton);
            }
            return;
        }
        executePermission(gui, clicked, currentButton);
    }

    /**
     * 设置此按钮的执行对象
     *
     * @param gui 点击的 GUI 对象
     * @param clicked 点击的玩家对象
     * @param currentButton 点击的按钮对象
     */
    public abstract void executePermission(GUI gui, Player clicked, GUIButton currentButton);
}
