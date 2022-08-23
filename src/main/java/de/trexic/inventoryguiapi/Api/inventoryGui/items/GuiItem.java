package de.trexic.inventoryguiapi.Api.inventoryGui.items;

import de.trexic.inventoryguiapi.Api.inventoryGui.eventCall.ItemInteract;
import org.bukkit.inventory.ItemStack;


import java.util.UUID;

public class GuiItem implements Cloneable {
    private ItemStack itemStack;
    private boolean clickable = false;
    private boolean collectable = false;
    private ItemInteract eventCall;
    private UUID itemUUID = UUID.randomUUID();
    private int page = 1;
    private int slot = 1;


    public GuiItem(ItemStack itemStack){
        this.itemStack = itemStack;
    }

    public GuiItem(ItemStack itemStack, int slot){
        this.itemStack = itemStack;
        this.slot = slot;
    }

    public GuiItem(ItemStack itemStack, int slot, int page){
        this.itemStack = itemStack;
        this.page = page;
        this.slot = slot;
    }

    public void setCollectable(boolean collectable){
        this.collectable = collectable;
    }

    public boolean isCollectable(){
        return this.collectable;
    }

    public void setClickable(boolean clickable){
        this.clickable = clickable;
    }

    /**
     * if set to false, attached event will not be called
     */
    public boolean isClickable(){
        return clickable;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public void setEventCall(ItemInteract itemInteractExtension) {
        this.eventCall = itemInteractExtension;
    }

    public ItemInteract getEventCall() {
        return eventCall;
    }

    public int getPage() {
        return page;
    }

    /**
     * Changes the page, the item should be rendered on
     */
    public void setPage(int page) {
        this.page = page;
    }

    public UUID getUUID() {
        return itemUUID;
    }

    public void setUUID(UUID itemUUID) {
        this.itemUUID = itemUUID;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public GuiItem clone() {
        try {
            return (GuiItem) super.clone();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}