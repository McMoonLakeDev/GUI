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
import com.minecraft.moonlake.property.ReadOnlyIntegerProperty;
import com.minecraft.moonlake.property.ReadOnlyObjectProperty;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

/**
 * Created by MoonLake on 2016/7/24.
 */
public interface GUIButton {

    /**
     * 获取此按钮的 GUI 对象
     *
     * @return GUI 对象
     */
    ReadOnlyObjectProperty<GUI> getGUI();

    /**
     * 获取此按钮的执行对象
     *
     * @see GUIButton#getExecute(GUIButtonClick)
     * @return 默认点击类型的执行对象 没有则返回空执行对象
     */
    @Deprecated
    GUIButtonExecute getExecute();

    /**
     * 获取此按钮的指定点击类型的执行对象
     *
     * @param click 点击类型
     * @return 指定点击类型的执行对象 没有则返回 null
     */
    GUIButtonExecute getExecute(GUIButtonClick click);

    /**
     * 设置此按钮的执行对象
     *
     * @see GUIButton#setClick(GUIButtonClick, GUIButtonExecute)
     * @param execute 执行对象
     */
    @Deprecated
    void setExecute(GUIButtonExecute execute);

    /**
     * 获取此按钮的图标对象
     *
     * @return 图标 没有则返回 null
     */
    ItemStack getIcon();

    /**
     * 获取此按钮的索引
     *
     * @return 索引
     */
    ReadOnlyIntegerProperty getSlot();

    /**
     * 设置此按钮的图标对象
     *
     * @param icon 图标
     * @see GUIButton#updateIcon(ItemStack)
     */
    @Deprecated
    void setIcon(ItemStack icon);

    /**
     * 更新此按钮的图标对象
     *
     * @param icon 图标
     */
    void updateIcon(ItemStack icon);

    /**
     * 将删除此 GUI 的此按钮对象
     */
    void remove();

    /**
     * 设置此按钮的指定点击的执行对象
     *
     * @param click 点击类型
     * @param execute 执行对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     */
    void setClick(GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置此按钮的执行点击的执行对象
     *
     * @param wrapped 按钮包装对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     */
    void setClick(GUIButtonWrapped wrapped);

    /**
     * 获取此按钮的点击执行对象集合
     *
     * @return 点击执行对象集合 没有则返回空集合
     */
    Map<GUIButtonClick, GUIButtonExecute> getExecuteMap();

    /**
     * 获取此按钮对象是否符合目标对象
     *
     * @param obj 对象
     * @return true 则符合
     */
    boolean equals(Object obj);
}
