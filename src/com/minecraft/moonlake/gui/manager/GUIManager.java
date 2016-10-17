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
 
 
package com.minecraft.moonlake.gui.manager;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.MoonLakeGUI;
import com.minecraft.moonlake.gui.api.MoonLakeGUIManager;
import com.minecraft.moonlake.gui.exception.IllegalGUIAlreadyExistsException;
import com.minecraft.moonlake.gui.util.GUIReference;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by MoonLake on 2016/7/24.
 */
public final class GUIManager implements MoonLakeGUIManager {

    private final MoonLakeGUI main;
    private final Map<String, GUI> GUI_MAP;

    public GUIManager(MoonLakeGUI main) {

        this.main = main;
        this.GUI_MAP = new HashMap<>();
    }

    /**
     * 获取月色之湖 GUI 实例对象
     *
     * @return 实例对象
     */
    @Override
    public MoonLakeGUI getMain() {

        return main;
    }

    /**
     * 创建新的 GUI 对象
     *
     * @param name  名称
     * @param title 标题
     * @param size  大小
     * @return GUI 对象
     */
    @Override
    public GUI createGUI(String name, String title, int size) {

        return new GUIReference(name, title, size);
    }

    /**
     * 注册指定 GUI 对象
     *
     * @param gui GUI 对象
     * @param <T> GUI 对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIAlreadyExistsException 如果此 GUI 已经存在则抛出异常
     */
    public <T extends GUI> void registerGUI(T gui) {

        if(hasGUI(gui)) {

            throw new IllegalGUIAlreadyExistsException();
        }
        GUI_MAP.put(gui.getName(), gui);
    }

    /**
     * 卸载指定 GUI 对象
     *
     * @param gui GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    @Override
    public <T extends GUI> GUI unregisterGUI(T gui) {

        if(gui == null) {

            return null;
        }
        GUI_MAP.remove(gui.getName());

        return gui;
    }

    /**
     * 卸载指定 GUI 对象
     *
     * @param name GUI 名称
     * @return GUI 对象 没有则返回 null
     */
    public GUI unregisterGUI(String name) {

        GUI gui = getGUI(name);

        if(gui == null) {

            return null;
        }
        GUI_MAP.remove(gui.getName());

        return gui;
    }

    /**
     * 卸载所有的 GUI 对象
     */
    @Override
    public void unregisterAll() {

        if(getSize() > 0) {

            GUI_MAP.clear();
        }
    }

    /**
     * 获取指定名称的 GUI 对象
     *
     * @param gui GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    public GUI getGUI(GUI gui) {

        return gui != null ? getGUI(gui.getName()) : null;
    }

    /**
     * 获取指定名称的 GUI 对象
     *
     * @param name GUI 名称
     * @return GUI 对象 没有则返回 null
     */
    public GUI getGUI(String name) {

        return hasGUI(name) ? GUI_MAP.get(name) : null;
    }

    /**
     * 获取指定 GUI 对象是否已经存在
     *
     * @param gui GUI 对象
     * @return true 则已经存在 else 没有
     */
    public boolean hasGUI(GUI gui) {

        return gui != null && hasGUI(gui.getName());
    }

    /**
     * 获取指定 GUI 对象是否已经存在
     *
     * @param name GUI 名称
     * @return true 则已经存在 else 没有
     */
    public boolean hasGUI(String name) {

        return GUI_MAP.containsKey(name);
    }

    /**
     * 获取 GUI 对象从物品栏
     *
     * @param inventory 物品栏
     * @return GUI 对象 没有则返回 null
     */
    @Override
    public GUI fromInventory(Inventory inventory) {

        if(inventory == null) {

            return null;
        }
        if(getSize() > 0) {

            for(GUI gui : getAllGUI()) {

                if(gui.equals(inventory)) {

                    return gui;
                }
            }
        }
        return null;
    }

    /**
     * 获取所有的 GUI 对象
     *
     * @return GUI 对象集合
     */
    @Override
    public Set<GUI> getAllGUI() {

        return getSize() > 0 ? new HashSet<>(GUI_MAP.values()) : new HashSet<>();
    }

    /**
     * 获取注册的 GUI 数量大小
     *
     * @return 数量大小
     */
    @Override
    public int getSize() {

        return GUI_MAP.size();
    }
}
