package com.minecraft.moonlake.gui.api.event;

import com.minecraft.moonlake.gui.api.GUI;
import org.bukkit.event.Event;

/**
 * Created by MoonLake on 2016/7/24.
 */
public abstract class MoonLakeGUIEvent extends Event {

    private final GUI gui;

    public MoonLakeGUIEvent(GUI gui) {

        this.gui = gui;
    }

    /**
     * 获取此事件的 GUI 对象
     *
     * @return GUI 对象
     */
    public final GUI getGUI() {

        return gui;
    }
}
