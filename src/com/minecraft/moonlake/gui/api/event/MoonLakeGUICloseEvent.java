package com.minecraft.moonlake.gui.api.event;

import com.minecraft.moonlake.gui.api.GUI;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class MoonLakeGUICloseEvent extends MoonLakeGUIEvent {

    private final static HandlerList handlers = new HandlerList();
    private Player player;

    public MoonLakeGUICloseEvent(GUI gui, Player player) {

        super(gui);

        this.player = player;
    }

    /**
     * 获取关闭此 GUI 对象的玩家
     *
     * @return 玩家
     */
    public Player getPlayer() {

        return player;
    }

    @Override
    public HandlerList getHandlers() {

        return handlers;
    }

    public static HandlerList getHandlerList() {

        return handlers;
    }
}
