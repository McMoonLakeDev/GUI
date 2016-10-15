package com.minecraft.moonlake.gui.listeners;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.GUIAction;
import com.minecraft.moonlake.gui.api.GUIClickType;
import com.minecraft.moonlake.gui.api.MoonLakeGUI;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.button.GUIButtonClick;
import com.minecraft.moonlake.gui.api.button.GUIButtonExecute;
import com.minecraft.moonlake.gui.api.event.MoonLakeGUIClickEvent;
import com.minecraft.moonlake.gui.api.event.MoonLakeGUICloseEvent;
import com.minecraft.moonlake.gui.api.event.MoonLakeGUIOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;

/**
 * Created by MoonLake on 2016/7/24.
 */
public class InventoryListener implements Listener {

    private final MoonLakeGUI main;

    public InventoryListener(MoonLakeGUI main) {

        this.main = main;
    }

    public MoonLakeGUI getMain() {

        return main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onOpen(InventoryOpenEvent event) {

        if(getMain().getManager().getSize() <= 0) return;

        GUI gui = getMain().getManager().fromInventory(event.getInventory());

        if(gui == null) return;
        if(!(event.getPlayer() instanceof Player)) return;

        MoonLakeGUIOpenEvent mgoe = new MoonLakeGUIOpenEvent(gui, (Player)event.getPlayer());
        Bukkit.getServer().getPluginManager().callEvent(mgoe);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClose(InventoryCloseEvent event) {

        if(getMain().getManager().getSize() <= 0) return;

        GUI gui = getMain().getManager().fromInventory(event.getInventory());

        if(gui == null) return;
        if(!(event.getPlayer() instanceof Player)) return;

        MoonLakeGUICloseEvent mgce = new MoonLakeGUICloseEvent(gui, (Player)event.getPlayer());
        Bukkit.getServer().getPluginManager().callEvent(mgce);

        if(gui.isCloseToUnregister()) {

            gui.unregister();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {

        if(event.getInventory() == null) return;
        if(event.getClickedInventory() == null) return;
        if(event.getClickedInventory().getType() != InventoryType.CHEST) return;
        if(getMain().getManager().getSize() <= 0) return;

        GUI gui = getMain().getManager().fromInventory(event.getClickedInventory());

        if(gui == null) return;
        if(!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player) event.getWhoClicked();

        GUIAction guiAction = GUIAction.fromType(event.getAction().name());
        GUIClickType guiClickType = GUIClickType.fromType(event.getClick().name());
        GUIButton button = gui.getButton(event.getSlot());

        if(!gui.isAllowMove()) {

            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }
        if(gui.isAllowMove()) {

            if(button != null || !guiClickType.isLeftOrRightClick()) {

                event.setCancelled(true);
                event.setResult(Event.Result.DENY);
            }
        }
        if(button == null) return;

        GUIButtonExecute execute = button.getExecute(GUIButtonClick.ALL);

        if(execute == null) {

            if(guiClickType == GUIClickType.LEFT) {

                execute = button.getExecute(GUIButtonClick.LEFT_CLICK);
            }
            else if(guiClickType == GUIClickType.SHIFT_LEFT) {

                execute = button.getExecute(GUIButtonClick.SHIFT_LEFT_CLICK);
            }
            else if(guiClickType == GUIClickType.RIGHT) {

                execute = button.getExecute(GUIButtonClick.RIGHT_CLICK);
            }
            else if(guiClickType == GUIClickType.SHIFT_RIGHT) {

                execute = button.getExecute(GUIButtonClick.SHIFT_RIGHT_CLICK);
            }
            else if(guiClickType == GUIClickType.DOUBLE_CLICK) {

                execute = button.getExecute(GUIButtonClick.DOUBLE_CLICK);
            }
            else if(guiClickType == GUIClickType.MIDDLE) {

                execute = button.getExecute(GUIButtonClick.MIDDLE_CLICK);
            }
        }
        if(execute != null) {

            MoonLakeGUIClickEvent mgce = new MoonLakeGUIClickEvent(gui, player, button.getSlot().get(), guiAction, guiClickType);
            Bukkit.getServer().getPluginManager().callEvent(mgce);

            if(!mgce.isCancelled()) {

                execute.execute(gui, player, button);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(InventoryClickEvent event) {

        if(event.getClickedInventory() == null) return;
        if(event.getClickedInventory().getType() != InventoryType.PLAYER) return;
        if(!(event.getWhoClicked() instanceof Player)) return;
        if(getMain().getManager().getSize() <= 0) return;

        Player player = (Player)event.getWhoClicked();

        if(player.getOpenInventory() == null) return;
        if(player.getOpenInventory().getTopInventory() == null) return;
        if(player.getOpenInventory().getTopInventory().getType() != InventoryType.CHEST) return;

        GUI gui = getMain().getManager().fromInventory(player.getOpenInventory().getTopInventory());

        if(gui != null) {

            if(gui.isAllowMove()) {

                if(event.getClick() == ClickType.LEFT || event.getClick() == ClickType.RIGHT) {

                    return;
                }
            }
            event.setCancelled(true);
            event.setResult(Event.Result.DENY);
        }
    }
}
