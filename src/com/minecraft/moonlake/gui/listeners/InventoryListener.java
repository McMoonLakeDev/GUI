package com.minecraft.moonlake.gui.listeners;

import com.minecraft.moonlake.gui.api.GUI;
import com.minecraft.moonlake.gui.api.MoonLakeGUI;
import com.minecraft.moonlake.gui.api.button.GUIButton;
import com.minecraft.moonlake.gui.api.event.MoonLakeGUIClickEvent;
import com.minecraft.moonlake.gui.api.event.MoonLakeGUICloseEvent;
import com.minecraft.moonlake.gui.api.event.MoonLakeGUIOpenEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

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

        GUI gui = getMain().getManager().fromTitle(event.getInventory());

        if(gui == null) return;
        if(!(event.getPlayer() instanceof Player)) return;

        MoonLakeGUIOpenEvent mgoe = new MoonLakeGUIOpenEvent(gui, (Player)event.getPlayer());
        Bukkit.getServer().getPluginManager().callEvent(mgoe);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClose(InventoryCloseEvent event) {

        GUI gui = getMain().getManager().fromTitle(event.getInventory());

        if(gui == null) return;
        if(!(event.getPlayer() instanceof Player)) return;

        MoonLakeGUICloseEvent mgce = new MoonLakeGUICloseEvent(gui, (Player)event.getPlayer());
        Bukkit.getServer().getPluginManager().callEvent(mgce);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {

        if(event.getInventory() == null) return;
        if(event.getClickedInventory() == null) return;

        GUI gui = getMain().getManager().fromTitle(event.getClickedInventory());

        if(gui == null) return;
        if(!(event.getWhoClicked() instanceof Player)) return;

        if(!gui.isAllowMove()) {

            event.setCancelled(true);
        }
        MoonLakeGUIClickEvent mgce = new MoonLakeGUIClickEvent(gui, (Player)event.getWhoClicked());
        Bukkit.getServer().getPluginManager().callEvent(mgce);

        GUIButton button = gui.getButton(event.getSlot());

        if(button == null) return;
        if(button.getExecute() == null) return;

        button.getExecute().execute(gui, (Player)event.getWhoClicked(), button);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMove(InventoryClickEvent event) {

        if(event.getClickedInventory() == null) return;
        if(event.getClickedInventory().getType() != InventoryType.PLAYER) return;
        if(!(event.getWhoClicked() instanceof Player)) return;

        Player player = (Player)event.getWhoClicked();

        if(player.getOpenInventory() == null) return;
        if(player.getOpenInventory().getTopInventory() == null) return;

        GUI gui = getMain().getManager().fromTitle(player.getOpenInventory().getTopInventory());

        if(gui != null) {

            event.setCancelled(true);
            player.updateInventory();
        }
    }
}
