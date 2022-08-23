package de.trexic.inventoryguiapi.Api.inventoryContainer;

import de.trexic.inventoryguiapi.Api.inventoryGui.InventoryGui;
import de.trexic.inventoryguiapi.Api.inventoryGui.items.GuiItem;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InventoryContainer {

    private InventoryGui inventoryGui;
    private List<Integer> containerSlots = new ArrayList<>();
    private int containerID;
    private List<GuiItem> guiItems = new ArrayList<>();
    private int containerPage = 1;
    private int containerInventoryGuiPage;

    public InventoryContainer(InventoryGui inventoryGui, int containerPage, int containerID) {
        this.inventoryGui = inventoryGui;
        this.containerID = containerID;
        this.containerInventoryGuiPage = containerPage;
    }

    public void setContainerSlots(List<Integer> containerSlots) {
        this.containerSlots = containerSlots;
    }

    public List<Integer> getContainerSlots() {
        return containerSlots;
    }

    public int getContainerID() {
        return containerID;
    }

    public int getSlots() {
        return this.containerSlots.size();
    }

    /**
     * Adds a GuiItem to the InventoryGui.
     * Note: The GuiItem slot will be handled as a container Slot.
     * Example:
     * Slot in inventory: 5
     * Slot in Container: 0
     *
     * Slot in inventory: 6
     * Slot in Container: 1
     */
    public void addGuiItem(GuiItem guiItem) {
        if(guiItem.getSlot() > this.containerSlots.size() - 1)
            throw new IndexOutOfBoundsException("The slot " + guiItem.getSlot() + " is higher than the max container slot!");

        int pos = 0;
        boolean guiItemSet = false;

        for(GuiItem guiItem_ : guiItems) {
            if(guiItem_.getSlot() == guiItem.getSlot() && guiItem_.getPage() == guiItem.getPage()) {
                guiItems.set(pos, guiItem);
                guiItemSet = true;
                break;
            }
            pos++;
        }

        if(!guiItemSet)
            guiItems.add(guiItem);


        if(guiItem.getPage() == containerPage)
            this.inventoryGui.setGuiItem(overrideGuiItemValues(guiItem));
    }

    /**
     * Removes the GuiItem from the Container
     */
    public void removeGuiItem(GuiItem guiItem) {
        int pos = 0;

        for(GuiItem guiItem_ : guiItems) {
            if(guiItem_.getSlot() == guiItem.getSlot() && guiItem_.getPage() == guiItem.getPage()) {
                this.guiItems.remove(pos);

                this.inventoryGui.removeGuiItem(overrideGuiItemValues(guiItem));
                break;
            }
            pos++;
        }
    }

    public void removeGuiItem(int slot, int page) {
        int pos = 0;

        for(GuiItem guiItem : guiItems) {
            if(guiItem.getSlot() == slot && guiItem.getPage() == page) {
                this.guiItems.remove(pos);

                this.inventoryGui.removeGuiItem(overrideGuiItemValues(guiItem));
                break;
            }
            pos++;
        }
    }

    public void setGuiItem(GuiItem guiItem) {
        if(guiItem.getSlot() > this.containerSlots.size() - 1)
            throw new IndexOutOfBoundsException("The slot " + guiItem.getSlot() + " is higher than the max container slot!");

        int pos = 0;
        boolean guiItemSet = false;

        for(GuiItem guiItem_ : guiItems) {
            if(guiItem_.getSlot() == guiItem.getSlot() && guiItem_.getPage() == guiItem.getPage()) {
                this.guiItems.set(pos, guiItem);
                guiItemSet = true;

                if(guiItem.getPage() == containerPage) {
                    this.inventoryGui.setGuiItem(overrideGuiItemValues(guiItem));
                }
                return;
            }
            pos++;
        }

        if(!guiItemSet)
            this.guiItems.add(guiItem);

        if(guiItem.getPage() == containerPage) {
            this.inventoryGui.setGuiItem(overrideGuiItemValues(guiItem));
        }
    }

    /**
     * All GuiItems on the container Page will be rendered and active
     */
    public void changeContainerPage(int page) {

        for(GuiItem guiItem : guiItems) {
            if(guiItem.getPage() == containerPage) {
                this.inventoryGui.removeGuiItem(overrideGuiItemValues(guiItem));
            }
            else if(guiItem.getPage() == page) {
                this.inventoryGui.setGuiItem(overrideGuiItemValues(guiItem));
            }
        }

        this.containerPage = page;
    }

    public void nextContainerPage() {
        changeContainerPage(this.containerPage + 1);
    }

    public void lastContainerPage() {
        if(!((this.containerPage - 1) < 1))
            changeContainerPage(this.containerPage - 1);
    }

    private GuiItem overrideGuiItemValues(GuiItem guiItem) {
        GuiItem guiItemOverride = guiItem.clone();

        guiItemOverride.setPage(containerInventoryGuiPage);
        guiItemOverride.setSlot(containerSlots.get(guiItem.getSlot()));

        return guiItemOverride;
    }

    public int getContainerPage() {
        return containerPage;
    }

    public int getContainerInventoryGuiPage() {
        return containerInventoryGuiPage;
    }

    public List<GuiItem> getGuiItems() {
        return guiItems;
    }

    public InventoryGui getInventoryGui() {
        return inventoryGui;
    }
}
