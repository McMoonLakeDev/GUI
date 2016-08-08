package com.minecraft.moonlake.gui.api;

import com.minecraft.moonlake.api.MLogger;
import com.minecraft.moonlake.gui.GUIPlugin;

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
