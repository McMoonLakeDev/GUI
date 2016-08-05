package com.minecraft.moonlake.gui.api.event;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.GUIAction;
import com.minecraft.moonlake.gui.api.GUIClickType;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class MoonLakeGUIClickEvent extends MoonLakeGUIEvent implements Cancellable {

    private final static HandlerList handlers = new HandlerList();
    private Player player;
    private int slot;
    private GUIAction action;
    private GUIClickType clickType;
    private boolean cancel;

    public MoonLakeGUIClickEvent(GUI gui, Player player, int slot, GUIAction action, GUIClickType clickType) {

        super(gui);

        this.player = player;
        this.slot = slot;
        this.action = action;
        this.clickType = clickType;
    }

    /**
     * 获取点击的 GUI 对象的玩家
     *
     * @return 玩家
     */
    public Player getPlayer() {

        return player;
    }

    /**
     * 获取点击的 GUI 对象的索引
     *
     * @return 索引
     */
    public int getSlot() {

        return slot;
    }

    /**
     * 获取点击的 GUI 对象的按钮对象
     *
     * @return 按钮对象 非点击按钮则返回 null
     */
    public GUIButton getCurrentButton() {

        return getGUI().getButton(slot);
    }

    /**
     * 获取点击的 GUI 对象是否为按钮
     *
     * @return true 则为按钮 else 不是
     */
    public boolean isButton() {

        return getGUI().isButton(slot);
    }

    /**
     * 获取点击的 GUI 对象的交互类型
     *
     * @return 交互类型
     */
    public GUIAction getAction() {

        return action;
    }

    /**
     * 获取点击的 GUI 对象的点击类型
     *
     * @return 点击类型
     */
    public GUIClickType getClickType() {

        return clickType;
    }

    /**
     * 获取此点击类型是否是键盘的按键被按下（数字键、丢弃键）
     *
     * @return true 则是键盘的按键被按下
     */
    public boolean isKeyboardClick() {

        return clickType != null && clickType.isKeyboardClick();
    }

    /**
     * 获取此点击类型是否是创造模式物品栏的点击行为（中键、CREATIVE）
     *
     * @return true 则是创造模式物品栏的点击行为
     */
    public boolean isCreativeAction() {
        // Why use middle click?
        return clickType != null && clickType.isCreativeAction();
    }

    /**
     * 获取此点击类型是否是右键点击（右键、Shift 键 + 右键）
     *
     * @return true 则是右键点击
     */
    public boolean isRightClick() {

        return clickType != null && clickType.isRightClick();
    }

    /**
     * 获取此点击类型是否是左（或主）键点击（左（或主）键、Shift 键 + 左（或主）键）
     *
     * @return true 则是左（或主）键点击
     */
    public boolean isLeftClick() {

        return clickType != null && clickType.isLeftClick();
    }

    /**
     * 获取此点击类型是否是 Shift 键点击（Shift 键 + 左（或主）键、Shift 键 + 右键、Ctrl 键 + 丢弃键（默认 Q 键））
     *
     * @return true 则是 Shift 键点击
     */
    public boolean isShiftClick() {

        return clickType != null && clickType.isShiftClick();
    }

    @Override
    public HandlerList getHandlers() {

        return handlers;
    }

    public static HandlerList getHandlerList() {

        return handlers;
    }

    /**
     * Gets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins
     *
     * @return true if this event is cancelled
     */
    @Override
    public boolean isCancelled() {

        return cancel;
    }

    /**
     * Sets the cancellation state of this event. A cancelled event will not
     * be executed in the server, but will still pass to other plugins.
     *
     * @param cancel true if you wish to cancel this event
     */
    @Override
    public void setCancelled(boolean cancel) {

        this.cancel = cancel;
    }
}
