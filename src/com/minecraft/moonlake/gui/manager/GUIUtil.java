package com.minecraft.moonlake.gui.manager;

import com.minecraft.moonlake.api.itemlib.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * Created by MoonLake on 2016/7/25.
 */
public final class GUIUtil {

    public final static ItemStack DEFAULT_ICON;

    static {

        DEFAULT_ICON = new ItemBuilder(Material.BEDROCK).build();
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
}
