package com.minecraft.moonlake.gui.util;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import com.minecraft.moonlake.api.player.MoonLakePlayer;
import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException;
import com.minecraft.moonlake.gui.exception.IllegalGUIButtonOverflowException;
import com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException;
import com.minecraft.moonlake.gui.manager.GUIUtil;
import com.minecraft.moonlake.gui.util.button.GUIButtonExecuteNone;
import com.minecraft.moonlake.gui.util.button.GUIButtonReference;
import com.minecraft.moonlake.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class GUIReference implements GUI {

    private String name;
    private String title;
    private int size;
    private boolean allowMove;
    private Map<Integer, GUIButton> buttonMap;

    private final Inventory inventory;

    public GUIReference(String name, String title, int size) {

        // changed color text
        title = Util.color(title);

        this.name = name;
        this.size = size;
        this.title = title;
        this.allowMove = false;
        this.buttonMap = new HashMap<>();
        this.inventory = Bukkit.getServer().createInventory(null, GUIUtil.checkSize(size), title);
    }

    /**
     * 获取此 GUI 对象的名称
     *
     * @return 名称
     */
    @Override
    public String getName() {

        return name;
    }

    /**
     * 获取此 GUI 对象的标题名称
     *
     * @return 标题名称
     */
    @Override
    public String getTitle() {

        return title;
    }

    /**
     * 获取此 GUI 对象的大小
     *
     * @return 大小
     */
    @Override
    public int getSize() {

        return size;
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @return GUI 的按钮对象
     */
    @Override
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
     */
    @Override
    public GUIButton setButton(int x, int y) {

        return setButton(GUIUtil.getSlot(x, y));
    }

    /**
     * 设置指定索引为物品对象
     *
     * @param slot 索引
     * @param icon 图标
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     *  @throws com.minecraft.moonlake.gui.exception.IllegalGUIButtonConflictException 如果此索引已经为按钮则抛出异常
     */
    @Override
    public void setItem(int slot, ItemStack icon) {

        if(slot + 1 > size) {

            throw new IllegalGUISlotOutBoundException();
        }
        if(isButton(slot)) {

            throw new IllegalGUIButtonConflictException();
        }
        inventory.setItem(slot, icon);
    }

    /**
     * 设置指定二维坐标为按钮对象
     *
     * @param x    X 坐标
     * @param y    Y 坐标
     * @param icon 图标
     * @return GUI 按钮对象
     * @throws IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     */
    @Override
    public GUIButton setButton(int x, int y, ItemStack icon) {

        return setButton(GUIUtil.getSlot(x, y), icon);
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot 索引
     * @param icon 图标
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @return GUI 的按钮对象
     */
    @Override
    public GUIButton setButton(int slot, ItemStack icon) {

        return setButton(slot, icon, new GUIButtonExecuteNone());
    }

    /**
     * 设置指定索引为按钮对象
     *
     * @param slot    索引
     * @param icon    图标
     * @param execute 执行
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUISlotOutBoundException 如果索引越界超出大小则抛出异常
     * @return GUI 的按钮对象
     */
    @Override
    public GUIButton setButton(int slot, ItemStack icon, GUIButtonExecute execute) {

        if(slot + 1 > size) {

            throw new IllegalGUISlotOutBoundException();
        }
        GUIButton button = new GUIButtonReference(execute, icon);

        setItem(slot, icon);

        buttonMap.put(slot, button);

        return button;
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
     */
    @Override
    public GUIButton setButton(int x, int y, ItemStack icon, GUIButtonExecute execute) {

        return setButton(GUIUtil.getSlot(x, y), icon, execute);
    }

    /**
     * 将此 GUI 添加按钮对象
     *
     * @param icon 图标
     * @return GUI 按钮对象 异常则返回 null
     * @throws IllegalGUIButtonOverflowException 如果无法再添加按钮则抛出异常
     */
    @Override
    public GUIButton addButton(ItemStack icon) {

        return addButton(icon, new GUIButtonExecuteNone());
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
    public GUIButton addButton(ItemStack icon, GUIButtonExecute execute) {

        if(buttonMap.size() >= size) {

            throw new IllegalGUIButtonOverflowException();
        }
        int slot = -1;

        for(int i = 0; i < size; i++) {

            ItemStack invIcon = inventory.getItem(i);

            if(invIcon != null && invIcon.getType() != Material.AIR) {

                continue;
            }
            slot = i;

            break;
        }
        if(slot == -1) {

            throw new IllegalGUIButtonOverflowException();
        }
        return setButton(slot, icon, execute);
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
     * 清除指定索引的物品对象
     *
     * @param slot 索引
     */
    @Override
    public void removeItem(int slot) {

        if(isButton(slot)) {

            return;
        }
        setItem(slot, new ItemBuilder(Material.AIR).build());
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
        setItem(slot, new ItemBuilder(Material.AIR).build());

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

        for(int i = 0; i < size; i++) {

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

            for (int i = 0; i < size; i++) {

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

    }

    /**
     * 将此 GUI 对象打开给指定玩家
     *
     * @param player 玩家
     */
    @Override
    public void open(Player player) {

        player.openInventory(inventory);
    }

    /**
     * 将此 GUI 对象打开给指定玩家
     *
     * @param player 玩家
     */
    @Override
    public void open(MoonLakePlayer player) {

        player.openInventory(inventory);
    }

    /**
     * 获取此 GUI 对象是否允许移动物品
     *
     * @return true 则允许 else 不允许
     */
    @Override
    public boolean isAllowMove() {

        return allowMove;
    }

    /**
     * 设置此 GUI 对象是否允许移动物品
     *
     * @param flag 是否允许
     */
    @Override
    public void setAllowMove(boolean flag) {

        this.allowMove = flag;
    }
}
