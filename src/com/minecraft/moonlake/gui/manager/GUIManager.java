package com.minecraft.moonlake.gui.manager;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.MoonLakeGUI;
import com.minecraft.moonlake.gui.api.MoonLakeGUIManager;
import com.minecraft.moonlake.gui.exception.IllegalGUIAlreadyExistsException;
import com.minecraft.moonlake.gui.util.GUIReference;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by MoonLake on 2016/7/24.
 */
public final class GUIManager implements MoonLakeGUIManager {

    private final MoonLakeGUI main;
    private final Map<String, GUI> GUI_MAP;
    private final Map<String, String> GUI_TITLE_MAP;

    public GUIManager(MoonLakeGUI main) {

        this.main = main;
        this.GUI_MAP = new HashMap<>();
        this.GUI_TITLE_MAP = new HashMap<>();
    }

    /**
     * 获取月色之湖 GUI 实例对象
     *
     * @return 实例对象
     */
    @Override
    public MoonLakeGUI getMain() {

        return main;
    }

    /**
     * 创建新的 GUI 对象
     *
     * @param name  名称
     * @param title 标题
     * @param size  大小
     * @return GUI 对象
     */
    @Override
    public <T extends GUI> GUI createGUI(String name, String title, int size) {

        return new GUIReference(name, title, size);
    }

    /**
     * 注册指定 GUI 对象
     *
     * @param gui GUI 对象
     * @param <T> GUI 对象
     * @throws com.minecraft.moonlake.gui.exception.IllegalGUIAlreadyExistsException 如果此 GUI 已经存在则抛出异常
     */
    public <T extends GUI> void registerGUI(T gui) {

        if(hasGUI(gui)) {

            throw new IllegalGUIAlreadyExistsException();
        }
        GUI_MAP.put(gui.getName(), gui);
        GUI_TITLE_MAP.put(gui.getTitle(), gui.getName());
    }

    /**
     * 卸载指定 GUI 对象
     *
     * @param name GUI 名称
     * @param <T> GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    public <T extends GUI> GUI unregisterGUI(String name) {

        GUI gui = getGUI(name);

        if(gui == null) {

            return null;
        }
        GUI_MAP.remove(gui.getName());
        GUI_TITLE_MAP.remove(gui.getTitle());

        return gui;
    }

    /**
     * 卸载所有的 GUI 对象
     */
    @Override
    public void unregisterAll() {

        if(getSize() > 0) {

            GUI_MAP.clear();
            GUI_TITLE_MAP.clear();
        }
    }

    /**
     * 获取指定名称的 GUI 对象
     *
     * @param gui GUI 对象
     * @param <T> GUI 对象
     * @return GUI 对象 没有则返回 null
     */
    public <T extends GUI> GUI getGUI(GUI gui) {

        return gui != null ? getGUI(gui.getName()) : null;
    }

    /**
     * 获取指定名称的 GUI 对象
     *
     * @param name GUI 名称
     * @return GUI 对象 没有则返回 null
     */
    public <T extends GUI> GUI getGUI(String name) {

        return hasGUI(name) ? GUI_MAP.get(name) : null;
    }

    /**
     * 获取指定 GUI 对象是否已经存在
     *
     * @param gui GUI 对象
     * @return true 则已经存在 else 没有
     */
    public boolean hasGUI(GUI gui) {

        return gui != null && hasGUI(gui.getName());
    }

    /**
     * 获取指定 GUI 对象是否已经存在
     *
     * @param name GUI 名称
     * @return true 则已经存在 else 没有
     */
    public boolean hasGUI(String name) {

        return GUI_MAP.containsKey(name);
    }

    /**
     * 获取 GUI 对象从物品栏标题
     *
     * @param title 标题
     * @return GUI 对象 没有则返回 null
     */
    public GUI fromTitle(String title) {

        return GUI_TITLE_MAP.containsKey(title) ? getGUI(GUI_TITLE_MAP.get(title)) : null;
    }

    /**
     * 获取 GUI 对象从物品栏
     *
     * @param inventory 物品栏
     * @return GUI 对象 没有则返回 null
     */
    @Override
    public GUI fromInventory(Inventory inventory) {

        return inventory != null ? fromTitle(inventory.getTitle()) : null;
    }

    /**
     * 获取所有的 GUI 对象
     *
     * @return GUI 对象集合
     */
    @Override
    public Set<GUI> getAllGUI() {

        return getSize() > 0 ? new HashSet<>(GUI_MAP.values()) : new HashSet<>();
    }

    /**
     * 获取注册的 GUI 数量大小
     *
     * @return 数量大小
     */
    @Override
    public int getSize() {

        return GUI_MAP.size();
    }
}
