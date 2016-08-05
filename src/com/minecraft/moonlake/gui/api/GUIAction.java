package com.minecraft.moonlake.gui.api;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/8/5.
 */
public enum GUIAction {

    /**
     * GUI 交互类型：没有任何事情会发生
     * <p>
     * 可能会有情况下任何都不会发生，这是没有提供的价值，但它保证当给定后这个值是准确的
     */
    NOTHING,
    /**
     * GUI 交互类型：点击槽上的所有物品被移动到光标上
     */
    PICKUP_ALL,
    /**
     * GUI 交互类型: 点击槽上的一些物品被移动到光标上
     */
    PICKUP_SOME,
    /**
     * GUI 交互类型: 点击槽上的一半物品被移动到光标上
     */
    PICKUP_HALF,
    /**
     * GUI 交互类型: 点击槽上的一个物品被移动到光标上
     */
    PICKUP_ONE,
    /**
     * GUI 交互类型: 光标上的所有物品被移动到点击槽上
     */
    PLACE_ALL,
    /**
     * GUI 交互类型: 光标上的一些物品被移动到点击槽上
     * (通常最多到最大堆栈大小).
     */
    PLACE_SOME,
    /**
     * GUI 交互类型: 光标上的一个物品被移动到点击槽上
     */
    PLACE_ONE,
    /**
     * GUI 交互类型: 点击槽上的物品和光标物品被交换
     */
    SWAP_WITH_CURSOR,
    /**
     * GUI 交互类型: 光标上的所有物品被丢弃
     */
    DROP_ALL_CURSOR,
    /**
     * GUI 交互类型: 光标上的一个物品被丢弃
     */
    DROP_ONE_CURSOR,
    /**
     * GUI 交互类型: 点击槽的所有物品被丢弃
     */
    DROP_ALL_SLOT,
    /**
     * GUI 交互类型: 点击槽的一个物品被丢弃
     */
    DROP_ONE_SLOT,
    /**
     * GUI 交互类型: 该物品被移动到相反的物品栏，前提是拥有空间
     */
    MOVE_TO_OTHER_INVENTORY,
    /**
     * GUI 交互类型: 点击槽的物品被移动到快捷栏或物品重新移动到玩家的背包
     */
    HOTBAR_MOVE_AND_READD,
    /**
     * GUI 交互类型: 点击槽的物品和快捷栏槽的物品被交换
     */
    HOTBAR_SWAP,
    /**
     * GUI 交互类型: 点击槽的最大堆栈大小数量物品被移动到光标
     */
    CLONE_STACK,
    /**
     * GUI 交互类型: GUI 搜索的相同物品类型最大堆栈大小数量物品被移动光标
     */
    COLLECT_TO_CURSOR,
    /**
     * GUI 交互类型: 未知
     */
    UNKNOWN,
    ;

    private final static Map<String, GUIAction> NAME_MAP;

    static {

        NAME_MAP = new HashMap<>();

        for(GUIAction guiAction : values()) {

            NAME_MAP.put(guiAction.name(), guiAction);
        }
    }

    public static GUIAction fromType(String type) {

        GUIAction guiAction = NAME_MAP.get(type);
        return guiAction == null ? GUIAction.UNKNOWN : guiAction;
    }
}
