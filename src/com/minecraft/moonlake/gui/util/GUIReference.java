package com.minecraft.moonlake.gui.util;

import com.minecraft.moonlake.api.player.MoonLakePlayer;
import com.minecraft.moonlake.gui.GUIPlugin;
import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.button.GUIButtonClick;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import com.minecraft.moonlake.gui.api.button.GUIButtonWrapped;
import com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException;
import com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException;
import com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException;
import com.minecraft.moonlake.gui.manager.GUIUtil;
import com.minecraft.moonlake.gui.util.button.GUIButtonExecuteNone;
import com.minecraft.moonlake.gui.util.button.GUIButtonReference;
import com.minecraft.moonlake.property.*;
import com.minecraft.moonlake.util.StringUtil;
import com.minecraft.moonlake.validate.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class GUIReference implements GUI {

    private ReadOnlyStringProperty nameProperty;
    private ReadOnlyStringProperty titleProperty;
    private ReadOnlyIntegerProperty sizeProperty;
    private ReadOnlyObjectProperty<Inventory> inventoryProperty;
    private final Map<Integer, GUIButton> buttonMap;

    private BooleanProperty allowMoveProperty;
    private BooleanProperty closeToUnregisterProperty;

    public GUIReference(String name, String title, int size) {

        // changed color text and check size
        title = StringUtil.toColor(title).get();
        size = GUIUtil.checkSize(size);
        size = size <= 6 ? size * 9 : size;

        this.nameProperty = new SimpleStringProperty(name);
        this.sizeProperty = new SimpleIntegerProperty(size);
        this.titleProperty = new SimpleStringProperty(title);
        this.allowMoveProperty = new SimpleBooleanProperty(false);
        this.closeToUnregisterProperty = new SimpleBooleanProperty(false);
        this.inventoryProperty = new SimpleObjectProperty<>(Bukkit.getServer().createInventory(null, size, title));
        this.buttonMap = new HashMap<>();
    }

    /**
     * 获取此 GUI 对象的名称
     *
     * @return 名称
     */
    @Override
    public ReadOnlyStringProperty getName() {

        return nameProperty;
    }

    /**
     * 获取此 GUI 对象的标题名称
     *
     * @return 标题名称
     */
    @Override
    public ReadOnlyStringProperty getTitle() {

        return titleProperty;
    }

    /**
     * 获取此 GUI 对象的大小
     *
     * @return 大小
     */
    @Override
    public ReadOnlyIntegerProperty getSize() {

        return sizeProperty;
    }

    /**
     * 创建指定索引为按钮对象
     *
     * @param slot 索引
     * @return GUI 按钮对象
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton createButton(int slot) {

        return setClickButton(slot, new HashMap<>());
    }

    /**
     * 创建指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @return GUI 按钮对象
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton createButton(int slot, ItemStack icon) {

        return setClickButton(slot, icon, new HashMap<>());
    }

    /**
     * 创建指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return GUI 按钮对象
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton createButton(int x, int y) {

        return createButton(GUIUtil.getSlot(x, y));
    }

    /**
     * 创建指定二维坐标为按钮对象
     *
     * @param x    X 坐标
     * @param y    Y 坐标
     * @param icon 图标
     * @return GUI 按钮对象
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton createButton(int x, int y, ItemStack icon) {

        return createButton(GUIUtil.getSlot(x, y), icon);
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot    索引
     * @param click   点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int slot, GUIButtonClick click, GUIButtonExecute execute) {

        return setClickButton(slot, GUIUtil.DEFAULT_ICON, click, execute);
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot    索引
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int slot, GUIButtonWrapped wrapped) {

        return setClickButton(slot, new GUIButtonWrapped[] { wrapped });
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot    索引
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int slot, GUIButtonWrapped... wrapped) {

        return setClickButton(slot, GUIUtil.DEFAULT_ICON, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot       索引
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int slot, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        return setClickButton(slot, GUIUtil.DEFAULT_ICON, executeMap);
    }

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
    @Override
    public GUIButton setClickButton(int slot, ItemStack icon, GUIButtonClick click, GUIButtonExecute execute) {

        Validate.notNull(click, "The button click object is null.");
        Validate.notNull(execute, "The button execute object is null.");

        return setClickButton(slot, icon, GUIUtil.wrappedToExecuteMap(click, execute));
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot    索引
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int slot, ItemStack icon, GUIButtonWrapped wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setClickButton(slot, icon, new GUIButtonWrapped[] { wrapped });
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot    索引
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int slot, ItemStack icon, GUIButtonWrapped... wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setClickButton(slot, icon, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot       索引
     * @param icon       图标
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int slot, ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        Validate.notNull(executeMap, "The button execute map object is null.");

        if(slot + 1 > sizeProperty.get()) {

            throw new IllegalGUISlotOutBoundException();
        }
        if(isButton(slot)) {

            throw new IllegalGUIButtonConflictException();
        }
        GUIButton button = new GUIButtonReference(this, slot, executeMap);

        setItem(slot, icon);

        buttonMap.put(slot, button);

        return button;
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param click   点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int x, int y, GUIButtonClick click, GUIButtonExecute execute) {

        return setClickButton(GUIUtil.getSlot(x, y), click, execute);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int x, int y, GUIButtonWrapped wrapped) {

        return setClickButton(GUIUtil.getSlot(x, y), wrapped);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int x, int y, GUIButtonWrapped[] wrapped) {

        return setClickButton(GUIUtil.getSlot(x, y), wrapped);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x          X 坐标
     * @param y          Y 坐标
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int x, int y, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        return setClickButton(GUIUtil.getSlot(x, y), executeMap);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icon    图标
     * @param click   点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int x, int y, ItemStack icon, GUIButtonClick click, GUIButtonExecute execute) {

        return setClickButton(GUIUtil.getSlot(x, y), icon, click, execute);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int x, int y, ItemStack icon, GUIButtonWrapped wrapped) {

        return setClickButton(GUIUtil.getSlot(x, y), icon, wrapped);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int x, int y, ItemStack icon, GUIButtonWrapped[] wrapped) {

        return setClickButton(GUIUtil.getSlot(x, y), icon, wrapped);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x          X 坐标
     * @param y          Y 坐标
     * @param icon       图标
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton setClickButton(int x, int y, ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        return setClickButton(GUIUtil.getSlot(x, y), icon, executeMap);
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @return GUI 的按钮对象
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton addClickButton() {

        return addClickButton(GUIUtil.DEFAULT_ICON);
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @return GUI 的按钮对象
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    public GUIButton addClickButton(ItemStack icon) {

        return addClickButton(icon, new HashMap<>());
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    public GUIButton addClickButton(ItemStack icon, GUIButtonWrapped wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return addClickButton(icon, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    public GUIButton addClickButton(ItemStack icon, GUIButtonWrapped[] wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return addClickButton(icon, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon    图标
     * @param click   点击类型
     * @param execute 执行对象
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    public GUIButton addClickButton(ItemStack icon, GUIButtonClick click, GUIButtonExecute execute) {

        Validate.notNull(click, "The button click object is null.");
        Validate.notNull(execute, "The button execute object is null.");

        return addClickButton(icon, GUIUtil.wrappedToExecuteMap(click, execute));
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon       图标
     * @param executeMap 执行对象集合
     * @return GUI 的按钮对象
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    public GUIButton addClickButton(ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        Validate.notNull(executeMap, "The button execute map object is null.");

        if(buttonMap.size() >= sizeProperty.get()) {

            throw new IllegalGUIButtonOverflowException();
        }
        int slot = -1;

        for(int i = 0; i < sizeProperty.get(); i++) {

            if(isButton(i)) {

                continue;
            }
            slot = i;

            break;
        }
        if(slot == -1) {

            throw new IllegalGUIButtonOverflowException();
        }
        return setClickButton(slot, icon, executeMap);
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots   索引集合
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] slots, ItemStack icon, GUIButtonWrapped wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setSameClickButton(slots, icon, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots   索引集合
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] slots, ItemStack icon, GUIButtonWrapped[] wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setSameClickButton(slots, icon, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots   索引集合
     * @param icon    图标
     * @param click   点击类型
     * @param execute 执行对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] slots, ItemStack icon, GUIButtonClick click, GUIButtonExecute execute) {

        Validate.notNull(click, "The button click object is null.");
        Validate.notNull(execute, "The button execute object is null.");

        return setSameClickButton(slots, icon, GUIUtil.wrappedToExecuteMap(click, execute));
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots      索引集合
     * @param icon       图标
     * @param executeMap 执行对象集合
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] slots, ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        Validate.notNull(executeMap, "The button execute map object is null.");
        Validate.isTrue(slots != null && slots.length > 0, "The slot object exception.");

        GUIButton[] buttons = new GUIButton[slots.length];

        for(int i = 0; i < slots.length; i++) {

            buttons[i] = setClickButton(slots[i], icon, executeMap);
        }
        return buttons;
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack icon, GUIButtonWrapped wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setSameClickButton(x, y, icon, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icon    图标
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack icon, GUIButtonWrapped[] wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setSameClickButton(x, y, icon, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icon    图标
     * @param click   点击类型
     * @param execute 执行对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack icon, GUIButtonClick click, GUIButtonExecute execute) {

        Validate.notNull(click, "The button click object is null.");
        Validate.notNull(execute, "The button execute object is null.");

        return setSameClickButton(x, y, icon, GUIUtil.wrappedToExecuteMap(click, execute));
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x          X 坐标
     * @param y          Y 坐标
     * @param icon       图标
     * @param executeMap 执行对象集合
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack icon, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        Validate.notNull(executeMap, "The button execute map object is null.");
        Validate.isTrue(x != null && y != null, "The two dimension coordinate argument exception.");
        Validate.isTrue(x.length == y.length, "The two dimension coordinate argument exception.");

        int[] slots = new int[x.length];

        for(int i = 0; i < slots.length; i++) {

            slots[i] = GUIUtil.getSlot(x[i], y[i]);
        }
        return setSameClickButton(slots, icon, executeMap);
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots   索引集合
     * @param icons   图标集合
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] slots, ItemStack[] icons, GUIButtonWrapped wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setSameClickButton(slots, icons, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots   索引集合
     * @param icons   图标集合
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] slots, ItemStack[] icons, GUIButtonWrapped[] wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setSameClickButton(slots, icons, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots   索引集合
     * @param icons   图标集合
     * @param click   点击类型
     * @param execute 执行对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] slots, ItemStack[] icons, GUIButtonClick click, GUIButtonExecute execute) {

        Validate.notNull(click, "The button click object is null.");
        Validate.notNull(execute, "The button execute object is null.");

        return setSameClickButton(slots, icons, GUIUtil.wrappedToExecuteMap(click, execute));
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots      索引集合
     * @param icons      图标集合
     * @param executeMap 执行对象集合
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] slots, ItemStack[] icons, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        Validate.notNull(executeMap, "The button execute map object is null.");
        Validate.isTrue(slots != null && slots.length > 0 && icons != null, "The slot or icons argument exception.");

        List<ItemStack> icons0 = new ArrayList<>(Arrays.asList(icons));

        if(icons0.size() < slots.length) {

            for(int i = icons0.size(); i < slots.length; i++) {

                icons0.add(GUIUtil.DEFAULT_ICON);
            }
        }
        GUIButton[] buttons = new GUIButton[slots.length];

        for(int i = 0; i < slots.length; i++) {

            buttons[i] = setClickButton(slots[i], icons0.get(i), executeMap);
        }
        return buttons;
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icons   图标集合
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack[] icons, GUIButtonWrapped wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setSameClickButton(x, y, icons, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icons   图标集合
     * @param wrapped 按钮包装对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果按钮包装对象为空则抛出异常
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack[] icons, GUIButtonWrapped[] wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        return setSameClickButton(x, y, icons, GUIUtil.wrappedToExecuteMap(wrapped));
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icons   图标集合
     * @param click   点击类型
     * @param execute 执行对象
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果点击类型或者执行对象为空则抛出异常
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack[] icons, GUIButtonClick click, GUIButtonExecute execute) {

        Validate.notNull(click, "The button click object is null.");
        Validate.notNull(execute, "The button execute object is null.");

        return setSameClickButton(x, y, icons, GUIUtil.wrappedToExecuteMap(click, execute));
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x          X 坐标
     * @param y          Y 坐标
     * @param icons      图标集合
     * @param executeMap 执行对象集合
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果执行对象集合为空则抛出异常
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public GUIButton[] setSameClickButton(int[] x, int[] y, ItemStack[] icons, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        Validate.notNull(executeMap, "The button execute map object is null.");
        Validate.isTrue(x != null && y != null, "The two dimension coordinate argument exception.");
        Validate.isTrue(x.length == y.length, "The two dimension coordinate argument exception.");

        int[] slots = new int[x.length];

        for(int i = 0; i < slots.length; i++) {

            slots[i] = GUIUtil.getSlot(x[i], y[i]);
        }
        return setSameClickButton(slots, icons, executeMap);
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @return GUI 的按钮对象
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton setButton(int slot) {

        return setButton(slot, GUIUtil.DEFAULT_ICON);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return GUI 按钮对象
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton setButton(int x, int y) {

        return setButton(GUIUtil.getSlot(x, y));
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x    X 坐标
     * @param y    Y 坐标
     * @param icon 图标
     * @return GUI 按钮对象
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton setButton(int x, int y, ItemStack icon) {

        return setButton(GUIUtil.getSlot(x, y), icon);
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @return GUI 的按钮对象
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton setButton(int slot, ItemStack icon) {

        return setButton(slot, icon, new GUIButtonExecuteNone());
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot    索引
     * @param icon    图标
     * @param execute 执行
     * @return GUI 的按钮对象
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton setButton(int slot, ItemStack icon, GUIButtonExecute execute) {

        return setClickButton(slot, icon, GUIButtonClick.ALL, execute);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x       X 坐标
     * @param y       Y 坐标
     * @param icon    图标
     * @param execute 执行
     * @return GUI 按钮对象
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton setButton(int x, int y, ItemStack icon, GUIButtonExecute execute) {

        return setButton(GUIUtil.getSlot(x, y), icon, execute);
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots   索引集合
     * @param icons   图标集合
     * @param execute 执行
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果索引或图标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton[] setSameButton(int[] slots, ItemStack[] icons, GUIButtonExecute execute) {

        return setSameClickButton(slots, icons, GUIButtonClick.ALL, execute);
    }

    /**
     * 设置指定索引集合为相同的按钮对象
     *
     * @param slots   索引集合
     * @param icon    图标
     * @param execute 执行
     * @return GUI 按钮对象集合
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton[] setSameButton(int[] slots, ItemStack icon, GUIButtonExecute execute) {

        return setSameClickButton(slots, icon, GUIButtonClick.ALL, execute);
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x       X 坐标集合
     * @param y       Y 坐标集合
     * @param icons   图标集合
     * @param execute 执行
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException 如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton[] setSameButton(int[] x, int[] y, ItemStack[] icons, GUIButtonExecute execute) {

        return setSameClickButton(x, y, icons, GUIButtonClick.ALL, execute);
    }

    /**
     * 设置指定二维坐标集合为相同的按钮对象
     *
     * @param x       X 坐标集合
     * @param y       Y 坐标集合
     * @param icon    图标
     * @param execute 执行
     * @return GUI 按钮对象集合
     * @throws IllegalArgumentException          如果二维坐标参数集合不正确则抛出异常
     * @throws IllegalGUISlotOutBoundException   如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton[] setSameButton(int[] x, int[] y, ItemStack icon, GUIButtonExecute execute) {

        return setSameClickButton(x, y, icon, GUIButtonClick.ALL, execute);
    }

    /**
     * 设置指定索引为物品对象
     *
     * @param slot 索引
     * @param icon 图标
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @throws IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public void setItem(int slot, ItemStack icon) {

        if(slot + 1 > sizeProperty.get()) {

            throw new IllegalGUISlotOutBoundException();
        }
        if(isButton(slot)) {

            throw new IllegalGUIButtonConflictException();
        }
        inventoryProperty.get().setItem(slot, icon);
    }

    /**
     * 设置指定索引的按钮对象的图标
     *
     * @param slot 索引
     * @param icon 图标
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     */
    @Override
    public void setButtonIcon(int slot, ItemStack icon) {

        if(slot + 1 > sizeProperty.get()) {

            throw new IllegalGUISlotOutBoundException();
        }
        if(icon == null || icon.getType() == Material.AIR) {

            return;
        }
        GUIButton button = getButton(slot);

        if(button == null) {

            return;
        }
        inventoryProperty.get().setItem(slot, icon);
    }

    /**
     * 设置指定二维坐标的按钮对象的图标
     *
     * @param x    X 坐标
     * @param y    Y 坐标
     * @param icon 图标
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     */
    @Override
    public void setButtonIcon(int x, int y, ItemStack icon) {

        setButtonIcon(GUIUtil.getSlot(x, y), icon);
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @return GUI 按钮对象 异常则返回 null
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton addButton(ItemStack icon) {

        return addClickButton(icon);
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon    图标
     * @param execute 执行
     * @return GUI 按钮对象 异常则返回 null
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    @Deprecated
    public GUIButton addButton(ItemStack icon, GUIButtonExecute execute) {

        return addClickButton(icon, GUIButtonClick.ALL, execute);
    }

    /**
     * 获取指定索引是否为按钮对象
     *
     * @param slot 索引
     * @return true 则为按钮 else 不是
     */
    @Override
    public boolean isButton(int slot) {

        return buttonMap.containsKey(slot);
    }

    /**
     * 获取指定二维坐标是否为按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return true 则为按钮 else 不是
     */
    @Override
    public boolean isButton(int x, int y) {

        return isButton(GUIUtil.getSlot(x, y));
    }

    /**
     * 获取指定索引的按钮对象
     *
     * @param slot 索引
     * @return 按钮对象 异常或没有则返回 null
     */
    @Override
    public GUIButton getButton(int slot) {

        return isButton(slot) ? buttonMap.get(slot) : null;
    }

    /**
     * 获取指定二维坐标的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return 按钮对象 异常或没有则返回 null
     */
    @Override
    public GUIButton getButton(int x, int y) {

        return getButton(GUIUtil.getSlot(x, y));
    }

    /**
     * 获取此 GUI 对象的按钮集合
     *
     * @return 按钮集合 没有则返回 null
     */
    @Override
    public Set<GUIButton> getButtons() {

        Set<GUIButton> guiButtonSet = new HashSet<>();

        if(buttonMap.size() > 0) {

            guiButtonSet.addAll(buttonMap.values());
        }
        return guiButtonSet.size() > 0 ? guiButtonSet : null;
    }

    /**
     * 获取此 GUI 指定索引的物品栈对象
     *
     * @param slot 索引
     * @return 物品栈对象 没有则返回 null
     */
    @Override
    public ItemStack getItem(int slot) {

        return inventoryProperty.get().getItem(slot);
    }

    /**
     * 获取此 GUI 指定二维坐标的物品栈对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return 物品栈对象 没有则返回 null
     */
    @Override
    public ItemStack getItem(int x, int y) {

        return inventoryProperty.get().getItem(GUIUtil.getSlot(x, y));
    }

    /**
     * 清除指定索引的物品对象
     *
     * @param slot 索引
     */
    @Override
    public void removeItem(int slot) {

        if(isButton(slot)) {

            return;
        }
        setItem(slot, new ItemStack(Material.AIR));
    }

    /**
     * 清除指定二维坐标的物品对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     */
    @Override
    public void removeItem(int x, int y) {

        removeItem(GUIUtil.getSlot(x, y));
    }

    /**
     * 清除指定索引的按钮对象
     *
     * @param slot 索引
     * @return 按钮对象 异常没有则返回 null
     */
    @Override
    public GUIButton removeButton(int slot) {

        if(!isButton(slot)) {

            return null;
        }
        setItem(slot, new ItemStack(Material.AIR));

        return buttonMap.remove(slot);
    }

    /**
     * 清除指定二维坐标的按钮对象
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return 按钮对象 异常没有则返回 null
     */
    @Override
    public GUIButton removeButton(int x, int y) {

        return removeButton(GUIUtil.getSlot(x, y));
    }

    /**
     * 清除此 GUI 的所有物品对象
     */
    @Override
    public void clearItems() {

        for(int i = 0; i < sizeProperty.get(); i++) {

            if(!isButton(i)) {

                removeItem(i);
            }
        }
    }

    /**
     * 清除此 GUI 的所有按钮对象
     */
    @Override
    public void clearButtons() {

        if(buttonMap.size() > 0) {

            for (int i = 0; i < sizeProperty.get(); i++) {

                if (isButton(i)) {

                    removeButton(i);
                }
            }
        }
    }

    /**
     * 清除此 GUI 的所有物品和按钮对象
     */
    @Override
    public void clearAll() {

        inventoryProperty.get().clear();
        buttonMap.clear();
    }

    /**
     * 将此 GUI 对象打开给指定玩家
     *
     * @param player 玩家
     * @throws IllegalArgumentException 如果玩家对象为空或不在线则抛出异常
     */
    @Override
    public void open(Player player) {

        Validate.isTrue(player != null && player.isOnline(), "The open gui player object is null or not online.");

        player.openInventory(inventoryProperty.get());
    }

    /**
     * 将此 GUI 对象打开给指定玩家
     *
     * @param player 玩家
     * @throws IllegalArgumentException 如果玩家对象为空或不在线则抛出异常
     */
    @Override
    public void open(MoonLakePlayer player) {

        Validate.isTrue(player != null, "The open gui player object is null.");

        open(player.getBukkitPlayer());
    }

    /**
     * 获取此 GUI 对象是否允许移动物品
     *
     * @return true 则允许 else 不允许
     */
    @Override
    public BooleanProperty getAllowMove() {

        return allowMoveProperty;
    }

    /**
     * 设置此 GUI 对象是否允许移动物品
     *
     * @param flag 是否允许
     */
    @Override
    public void setAllowMove(boolean flag) {

        this.allowMoveProperty.set(flag);
    }

    /**
     * 获取此 GUI 对象是否将关闭后从对象集合卸载
     *
     * @return true 则关闭后卸载 else 不卸载
     */
    @Override
    public BooleanProperty getCloseToUnregister() {

        return closeToUnregisterProperty;
    }

    /**
     * 设置此 GUI 对象是否将关闭后从对象集合卸载
     *
     * @param flag true 则关闭后卸载 else 不卸载
     */
    @Override
    public void onCloseToUnregister(boolean flag) {

        this.closeToUnregisterProperty.set(flag);
    }

    @Override
    public final int hashCode() {

        return nameProperty.get().hashCode();
    }

    @Override
    public final boolean equals(Object obj) {

        if(obj == null) {

            return false;
        }
        if(obj == this) {

            return true;
        }
        if(obj instanceof GUI) {

            GUI gui = (GUI) obj;
            return gui.getName().get().equals(getName().get());
        }
        else if(obj instanceof Inventory) {

            Inventory guiBase = (Inventory) obj;
            return guiBase.equals(inventoryProperty.get());
        }
        return false;
    }

    /**
     * 将此 GUI 对象注册到对象集合
     */
    @Override
    @Deprecated
    public void register() {

        GUIPlugin.getManagers().registerGUI(this);
    }

    /**
     * 将此 GUI 对象从对象集合卸载
     */
    @Override
    @Deprecated
    public void unregister() {

        GUIPlugin.getManagers().unregisterGUI(this);
    }
}
