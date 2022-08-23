package de.trexic.inventoryguiapi.Api.inventoryGui.eventCall;

import de.trexic.inventoryguiapi.Api.inventoryContainer.InventoryContainer;
import de.trexic.inventoryguiapi.Api.inventoryGui.InventoryGui;
import de.trexic.inventoryguiapi.Api.inventoryGui.items.GuiItem;
import jline.internal.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ItemInteract {
    /**
     * @param slot                The slot, the clicked Item is in
     * @param itemStack           The clicked items, ItemStack
     * @param playerClicked       The Player. who clicked the Item
     * @param inventoryClickEvent The InventoryClickEvent, which was fired on the click
     */
    public void onClick(int slot, ItemStack itemStack, Player playerClicked, InventoryClickEvent inventoryClickEvent, InventoryGui inventoryGui, GuiItem guiItem, @Nullable InventoryContainer inventoryContainer) {

    }

    public void onLeftClick(int slot, ItemStack itemStack, Player playerClicked, InventoryClickEvent inventoryClickEvent, InventoryGui inventoryGui, GuiItem guiItem, @Nullable InventoryContainer inventoryContainer) {
            
    }

    public void onRightClick(int slot, ItemStack itemStack, Player playerClicked, InventoryClickEvent inventoryClickEvent, InventoryGui inventoryGui, GuiItem guiItem, @Nullable InventoryContainer inventoryContainer) {

    }

    public void onShiftClick(int slot, ItemStack itemStack, Player playerClicked, InventoryClickEvent inventoryClickEvent, InventoryGui inventoryGui, GuiItem guiItem, @Nullable InventoryContainer inventoryContainer) {

    }
}