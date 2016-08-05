package com.minecraft.moonlake.gui.api;

/**
 * Created by MoonLake on 2016/8/5.
 */
public enum GUIClickType {

    /**
     * GUI 点击类型: 鼠标左（或主）键
     */
    LEFT,
    /**
     * GUI 点击类型: Shift 键 + 鼠标左（或主）键
     */
    SHIFT_LEFT,
    /**
     * GUI 点击类型: 鼠标右键
     */
    RIGHT,
    /**
     * GUI 点击类型: Shift 键 + 鼠标右键
     */
    SHIFT_RIGHT,
    /**
     * GUI 点击类型: 鼠标左（或主）键点击在窗口灰色区域周围的物品栏
     */
    WINDOW_BORDER_LEFT,
    /**
     * GUI 点击类型: 鼠标右键点击在窗口灰色区域周围的物品栏
     */
    WINDOW_BORDER_RIGHT,
    /**
     * GUI 点击类型: 鼠标中（或滑轮）键
     */
    MIDDLE,
    /**
     * GUI 点击类型: 数字键 1-9 对应索引槽上的快捷栏
     */
    NUMBER_KEY,
    /**
     * GUI 点击类型: 快速或连续按鼠标左（或主）键两次
     */
    DOUBLE_CLICK,
    /**
     * GUI 点击类型: 丢弃物品键（默认 Q 键）
     */
    DROP,
    /**
     * GUI 点击类型: Ctrl 键 + 丢弃物品键（默认 Q 键）
     */
    CONTROL_DROP,
    /**
     * GUI 点击类型: 任何和创造模式物品栏点击的行为
     */
    CREATIVE,
    /**
     * GUI 点击类型: 未知
     * <p>
     * 这个点击类型 Bukkit 尚未认可, 这只是过渡的目的为了 Minecraft 的更新，而不应该依赖。
     * <p>
     * 任何 GUIClickType.UNKNOW 就是尽最大的努力基础上
     */
    UNKNOWN,
    ;

    /**
     * 获取此点击类型是否是键盘的按键被按下（数字键、丢弃键）
     *
     * @return true 则是键盘的按键被按下
     */
    public boolean isKeyboardClick() {

        return (this == GUIClickType.NUMBER_KEY) || (this == GUIClickType.DROP) || (this == GUIClickType.CONTROL_DROP);
    }

    /**
     * 获取此点击类型是否是创造模式物品栏的点击行为（中键、CREATIVE）
     *
     * @return true 则是创造模式物品栏的点击行为
     */
    public boolean isCreativeAction() {
        // Why use middle click?
        return (this == GUIClickType.MIDDLE) || (this == GUIClickType.CREATIVE);
    }

    /**
     * 获取此点击类型是否是右键点击（右键、Shift 键 + 右键）
     *
     * @return true 则是右键点击
     */
    public boolean isRightClick() {

        return (this == GUIClickType.RIGHT) || (this == GUIClickType.SHIFT_RIGHT);
    }

    /**
     * 获取此点击类型是否是左（或主）键点击（左（或主）键、Shift 键 + 左（或主）键）
     *
     * @return true 则是左（或主）键点击
     */
    public boolean isLeftClick() {

        return (this == GUIClickType.LEFT) || (this == GUIClickType.SHIFT_LEFT) || (this == GUIClickType.DOUBLE_CLICK) || (this == GUIClickType.CREATIVE);
    }

    /**
     * 获取此点击类型是否是 Shift 键点击（Shift 键 + 左（或主）键、Shift 键 + 右键、Ctrl 键 + 丢弃键（默认 Q 键））
     *
     * @return true 则是 Shift 键点击
     */
    public boolean isShiftClick() {

        return (this == GUIClickType.SHIFT_LEFT) || (this == GUIClickType.SHIFT_RIGHT) || (this == GUIClickType.CONTROL_DROP);
    }
}
