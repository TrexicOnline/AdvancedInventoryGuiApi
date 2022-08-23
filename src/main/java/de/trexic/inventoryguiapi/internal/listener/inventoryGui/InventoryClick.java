package de.trexic.inventoryguiapi.internal.listener.inventoryGui;

import de.trexic.inventoryguiapi.Api.inventoryContainer.InventoryContainer;
import de.trexic.inventoryguiapi.Api.inventoryGui.InventoryGui;
import de.trexic.inventoryguiapi.Api.inventoryGui.items.GuiItem;
import de.trexic.inventoryguiapi.internal.Lists;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.HashMap;

public class InventoryClick implements Listener {
    @EventHandler
    public void onClick(InventoryClickEvent event){
        String title = event.getView().getTitle();

        try {
            InventoryGui inventoryGui = Lists.guiItemHashMap.get(title);

            HashMap<Integer, GuiItem> guiItems = inventoryGui.getPageGuiItems();

            //Check if Inventory is an InvGui
            if(guiItems != null){
                int clickedSlot = event.getSlot();

                //Check if at slot is an GuiItem
                if(guiItems.containsKey(clickedSlot)){
                    GuiItem guiItem = guiItems.get(clickedSlot);

                    if(guiItem.getItemStack().equals(event.getCurrentItem())){

                        int slot = event.getSlot();
                        InventoryContainer inventoryContainer = null;

                        //Check if GuiItem is in a Container
                        for(InventoryContainer _inventoryContainer : inventoryGui.getCurrentPageInventoryContainers()) {
                            for(GuiItem containerGuiItem : _inventoryContainer.getGuiItems()) {
                                if(containerGuiItem.getUUID() == guiItem.getUUID()) {
                                    //Set the Slot to the Container slot
                                    slot = containerGuiItem.getSlot();
                                    inventoryContainer = _inventoryContainer;

                                    break;
                                }
                            }
                        }


                        if(!guiItem.isCollectable()){
                            event.setCancelled(true);
                        }

                        if(guiItem.isClickable()){

                            if(event.isLeftClick()) {
                                guiItem.getEventCall().onLeftClick(slot, event.getCurrentItem(), (Player) event.getWhoClicked(), event, inventoryGui, guiItem, inventoryContainer);
                            }
                            else if (event.isRightClick()) {
                                guiItem.getEventCall().onRightClick(slot, event.getCurrentItem(), (Player) event.getWhoClicked(), event, inventoryGui, guiItem, inventoryContainer);
                            }

                            if(event.isShiftClick()) {
                                guiItem.getEventCall().onShiftClick(slot, event.getCurrentItem(), (Player) event.getWhoClicked(), event, inventoryGui, guiItem, inventoryContainer);
                            }

                            guiItem.getEventCall().onClick(slot, event.getCurrentItem(), (Player) event.getWhoClicked(), event, inventoryGui, guiItem, inventoryContainer);
                        }
                    }
                }
                else


                if(!Lists.guiItemHashMap.get(title).isItemPushable()){
                    event.setCancelled(true);
                }
            }
        }
        catch (Exception ignored){

        }
    }
}
