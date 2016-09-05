package com.minecraft.moonlake.gui.api;

import com.minecraft.moonlake.api.player.MoonLakePlayer;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.button.GUIButtonClick;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import com.minecraft.moonlake.gui.api.button.GUIButtonWrapped;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Set;

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
     * 创建指定索引为按钮对象
     *
     * @param slot 索引
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton createButton(int slot);

    /**
     * 创建指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton createButton(int slot, ItemStack icon);

    /**
     * 创建指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton createButton(int x, int y);

    /**
     * 创建指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton createButton(int x, int y, ItemStack icon);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int slot, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int slot, GUIButtonWrapped wrapped);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int slot, GUIButtonWrapped[] wrapped);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int slot, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int slot, ItemStack icon, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int slot, ItemStack icon, GUIButtonWrapped wrapped);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int slot, ItemStack icon, GUIButtonWrapped[] wrapped);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int slot, ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int x, int y, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int x, int y, GUIButtonWrapped wrapped);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int x, int y, GUIButtonWrapped[] wrapped);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int x, int y, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int x, int y, ItemStack icon, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int x, int y, ItemStack icon, GUIButtonWrapped wrapped);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int x, int y, ItemStack icon, GUIButtonWrapped[] wrapped);

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton setClickButton(int x, int y, ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 将此 GUI 添加按钮对象
     *
     * @return GUI 的按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    GUIButton addClickButton();

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @return GUI 的按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    GUIButton addClickButton(ItemStack icon);

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    GUIButton addClickButton(ItemStack icon, GUIButtonWrapped wrapped);

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    GUIButton addClickButton(ItemStack icon, GUIButtonWrapped[] wrapped);

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    GUIButton addClickButton(ItemStack icon, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    GUIButton addClickButton(ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] slots, ItemStack icon, GUIButtonWrapped wrapped);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] slots, ItemStack icon, GUIButtonWrapped[] wrapped);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icon 图标
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] slots, ItemStack icon, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icon 图标
     * @param executeMap 执行对象集合
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] slots, ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack icon, GUIButtonWrapped wrapped);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack icon, GUIButtonWrapped[] wrapped);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack icon, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icon 图标
     * @param executeMap 执行对象集合
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icons 图标集合
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] slots, ItemStack[] icons, GUIButtonWrapped wrapped);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icons 图标集合
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] slots, ItemStack[] icons, GUIButtonWrapped[] wrapped);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icons 图标集合
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] slots, ItemStack[] icons, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icons 图标集合
     * @param executeMap 执行对象集合
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] slots, ItemStack[] icons, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icons 图标集合
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack[] icons, GUIButtonWrapped wrapped);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icons 图标集合
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果按钮包装对象为空则抛出异常
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack[] icons, GUIButtonWrapped[] wrapped);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icons 图标集合
     * @param click 点击类型
     * @param execute 执行对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack[] icons, GUIButtonClick click, GUIButtonExecute execute);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @param icons 图标集合
     * @param executeMap 执行对象集合
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果执行对象集合为空则抛出异常
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack[] icons, Map<GUIButtonClick, GUIButtonExecute> executeMap);

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @return GUI 按钮对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Deprecated
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
    @Deprecated
    GUIButton setButton(int x, int y);

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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
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
    @Deprecated
    GUIButton[] setSameButton(int[] slots, ItemStack[] icons, GUIButtonExecute execute);

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots 索引集合
     * @param icon 图标
     * @param execute 执行
     * @return GUI 按钮对象集合
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Deprecated
    GUIButton[] setSameButton(int[] slots, ItemStack icon, GUIButtonExecute execute);

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
    @Deprecated
    GUIButton[] setSameButton(int[] x, int[] y, ItemStack[] icons, GUIButtonExecute execute);

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x X 坐标集合
     * @param y Y 坐标集合
     * @param icon 图标
     * @param execute 执行
     * @return GUI 按钮对象集合
     * @throws java.lang.IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Deprecated
    GUIButton[] setSameButton(int[] x, int[] y, ItemStack icon, GUIButtonExecute execute);

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
    @Deprecated
    GUIButton addButton(ItemStack icon);

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @param execute 执行
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     * @return GUI 按钮对象 异常则返回 null
     */
    @Deprecated
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
     * 获取此 GUI 对象的按钮集合
     *
     * @return 按钮集合 没有则返回 null
     */
    Set<GUIButton> getButtons();

    /**
     * 获取此 GUI 指定索引的物品栈对象
     *
     * @param slot 索引
     * @return 物品栈对象 没有则返回 null
     */
    ItemStack getItem(int slot);

    /**
     * 获取此 GUI 指定二维坐标的物品栈对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return 物品栈对象 没有则返回 null
     */
    ItemStack getItem(int x, int y);

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
     * @throws IllegalArgumentException 如果玩家对象为空或不在线则抛出异常
     */
    void open(Player player);

    /**
     * 将此 GUI 对象打开给指定玩家
     *
     * @param player 玩家
     * @throws IllegalArgumentException 如果玩家对象为空或不在线则抛出异常
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

    /**
     * 获取此 GUI 对象是否将关闭后从对象集合卸载
     *
     * @return true 则关闭后卸载 else 不卸载
     */
    boolean isCloseToUnregister();

    /**
     * 设置此 GUI 对象是否将关闭后从对象集合卸载
     *
     * @param flag true 则关闭后卸载 else 不卸载
     */
    void onCloseToUnregister(boolean flag);

    /**
     * 获取此 GUI 对象是否符合目标对象
     *
     * @param obj 对象
     * @return true 则符合
     */
    boolean equals(Object obj);

    /**
     * 将此 GUI 对象注册到对象集合
     */
    @Deprecated
    void register();

    /**
     * 将此 GUI 对象从对象集合卸载
     */
    @Deprecated
    void unregister();
}
