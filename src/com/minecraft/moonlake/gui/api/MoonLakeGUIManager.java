package com.minecraft.moonlake.gui.api;

import org.bukkit.inventory.Inventory;

import java.util.Set;

/**
 * Created by MoonLake on 2016/7/25.
 */
public interface MoonLakeGUIManager {

    /**
     * 获取月色之湖 GUI 实例对象
     *
     * @return 实例对象
     */
    MoonLakeGUI getMain();

    /**
     * 创建新的 GUI 对象
     *
     * @param name 名称
     * @param title 标题
     * @param size 大小
     * @param <T> GUI 对象
     * @return GUI 对象
     */
    <T extends GUI> GUI createGUI(String name, String title, int size);

    /**
     * 注册指定 GUI 对象
     *
     * @param gui GUI 对象
     * @param <T> GUI 对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIAlreadyExistsException 如果此 GUI 已经存在则抛出异常
     */
    <T extends GUI> void registerGUI(T gui);

    /**
     * 卸载指定 GUI 对象
     *
     * @param name GUI 名称
     * @param <T> GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    <T extends GUI> GUI unregisterGUI(String name);

    /**
     * 卸载所有的 GUI 对象
     *
     * @return GUI 对象集合
     */
    <T extends GUI> Set<T> unregisterAll();

    /**
     * 获取指定名称的 GUI 对象
     *
     * @param name GUI 名称
     * @param <T> GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    <T extends GUI> GUI getGUI(String name);

    /**
     * 获取指定名称的 GUI 对象
     *
     * @param gui GUI 对象
     * @param <T> GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    <T extends GUI> GUI getGUI(GUI gui);

    /**
     * 获取指定 GUI 对象是否已经存在
     *
     * @param gui GUI 对象
     * @return true 则已经存在 else 没有
     */
    boolean hasGUI(GUI gui);

    /**
     * 获取指定 GUI 对象是否已经存在
     *
     * @param name GUI 名称
     * @return true 则已经存在 else 没有
     */
    boolean hasGUI(String name);

    /**
     * 获取 GUI 对象从物品栏标题
     *
     * @param title 标题
     * @return GUI 对象 没有则返回 null
     */
    GUI fromTitle(String title);

    /**
     * 获取 GUI 对象从物品栏
     *
     * @param inventory 物品栏
     * @return GUI 对象 没有则返回 null
     */
    GUI fromTitle(Inventory inventory);

    /**
     * 获取所有的 GUI 对象
     *
     * @return GUI 对象集合
     */
    Set<GUI> getAllGUI();

    /**
     * 获取注册的 GUI 数量大小
     *
     * @return 数量大小
     */
    int getSize();
}
