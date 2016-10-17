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

import java.util.Set;

/**
 * Created by MoonLake on 2016/7/25.
 */
public interface MoonLakeGUIManager {

    /**
     * 获取月色之湖 GUI 实例对象
     *
     * @return 实例对象
     */
    MoonLakeGUI getMain();

    /**
     * 创建新的 GUI 对象
     *
     * @param name 名称
     * @param title 标题
     * @param size 大小
     * @return GUI 对象
     */
    GUI createGUI(String name, String title, int size);

    /**
     * 注册指定 GUI 对象
     *
     * @param gui GUI 对象
     * @param <T> GUI 对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIAlreadyExistsException 如果此 GUI 已经存在则抛出异常
     */
    <T extends GUI> void registerGUI(T gui);

    /**
     * 卸载指定 GUI 对象
     *
     * @param gui GUI 对象
     * @param <T> GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    <T extends GUI> GUI unregisterGUI(T gui);

    /**
     * 卸载指定 GUI 对象
     *
     * @param name GUI 名称
     * @return GUI 对象 没有则返回 null
     */
    GUI unregisterGUI(String name);

    /**
     * 卸载所有的 GUI 对象
     */
    void unregisterAll();

    /**
     * 获取指定名称的 GUI 对象
     *
     * @param name GUI 名称
     * @return GUI 对象 没有则返回 null
     */
    GUI getGUI(String name);

    /**
     * 获取指定名称的 GUI 对象
     *
     * @param gui GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    GUI getGUI(GUI gui);

    /**
     * 获取指定 GUI 对象是否已经存在
     *
     * @param gui GUI 对象
     * @return true 则已经存在 else 没有
     */
    boolean hasGUI(GUI gui);

    /**
     * 获取指定 GUI 对象是否已经存在
     *
     * @param name GUI 名称
     * @return true 则已经存在 else 没有
     */
    boolean hasGUI(String name);

    /**
     * 获取 GUI 对象从物品栏
     *
     * @param inventory 物品栏
     * @return GUI 对象 没有则返回 null
     */
    GUI fromInventory(Inventory inventory);

    /**
     * 获取所有的 GUI 对象
     *
     * @return GUI 对象集合
     */
    Set<GUI> getAllGUI();

    /**
     * 获取注册的 GUI 数量大小
     *
     * @return 数量大小
     */
    int getSize();
}
