package de.trexic.inventoryguiapi.Api.inventoryGui.items;

import de.trexic.inventoryguiapi.internal.utilities.SkullCreator;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class GuiHeadItem extends GuiItem {
    public GuiHeadItem(int slot) {
        super(new ItemStack(Material.ZOMBIE_HEAD, 1), slot, 1);
    }

    public GuiHeadItem(int slot, int page) {
        super(new ItemStack(Material.ZOMBIE_HEAD, 1), slot, page);
    }

    public void setPlayerHead(Player player) {
        setItemStack(SkullCreator.skullByUUID(player.getUniqueId()));
    }

    public void setPlayerHead(OfflinePlayer offlinePlayer) {
        setItemStack(SkullCreator.skullByUUID(offlinePlayer.getUniqueId()));
    }

    public void setPlayerHead(UUID uuid) {
        setItemStack(SkullCreator.skullByUUID(uuid));
    }

    @Deprecated
    public void setPlayerHead(String playerName) {
        setItemStack(SkullCreator.skullByName(playerName));
    }

    public void setHead_base64(String base64) {
        setItemStack(SkullCreator.skullByBase64(base64));
    }


    /**
     * Note that the Url must be valid, else the game could crash
     */
    public void setHead_url(String url) {
        setItemStack(SkullCreator.skullByUrl(url));
    }
}
