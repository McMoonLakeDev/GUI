package com.minecraft.moonlake.gui.manager;

import com.minecraft.moonlake.api.item.ItemLibraryFactorys;
import com.minecraft.moonlake.gui.api.button.GUIButtonClick;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import com.minecraft.moonlake.gui.api.button.GUIButtonWrapped;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/7/25.
 */
public final class GUIUtil {

    public final static ItemStack DEFAULT_ICON;

    static {

        DEFAULT_ICON = ItemLibraryFactorys.itemBuilder(Material.BEDROCK, 0, 1, ChatColor.GRAY + "GUI DEFAULT ICON").build();
    }

    /**
     * 检测指定 GUI 大小值是否符合
     *
     * @param size 大小值 (1 - 6) | (9、18、27、36、45、54)
     * @return 检测完毕的大小值 不符合则返回 9
     */
    public static int checkSize(int size) {

        return (size > 6 && size <= 54 && size % 9 == 0) ? size : (size >= 1 && size <= 6) ? size * 9 : 9;
    }

    /**
     * 将二维坐标转换到 GUI 所需的索引值
     *
     * @param x X 坐标
     * @param y Y 坐标
     * @return 索引值
     */
    public static int getSlot(int x, int y) {

        return (y * 9) - (9 - x) - 1;
    }

    /**
     * 将按钮包装对象数组转换到按钮执行对象集合
     *
     * @param wrapped 按钮包装对象数组
     * @return 按钮执行对象集合 没有则返回空集合
     */
    public static Map<GUIButtonClick, GUIButtonExecute> wrappedToExecuteMap(GUIButtonWrapped... wrapped) {

        if(wrapped == null || wrapped.length <= 0) {

            return new HashMap<>();
        }
        Map<GUIButtonClick, GUIButtonExecute> executeMap = new HashMap<>();

        for(GUIButtonWrapped wrapped0 : wrapped) {

            executeMap.put(wrapped0.getClick(), wrapped0.getExecute());
        }
        return executeMap;
    }

    /**
     * 将按钮包装对象数组转换到按钮执行对象集合
     *
     * @param click 点击类型
     * @param execute 执行对象
     * @return 按钮执行对象集合 没有则返回空集合
     */
    public static Map<GUIButtonClick, GUIButtonExecute> wrappedToExecuteMap(GUIButtonClick click, GUIButtonExecute execute) {

        if(click == null || execute == null) {

            return new HashMap<>();
        }
        Map<GUIButtonClick, GUIButtonExecute> executeMap = new HashMap<>();
        executeMap.put(click, execute);

        return executeMap;
    }
}
