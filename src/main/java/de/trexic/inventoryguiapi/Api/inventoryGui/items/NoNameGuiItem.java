package de.trexic.inventoryguiapi.Api.inventoryGui.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NoNameGuiItem extends GuiItem{

    public NoNameGuiItem(Material material) {
        super(null);
        noNameItemStack(material, 1, 1, 1);
    }

    public NoNameGuiItem(Material material, int slot) {
        super(null, slot);
        noNameItemStack(material, 1, slot, 1);
    }

    public NoNameGuiItem(Material material, int slot, int page) {
        super(null, slot, page);
        noNameItemStack(material, 1, slot, page);
    }

    public NoNameGuiItem(Material material, int amount, int slot, int page) {
        super(null, slot, page);
        noNameItemStack(material, amount, slot, page);
    }

    private void noNameItemStack(Material material, int amount, int slot, int page) {
        ItemStack itemStack = new ItemStack(material, amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(" ");
        itemStack.setItemMeta(itemMeta);

        super.setItemStack(itemStack);
    }
}
