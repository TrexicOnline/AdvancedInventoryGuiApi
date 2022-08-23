package de.trexic.inventoryguiapi.Api.inventoryDesigner;

import de.trexic.inventoryguiapi.Api.inventoryContainer.InventoryContainer;
import de.trexic.inventoryguiapi.Api.inventoryGui.InventoryGui;
import de.trexic.inventoryguiapi.Api.inventoryGui.items.GuiItem;
import de.trexic.inventoryguiapi.Api.inventoryGui.items.NoNameGuiItem;
import de.trexic.inventoryguiapi.internal.exeptions.UnsupportedInventoryTypeException;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;

import java.util.ArrayList;
import java.util.List;

public class InventoryDesigner{
    protected char[] pattern;
    protected int slots;
    protected InventoryGui inventoryGui;

    public InventoryDesigner(String pattern, String title) throws Exception {
        this.pattern = pattern.replace(" ", "").replace("\n", "").toCharArray();

        prepare(title);
    }

    public InventoryDesigner(InventoryGui inventoryGui, String pattern) {
        this.inventoryGui = inventoryGui;
        this.slots = pattern.length();
    }

    public InventoryGui getInventoryGui() {
        return inventoryGui;
    }

    /**
     * Replaces the character in the pattern, with the GuiItem.
     * The slot of the GuiItem will be set automatically.
     */
    public void assignGuiItem(char character, GuiItem guiItem) {
        int pos = 0;
        for(char pattern_char : pattern) {
            if(pattern_char == character) {
                GuiItem newGuiItem = guiItem;
                newGuiItem.setSlot(pos);
                inventoryGui.setGuiItem(newGuiItem);
            }
            pos++;
        }
    }

    public void assignNoNameGuiItem(char character, Material material) {
        assignGuiItem(character, new NoNameGuiItem(material, 0, 1));
    }

    public void assignNoNameGuiItem(char character, Material material, int page) {
        assignGuiItem(character, new NoNameGuiItem(material, 0, page));
    }

    public void assignNoNameGuiItem(char character, Material material, int page, int amount) {
        assignGuiItem(character, new NoNameGuiItem(material, amount,0, page));
    }

    /**
     *
     * @return InventoryContainer id
     */
    public InventoryContainer assignInventoryContainer(char character, InventoryContainer inventoryContainer) {
        List<Integer> containerSlots = new ArrayList<>();

        int slot = 0;

        for(char pattern_character : pattern) {
            if(pattern_character == character) {
                containerSlots.add(slot);
            }

            slot++;
        }

        inventoryContainer.setContainerSlots(containerSlots);

        return inventoryContainer;
    }

    private void prepare(String title) throws Exception {
        //CheckInventory Type
        this.slots = pattern.length;

        switch (slots) {
            case(5):
                this.inventoryGui = new InventoryGui(title, InventoryType.HOPPER);
            case(9):
            case(27):
            case(54):
                this.inventoryGui = new InventoryGui(title, slots);
                break;
            default:
                throw new UnsupportedInventoryTypeException("There is no InventoryType with " + slots + " slots!");
        }
    }

    public String getPattern() {
        return pattern.toString();
    }

    /**
     *
     * @param pattern Note that the pattern must be the same size as the original
     */
    public void setPattern(String pattern) {
        this.pattern = pattern.replace(" ", "").replace("\n", "").toCharArray();
    }
}
