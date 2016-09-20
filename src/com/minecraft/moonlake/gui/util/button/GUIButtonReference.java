package com.minecraft.moonlake.gui.util.button;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.button.GUIButtonClick;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import com.minecraft.moonlake.gui.api.button.GUIButtonWrapped;
import com.minecraft.moonlake.manager.ItemManager;
import com.minecraft.moonlake.property.ReadOnlyIntegerProperty;
import com.minecraft.moonlake.property.ReadOnlyObjectProperty;
import com.minecraft.moonlake.property.SimpleIntegerProperty;
import com.minecraft.moonlake.property.SimpleObjectProperty;
import com.minecraft.moonlake.validate.Validate;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class GUIButtonReference implements GUIButton {

    private ReadOnlyObjectProperty<GUI> guiProperty;
    private ReadOnlyIntegerProperty slotProperty;
    private final Map<GUIButtonClick, GUIButtonExecute> executeMap;

    public GUIButtonReference(GUI gui, int slot) {

        this(gui, slot, new HashMap<>());
    }

    public GUIButtonReference(GUI gui, int slot, Map<GUIButtonClick, GUIButtonExecute> executeMap) {

        this.guiProperty = new SimpleObjectProperty<>(gui);
        this.slotProperty = new SimpleIntegerProperty(slot);
        this.executeMap = executeMap == null ? new HashMap<>() : executeMap;
    }

    @Deprecated
    public GUIButtonReference(GUI gui, int slot, GUIButtonExecute execute, ItemStack icon) {

        this(gui, slot);

        setClick(GUIButtonClick.ALL, execute);
    }

    /**
     * 获取此按钮的 GUI 对象
     *
     * @return GUI 对象
     */
    @Override
    public ReadOnlyObjectProperty<GUI> getGUI() {

        return guiProperty;
    }

    /**
     * 获取此按钮的执行对象
     *
     * @see GUIButtonReference#getExecute(GUIButtonClick)
     * @return 默认点击类型的执行对象 没有则返回空执行对象
     */
    @Override
    @Deprecated
    public GUIButtonExecute getExecute() {

        GUIButtonExecute execute = getExecute(GUIButtonClick.ALL);
        return execute == null ? new GUIButtonExecuteNone() : execute;
    }

    /**
     * 获取此按钮的执行对象
     *
     * @param click 点击类型
     * @return 指定点击类型的执行对象 没有则返回 null
     */
    @Override
    public GUIButtonExecute getExecute(GUIButtonClick click) {

        return executeMap.get(click);
    }

    /**
     * 设置此按钮的执行对象
     *
     * @param execute 执行对象
     * @see GUIButtonReference#setClick(GUIButtonClick, GUIButtonExecute)
     */
    @Override
    @Deprecated
    public void setExecute(GUIButtonExecute execute) {

        setClick(GUIButtonClick.ALL, execute);
    }

    /**
     * 获取此按钮的图标对象
     *
     * @return 图标 没有则返回 null
     */
    @Override
    public ItemStack getIcon() {

        ItemStack icon = guiProperty.get().getItem(slotProperty.get());
        return ItemManager.isAir(icon) ? null : icon;
    }

    /**
     * 获取此按钮的索引
     *
     * @return 索引
     */
    @Override
    public ReadOnlyIntegerProperty getSlot() {

        return slotProperty;
    }

    /**
     * 设置此按钮的图标对象
     *
     * @param icon 图标
     * @see GUIButtonReference#updateIcon(ItemStack)
     */
    @Override
    @Deprecated
    public void setIcon(ItemStack icon) {

        updateIcon(icon);
    }

    /**
     * 设置此按钮的图标对象
     *
     * @param icon 图标
     */
    @Override
    public void updateIcon(ItemStack icon) {

        guiProperty.get().setButtonIcon(slotProperty.get(), icon);
    }

    /**
     * 将删除此 GUI 的此按钮对象
     */
    @Override
    public void remove() {

        guiProperty.get().removeButton(slotProperty.get());
    }

    /**
     * 设置此按钮的指定点击的执行对象
     *
     * @param click   点击类型
     * @param execute 执行对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     */
    @Override
    public void setClick(GUIButtonClick click, GUIButtonExecute execute) {

        Validate.notNull(click, "The button click object is null.");
        Validate.notNull(execute, "The button execute object is null.");

        executeMap.put(click, execute);
    }

    /**
     * 设置此按钮的执行点击的执行对象
     *
     * @param wrapped 按钮包装对象
     * @throws IllegalArgumentException 如果点击类型或者执行对象为空则抛出异常
     */
    @Override
    public void setClick(GUIButtonWrapped wrapped) {

        Validate.notNull(wrapped, "The button wrapped object is null.");

        setClick(wrapped.getClick(), wrapped.getExecute());
    }

    /**
     * 获取此按钮的点击执行对象集合
     *
     * @return 点击执行对象集合 没有则返回空集合
     */
    @Override
    public Map<GUIButtonClick, GUIButtonExecute> getExecuteMap() {

        Map<GUIButtonClick, GUIButtonExecute> executeMap = new HashMap<>();

        if(executeMap.size() > 0) {

            executeMap.putAll(this.executeMap);
        }
        return executeMap;
    }


    @Override
    public boolean equals(Object obj) {

        if(obj == null) {

            return false;
        }
        if(obj == this) {

            return true;
        }
        if(obj instanceof GUIButton) {

            GUIButton guiButton = (GUIButton) obj;

            return guiButton.getGUI().isEqualTo(getGUI()).get() &&
                   guiButton.getSlot().isEqualTo(getSlot()).get();
        }
        return false;
    }
}
