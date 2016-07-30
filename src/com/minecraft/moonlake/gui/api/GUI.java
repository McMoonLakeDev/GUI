package com.minecraft.moonlake.gui.api;

import com.minecraft.moonlake.api.player.MoonLakePlayer;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/24.
 */
public interface GUI {

    /**
     * 获取此 GUI 对象的名称
     *
     * @return 名称
     */
    String getName();

    /**
     * 获取此 GUI 对象的标题名称
     *
     * @return 标题名称
     */
    String getTitle();

    /**
     * 获取此 GUI 对象的大小
     *
     * @return 大小
     */
    int getSize();

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setButton(int slot);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setButton(int x, int y);

    /**
     * 设置指定索引为物品对象
     *
     * @param slot 索引
     * @param icon 图标
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    void setItem(int slot, ItemStack icon);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setButton(int x, int y, ItemStack icon);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setButton(int slot, ItemStack icon);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @param execute 执行
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setButton(int slot, ItemStack icon, GUIButtonExecute execute);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param execute 执行
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setButton(int x, int y, ItemStack icon, GUIButtonExecute execute);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icons 图标集合
     * @param execute 执行
     * @return GUI 按钮对象集合
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameButton(int[] slots, ItemStack[] icons, GUIButtonExecute execute);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标集合
     * @param y Y 坐标集合
     * @param icons 图标集合
     * @param execute 执行
     * @return GUI 按钮对象集合
     * @throws java.lang.IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameButton(int[] x, int[] y, ItemStack[] icons, GUIButtonExecute execute);

    /**
     * 设置指定索引的按钮对象的图标
     *
     * @param slot 索引
     * @param icon 图标
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     */
    void setButtonIcon(int slot, ItemStack icon);

    /**
     * 设置指定二维坐标的按钮对象的图标
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     */
    void setButtonIcon(int x, int y, ItemStack icon);

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     * @return GUI 按钮对象 异常则返回 null
     */
    GUIButton addButton(ItemStack icon);

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @param execute 执行
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     * @return GUI 按钮对象 异常则返回 null
     */
    GUIButton addButton(ItemStack icon, GUIButtonExecute execute);

    /**
     * 获取指定索引是否为按钮对象
     *
     * @param slot 索引
     * @return true 则为按钮 else 不是
     */
    boolean isButton(int slot);

    /**
     * 获取指定二维坐标是否为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return true 则为按钮 else 不是
     */
    boolean isButton(int x, int y);

    /**
     * 获取指定索引的按钮对象
     *
     * @param slot 索引
     * @return 按钮对象 异常或没有则返回 null
     */
    GUIButton getButton(int slot);

    /**
     * 获取指定二维坐标的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return 按钮对象 异常或没有则返回 null
     */
    GUIButton getButton(int x, int y);

    /**
     * 清除指定索引的物品对象
     *
     * @param slot 索引
     */
    void removeItem(int slot);

    /**
     * 清除指定二维坐标的物品对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     */
    void removeItem(int x, int y);

    /**
     * 清除指定索引的按钮对象
     *
     * @param slot 索引
     * @return 按钮对象 异常没有则返回 null
     */
    GUIButton removeButton(int slot);

    /**
     * 清除指定二维坐标的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return 按钮对象 异常没有则返回 null
     */
    GUIButton removeButton(int x, int y);

    /**
     * 清除此 GUI 的所有物品对象
     */
    void clearItems();

    /**
     * 清除此 GUI 的所有按钮对象
     */
    void clearButtons();

    /**
     * 清除此 GUI 的所有物品和按钮对象
     */
    void clearAll();

    /**
     * 将此 GUI 对象打开给指定玩家
     *
     * @param player 玩家
     */
    void open(Player player);

    /**
     * 将此 GUI 对象打开给指定玩家
     *
     * @param player 玩家
     */
    void open(MoonLakePlayer player);

    /**
     * 获取此 GUI 对象是否允许移动物品
     *
     * @return true 则允许 else 不允许
     */
    boolean isAllowMove();

    /**
     * 设置此 GUI 对象是否允许移动物品
     *
     * @param flag 是否允许
     */
    void setAllowMove(boolean flag);
}
