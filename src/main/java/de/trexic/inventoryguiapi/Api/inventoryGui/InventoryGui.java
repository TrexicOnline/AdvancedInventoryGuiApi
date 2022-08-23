package de.trexic.inventoryguiapi.Api.inventoryGui;

import de.trexic.inventoryguiapi.Api.inventoryContainer.InventoryContainer;
import de.trexic.inventoryguiapi.Api.inventoryDesigner.InventoryDesigner;
import de.trexic.inventoryguiapi.Api.inventoryGui.items.GuiItem;
import de.trexic.inventoryguiapi.internal.Lists;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.*;

public class InventoryGui {
    private Inventory inventory;
    private String title;
    private int slots;
    private boolean itemPushable = false;
    private InventoryHolder inventoryHolder;
    private int currentPage = 1;

    private List<GuiItem> guiItems = new ArrayList<>();
    private HashMap<Integer, GuiItem> pageGuiItems = new HashMap<>();

    private List<InventoryContainer> inventoryContainers = new ArrayList<>();

    public InventoryGui(String title, int slots){
        this.title = title;
        this.slots = slots;

        this.inventory = Bukkit.createInventory(null, slots, title);
    }

    public InventoryGui(String title, InventoryType inventoryType){
        this.title = title;
        this.slots = slots;

        this.inventory = Bukkit.createInventory(null, inventoryType, title);
    }

    public InventoryGui(String title, int slots, InventoryHolder inventoryOwner){
        this.title = title;
        this.slots = slots;
        this.inventoryHolder = inventoryOwner;

        this.inventory = Bukkit.createInventory(inventoryHolder, slots, title);
    }

    public InventoryGui(String title, InventoryType inventoryType, InventoryHolder inventoryOwner){
        this.title = title;
        this.slots = slots;
        this.inventoryHolder = inventoryOwner;

        this.inventory = Bukkit.createInventory(inventoryHolder, slots, title);
    }

    public Inventory create() {
        if(Lists.guiItemHashMap.containsKey(title)){
            Lists.guiItemHashMap.replace(title, this);
        }
        else {
            Lists.guiItemHashMap.put(title, this);
        }

        return inventory;
    }

    /**
     Adds an item to the InvGui
     */
    public void addGuiItem(GuiItem guiItem){
        if(guiItem.getSlot() > this.slots)
            throw new IndexOutOfBoundsException("The slot index cannot be higher, than the maximum slot number!");


        if(guiItem.getPage() == currentPage) {
            this.inventory.setItem(guiItem.getSlot(), guiItem.getItemStack());

            if(!this.pageGuiItems.containsKey(guiItem.getSlot())){
                this.pageGuiItems.put(guiItem.getSlot(), guiItem);
            }
            else {
                this.pageGuiItems.replace(guiItem.getSlot(), guiItem);
            }
        }

        for(GuiItem guiItem_ : guiItems) {
            if(guiItem_.getSlot() == guiItem.getSlot() && guiItem_.getPage() == guiItem.getPage()) {
                return;
            }
        }

        guiItems.add(guiItem);
    }

    /**
     * Removes the GuiItem. If there is no GuiItem, nothing will change.
     */
    public void removeGuiItem(GuiItem guiItem) {
        if(guiItem.getPage() == currentPage) {
            pageGuiItems.remove(guiItem.getSlot());
            inventory.setItem(guiItem.getSlot(), null);
        }

        int pos = 0;

        for(GuiItem guiItem_ : guiItems) {
            if(guiItem_.getSlot() == guiItem.getSlot() && guiItem_.getPage() == guiItem.getPage()) {
                guiItems.remove(pos);
                break;
            }
            pos++;
        }
    }
    /**
     * Removes the GuiItem at the slot. If there is no GuiItem, nothing will change.
     */
    public void removeGuiItem(int slot, int page) {
        if(page == currentPage) {
            pageGuiItems.remove(slot);
            inventory.setItem(slot, null);
        }

        int pos = 0;

        for(GuiItem guiItem_ : guiItems) {
            if(guiItem_.getSlot() == slot && guiItem_.getPage() == page) {
                guiItems.remove(pos);
                break;
            }
            pos++;
        }
    }

