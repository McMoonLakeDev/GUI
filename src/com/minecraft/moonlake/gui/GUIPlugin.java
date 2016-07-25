package com.minecraft.moonlake.gui;

import com.minecraft.moonlake.MoonLakePlugin;
import com.minecraft.moonlake.gui.api.MoonLakeGUI;
import com.minecraft.moonlake.gui.api.MoonLakeGUIManager;
import com.minecraft.moonlake.gui.listeners.InventoryListener;
import com.minecraft.moonlake.gui.manager.GUIManager;
import com.minecraft.moonlake.util.Util;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class GUIPlugin extends JavaPlugin implements MoonLakeGUI {

    private MoonLakeGUIManager manager;
    private final ConsoleCommandSender console;
    private static MoonLakeGUI MAIN;

    public GUIPlugin() {

        this.console = this.getServer().getConsoleSender();
    }

    @Override
    public void onEnable() {

        MAIN = this;

        if(!setupMoonLake()) {

            this.log("前置月色之湖核心 API 插件加载失败.");
            this.getServer().getPluginManager().disablePlugin(this);
            return;
        }
        manager = new GUIManager(getInstance());

        this.getServer().getPluginManager().registerEvents(new InventoryListener(this), this);
        this.log("月色之湖 GUI 插件 v" + getDescription().getVersion() + " 成功加载.");
    }

    @Override
    public void onDisable() {

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
     * 给控制台输出日志信息
     *
     * @param message 日志
     */
    @Override
    public void log(String message) {

        this.console.sendMessage("[MoonLakeGUI] " + Util.color(message));
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
}
