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
 
 
package com.minecraft.moonlake.gui;

import com.minecraft.moonlake.MoonLakePlugin;
import com.minecraft.moonlake.gui.api.MoonLakeGUI;
import com.minecraft.moonlake.gui.api.MoonLakeGUIManager;
import com.minecraft.moonlake.gui.listeners.InventoryListener;
import com.minecraft.moonlake.gui.manager.GUIManager;
import com.minecraft.moonlake.logger.MLogger;
import com.minecraft.moonlake.logger.MLoggerWrapped;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <h1>Minecraft MoonLake GUI Plugin</h1>
 */
public class GUIPlugin extends JavaPlugin implements MoonLakeGUI {

    private MoonLakeGUIManager manager;
    private final MLogger mLogger;
    private static MoonLakeGUI MAIN;

    public GUIPlugin() {

        this.mLogger = new MLoggerWrapped("MoonLakeGUI");
    }

    @Override
    public void onEnable() {

        MAIN = this;

        if(!setupMoonLake()) {

            this.getMLogger().warn("前置月色之湖核心 API 插件加载失败.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        manager = new GUIManager(getInstance());

        this.getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        this.getMLogger().info("月色之湖 GUI 插件 v" + getDescription().getVersion() + " 成功加载.");
    }

    @Override
    public void onDisable() {

        manager.unregisterAll();
    }

    /**
     * 初始化插件数据目录
     */
    private void initFolder() {

    }

    /**
     * 加载月色之湖前置核心 API 插件
     *
     * @return 是否成功
     */
    private boolean setupMoonLake() {

        Plugin plugin = this.getServer().getPluginManager().getPlugin("MoonLake");
        return plugin != null && plugin instanceof MoonLakePlugin;
    }

    /**
     * 获取月色之湖 GUI 实例对象
     *
     * @return 实例对象
     */
    public static MoonLakeGUI getInstances() {

        return MAIN;
    }

    /**
     * 获取插件的主类对象
     *
     * @return 主类
     */
    @Override
    public GUIPlugin getMain() {

        return this;
    }

    /**
     * 获取月色之湖 GUI 实例对象
     *
     * @return 实例对象
     */
    @Override
    public MoonLakeGUI getInstance() {

        return MAIN;
    }

    /**
     * 获取月色之湖控制台日志对象
     *
     * @return 日志对象
     */
    @Override
    public MLogger getMLogger() {

        return mLogger;
    }

    /**
     * 获取月色之湖 GUI 管理实例对象
     *
     * @return 管理实例对象
     */
    @Override
    public MoonLakeGUIManager getManager() {

        return manager;
    }

    /**
     * 获取月色之湖 GUI 管理实例对象
     *
     * @return 管理实例对象
     */
    public static MoonLakeGUIManager getManagers() {

        return getInstances().getManager();
    }
}
