package com.minecraft.moonlake.gui.api.event;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class MoonLakeGUIClickEvent extends MoonLakeGUIEvent {

    private final static HandlerList handlers = new HandlerList();
    private Player player;
    private GUIButton currentButton;

    public MoonLakeGUIClickEvent(GUI gui, Player player, GUIButton currentButton) {

        super(gui);

        this.player = player;
        this.currentButton = currentButton;
    }

    /**
     * 获取点击此 GUI 对象的玩家
     *
     * @return 玩家
     */
    public Player getPlayer() {

        return player;
    }

    /**
     * 获取点击的 GUI 对象的按钮对象
     *
     * @return 按钮对象 非点击按钮则返回 null
     */
    public GUIButton getCurrentButton() {

        return currentButton;
    }

    /**
     * 获取点击的 GUI 对象是否为按钮
     *
     * @return true 则为按钮 else 不是
     */
    public boolean isButton() {

        return currentButton != null;
    }

    @Override
    public HandlerList getHandlers() {

        return handlers;
    }

    public static HandlerList getHandlerList() {

        return handlers;
    }
}