    /**
     * Adds the GuiItem, if not existing.
     * Replaces the GuiItem, if it already exists.
     */
    public void setGuiItem(GuiItem guiItem) {
        if(guiItem.getSlot() > slots)
            throw new IndexOutOfBoundsException("The slot index cannot be higher, than the maximum slot number!");

        int pos = 0;
        boolean guiItemSet = false;

        for(GuiItem guiItem_ : guiItems) {
            if(guiItem_.getSlot() == guiItem.getSlot() && guiItem_.getPage() == guiItem.getPage()) {
                guiItems.set(pos, guiItem);
                guiItemSet = true;
            }
            pos++;
        }

        if(!guiItemSet)
            guiItems.add(guiItem);

        if(guiItem.getPage() == currentPage) {
            inventory.setItem(guiItem.getSlot(), guiItem.getItemStack());

            if(!this.pageGuiItems.containsKey(guiItem.getSlot())){
                this.pageGuiItems.put(guiItem.getSlot(), guiItem);
            }
            else {
                this.pageGuiItems.replace(guiItem.getSlot(), guiItem);
            }
        }
    }

    /**
     * @returns GuiItem, null if not found
     */
    public GuiItem getGuiItem(UUID guiItemId){
        for(GuiItem guiItem : guiItems){
            if(guiItem.getUUID().equals(guiItemId)){
                return guiItem;
            }
        }
        return null;
    }

    public List<GuiItem> getGuiItems(){
        return this.guiItems;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getSlots() {
        return slots;
    }

    /**
     * Sets, that the player can put items in the inventory
     */
    public void setItemPushable(boolean itemPushable){
        this.itemPushable = itemPushable;
    }

    public boolean isItemPushable(){
        return this.itemPushable;
    }

    /**
     * Displays all items on set page.
     * Inventory has to be set new on opened player
     */
    public void changePage(int page) {
        inventory.clear();
        this.currentPage = page;
        this.pageGuiItems.clear();

        for(GuiItem guiItem : this.guiItems){
            if(guiItem.getPage() == page){
                inventory.setItem(guiItem.getSlot(), guiItem.getItemStack());
                this.pageGuiItems.put(guiItem.getSlot(), guiItem);
            }
        }
    }

    public int getPage() {
        return currentPage;
    }

    /**
     * Changes the Page to the next Page
     */
    public void nextPage() {
        changePage(currentPage + 1);
    }

    /**
     * Changes the Page to the last Page.
     * If the current Page is 0, the Page will not change.
     */
    public void lastPage() {
        if(!(currentPage <= 0)) {
            changePage(currentPage - 1);
        }
    }

    public HashMap<Integer, GuiItem> getPageGuiItems() {
        return pageGuiItems;
    }

    public InventoryType getInventoryType() {
        return inventory.getType();
    }

    public List<InventoryContainer> getAllInventoryContainers() {
        return inventoryContainers;
    }

    public InventoryContainer createInventoryContainer(int containerPage) {
        InventoryContainer inventoryContainer = new InventoryContainer(this, containerPage, inventoryContainers.size());
        this.inventoryContainers.add(inventoryContainer);
        return inventoryContainer;
    }

    public List<InventoryContainer> getInventoryContainers(int page) {
        List<InventoryContainer> inventoryContainerList = new ArrayList<>();
        for(InventoryContainer inventoryContainer : inventoryContainers) {
            if(inventoryContainer.getContainerPage() == page) {
                inventoryContainers.add(inventoryContainer);
            }
        }

        return inventoryContainerList;
    }

    public InventoryContainer getInventorContainer(int containerID) {
        for(InventoryContainer inventoryContainer : inventoryContainers) {
            if(inventoryContainer.getContainerID() == containerID) {
                return inventoryContainer;
            }
        }

        return null;
    }

    public List<InventoryContainer> getCurrentPageInventoryContainers() {
        List<InventoryContainer> containerList = new ArrayList<>();

        for(InventoryContainer inventoryContainer : inventoryContainers) {
            if(inventoryContainer.getContainerInventoryGuiPage() == currentPage) {
                containerList.add(inventoryContainer);
            }
        }

        return containerList;
    }
}